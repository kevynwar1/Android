package br.com.helloworld.estudantes.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.helloworld.estudantes.modelo.TipoEstudante;

/**
 * Created by kevyn on 26/03/2017.
 */

public class Banco {
    private Context ctx;
    private Cursor cursorTipo;
    private String NOME_BANCO = "ESTUDANTES";
    private String NOME_TABELA = "TIPO_ESTUDANTES";
    private String TIPO = "TIPO";
    private SQLiteDatabase bancoDados = null;

    public Banco(Context ctx) {
        this.ctx = ctx;
    }

    //cria o banco se não existir
    public void criaBancoTipo() {
        try {
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            String sql = "CREATE TABLE IF NOT EXISTS "+NOME_TABELA+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+TIPO+" TEXT)";
            bancoDados.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bancoDados != null){
                bancoDados.close();
            }
        }
    }
    public int inserirTipo(TipoEstudante tipoEstudante){

        int retorno = 0;
        try {
            if (verificaTipo(tipoEstudante.getConteudo())){
                return 2;
            }
          criaBancoTipo();
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            String sql = "INSERT INTO "+NOME_TABELA+" ("+TIPO+") VALUES ('"+tipoEstudante.getConteudo()+"')";
            bancoDados.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
            retorno = 1;
        }finally {
            if(bancoDados != null){
                bancoDados.close();
            }
        }
        return retorno;
    }
    public boolean verificaTipo(String tipo){
        boolean retorno = false;
        try{
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            cursorTipo = bancoDados.rawQuery("SELECT * FROM "+NOME_TABELA+" WHERE "+TIPO+" = '"+tipo+"' ",null);
            if(cursorTipo != null && cursorTipo.getCount() != 0){
               retorno  = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bancoDados != null){
                bancoDados.close();
            } if (cursorTipo != null){
                cursorTipo.close();
            }
        }
        return retorno;
    }
    public List<TipoEstudante> listaEstudante(){
       ArrayList<TipoEstudante> lista = new ArrayList<TipoEstudante>();
     try {
         bancoDados = ctx.openOrCreateDatabase(NOME_BANCO, 0, null);
         Cursor cursorList = bancoDados.rawQuery("SELECT * FROM " + NOME_TABELA + "", null);
         if (cursorList != null && cursorList.getCount() != 0) {
            cursorList.moveToFirst();
             do {

                 TipoEstudante tipoEstudante = new TipoEstudante();

                 tipoEstudante.setId_tipo_estudante(Integer.parseInt(cursorList.getString(cursorList.getColumnIndex("_ID"))));
                 tipoEstudante.setConteudo(cursorList.getString(cursorList.getColumnIndex("TIPO")));

                 lista.add(tipoEstudante);

             }while (cursorList.moveToNext());

         }
         if (cursorList != null){
             cursorList.close();
         }
     }catch (Exception e){
         e.printStackTrace();
     }finally {
         if(bancoDados != null){
             bancoDados.close();
         }

     }
        return  lista;
    }

}
