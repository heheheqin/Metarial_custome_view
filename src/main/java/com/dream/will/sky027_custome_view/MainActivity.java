package com.dream.will.sky027_custome_view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.dream.will.sky027_custome_view.widget.MyprogressBar;

public class MainActivity extends AppCompatActivity {

    private MyprogressBar pb;
    private RelativeLayout activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    static int pr  = 0;
    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    pr = i;
//                    Log.i("TAG", "run: pr---------" + pr);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(pr);
                        }
                    });
                    SystemClock.sleep(500);
                }
            }
        }).start();
    }

    private void initView() {
        pb = (MyprogressBar) findViewById(R.id.pb);
    }
}
