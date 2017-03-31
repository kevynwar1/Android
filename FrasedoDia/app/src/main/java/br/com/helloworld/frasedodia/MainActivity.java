package br.com.helloworld.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText edtFrase;
    private Button btNovaFrase;

    private String[] frases = {"Um amigo verdadeiro é aquele que realça as tuas qualidades e que nunca te abandona, mesmo quando descobre os teus defeitos. Bom dia, amigo!",
            "Para a minha vida ser perfeita só precisava que você fizesse parte do meu dia a dia.", "O amanhecer é uma prova divina da existência de Deus e de como Ele nos ama. Bom dia!",
            "Três regras: não prometa nada quando estiver feliz; não responda nada quando estiver irritado; não decida nada quando estiver triste.",
            "Breves segundos de um abraço de bom dia é o carinho perfeito para fazer tudo valer a pena.", "Não desista perante os problemas; lute com mais força porque as dificuldades fazem parte de qualquer caminho.",
            "O sucesso depende mais da sua determinação do que dos acasos da vida ou da sorte.", "Se um dia se sentir triste com a vida não se renda; lute para mudar o que está mal e verá como tudo ficará bem.",
            "Cidades, monumentos e sonhos se constroem dia a dia, um passo de cada vez.", "Hoje é tarde para fazer o que não fez ontem, mas é o dia perfeito para realizar o que acha que pode deixar para amanhã!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtFrase = (EditText) findViewById(R.id.edtFrase);
        btNovaFrase = (Button) findViewById(R.id.btNovaFrase);

        btNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int numero = random.nextInt(frases.length);
                edtFrase.setText(frases[numero]);
            }
        });

    }
}
