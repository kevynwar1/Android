package br.com.helloworld.contatos;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.helloworld.contatos.database.Banco;
import br.com.helloworld.contatos.model.Contato;
import br.com.helloworld.contatos.model.ContatoDAO;

public class CadContatos extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtData;
    private EditText edtEndereco;
    private EditText edtGrupo;

    private Spinner spTelefone;
    private Spinner spEmail;
    private Spinner spData;
    private Spinner spEndereco;

    private ArrayAdapter<String> adpTelefone;
    private ArrayAdapter<String> adpEmail;
    private ArrayAdapter<String> adpData;
    private ArrayAdapter<String> adpEndereco;

    private Banco banco;
    private SQLiteDatabase con;
    private ContatoDAO contatodao;
    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_contatos);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtData = (EditText) findViewById(R.id.edtData);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtGrupo = (EditText) findViewById(R.id.edtGrupo);

        spTelefone = (Spinner) findViewById(R.id.spTelefone);
        spEmail = (Spinner) findViewById(R.id.spEmail);
        spData = (Spinner) findViewById(R.id.spData);
        spEndereco = (Spinner) findViewById(R.id.spEndereco);
//instanciando os arrayAdapter para spinner do Telefone, Email, Datas Especiais, Endereço
        adpTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTelefone.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        adpEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//setando o adapter nos meus spinner's. Obvio!
        spTelefone.setAdapter(adpTelefone);
        spEmail.setAdapter(adpEmail);
        spData.setAdapter(adpData);
        spEndereco.setAdapter(adpEndereco);

//adicionando conteudo do spinner
        adpEmail.add("Casa");
        adpEmail.add("Trabalho");
        adpEmail.add("Outros");

        adpTelefone.add("Celular");
        adpTelefone.add("Principal");
        adpTelefone.add("Trabalho");
        adpTelefone.add("Casa");
        adpTelefone.add("Outros");

        adpEndereco.add("Casa");
        adpEndereco.add("Trabalho");
        adpEndereco.add("Outros");

        adpData.add("Aniversário");
        adpData.add("Data Comemorativa");
        adpData.add("Outros");

        edtData.setOnClickListener(new exibeDataListener());
        edtData.setOnFocusChangeListener(new exibeDataListener());

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CONTATO"))) {
            contato = (Contato) bundle.getSerializable("CONTATO");
            preencheDados();
        } else
            contato = new Contato();

        try {
            banco = new Banco(this);
            con = banco.getWritableDatabase();

            contatodao = new ContatoDAO(con);


        } catch (SQLException e) {
            AlertDialog.Builder alg = new AlertDialog.Builder(this);
            alg.setTitle("Conexão Com Banco de Dados");
            alg.setMessage("Error ao criada o Banco" + e.getMessage());
            alg.setNeutralButton("OK", null);
            alg.show();
        }

        inserir();


    }

    //adicionando o menu na minha respectiva activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu_cad_contatos, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acao1:
                if (contato.getId() == 0) {
                    inserir();
                }
                finish();
                break;
            case R.id.acao2:
                break;
            case R.id.acao3:
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    private void preencheDados() {
        edtNome.setText(contato.getNome());
        edtTelefone.setText(contato.getTelefone());
        spTelefone.setSelection(Integer.parseInt(contato.getTipoTelefone()));
        edtEmail.setText(contato.getEmail());
        spEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        edtEndereco.setText(contato.getEndereco());
        spEndereco.setSelection(Integer.parseInt(contato.getTipoEndereco()));

        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = format.format(contato.getData());
        edtData.setText(dt);

        spData.setSelection(Integer.parseInt(contato.getTipoData()));
        edtGrupo.setText(contato.getGrupo());

    }

    private void inserir() {
        try {

            contato = new Contato();

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());

            contato.setEndereco(edtEndereco.getText().toString());
            contato.setGrupo(edtGrupo.getText().toString());

            contato.setTipoTelefone(String.valueOf(spTelefone.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spEmail.getSelectedItemPosition()));
            contato.setTipoEndereco(String.valueOf(spEndereco.getSelectedItemPosition()));
            contato.setTipoData(String.valueOf(spData.getSelectedItemPosition()));

            contatodao.inserirContato(contato);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR ao inserir dados" + e.getMessage(), Toast.LENGTH_LONG);
        }

    }
/// DAQUI PRA BAIXO É SO A DATA
    private void exibeData() {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        // classe responsavel por exiber janela de dialogo para selecionar uma data
        DatePickerDialog dpd = new DatePickerDialog(this, new selecionaDataListener(), ano, mes, dia);
        dpd.show();
    }

    //classe interna
    private class exibeDataListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                exibeData();
        }
    }

    //setando a data no EditText
    private class selecionaDataListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // getinstance ja cria a instancia do objeto e retorna a  data
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            Date date = calendar.getTime();

            DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String dt = format.format(date);

            edtData.setText(dt);
            contato.setData(date);

        }
    }
}
