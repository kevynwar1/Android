package br.com.helloworld.calculadora;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtValor1;
    private EditText edtValor2;
    private Button btSomar;
    private Button btSubtrair;
    private Button btMultiplicar;
    private Button btDividir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtValor1 = (EditText) findViewById(R.id.edtValor1);
        edtValor2 = (EditText) findViewById(R.id.edtValor2);
        btSomar = (Button) findViewById(R.id.btSomar);
        btSubtrair = (Button) findViewById(R.id.btSubtrair);
        btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
        btDividir = (Button) findViewById(R.id.btDividir);
        clickSomar();
        clickSubtrair();
        clickMultiplicar();
        clickDividir();
    }

    public void clickSomar() {
        btSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtValor1 != null && edtValor2 != null) {
                    double valor1 = Double.parseDouble(edtValor1.getText().toString());
                    double valor2 = Double.parseDouble(edtValor2.getText().toString());

                    double resultado = valor1 + valor2;
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("O Resultado da soma é: " + resultado);
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("Um dos campos está vazio !!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();

                }
            }
        });

    }

    public void clickSubtrair() {
        btSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtValor1 != null && edtValor2 != null) {
                    double valor1 = Double.parseDouble(edtValor1.getText().toString());
                    double valor2 = Double.parseDouble(edtValor2.getText().toString());

                    double resultado = valor1 - valor2;

                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("O Resultado é: " + resultado);
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("Um dos campos está vazio !!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });
    }

    public void clickMultiplicar() {
        btMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtValor1 != null && edtValor2 != null) {
                    double valor1 = Double.parseDouble(edtValor1.getText().toString());
                    double valor2 = Double.parseDouble(edtValor2.getText().toString());

                    double resultado = valor1 * valor2;

                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("O Resultado é: " + resultado);
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("Um dos campos está vazio !!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });

    }

    public void clickDividir() {
        btDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtValor1 != null && edtValor2 != null) {
                    double valor1 = Double.parseDouble(edtValor1.getText().toString());
                    double valor2 = Double.parseDouble(edtValor2.getText().toString());

                    double resultado = valor1 / valor2;

                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("O Resultado é: " + resultado);
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("Um dos campos está vazio !!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });
    }

}
