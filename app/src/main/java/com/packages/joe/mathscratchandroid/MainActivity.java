package com.packages.joe.mathscratchandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button begin;
    Button load;
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        img = (ImageView) findViewById(R.id.titleArt);
        img.setMaxHeight(screenHeight/4);
        img.setMaxWidth(screenWidth/4);
        link = (TextView) findViewById(R.id.patreonLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        begin = (Button) findViewById(R.id.startButton);
        final Intent intent = new Intent(this, MathActivity.class);
        Intent intent3 = new Intent(this, CreateNewMathActivity.class);
        //final Intent intent2 = new Intent(this, SetUpDrive.class);
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("File", "NONE");
                editor.commit();
                startActivity(intent3);
            }
        });
        load = findViewById(R.id.load);
        Intent intent4 = new Intent(this, LoadMathActivity.class);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent4);
            }
        });
    }
}
