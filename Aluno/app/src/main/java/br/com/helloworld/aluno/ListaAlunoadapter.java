package br.com.helloworld.aluno;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kevyn on 13/04/2017.
 */

class ListaAlunoadapter extends BaseAdapter {
    private List<Aluno> alunos;
    private Activity activity;

    public ListaAlunoadapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno aluno = alunos.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        // retorna uma linha do meu layout
        View linha = inflater.inflate(R.layout.linha_listagem, null);
        TextView nome = (TextView) linha.findViewById(R.id.nomealuno);
        nome.setText(aluno.getNome());

        ImageView foto = (ImageView) linha.findViewById(R.id.fotoaluno);

        if (aluno.getFoto() != null) {
            Bitmap fotoAluno = BitmapFactory.decodeFile(aluno.getFoto());

//            Bitmap fotoreduzida = Bitmap.createScaledBitmap(fotoAluno, 100, 100, true);
            foto.setImageBitmap(fotoAluno);

        } else {
            Drawable semfoto = activity.getResources().getDrawable(R.drawable.student);
            foto.setImageDrawable(semfoto);

        }
        return linha;
    }
}
