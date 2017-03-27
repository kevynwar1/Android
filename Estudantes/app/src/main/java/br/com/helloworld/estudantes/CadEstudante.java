package br.com.helloworld.estudantes;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.helloworld.estudantes.controller.Banco;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

public class CadEstudante extends AppCompatActivity {
    EditText edtNome;
    EditText edtTelefone;
    EditText edtEndereco;
    EditText edtSite;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_estudante);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtSite = (EditText) findViewById(R.id.edtSite);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        clickSalvar();
    }

    private void clickSalvar() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


}
