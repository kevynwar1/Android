package br.com.helloworld.estudantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.helloworld.estudantes.adapter.AdapterTipoEstudante;
import br.com.helloworld.estudantes.controller.Banco;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

public class MainActivity extends Activity {
    private ArrayList<TipoEstudante> listaTipoEstudante;
    private ListView lvTipoEstudante;
    private Button btCadastrar;
    private Button btTipoEstudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTipoEstudante = (ListView) findViewById(R.id.lvTipoEstudante);
        btCadastrar = (Button) findViewById(R.id.btCadastra);
        btTipoEstudante = (Button) findViewById(R.id.btTipoEstudante);
        preencherLista();
        clickLista();
        clickCadastrar();
        clickTipo();

    }
// preenche lista manualmente
    /*
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

    }*/

    private void preencherLista() {
        AdapterTipoEstudante adapterTipoEstudante = new AdapterTipoEstudante(MainActivity.this, new Banco(MainActivity.this).listaEstudante());
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

    private void clickCadastrar() {
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadEstudante.class);
                startActivity(intent);
            }
        });
    }

    private void clickTipo() {
        btTipoEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadTipoEstudante.class);
                startActivity(intent);
            }
        });
    }
}
