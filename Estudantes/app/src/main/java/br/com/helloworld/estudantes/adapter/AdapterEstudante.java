package br.com.helloworld.estudantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.helloworld.estudantes.R;
import br.com.helloworld.estudantes.modelo.Estudante;

/**
 * Created by kevyn on 23/03/2017.
 */

public class AdapterEstudante extends BaseAdapter {
    private Context ctx;
    private List<Estudante> listaEstudante;

    public AdapterEstudante(Context ctx, List<Estudante> listaEstudante) {
        this.ctx = ctx;
        this.listaEstudante = listaEstudante;
    }

    @Override//Contador
    public int getCount() {
        return listaEstudante.size();
    }

    @Override// posição do item da tv_tipo_estudante
    public Object getItem(int position) {
        return listaEstudante.get(position);
    }

    @Override//item da tv_tipo_estudante pelo id
    public long getItemId(int position) {
        return listaEstudante.get(position).getId_estudante();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estudante estudante = listaEstudante.get(position);

        View v = LayoutInflater.from(ctx).inflate(R.layout.tv_estudante, null);
        TextView tvestudante = (TextView) v.findViewById(R.id.tvestudante);
        tvestudante.setText(estudante.getNome());
        return v;
    }
}
