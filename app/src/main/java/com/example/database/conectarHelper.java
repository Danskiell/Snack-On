package com.example.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
// Criando classe extendendo as funções de abertura de banco
public class conectarHelper extends SQLiteOpenHelper{
    // Definindo caminho do banco
    private static String DB_PATH = "C:\\Snack On App Final\\Snack-On\\app\\src\\main\\assets\\snackOn.db";
    // Definindo nome do banco
    private static String DB_NAME = "snackOn.db";
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    public conectarHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void criarBanco() throws IOException{
        boolean dbExist = checkDataBase();
        if(!dbExist){
            this.getReadableDatabase();
            try{
                copyDataBase();
            } catch (IOException e){
                throw new Error("Erro ao copiar bacno");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String caminho = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(caminho, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e){
            // Banco não existe
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException{
        InputStream myImput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myImput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myImput.close();
    }

    public void abrirBanco() throws SQLiteException{
        String caminho = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(caminho, null, SQLiteDatabase.OPEN_READONLY);
    }

@Override
    public synchronized void close(){
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
}

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }











}
