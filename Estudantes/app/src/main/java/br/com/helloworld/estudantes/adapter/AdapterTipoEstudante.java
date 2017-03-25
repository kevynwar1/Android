package br.com.helloworld.estudantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.helloworld.estudantes.R;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

/**
 * Created by kevyn on 23/03/2017.
 */

public class AdapterTipoEstudante extends BaseAdapter {
    private Context ctx;
    private List<TipoEstudante> listaTipoEstudante;

    public AdapterTipoEstudante(Context ctx, List<TipoEstudante> listaTipoEstudante) {
        this.ctx = ctx;
        this.listaTipoEstudante = listaTipoEstudante;
    }

    @Override//Contador
    public int getCount() {
        return listaTipoEstudante.size();
    }

    @Override// posição do item da tv_tipo_estudante
    public Object getItem(int position) {
        return listaTipoEstudante.get(position);
    }

    @Override//item da tv_tipo_estudante pelo id
    public long getItemId(int position) {
        return listaTipoEstudante.get(position).getId_tipo_estudante();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoEstudante tipoestudante = listaTipoEstudante.get(position);

        View v = LayoutInflater.from(ctx).inflate(R.layout.tv_tipo_estudante, null);
        TextView tvestudante = (TextView) v.findViewById(R.id.tvconteudo);
        tvestudante.setText(tipoestudante.getConteudo());
        return v;
    }
}
