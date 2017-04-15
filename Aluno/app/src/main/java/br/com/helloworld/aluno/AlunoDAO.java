package br.com.helloworld.aluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevyn on 11/04/2017.
 */

class AlunoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "Aluno";
    private static final int VERSAO = 1;

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("nota", aluno.getNota());
        values.put("foto", aluno.getFoto());

        getWritableDatabase().insertOrThrow("alunos", null, values);
    }

    public void deletar(Aluno aluno) {
        //  String[] args = {String.valueOf(aluno.getId())};
        // com o nome pode ser porque ele é UNIQUE mas com o id é bem melhor
        String[] args = {aluno.getNome()};
        getWritableDatabase().delete("alunos", "nome=?", args);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos (id PRIMARY KEY, nome TEXT UNIQUE NOT NULL,telefone TEXT,endereco TEXT, site TEXT, foto TEXT, nota REAL); ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS alunos";
        db.execSQL(sql);

        this.onCreate(db);
    }


    public List<Aluno> getLista() {

        String[] colunas = {"id", "nome", "site", "telefone", "endereco", "foto", "nota"};

        Cursor cursor = getWritableDatabase().query("alunos", colunas, null, null, null, null, null);
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setSite(cursor.getString(2));
            aluno.setTelefone(cursor.getString(3));
            aluno.setEndereco(cursor.getString(4));
            aluno.setFoto(cursor.getString(5));
            aluno.setNota(cursor.getDouble(6));

            alunos.add(aluno);
        }
        return alunos;
    }

    public void alterar(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("nota", aluno.getNota());
        values.put("foto", aluno.getFoto());
        String[] args = {aluno.getNome()};
        getWritableDatabase().update("alunos", values, "nome=?", args);

    }

    public boolean isaluno(String telefone) {
        String[] args = {telefone};
        Cursor cursor = getWritableDatabase().rawQuery("SELECT id  FROM alunos WHERE telefone = ? ", args);
        boolean existeumPrimeiro = cursor.moveToFirst();

        return existeumPrimeiro;

    }
}

