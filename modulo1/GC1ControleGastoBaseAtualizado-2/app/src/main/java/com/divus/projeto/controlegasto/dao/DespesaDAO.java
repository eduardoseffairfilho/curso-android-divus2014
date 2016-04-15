package com.divus.projeto.controlegasto.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.divus.projeto.controlegasto.db.PersistenceHelper;
import com.divus.projeto.controlegasto.model.Despesa;
public class DespesaDAO {

	private static final String NOME_TABELA = "despesa";
	
	private static final String COLUNA_ID = "id";
	private static final String COLUNA_DESCRICAO = "descricao";
	private static final String COLUNA_LOCAL = "local";
	private static final String COLUNA_DATA = "data";
	private static final String COLUNA_CATEGORIA = "categoria";
	private static final String COLUNA_VALOR = "valor";
	
	private SQLiteDatabase database = null;
	
    private static DespesaDAO instance;
	
	private DespesaDAO(Context context) {
		PersistenceHelper helper = PersistenceHelper.getInstace(context);
		database = helper.getWritableDatabase();
	}
	
	public static DespesaDAO getInstance(Context context) {
		if (instance == null) {
			instance = new DespesaDAO(context);
		}
		return instance;
	}
	
	public void update(Despesa despesa) {
		String[] parametros = {String.valueOf(despesa.getId())};
		ContentValues value = gerarContentValueDespesa(despesa);
		
		database.update(NOME_TABELA, value, COLUNA_ID + " = ? ", parametros);
	}
	
	public void salvar(Despesa despesa) {
		ContentValues value = gerarContentValueDespesa(despesa);
		Long id =  database.insert(NOME_TABELA, null, value);
		despesa.setId(id.intValue());
	}
	
	public List<Despesa> listarTodos() {
		String query = "SELECT * FROM " + NOME_TABELA 
				+ " ORDER BY " + COLUNA_DESCRICAO + " ASC";
		Cursor cursor = database.rawQuery(query, null);
		List<Despesa> despesas = contruirDespesa(cursor);
		
		return despesas;
	}
	
	public void deletar(Despesa despesa) {
		String[] parametro = { String.valueOf(despesa.getId()) };
		database.delete(NOME_TABELA, COLUNA_ID + " = ? ", parametro);
	}
	
	public List<Despesa> contruirDespesa(Cursor cursor) {
		List<Despesa> despesas = new ArrayList<Despesa>();
		if (cursor == null) {
			return despesas;
		}
		
		try {
			
			if (cursor.moveToFirst()) {
				do {
					int id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
					String descricao = cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO));
					String local = cursor.getString(cursor.getColumnIndex(COLUNA_LOCAL));
					String categoria = cursor.getString(cursor.getColumnIndex(COLUNA_CATEGORIA)); 
					String data = cursor.getString(cursor.getColumnIndex(COLUNA_DATA));
					Double valor = cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR));
					
					Despesa despesa = new Despesa(id, descricao, categoria, valor, data, local);
					despesas.add(despesa);
				} while (cursor.moveToNext());
			}
			
		} finally {
			cursor.close();
		}
		return despesas;
	}
	
	
	
	private ContentValues gerarContentValueDespesa(Despesa despesa) {
		ContentValues value = new ContentValues();
		value.put(COLUNA_DESCRICAO, despesa.getDescricao());
		value.put(COLUNA_DATA, despesa.getData());
		value.put(COLUNA_CATEGORIA, despesa.getCategoria());
		value.put(COLUNA_LOCAL, despesa.getLocal());
		value.put(COLUNA_VALOR, despesa.getValor());
		
		return value;
	}
	
}
