package br.com.helloworld.atv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity {
public void criarObjetos() {

   Button btTransferir = (Button) findViewById(R.id.btSend);
   // btTransferir.setOnClickListener(this);
   final EditText edtNome = (EditText) findViewById(R.id.edtNome);
   final EditText edtTelefone = (EditText) findViewById(R.id.edtTelefone);
   final EditText edtEndereco = (EditText) findViewById(R.id.edtEndereco);
   final  EditText edtSite = (EditText) findViewById(R.id.edtSite);
   final EditText edtNota = (EditText) findViewById(R.id.edtNota);
    Button btToast = (Button) findViewById(R.id.btToast);

    //listener para passar os daods para outra activity
    btTransferir.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
            //bundle para passar os dados para outra activity

            Bundle b = new Bundle();
            b.putString("nome", edtNome.getText().toString());
            b.putInt("telefone",Integer.parseInt(edtTelefone.getText().toString()));
            b.putString("endereco",edtEndereco.getText().toString());
            b.putString("site",edtSite.getText().toString());
            b.putDouble("nota",Double.parseDouble(edtNota.getText().toString()));
            //intent para abrir outra activity
            Intent telaSecundaria = new Intent(getApplicationContext(), ActivitySecundaria.class);
            //adiciona os dados na instacia criada
            telaSecundaria.putExtras(b);
            startActivity(telaSecundaria);


        }


    });
    btToast.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplication(),"Olá Pessoa Chamada:"+edtNome.getText().toString()+"!!",Toast.LENGTH_SHORT).show();
        }
    });

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        criarObjetos();
    }
}
