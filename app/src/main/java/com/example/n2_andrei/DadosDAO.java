package com.example.n2_andrei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DadosDAO {
    public static void inserir(Context context, Dados dados){

        ContentValues valores = new ContentValues();
        valores.put("email" , dados.getEmail() );
        valores.put("senha" , dados.getSenha() );
        valores.put("saldo" , dados.getSaldo() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("Dados", null, valores);

    }
    public static void editarSaldo(Context context,String email,int quantia){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        Dados d;

        d=getDadosByEmail(context,email);

        d.setSaldo(d.getSaldo()+quantia);

        ContentValues valores = new ContentValues();
        valores.put("email" , d.getEmail() );
        valores.put("senha" , d.getSenha() );
        valores.put("saldo" , d.getSaldo() );

        db.update("Dados", valores, " id = " + d.getId(), null);
    }
    public static Dados getDadosByEmail(Context context, String em){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT * FROM Dados WHERE email = '" + em + "'" , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Dados p = new Dados();
            p.setId( cursor.getInt( 0 ) );
            p.setEmail( cursor.getString( 1 ) );
            p.setSenha( cursor.getString( 2 ));//provavelmente nao e seguro mandar a senha junto
            p.setSaldo(cursor.getDouble( 3 ));

            return p;

        }else{
            return null;
        }
    }
    public static Dados getDadosById(Context context, int em){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT * FROM Dados WHERE id = '" + em + "'" , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Dados p = new Dados();
            p.setId( cursor.getInt( 0 ) );
            p.setEmail( cursor.getString( 1 ) );
            p.setSenha( cursor.getString( 2 ));//provavelmente nao e seguro mandar a senha junto
            p.setSaldo(cursor.getDouble( 3 ));

            return p;

        }else{
            return null;
        }
    }
}
