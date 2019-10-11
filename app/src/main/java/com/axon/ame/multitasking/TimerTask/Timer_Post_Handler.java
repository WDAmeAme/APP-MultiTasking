package com.axon.ame.multitasking.TimerTask;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.R;

import java.util.Timer;
import java.util.TimerTask;

public class Timer_Post_Handler extends AppCompatActivity {
    private Handler handler = new Handler();
    private TextView tv1, tv2, tv3;
    private int inicio = 0;
    int aumento = 100;
    Timer timer1, timer2, timer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tim_pos_han);
        tv1 = (TextView) findViewById(R.id.tvRun3_1);
        tv2 = (TextView) findViewById(R.id.tvRun3_2);
        tv3 = (TextView) findViewById(R.id.tvRun3_3);

        int factor1 = 1;
        int factor2 = 2;
        int factor3 = 3;

        timer1 = new Timer("TEMPORIZADOR1");
        Tarea1 tarea1 = new Tarea1(factor1, tv1);
        timer1.scheduleAtFixedRate(tarea1, 0, aumento);

        timer2 = new Timer("TEMPORIZADOR2");
        Tarea2 tarea2 = new Tarea2(factor2, tv2);
        timer2.scheduleAtFixedRate(tarea2, 0, aumento);

        timer3 = new Timer("TEMPORIZADOR3");
        Tarea3 tarea3 = new Tarea3(factor3, tv3);
        timer3.scheduleAtFixedRate(tarea3, 0, aumento);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer1.cancel();
        timer2.cancel();
        timer3.cancel();
    }

    class Tarea1 extends TimerTask {
        int factor;
        TextView tvTarea;

        public Tarea1(int factor, TextView tvTarea) {
            this.factor = factor;
            this.tvTarea = tvTarea;
        }

        @Override
        public void run() {
            Runnable cambiarTexto1 = new CambiarTexto(factor, tvTarea);
            runOnUiThread(cambiarTexto1);
        }
    }

    class Tarea2 extends TimerTask {
        int factor;
        TextView tvTarea;

        public Tarea2(int factor, TextView tvTarea) {
            this.factor = factor;
            this.tvTarea = tvTarea;
        }

        @Override
        public void run() {
            Runnable cambiarTexto2 = new CambiarTexto(factor, tvTarea);
            tvTarea.post(cambiarTexto2);
        }
    }

    class Tarea3 extends TimerTask {
        int factor;
        TextView tvTarea;

        public Tarea3(int factor, TextView tvTarea) {
            this.factor = factor;
            this.tvTarea = tvTarea;
        }

        @Override
        public void run() {
            Runnable cambiarTexto3 = new CambiarTexto(factor, tvTarea);
            handler.post(cambiarTexto3);
        }
    }

    class CambiarTexto implements Runnable {
        private int red;
        private int green;
        private int blue;
        private int factor;
        private TextView textoCambia;

        public CambiarTexto(int factor, TextView textoCambia) {
            this.factor = factor;
            this.textoCambia = textoCambia;
        }

        @Override
        public void run() {
            inicio = inicio + aumento;
            red = (inicio / factor) % 255;
            green = (int) ((0.75 * inicio / factor) % 255);
            blue = (int) ((0.60 * inicio / factor) % 255);

            String texto = "TEMP0RIZADOR\nrate= " + aumento + "\nt= " + inicio;
            textoCambia.setText(texto);
            textoCambia.setTypeface(null, Typeface.BOLD);
            textoCambia.setTextSize(30);
            textoCambia.setTextColor(Color.rgb(red, green, blue));
        }
    }
}
