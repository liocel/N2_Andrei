package com.example.n2_andrei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Contas";
    private static final int VERSAO_BANCO = 2;

    public Banco(Context context){
        super(context, NOME_BANCO, null , VERSAO_BANCO );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS Dados ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " email TEXT NOT NULL , " +
                " senha TEXT NOT NULL , " +
                " saldo DOUBLE  ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

