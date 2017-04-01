package br.com.helloworld.idadedocachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtIdade;
    private Button descobrir;
    private TextView tvresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIdade = (EditText) findViewById(R.id.edtIdade);
        descobrir = (Button) findViewById(R.id.btDescobrir);
        tvresultado = (TextView) findViewById(R.id.resultado);
    }

    public void clickDescobrir() {
        descobrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String captura = edtIdade.getText().toString();

                if (captura.isEmpty()) {
                    tvresultado.setText("Nenhum número digitado");
                } else {
                    int valordigitado = Integer.parseInt(captura);

                    int resultadofinal = valordigitado * 7;

                    tvresultado.setText("A idade do cachorro em anos Humanos é :" + resultadofinal + "Anos");
                }
            }
        });

    }
}
