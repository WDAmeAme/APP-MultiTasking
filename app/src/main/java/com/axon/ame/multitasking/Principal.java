package com.axon.ame.multitasking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.TimerTask.AsyncTaskPer;
import com.axon.ame.multitasking.TimerTask.RunUiThread;
import com.axon.ame.multitasking.TimerTask.Timer_Post_Handler;

public class Principal extends AppCompatActivity implements View.OnClickListener {
    private Button btnRunUi;
    private Button btPrinTimPosHan;
    private Button btPrinTimAsync;
    private Button btPrinSMAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);
        btnRunUi = findViewById(R.id.btPrinRunUi);
        btPrinTimPosHan = findViewById(R.id.btPrinTimPosHan);
        btPrinTimAsync = findViewById(R.id.btPrinTimAsync);
        btPrinSMAsync = findViewById(R.id.btPrinSMAsync);
        btnRunUi.setOnClickListener(this);
        btPrinTimPosHan.setOnClickListener(this);
        btPrinTimAsync.setOnClickListener(this);
        btPrinSMAsync.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPrinRunUi:
                Intent irRunUI = new Intent(Principal.this, RunUiThread.class);
                startActivity(irRunUI);
                break;
            case R.id.btPrinTimPosHan:
                Intent irRunTres = new Intent(Principal.this, Timer_Post_Handler.class);
                startActivity(irRunTres);
                break;
            case R.id.btPrinTimAsync:
                Intent irRunAsyn = new Intent(Principal.this, AsyncTaskPer.class);
                startActivity(irRunAsyn);
                break;
            case R.id.btPrinSMAsync:
                Intent irRunSMAsyn = new Intent(Principal.this, SlotMachine.class);
                startActivity(irRunSMAsyn);
                break;
        }
    }
}
