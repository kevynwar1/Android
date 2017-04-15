package br.com.helloworld.aluno;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListaAlunos extends AppCompatActivity {
    private ListView lvAlunos;
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);
        lvAlunos = (ListView) findViewById(R.id.lvAlunos);
        //avisa a minha listview ao clique longo que é para abrir o menu do metodo ContextMenu
        registerForContextMenu(lvAlunos);
        clickLongo();
        clickcurto();

    }

    // O METODO ABAIXO CRIAR UM MENU AO CLICAR EM UM ITEM DA LISTA
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem ligar = menu.add("Ligar");

        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri discar = Uri.parse("tel:" + aluno.getTelefone());
                intent.setData(discar);

                startActivity(intent);

                return false;
            }
        });
        MenuItem sms = menu.add("Enviar SMS");
        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri discar = Uri.parse("sms:" + aluno.getTelefone());
                intent.setData(discar);

                startActivity(intent);

                return false;
            }
        });
        MenuItem site = menu.add("Navegar no site");
        site.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri local = Uri.parse("http://" + aluno.getSite());
                intent.setData(local);
                startActivity(intent);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
                dao.deletar(aluno);
                dao.close();
                carregalista();

                return false;
            }
        });
        MenuItem mapa = menu.add("Ver no mapa");
        MenuItem email = menu.add("Enviar e-mail");

    }


    // ATUALIZA A LISTA EM TEMPO DE EXECUÇÃO DO APP
    @Override
    protected void onResume() {
        super.onResume();

        carregalista();
    }

    private void carregalista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        // ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
///Amigo inflater transforma as tag XML em Objetos para eu usar na list
        LayoutInflater inflater = getLayoutInflater();
        ListaAlunoadapter adapter = new ListaAlunoadapter(alunos, this);
        lvAlunos.setAdapter(adapter);
    }

    //CRIA UM MENU NA MINHA ACTION BAR (INFLATER)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listagem_alunos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //METODO EXECUTA UMA AÇÃO AO CLICAR EM UM ITEM DO MENU DA ACTIONBAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemClicado = item.getItemId();
        switch (itemClicado) {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, Formulario.class);

                startActivity(irParaFormulario);
                break;
            case R.id.enviar_alunos:

        }

        return super.onOptionsItemSelected(item);
    }

    public void clickLongo() {
        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                aluno = (Aluno) parent.getItemAtPosition(position);
                return false;
            }
        });
    }

    public void clickcurto() {
        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aluno = (Aluno) parent.getItemAtPosition(position);

                Intent intent = new Intent(ListaAlunos.this, Formulario.class);
                intent.putExtra("alunoselecionado", aluno);
                startActivity(intent);
            }
        });
    }


}





