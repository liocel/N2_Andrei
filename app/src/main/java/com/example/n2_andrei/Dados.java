package com.example.n2_andrei;

public class Dados {
    private String email;
    private String senha;
    private int id;
    private double saldo;

    public Dados() {
        this.email = "";
        this.senha = "";
        this.saldo = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
