package br.com.helloworld.atv01;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySecundaria extends AppCompatActivity {

    public void recebendoDados(){

        TextView tvNome =(TextView) findViewById(R.id.tvNome);
        TextView tvTelefone =(TextView) findViewById(R.id.tvTelefone);
        TextView tvEndereco =(TextView) findViewById(R.id.tvEndereco);
        TextView tvSite=(TextView) findViewById(R.id.tvSite);
        TextView tvNota =(TextView) findViewById(R.id.tvNota);


        Bundle b = getIntent().getExtras();
        tvNome.setText(tvNome.getText() + "Esse é o Cara: "+b.getString("nome"));
        Integer telefone = b.getInt("telefone");
        tvTelefone.setText(tvTelefone.getText()+ "Ligue ai: "+telefone.toString());
        tvEndereco.setText(tvEndereco.getText()+"Vê o Endereço do Cara: "+b.getString("endereco"));
        tvSite.setText(tvSite.getText()+"Chama lá que Nois: "+b.getString("site"));
        Double nota = b.getDouble("nota");
        tvNota.setText(tvNota.getText()+"Que Notinha em Filhão: "+nota.toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        recebendoDados();


    }
}
