package com.packages.joe.mathscratchandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DecideMultiple extends AppCompatActivity {
    EditText multiple;
    Button start;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_multiple);
        init();
    }
    private void init(){
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        editor = preferences.edit();
        multiple = (EditText) findViewById(R.id.multiple);
        start = (Button) findViewById(R.id.startMult);
        multiple.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    editor.putInt("Multiple", Integer.parseInt(multiple.getText().toString()));
                    editor.commit();
                }
                return false;
            }
        });
        Intent intent = new Intent(this, MathActivity.class);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("STARTING MULT");
                editor.putInt("Multiple", Integer.parseInt(multiple.getText().toString()));
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
