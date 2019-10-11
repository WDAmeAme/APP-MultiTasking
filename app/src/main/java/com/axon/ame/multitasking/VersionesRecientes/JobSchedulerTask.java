package com.axon.ame.multitasking.VersionesRecientes;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerTask extends JobService {
    private static final String TAG = "EjemploJobService";
    private boolean jobCancelar = false;

    @Override
    public boolean onStartJob(JobParameters parametros) {
        Log.e(TAG, "Job iniciado");
        ejecucion(parametros);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters parametros) {
        Log.e(TAG, "Job cancelado antes de completar");
        jobCancelar = true;
        return true;
    }

    public void ejecucion(final JobParameters parametros) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.e(TAG, "run: " + i);
                    if (jobCancelar) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.e(TAG, "Job finalizado");
                jobFinished(parametros, false);

            }
        }).start();
    }
}
