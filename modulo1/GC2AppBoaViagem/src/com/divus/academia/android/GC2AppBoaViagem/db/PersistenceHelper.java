package com.divus.academia.android.GC2AppBoaViagem.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersistenceHelper extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	private static final String NOME_BANCO = "base_divus";
	private static final String SCRIPT_PATH = "scripts";
	private static final String SCRIPT_NOME = "script_";
	
	private static PersistenceHelper instance = null;
	private Context context;

	public PersistenceHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO);
		this.context = context;
	}
	
	public static PersistenceHelper getInstace(Context context) {
		if (instance == null) {
			instance = new PersistenceHelper(context);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		executarSqlScript(db, SCRIPT_NOME + "1.sql");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			int cont = oldVersion;
			while (cont <= newVersion) {
				executarSqlScript(db, SCRIPT_NOME + (cont+1) + ".sql");
				cont++;
			}
		}
	}

	private void executarSqlScript(SQLiteDatabase db, String nomeScript) {
		try {
			final InputStream input = context.getAssets().open(SCRIPT_PATH+"/"+nomeScript);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String linha = null;
			while ((linha = reader.readLine()) != null) {
				if (linha.compareTo("") != 0) {
					db.execSQL(linha);
				}
			}
		} catch (IOException e) {
			Log.e("DATABASE", "Falha ao executar script!");
		}
	}
}
