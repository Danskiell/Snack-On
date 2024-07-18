package com.example.Gerenciar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.conectarHelper;

import java.io.IOException;
public class Usuarios {
    conectarHelper dbHelper;

    public Usuarios(Context context) {
        dbHelper = new conectarHelper(context);
        try {
            dbHelper.criarBanco();
            dbHelper.abrirBanco();
        }catch (IOException e) {
            throw new Error("Não foi posssivel criar o banco");
        }

    }
    public boolean adicionarUsuario(String nome, String Sobrenome, String Email, String Telefone, String Senha){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("nome_Cliente", nome);
        value.put("sobrenome_Cliente", Sobrenome);
        value.put("email_Cliente", Email);
        value.put("fone_Cliente", Telefone);
        value.put("senha", Senha);
        long resultado = db.insert("Cliente", null, value);
        db.close();
        return resultado!= -1;
    }

    public boolean checarUsuario(String email, String senha){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id_Cliente"};
        String selection = "email_Cliente=? AND senha=?";
        String[] selectionArgs = {email, senha};

        Cursor cursor = db.query("Cliente", columns, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;

    }
}
