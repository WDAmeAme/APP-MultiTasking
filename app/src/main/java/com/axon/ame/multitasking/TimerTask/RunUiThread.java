package com.axon.ame.multitasking.TimerTask;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.R;

import java.util.Timer;
import java.util.TimerTask;

public class RunUiThread extends AppCompatActivity {
    private TextView tvRunUi;
    private int time = 0;
    private int rate = 100;
    private Timer timer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_timtas_runuithread);

        tvRunUi = findViewById(R.id.tvRunUi);
        timer = new Timer("TEMPORIZADOR");
        Tarea tarea = new Tarea();
        timer.scheduleAtFixedRate(tarea, 0, rate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    class Tarea extends TimerTask {
        @Override
        public void run() {
            Runnable cambiarTexto = new CambiarTexto();
            //TODO: EJECUTANDOSE CON RUN UI THREAD
            //runOnUiThread(cambiarTexto);
            //TODO: EJECUTANDOSE CON POST
            //tvRunUi.post(cambiarTexto);
            //TODO: EJECUTANDOSE CON HANDLER
            handler.post(cambiarTexto);
        }
    }

    // METODO SIMPLE DEL PRIMER EJEMPLO
    class CambiarTexto implements Runnable {
        @Override
        public void run() {
            time = time + rate;
            String texto = "TEMPORIZADOR\nRate = " + rate + "\nt = " + time;
            tvRunUi.setText(texto);
            tvRunUi.setTypeface(null, Typeface.BOLD);
            tvRunUi.setTextSize(30);
        }
    }

    // METODO AVANZADO DEL SEGUNDO EJEMPLO
}
