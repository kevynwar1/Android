package br.com.helloworld.contatos;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import br.com.helloworld.contatos.database.Banco;
import br.com.helloworld.contatos.model.Contato;
import br.com.helloworld.contatos.model.ContatoDAO;

public class MainActivity extends AppCompatActivity {

    private EditText edtPesquisa;
    private ImageButton btAdicionar;
    private ListView lvContatos;
    private ArrayAdapter<Contato> adpContatos;

    private Banco banco;
    private SQLiteDatabase con;
    private ContatoDAO contato;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        btAdicionar = (ImageButton) findViewById(R.id.btAdicionar);
        lvContatos = (ListView) findViewById(R.id.lvContatos);

        clickButton();
        clickLista();

        try {
            banco = new Banco(this);
            con = banco.getWritableDatabase();

            contato = new ContatoDAO(con);

            adpContatos = contato.buscaContato(this);
            lvContatos.setAdapter(adpContatos);


        } catch (SQLException e) {
            AlertDialog.Builder alg = new AlertDialog.Builder(this);
            alg.setTitle("Conexão Com Banco de Dados");
            alg.setMessage("Error ao criada o Banco" + e.getMessage());
            alg.setNeutralButton("OK", null);
            alg.show();
        }

    }

    public void clickButton() {
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadContatos.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        adpContatos = contato.buscaContato(this);
        lvContatos.setAdapter(adpContatos);


    }

    public void clickLista() {
        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //recuperando a posição do item selecionado
                Contato contato = adpContatos.getItem(position);

                Intent intent = new Intent(MainActivity.this, CadContatos.class);

                intent.putExtra("CONTATO", contato);

                startActivityForResult(intent, 0);

            }
        });
    }
}
