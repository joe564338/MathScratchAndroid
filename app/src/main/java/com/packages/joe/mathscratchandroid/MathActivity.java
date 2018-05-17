package com.packages.joe.mathscratchandroid;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class MathActivity extends FragmentActivity implements ScratchFragment.OnFragmentInteractionListener, MathFragment.OnFragmentInteractionListener{
    FragmentTransaction transaction;
    FrameLayout leftContainer;
    FrameLayout rightContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        leftContainer = (FrameLayout) findViewById(R.id.mathContainer);
        rightContainer = (FrameLayout) findViewById(R.id.drawingContainer);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        leftContainer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth*2/5, screenHeight - 50));
        rightContainer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth*3/5, screenHeight - 50));
        ScratchFragment scratchFragment = new ScratchFragment();
        MathFragment mathFragment = new MathFragment();
        FragmentManager manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.mathContainer, mathFragment, "math_frag");
        transaction.add(R.id.drawingContainer, scratchFragment, "draw_frag");
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
