package com.packages.joe.mathscratchandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.packages.joe.mathscratchandroid.adapter.FileAdapter;

import java.io.File;
import java.util.ArrayList;

public class LoadMathActivity extends AppCompatActivity {
    FileAdapter fa;
    ArrayList<File> files;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load_math);
        File f = getFilesDir();
        files = new ArrayList<File>();
        for (File f1: f.listFiles()) {
            files.add(f1);
        }
        fa = new FileAdapter(this, files);
        listView = (ListView) findViewById(R.id.fileList);
        listView.setAdapter(fa);
    }
}
