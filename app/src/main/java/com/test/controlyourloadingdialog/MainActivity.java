package com.test.controlyourloadingdialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LoadingPlugin mLoadingPlugin = new LoadingPlugin();

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingPlugin.onActivityCreated(this, savedInstanceState);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingPlugin.showLoading();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadingPlugin.dismissLoading();
                    }
                }, 1000);
            }
        });

    }


}
