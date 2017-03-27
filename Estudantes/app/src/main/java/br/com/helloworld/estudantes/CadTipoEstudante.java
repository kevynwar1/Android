package br.com.helloworld.estudantes;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.helloworld.estudantes.controller.Banco;
import br.com.helloworld.estudantes.modelo.TipoEstudante;

public class CadTipoEstudante extends AppCompatActivity {
    EditText edtTipo;
    Button btSalvarTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tipo_estudante);
        edtTipo = (EditText) findViewById(R.id.edtTipo);
        btSalvarTipo = (Button) findViewById(R.id.btSalvarTipo);
        clickSalvarTipo();

    }

    private void clickSalvarTipo() {
        btSalvarTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (edtTipo.getText() != null && !edtTipo.getText().toString().equals("")) {
                        TipoEstudante tipoEstudante = new TipoEstudante(edtTipo.getText().toString());
                        int retorno = new Banco(CadTipoEstudante.this).inserirTipo(tipoEstudante);
                        if (retorno == 0) {
                            mostramensagem("Inserido", "Inserido com Sucesso !!");
                            edtTipo.setText("");
                        } else if (retorno == 1) {
                            mostramensagem("ERROR", "Não foi possivel inserir !!");
                        } else if (retorno == 2) {
                            mostramensagem("AVISO!", "Tipo ja esta inserido");
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void mostramensagem(String titulo, String msg) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(CadTipoEstudante.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(msg);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }

}
