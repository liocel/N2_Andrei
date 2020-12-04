package com.example.n2_andrei;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {
    private EditText etEmail;
    private EditText etSenha;
    private Button btVoltar;
    private Button btCadastrar;
    private Dados d = new Dados();
    private Dados d2 = new Dados();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);
        btCadastrar = findViewById(R.id.bt_cadastrar);
        btVoltar = findViewById(R.id.bt_voltar);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cad = new Intent(Cadastro.this , MainActivity.class);
                startActivity( cad);
            }
        });
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                inserir();

            }
        });
    }
    private void inserir(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        d.setSenha(etSenha.getText().toString());
        d.setEmail(etEmail.getText().toString());
        d.setSaldo(0);
        String senha=etSenha.getText().toString();
        String email=etEmail.getText().toString();
        d2 = DadosDAO.getDadosByEmail(Cadastro.this,d.getEmail());
        if (senha.isEmpty()||email.isEmpty()){
            alert.setMessage("email ou senha vazio");
            alert.show();
        }else {
            if(d2==null) {
                DadosDAO.inserir(this,d);
                alert.setMessage("cadastro com sucesso");
                alert.show();
                Intent cad = new Intent(Cadastro.this , MainActivity.class);
                startActivity( cad);
            }else if(d2.getEmail()==d.getEmail()){
                DadosDAO.inserir(this,d);
                alert.setMessage("cadastro com sucesso");
                alert.show();
                Intent cad = new Intent(Cadastro.this , MainActivity.class);
                startActivity( cad);
            }else{
                alert.setMessage("email em uso");
                alert.show();
            }

        }

    }
}