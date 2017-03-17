package com.gome.ndkdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gome.ndkdemo.utils.ApkExtract;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Test test;
    File destApk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        test = new Test();
        destApk = new File(Environment.getExternalStorageDirectory(), "dest.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(),"PATCH.patch");

        if (!destApk.exists()){
            try {
                destApk.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("hongyang", "patch = " + patch.exists() + " , " + patch.getAbsolutePath());

        test.test(ApkExtract.extract(this),
                destApk.getAbsolutePath(),
                patch.getAbsolutePath());

        Log.e("hongyang", new File(Environment.getExternalStorageDirectory(), "old").getAbsolutePath() + " , " + destApk.exists());


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doBspatch();
                Toast.makeText(MainActivity.this,"i am a new",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void doBspatch() {
        if (destApk.exists())
            ApkExtract.install(this, destApk.getAbsolutePath());
    }
}
