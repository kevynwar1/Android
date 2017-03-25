package br.com.helloworld.estudantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.helloworld.estudantes.adapter.AdapterTipoEstudante;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

public class MainActivity extends Activity {
    private ArrayList<TipoEstudante> listaTipoEstudante;
    private ListView lvTipoEstudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTipoEstudante = (ListView) findViewById(R.id.lvTipoEstudante);
        preencherLista();
        clickLista();

    }

    private void preencherLista() {
        listaTipoEstudante = new ArrayList<TipoEstudante>();
        TipoEstudante tipoEstudante1 = new TipoEstudante(1, "Técnico");
        TipoEstudante tipoEstudante2 = new TipoEstudante(2, "Graduação");
        TipoEstudante tipoEstudante3 = new TipoEstudante(3, "Pós-Graduação");

        listaTipoEstudante.add(tipoEstudante1);
        listaTipoEstudante.add(tipoEstudante2);
        listaTipoEstudante.add(tipoEstudante3);
        AdapterTipoEstudante adapterTipoEstudante = new AdapterTipoEstudante(MainActivity.this, listaTipoEstudante);
        lvTipoEstudante.setAdapter(adapterTipoEstudante);

    }

    private void clickLista() {
        lvTipoEstudante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoEstudante tipoestudante = (TipoEstudante) parent.getAdapter().getItem(position);
                // Toast.makeText(MainActivity.this, tipoestudante.getConteudo(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra("estudante", tipoestudante);
                startActivity(intent);
            }
        });

    }
}
