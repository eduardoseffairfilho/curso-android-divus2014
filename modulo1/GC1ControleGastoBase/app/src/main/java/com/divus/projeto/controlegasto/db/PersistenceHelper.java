package com.divus.projeto.controlegasto.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersistenceHelper extends SQLiteOpenHelper {

	private static final String NOME_BANCO = "base_divus";
	
	private static final int VERSAO = 2;

	private static PersistenceHelper instace;

	private static final String SCRIPT_PATH = "scripts";
	
	private static final String SCRIPT_NOME = "script_";
	
	private Context context;
	
	private PersistenceHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO);
		this.context = context;
	}
	
	public static PersistenceHelper getInstace(Context context) {
		if (instace == null) {
			instace = new PersistenceHelper(context);
		}
		return instace;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		executarSqlScript(db, SCRIPT_NOME + "1.sql");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion < newVersion){
			int cont = oldVersion;
			while(cont < newVersion) {
				cont++;
				executarSqlScript(db, SCRIPT_NOME + cont + ".sql");
			}
		}
	}

	private void executarSqlScript(SQLiteDatabase db, String nomeScript){
		try {
			InputStream input = context.getAssets().open(SCRIPT_PATH + "/" + nomeScript);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String linha = null;
			
			while ((linha = reader.readLine()) != null) {
				if (!linha.equals("")) {
					db.execSQL(linha);
				}
			}
		} catch (Exception e) {
			Log.e("DATABASE", "Falha ao executar script");
		}
	}
	
}
