package br.com.helloworld.estudantes.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.helloworld.estudantes.modelo.Estudante;
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

    private String TABELA_ESTUDANTE = "ESTUDANTE";
    private String ID_TIPO = "ID_TIPO";
    private String NOME_ESTUDANTE = "NOME_ESTUDANTE";
    private String TELEFONE_ESTUDANTE = "TELEFONE_ESTUDANTE";
    private String ENDERECO_ESTUDANTE = "ENDERECO_ESTUDANTE";
    private String SITE_ESTUDANTE = "SITE_ESTUDANTE";
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

    public void criarBancoEstudante(){
        try {
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            String sql = "CREATE TABLE IF NOT EXISTS "+TABELA_ESTUDANTE+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_TIPO+" INTEGER," +
                    ""+NOME_ESTUDANTE+" TEXT, "+TELEFONE_ESTUDANTE+" TEXT,"+ENDERECO_ESTUDANTE+" TEXT, "+SITE_ESTUDANTE+" TEXT)";
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

    public int inserirEstudante(Estudante estudante){

        int retorno = 0;
        try {
            if (verificaEstudante(estudante.getNome(),estudante.getId_estudante())){
                return 2;
            }
            criarBancoEstudante();
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            String sql = "INSERT INTO "+TABELA_ESTUDANTE+" ("+ID_TIPO+","+NOME_ESTUDANTE+","+TELEFONE_ESTUDANTE+","+ENDERECO_ESTUDANTE+","+SITE_ESTUDANTE+")" +
                    " VALUES ('"+estudante.getIdTipo()+"','"+estudante.getNome()+"','"+estudante.getTelefone()+"','"+estudante.getEndereco()+"','"+estudante.getEmail()+"')";

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
    public boolean verificaEstudante(String dados, int id){
        boolean retorno = false;
        try{
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO,0,null);
            cursorTipo = bancoDados.rawQuery("SELECT * FROM "+TABELA_ESTUDANTE+" WHERE "+NOME_ESTUDANTE+" = '"+dados+"'AND "+ID_TIPO+" = '"+id+"'",null);
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
    public List<Estudante> listadoEstudante(int id){
        ArrayList<Estudante> lista = new ArrayList<Estudante>();
        try {
            bancoDados = ctx.openOrCreateDatabase(NOME_BANCO, 0, null);
            Cursor cursorList = bancoDados.rawQuery("SELECT * FROM " + TABELA_ESTUDANTE + " WHERE "+ID_TIPO+"= '"+id+"'", null);
            if (cursorList != null && cursorList.getCount() != 0) {
                cursorList.moveToFirst();
                do {

                    Estudante estudante = new Estudante();

                    estudante.setId_estudante(Integer.parseInt(cursorList.getString(cursorList.getColumnIndex("_ID"))));
                    estudante.setNome(cursorList.getString(cursorList.getColumnIndex("NOME_ESTUDANTE")));
                    estudante.setIdTipo(Integer.parseInt(cursorList.getString(cursorList.getColumnIndex("ID_TIPO"))));

                    lista.add(estudante);

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
