package br.com.helloworld.aluno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

/**
 * Created by kevyn on 11/04/2017.
 */

public class FormularioHelper {
    private EditText edtNome;
    private EditText edtSite;
    private EditText edtEndereco;
    private EditText edtTelefone;
    private ImageView foto;
    private RatingBar rbAluno;
    private Aluno aluno;

    public FormularioHelper(Formulario formulario) {

        edtNome = (EditText) formulario.findViewById(R.id.edtNome);
        edtSite = (EditText) formulario.findViewById(R.id.edtSite);
        edtEndereco = (EditText) formulario.findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) formulario.findViewById(R.id.edtTelefone);
        rbAluno = (RatingBar) formulario.findViewById(R.id.rbAluno);
        foto = (ImageView) formulario.findViewById(R.id.foto);
        aluno = new Aluno();
    }

    public Aluno pegaAlunodoFormulario() {

        aluno.setNome(edtNome.getText().toString());
        aluno.setSite(edtSite.getText().toString());
        aluno.setEndereco(edtEndereco.getText().toString());
        aluno.setTelefone(edtTelefone.getText().toString());
        aluno.setNota(Double.valueOf(rbAluno.getRating()));

        return aluno;
    }

    public void colocaAluno(Aluno alunoalter) {
        aluno = alunoalter;
        edtNome.setText(alunoalter.getNome());
        edtTelefone.setText(alunoalter.getTelefone());
        edtSite.setText(alunoalter.getSite());
        edtEndereco.setText(alunoalter.getEndereco());
        rbAluno.setRating(alunoalter.getNota().floatValue());
        if (aluno.getFoto() != null) {
            carregaImagem(alunoalter.getFoto());
        }

    }

    public ImageView getFoto() {
        return foto;
    }

    public void carregaImagem(String caminhoArquivo) {
       //salvando caminho da foto no banco de Dados
        aluno.setFoto(caminhoArquivo);
        //carregando imagem no ImageView so que muito grande
        Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
        // aqui da uma reduzida
        Bitmap imagemreduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
        foto.setImageBitmap(imagemreduzida);
    }
}
