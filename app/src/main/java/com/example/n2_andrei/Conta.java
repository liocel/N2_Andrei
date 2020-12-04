package com.example.n2_andrei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Conta extends AppCompatActivity {
    private TextView tvId;
    private String email;
    private TextView tvSaldo;
    private Button btVoltar;
    private Button btRetirar;
    private Button btAdicionar;
    private Button btCondfirmar;
    private EditText quantia;
    private EditText quantia2;
    private EditText conta;
    private Dados dados2 =new Dados();

    private Dados dados=new Dados();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);
        quantia = findViewById(R.id.et_quantia);
        quantia2 =findViewById(R.id.et_quantia2);
        conta = findViewById(R.id.et_conta);
        btVoltar = findViewById(R.id.bt_voltar);
        btRetirar = findViewById(R.id.bt_remover);
        btAdicionar = findViewById(R.id.bt_adicionar);
        btCondfirmar = findViewById(R.id.bt_confirmar);
        tvId = findViewById(R.id.tv_id);
        tvSaldo = findViewById(R.id.tv_saldo);
        email=getIntent().getExtras().getString("email");
        dados= DadosDAO.getDadosByEmail(Conta.this,email);
        tvId.setText("id:"+Integer.toString(dados.getId()));
        tvSaldo.setText("saldo: "+String.valueOf(dados.getSaldo()));
        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cad = new Intent(Conta.this , MainActivity.class);
                startActivity( cad);
            }

        });
        btRetirar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                retirar();
            }

        });
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                adicionar();
            }

        });
        btCondfirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                confirmar();
            }

        });
    }
    private void adicionar(){
        if (quantia.getText().toString().matches("")){}else {
            DadosDAO.editarSaldo(this, email, Integer.parseInt(quantia.getText().toString()));
            dados = DadosDAO.getDadosByEmail(this, email);
            tvSaldo.setText("saldo: "+String.valueOf(dados.getSaldo()));
        }
    }
    private void retirar(){

        if (quantia.getText().toString().matches("")){}else {
            DadosDAO.editarSaldo(this, email,-Integer.parseInt(quantia.getText().toString()));
            dados = DadosDAO.getDadosByEmail(this, email);
            tvSaldo.setText("saldo: "+String.valueOf(dados.getSaldo()));
        }
    }
    private void confirmar(){
        if (quantia2.getText().toString().matches("")||conta.getText().toString().matches("")){}else {
            dados2 =DadosDAO.getDadosById(this,Integer.parseInt(conta.getText().toString()));
            if (dados2!=null) {
                DadosDAO.editarSaldo(this, dados2.getEmail(), Integer.parseInt(quantia2.getText().toString()));
                DadosDAO.editarSaldo(this, email,-Integer.parseInt(quantia2.getText().toString()));
                dados = DadosDAO.getDadosByEmail(this, email);
                tvSaldo.setText("saldo: "+String.valueOf(dados.getSaldo()));
            }
        }
    }
}