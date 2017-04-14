package br.com.helloworld.atmempresaconsultoria;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView empresa;
    private ImageView servico;
    private ImageView cliente;
    private ImageView contato;
    private ImageView atm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        atm = (ImageView) findViewById(R.id.atm);
        empresa = (ImageView) findViewById(R.id.empresa);
        servico = (ImageView) findViewById(R.id.servicos);
        cliente = (ImageView) findViewById(R.id.clientes);
        contato = (ImageView) findViewById(R.id.contatos);


        empresa.setOnClickListener(this);
        servico.setOnClickListener(this);
        cliente.setOnClickListener(this);
        contato.setOnClickListener(this);
        atm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.empresa:
                Intent intent = new Intent(MainActivity.this, EmpresaActivity.class);
                startActivity(intent);


                break;
            case R.id.servicos:
                Intent in = new Intent(MainActivity.this, ServicoActivity.class);
                startActivity(in);


                break;
            case R.id.clientes:
                Intent intd = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intd);


                break;
            case R.id.contatos:
                Intent inten = new Intent(MainActivity.this, ContatoActivity.class);
                startActivity(inten);


                break;
            case R.id.atm:
                MediaPlayer player = MediaPlayer.create(this, R.raw.ninguem_explica);
                player.start();

        }

    }


}
