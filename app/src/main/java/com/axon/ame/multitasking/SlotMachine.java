package com.axon.ame.multitasking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SlotMachine extends AppCompatActivity implements View.OnClickListener {
    private int dificultad = 100;
    private int columna;
    private boolean[] continuar = {false, false, false};
    private TextView tv;
    private TextView txtDificultad;
    private int[] fotoId = {
            R.drawable.sm1,
            R.drawable.sm2,
            R.drawable.sm3,
            R.drawable.sm4,
            R.drawable.sm5,
            R.drawable.sm6,
            R.drawable.sm7,
            R.drawable.sm8,
            R.drawable.sm9,
    };
    private int[][] secuencia = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8},
            {8, 7, 6, 5, 4, 3, 2, 1, 0},
            {4, 5, 3, 2, 6, 7, 1, 0, 8}
    };
    private ImageView[][] iVs = new ImageView[3][3];
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slot_mac);
        tv = findViewById(R.id.texto);
        txtDificultad = findViewById(R.id.dificultad);

        iVs[0][0] = findViewById(R.id.iv11);
        iVs[1][0] = findViewById(R.id.iv21);
        iVs[2][0] = findViewById(R.id.iv31);
        iVs[0][1] = findViewById(R.id.iv12);
        iVs[1][1] = findViewById(R.id.iv22);
        iVs[2][1] = findViewById(R.id.iv32);
        iVs[0][2] = findViewById(R.id.iv13);
        iVs[1][2] = findViewById(R.id.iv23);
        iVs[2][2] = findViewById(R.id.iv33);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View boton) {
        int btnId = boton.getId();
        if (btnId == R.id.btn4 | btnId == R.id.btn5 | btnId == R.id.btn6) {
            if (btnId == R.id.btn4) dificultad = dificultad + 10;
            if (btnId == R.id.btn5) dificultad = dificultad + 200;
            if (btnId == R.id.btn6) dificultad = dificultad - 10;
            txtDificultad.setText("Dificultad: " + dificultad);
        } else {
            if (btnId == R.id.btn1) columna = 0;
            if (btnId == R.id.btn2) columna = 1;
            if (btnId == R.id.btn3) columna = 2;

            continuar[columna] = !continuar[columna];
            if (continuar[columna]) {
                new MyAsyncTask().execute(columna);
                ((TextView) boton).setText("Detener");
            } else {
                ((TextView) boton).setText("Continuar");
            }
        }
    }

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... parametro) {
            int columna = parametro[0];
            while (continuar[columna]) {
                int elemento1 = secuencia[columna][0];
                for (int t = 0; t < 8; t++) {
                    secuencia[columna][t] = secuencia[columna][t + 1];
                }
                secuencia[columna][8] = elemento1;
                try {
                    Thread.sleep(Math.abs(dificultad));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(columna);
            }
            return "Stop columna " + (columna + 1);
        }

        @Override
        protected void onProgressUpdate(Integer... progreso) {
            int columna = progreso[0];
            for (int i = 0; i < 3; i++) {
                iVs[i][columna].setImageResource(fotoId[secuencia[columna][i]]);
            }
        }

        @Override
        protected void onPostExecute(String resultado) {
            if (continuar[0] == false && continuar[1] == false && continuar[2] == false) {
                if (secuencia[0][1] == secuencia[1][1] && secuencia[0][1] == secuencia[2][1]) {
                    tv.setText("¡¡¡PREMIO!!!");
                } else tv.setText("Suerte para la próxima");
            } else tv.setText("" + resultado);
        }
    }
}