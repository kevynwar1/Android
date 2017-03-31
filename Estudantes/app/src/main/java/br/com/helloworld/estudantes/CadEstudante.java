package br.com.helloworld.estudantes;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.helloworld.estudantes.controller.Banco;
import br.com.helloworld.estudantes.modelo.Estudante;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

public class CadEstudante extends AppCompatActivity {
    EditText edtNome;
    EditText edtTelefone;
    EditText edtEndereco;
    EditText edtSite;
    Button btSalvar;
    private TipoEstudante estudantes = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_estudante);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtSite = (EditText) findViewById(R.id.edtSite);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        try {
            estudantes = (TipoEstudante) getIntent().getSerializableExtra("estudante");
        } catch (Exception e) {
            e.printStackTrace();
        }


        clickSalvar();
    }

    private void clickSalvar() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((edtNome.getText() != null && !edtNome.getText().toString().equals("")) &&
                        (edtTelefone.getText() != null && !edtTelefone.getText().toString().equals(""))) {

                    Estudante estudante = new Estudante(estudantes.getId_tipo_estudante(), edtNome.getText().toString(), edtTelefone.getText().toString(), edtEndereco.getText().toString(), edtSite.getText().toString());
                    int retorno = new Banco(CadEstudante.this).inserirEstudante(estudante);
                    // Toast.makeText(CadEstudante.this, "Cadastrado com Sucesso", Toast.LENGTH_LONG).show();

                    if (retorno == 1) {
                        Toast.makeText(CadEstudante.this, "Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                        edtNome.setText("");
                        edtTelefone.setText("");
                        edtEndereco.setText("");
                        edtSite.setText("");
                    } else if (retorno == 2) {
                        Toast.makeText(CadEstudante.this, "Não foi Possivel Cadastrar", Toast.LENGTH_LONG).show();
                    }
                }
            }


        });


    }


}
