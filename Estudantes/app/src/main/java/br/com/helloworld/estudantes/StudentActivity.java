package br.com.helloworld.estudantes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.helloworld.estudantes.adapter.AdapterEstudante;
import br.com.helloworld.estudantes.modelo.Estudante;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

/**
 * Created by kevyn on 23/03/2017.
 */

public class StudentActivity extends Activity {

    private ListView listaEstudante;
    private TipoEstudante estudantes;
    private ArrayList<Estudante> listadestudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity);
        listaEstudante = (ListView) findViewById(R.id.lvEstudante);
        estudantes = (TipoEstudante) getIntent().getSerializableExtra("estudante");
        listaEstudante();
    }

    private void listaEstudante() {
        listadestudante = new ArrayList<Estudante>();
        Estudante est1 = null;
        Estudante est2 = null;
        Estudante est3 = null;
        if (estudantes.getId_tipo_estudante() == 1) {
            est1 = new Estudante(1, "Ikaro Sales", "997368263", "Pina", "ikarosales@gmail.com");
            est2 = new Estudante(2, "Guilherme Santana", "97052822", "pointzinha", "guisants@gmail.com");
            est3 = new Estudante(3, "Diogo Rocha", "98295112", "Boa Viagem", "phantom@gmail.com");

        } else if (estudantes.getId_tipo_estudante() == 2) {
            est1 = new Estudante(1, "Milena Souza", "45678765", "rua de Candeias", "Mileninha_s@gmail.com");
            est2 = new Estudante(2, "Joana Amanda", "78658903", "av. Candeias Beach", "JoaninhaPlim@gmail.com");
            est3 = new Estudante(3, "Léo Stronda", "34561257", "rua das Bombas Buck", "Vaimonstro322@gmail.com");

        } else if (estudantes.getId_tipo_estudante() == 3) {
            est1 = new Estudante(1, "Kevyn Herbet", "348484861", "rua Vitor Meireles", "kevynh49@gmail.com");
            est2 = new Estudante(2, "Sakura Haruno", "36517823", "rua Sasuke-san", "Haruno223@gmail.com");
            est3 = new Estudante(3, "Hinata Hyuuga", "49639696", "Perto de Konoha", "Juukenhou@gmail.com");

        }
        listadestudante.add(est1);
        listadestudante.add(est2);
        listadestudante.add(est3);
        AdapterEstudante adapterestudante = new AdapterEstudante(StudentActivity.this, listadestudante);
        listaEstudante.setAdapter(adapterestudante);
    }

}
