package com.axon.ame.multitasking.VersionesRecientes;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.axon.ame.multitasking.R;

public class JobSchedulerAct extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EjemploJobService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_job_sch);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btJSIniciar:
                break;
            case R.id.btJSTerminar:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View v) {
        Log.e(TAG, "Instanciado");
        ComponentName componentName = new ComponentName(this, JobSchedulerTask.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(false)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resCodigo = scheduler.schedule(info);
        if (resCodigo == JobScheduler.RESULT_SUCCESS) {
            Log.e(TAG, "Job Schedule");
        } else {
            Log.e(TAG, "Job scheduling failed");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.e(TAG, "Job cancelado");
    }
}