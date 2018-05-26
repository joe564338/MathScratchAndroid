package com.packages.joe.mathscratchandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.File;

public class CreateNewMathActivity extends AppCompatActivity {
    Button addSub;
    Button mult;
    RadioButton easy;
    RadioButton hard;
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_math);
        init();
    }
    private void init(){
        File file = getFilesDir();
        File[] files = file.listFiles();
        int sheetNum = 1;
        for (File f: files) {
            if(f.getName().contains("MathScratch")){
                sheetNum++;
            }
        }
        String filename = "MathScratch" + Integer.toString(sheetNum)+".txt";
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        preferencesEditor.putString("Difficulty", "EASY");
        addSub = (Button) findViewById(R.id.addSubPractice);
        mult = (Button) findViewById(R.id.multPractice);
        easy = (RadioButton) findViewById(R.id.cupcake);
        hard = (RadioButton) findViewById(R.id.tough);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hard.setChecked(false);
                preferencesEditor.putString("Difficulty", "EASY");
                preferencesEditor.commit();
                easy.setChecked(true);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easy.setChecked(false);
                hard.setChecked(true);
                preferencesEditor.putString("Difficulty", "HARD");
                preferencesEditor.commit();
            }
        });
        Intent intent = new Intent(this, MathActivity.class);
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesEditor.putString("SheetType" , "ADDSUB");
                preferencesEditor.commit();
                preferencesEditor.putString("FileName", filename);
                preferencesEditor.commit();
                startActivity(intent);
            }
        });
        Intent intent2 = new Intent(this, DecideMultiple.class);
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesEditor.putString("SheetType", "MULT");
                preferencesEditor.commit();
                preferencesEditor.putString("FileName", filename);
                preferencesEditor.commit();
                startActivity(intent2);
            }
        });
    }
}
