package com.axon.ame.multitasking.TimerTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.R;

public class AsyncTaskPer extends AppCompatActivity {
    private TextView tvAsyTas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_asy_tas_per);
        tvAsyTas = findViewById(R.id.tvAsyTas);
        // TODO: Execute(número de veces que se ejecutará);
        new MiAsyncTask().execute(70);
    }

    //TODO: FUNCION A EJECUTAR | MIENTRAS SE EJECUTA | RESULTADO
    class MiAsyncTask extends AsyncTask<Integer, Integer, String> {
        // TODO: Lógica
        @Override
        protected String doInBackground(Integer... parametro) {
            int maximo = parametro[0];
            for (int t = 0; t < maximo; t++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(t);
            }
            return "Fin";
        }

        // TODO: Progreso
        @Override
        protected void onProgressUpdate(Integer... progreso) {
            int contador = progreso[0];
            String texto = "Contador: " + contador;
            tvAsyTas.setText(texto);
            tvAsyTas.setTextSize(contador);
        }

        // TODO: Resultado
        @Override
        protected void onPostExecute(String resultado) {
            tvAsyTas.append("\n" + resultado);
        }
    }
}