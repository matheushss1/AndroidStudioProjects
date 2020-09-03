package com.matheus.calculadora;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText resultado;
    private EditText novoNumero;
    private TextView mostrarOperacao;

    private Double operador1 = null;
    private Double operador2 = null;
    private String operacaoPendente = "=";

    private static final String ESTADO_OPERACAO_PENDENTE = "OperacaoPendente";
    private static final String ESTADO_OPERADOR1 = "Operador1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = (EditText) findViewById(R.id.resultado);
        novoNumero = (EditText) findViewById(R.id.novoNumero);
        mostrarOperacao = (TextView) findViewById(R.id.operacao);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        Button buttonPonto = (Button) findViewById(R.id.buttonPonto);
        Button buttonSomar = (Button) findViewById(R.id.buttonSomar);
        Button buttonSubtrair = (Button) findViewById(R.id.buttonSubtrair);
        Button buttonMultiplicar = (Button) findViewById(R.id.buttonMultiplicar);
        Button buttonDividir = (Button) findViewById(R.id.buttonDividir);
        Button buttonIgual = (Button) findViewById(R.id.buttonIgual);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                novoNumero.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonPonto.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = novoNumero.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    realizarOperacao(doubleValue, op);
                } catch (NumberFormatException e){
                    novoNumero.setText("");
                }
                operacaoPendente = op;
                mostrarOperacao.setText(operacaoPendente);
            }
        };
        buttonIgual.setOnClickListener(opListener);
        buttonDividir.setOnClickListener(opListener);
        buttonMultiplicar.setOnClickListener(opListener);
        buttonSomar.setOnClickListener(opListener);
        buttonSubtrair.setOnClickListener(opListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ESTADO_OPERACAO_PENDENTE, operacaoPendente);
        if(operador1 != null){
            outState.putDouble(ESTADO_OPERADOR1, operador1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operacaoPendente = savedInstanceState.getString(ESTADO_OPERACAO_PENDENTE);
        operador1 = savedInstanceState.getDouble(ESTADO_OPERADOR1);
        mostrarOperacao.setText(operacaoPendente);
    }

    private void realizarOperacao (Double value, String op){
        if(null == operador1){
            operador1 = value;
        } else {
            operador2 = value;
            if(operacaoPendente.equals("=")){
                operacaoPendente = op;
            }
            switch (operacaoPendente){
                case "=":
                    operador1 = operador2;
                    break;
                case "/":
                    if(operador2 == 0){
                        operador1 = 0.0;
                    } else {
                        operador1 /= operador2;
                    }
                    break;
                case "*":
                    operador1 *= operador2;
                    break;
                case "-":
                    operador1 -= operador2;
                    break;
                case "+":
                    operador1 += operador2;
                    break;
            }
        }
        resultado.setText(operador1.toString());
        novoNumero.setText("");
    }

}