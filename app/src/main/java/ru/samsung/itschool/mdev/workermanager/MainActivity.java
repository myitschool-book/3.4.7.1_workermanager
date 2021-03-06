package ru.samsung.itschool.mdev.workermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MY_TAG";
    private Button bStart, btJustDoIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = findViewById(R.id.btStart);
        btJustDoIt= findViewById(R.id.btJustDoIt);
        // Устанавливаем обработчик на кнопку "Начать в потоке"
        btJustDoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneTimeWorkRequest work =
                        new OneTimeWorkRequest.Builder(MyWorker.class)
                                .build();
                WorkManager.getInstance(getApplicationContext()).enqueue(work);

            }
        });

        // Устанавливаем обработчик на кнопку "Начать не в потоке"
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Work is in progress");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "Work finished");
            }
        });

    }
}