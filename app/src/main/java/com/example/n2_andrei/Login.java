package com.example.n2_andrei;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText etEmail;
    private EditText etSenha;
    private Button btVoltar;
    private Button btEntrar;
    Dados d =new Dados();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);
        btEntrar = findViewById(R.id.bt_entrar);



        btVoltar = findViewById(R.id.bt_voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cad = new Intent(Login.this , MainActivity.class);
                startActivity( cad);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });

    }
    private void login(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        d=DadosDAO.getDadosByEmail(Login.this,etEmail.getText().toString());
        if(d!=null){

            if (d.getSenha().equals(etSenha.getText().toString())){
                Intent cad = new Intent(Login.this , Conta.class);
                cad.putExtra("email",etEmail.getText().toString());
                startActivity( cad);}
            else{
                alert.setMessage("senha errada");
                alert.show();
            }

        }else{
            alert.setMessage("email errado");
            alert.show();
        }
    }

}