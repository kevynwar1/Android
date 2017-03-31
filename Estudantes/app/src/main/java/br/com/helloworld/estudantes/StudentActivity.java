package br.com.helloworld.estudantes;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.helloworld.estudantes.adapter.AdapterEstudante;
import br.com.helloworld.estudantes.controller.Banco;
import br.com.helloworld.estudantes.modelo.Estudante;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

/**
 * Created by kevyn on 23/03/2017.
 */

public class StudentActivity extends Activity {

    private ListView listaEstudante;
    private TipoEstudante estudantes;
    private List<Estudante> listadestudante;
    private Button btCadastrar;
    private Estudante estuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        listaEstudante = (ListView) findViewById(R.id.lvEstudante);
        estudantes = (TipoEstudante) getIntent().getSerializableExtra("estudante");
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        listaEstudante();
        clickCadastrar();
        clickLista();

    }

    private void listaEstudante() {
        Banco banco = new Banco(StudentActivity.this);
        listadestudante = banco.listadoEstudante(estudantes.getId_tipo_estudante());
        AdapterEstudante adapterEstudante = new AdapterEstudante(StudentActivity.this, listadestudante);
        listaEstudante.setAdapter(adapterEstudante);
    }



  /*  private void listaEstudante() {
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
    }*/


    public void clickCadastrar() {
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, CadEstudante.class);
                intent.putExtra("estudante", estudantes);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        listaEstudante();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void clickLista() {
        listaEstudante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, final int position, long id) {
                final Estudante estudante = (Estudante) adapter.getAdapter().getItem(position);

                final Estudante estuda = new Estudante();
                final CharSequence[] itens = {getString(R.string.excluir), getString(R.string.ligar), getString(R.string.sms), getString(R.string.localização), getString(R.string.site)};
                AlertDialog.Builder opçoes = new AlertDialog.Builder(StudentActivity.this);
                opçoes.setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String opcao = (String) itens[which];

                        if (opcao.equals(getString(R.string.excluir))) {
                            mostrasimnao(estudante);
                        } else if (opcao.equals(getString(R.string.ligar))) {
                            String numero = "tel:" + "988602849";
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numero)));
                        } else if (opcao.equals(getString(R.string.sms))) {
                            String sms = "sms:" + "988602849";
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sms)));
                        } else if (opcao.equals(getString(R.string.localização))) {

                        } else if (opcao.equals(getString(R.string.site))) {
                            Uri uri = Uri.parse("http://www.facebook.com");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    }
                });
                opçoes.setTitle(getString(R.string.opcoes));
                AlertDialog alert = opçoes.create();
                alert.show();


            }
        });
    }

    private void mostrasimnao(final Estudante estudante) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(StudentActivity.this);
        mensagem.setMessage(getString(R.string.confirma));
        mensagem.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int retorno = new Banco(StudentActivity.this).excluirestudante(estudante);
                if (retorno == 1) {
                    Toast.makeText(StudentActivity.this, getString(R.string.msgexcluido), Toast.LENGTH_LONG).show();
                    listaEstudante();
                } else if (retorno == 2) {
                    Toast.makeText(StudentActivity.this, getString(R.string.errorexcluir), Toast.LENGTH_LONG).show();
                }
            }
        });
        mensagem.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mensagem.create().show();
    }
}
