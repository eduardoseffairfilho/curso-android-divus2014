package com.divus.academia.android.GC2AppBoaViagem.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.divus.academia.android.GC2AppBoaViagem.db.PersistenceHelper;
import com.divus.academia.android.GC2AppBoaViagem.model.Despesa;

public class DespesaDAO {
	private static final String NOME_TABELA = "DESPESA";
	private static final String COLUNA_ID = "ID";
	private static final String COLUNA_LOCAL = "LOCAL";
	private static final String COLUNA_DATA = "DATA";
	private static final String COLUNA_VALOR = "VALOR";
	private static final String COLUNA_CATEGORIA = "CATEGORIA";
	private static final String COLUNA_DESCRICAO = "DESCRICAO";

	private static SQLiteDatabase database = null;
	
	private static DespesaDAO instance;

	private DespesaDAO(Context context) {
		PersistenceHelper persistence = PersistenceHelper.getInstace(context);
		database = persistence.getWritableDatabase();
	}
	
	public static DespesaDAO getInstance(Context context) {
		if (instance == null) {
			instance = new DespesaDAO(context);
		}
		return instance;
	}
	
	public void salvar(Despesa despesa) {
		ContentValues values = gerarContentValues(despesa);
		Long id = database.insert(NOME_TABELA, null, values);
		despesa.setId(id);
	}
	
	public List<Despesa> listarTodos() {
		Cursor cursor = database.rawQuery("SELECT * FROM " + NOME_TABELA + " ORDER BY " + COLUNA_DESCRICAO + " ASC ", null);
		List<Despesa> lista = construirDespesa(cursor);
		return lista;
	}
	
	public void deletar(Despesa despesa) {
		
	}

	private List<Despesa> construirDespesa(Cursor cursor) {
		List<Despesa> listaDespesa = new ArrayList<Despesa>();
		try {
			cursor.moveToFirst();
			while(!cursor.isAfterLast()) {
				Despesa despesa = new Despesa();
				despesa.setId(cursor.getLong(cursor.getColumnIndex(COLUNA_ID)));
				despesa.setLocal(cursor.getString(cursor.getColumnIndex(COLUNA_LOCAL)));
				despesa.setData(cursor.getString(cursor.getColumnIndex(COLUNA_DATA)));
				despesa.setCategoria(cursor.getString(cursor.getColumnIndex(COLUNA_CATEGORIA)));
				despesa.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO)));
				despesa.setValor(cursor.getLong(cursor.getColumnIndex(COLUNA_VALOR)));
				listaDespesa.add(despesa);
				cursor.moveToNext();
			}
		} catch(Exception e) {
			Log.e("ERROR", "Erro na consulta.");
		} finally {
			cursor.close();
		}
		return listaDespesa;
	}
	
	private ContentValues gerarContentValues(Despesa despesa) {
		ContentValues value = new ContentValues();
		value.put(COLUNA_DESCRICAO, despesa.getDescricao());
		value.put(COLUNA_CATEGORIA, despesa.getCategoria());
		value.put(COLUNA_VALOR, despesa.getValor());
		value.put(COLUNA_DATA, despesa.getData());
		value.put(COLUNA_LOCAL, despesa.getLocal());
		return value;
	}
}
