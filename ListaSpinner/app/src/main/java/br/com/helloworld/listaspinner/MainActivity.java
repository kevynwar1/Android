package br.com.helloworld.listaspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtValor;
    private Button btAdiconar;
    private Button btExcluir;
    private Spinner spOpcoes;
    private ListView lvDados;
    private ArrayAdapter<String> adpOpcoes;
    private ArrayAdapter<String> adpDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtValor = (EditText) findViewById(R.id.edtValor);
        btAdiconar = (Button) findViewById(R.id.btAdicionar);
        btExcluir = (Button) findViewById(R.id.btExcluir);
        spOpcoes = (Spinner) findViewById(R.id.spOpcoes);
        lvDados = (ListView) findViewById(R.id.lvDados);

        adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spOpcoes.setAdapter(adpOpcoes);
        adpOpcoes.add("Opção 1");
        adpOpcoes.add("Opção 2");
        adpOpcoes.add("Opção 3");
        adpOpcoes.add("Opção 4");

        adpDados = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvDados.setAdapter(adpDados);
        clickAdiconar();
        clickExcluir();

    }

    public void clickAdiconar() {
        btAdiconar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = edtValor.getText().toString();
                item += " - " + spOpcoes.getSelectedItem();
                adpDados.add(item);
            }
        });
    }

    public void clickExcluir() {
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adpDados.getCount() > 0) {
                    String item = adpDados.getItem(0);

                    adpDados.remove(item);
                }
            }
        });
    }
}
