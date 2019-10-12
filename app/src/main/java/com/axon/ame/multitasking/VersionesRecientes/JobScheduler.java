package com.axon.ame.multitasking.VersionesRecientes;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.R;

public class JobScheduler extends AppCompatActivity {
    private Button btJSIniciar;
    private Button btJSTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_job_sch);
        btJSIniciar = findViewById(R.id.btJSIniciar);
        btJSTerminar = findViewById(R.id.btJSTerminar);
    }
}