package com.axon.ame.multitasking.VersionesRecientes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.axon.ame.multitasking.R;

public class WorkerMarshmallow extends AppCompatActivity {
    public static final String MESSAGE_STATUS = "message_status";
    private TextView tvStatus;
    private WorkManager mWorkManager;
    private OneTimeWorkRequest mRequest;
    private Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wor_mar);
        mWorkManager = WorkManager.getInstance();
        mRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).build();
        tvStatus = findViewById(R.id.tvWorkStatus);
        btSend = findViewById(R.id.btWorkSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkManager.enqueue(mRequest);
            }
        });

        mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    tvStatus.append(state.toString() + "\n");

                }
            }
        });
    }
}
