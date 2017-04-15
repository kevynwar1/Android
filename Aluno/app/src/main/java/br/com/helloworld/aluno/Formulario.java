package br.com.helloworld.aluno;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;

/**
 * Created by kevyn on 11/04/2017.
 */

public class Formulario extends Activity {

    private FormularioHelper helper;
    private Button btGravar;
    private ImageView foto;
    private String caminhoArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);


        btGravar = (Button) findViewById(R.id.btGravar);
        Intent intentrecebe = getIntent();
        final Aluno alunoalter = (Aluno) intentrecebe.getSerializableExtra("alunoselecionado");

        helper = new FormularioHelper(this);

        if (alunoalter != null) {
            btGravar.setText("Alterar");
            helper.colocaAluno(alunoalter);
        }


        Button btGravar = (Button) findViewById(R.id.btGravar);
        btGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Aluno aluno = helper.pegaAlunodoFormulario();
                    AlunoDAO dao = new AlunoDAO(Formulario.this);
                    if (alunoalter == null) {
                        dao.salva(aluno);
                    } else {
                        aluno.setId(alunoalter.getId());
                        dao.alterar(aluno);
                    }
                    dao.close();

                    finish();
                    MediaPlayer media = MediaPlayer.create(Formulario.this, R.raw.ignite);
                    media.start();
                } catch (Exception e) {
                    Toast.makeText(Formulario.this, "Nome ja cadastrado" + e, Toast.LENGTH_SHORT).show();
                }

            }
        });
        foto = helper.getFoto();
        clickfoto();

    }

    public void clickfoto() {
        foto.setOnClickListener(new View.OnClickListener() {

            //LOGO ABAIXO ACESSANDO A CAMERA DO DISPOSITIVO E DEFININDO LOCAL PARA GUARDA UMA COPIA DA FOTO
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//gravar no SD... currentTimeMillis = tempo em milisegundos em que esta disparando o evento
                caminhoArquivo = Environment.getExternalStorageDirectory().toString() + "/" + System.currentTimeMillis() + ".png";

                //criando de fato o caminho do arquivo
                File arquivo = new File(caminhoArquivo);
// Uri.. explicando para o android onde fica o local da imagem
                Uri localImagem = Uri.fromFile(arquivo);
//salvar copia da imagem pelo Extra_output
                intent.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
                // ESPERANDO RSULTADO VAI QUE ELE CLICOU SEM QUERER ENTÃO NECESSIDADE DE FORRESULT
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificando a chamada da camera o resultCode ta verificando  se ele realmente quis a foto pro perfil dele
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                helper.carregaImagem(caminhoArquivo);
            } else {
                caminhoArquivo = null;
            }
        }
    }
}
