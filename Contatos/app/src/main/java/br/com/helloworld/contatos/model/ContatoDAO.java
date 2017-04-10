package br.com.helloworld.contatos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

/**
 * Created by kevyn on 08/04/2017.
 */

public class ContatoDAO {

    private SQLiteDatabase con;

    public ContatoDAO(SQLiteDatabase con) {
        this.con = con;
    }

    public void inserirContato(Contato contato) {

        ContentValues values = new ContentValues();

        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATA", contato.getData().getTime());
        values.put("TIPODATA", contato.getTipoData());
        values.put("GRUPOS", contato.getGrupo());

        con.insertOrThrow("CONTATO", null, values);
    }

    public ArrayAdapter<Contato> buscaContato(Context ctx) {
        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(ctx, android.R.layout.simple_list_item_1);

        Cursor cursor = con.query("CONTATO",null,null,null,null,null,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Contato contato = new Contato();

                contato.setId(cursor.getLong(0));
                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getString(7));
                contato.setData(new Date(cursor.getLong(8)));
                contato.setTipoData(cursor.getString(9));
                contato.setGrupo(cursor.getString(10));

                adpContatos.add(contato);
            } while (cursor.moveToNext());
        }

        return adpContatos;
    }
}

