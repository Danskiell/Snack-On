package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class conectarHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "snackOn.db";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    // Construtor
    public conectarHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }

    // Método para criar o banco, copiando da pasta assets se necessário
    public void criarBanco() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Erro ao copiar banco");
            }
        }
    }

    // Verifica se o banco de dados já existe
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String caminho = myContext.getDatabasePath(DB_NAME).getPath();
            checkDB = SQLiteDatabase.openDatabase(caminho, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            // Banco não existe
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    // Copia o banco de dados da pasta assets para o local de armazenamento do aplicativo
    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getPath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    // Abre o banco de dados para leitura
    public void abrirBanco() throws SQLiteException {
        String caminho = myContext.getDatabasePath(DB_NAME).getPath();
        myDatabase = SQLiteDatabase.openDatabase(caminho, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null) {
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
