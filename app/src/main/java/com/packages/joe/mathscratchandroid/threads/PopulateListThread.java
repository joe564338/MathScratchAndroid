package com.packages.joe.mathscratchandroid.threads;

import android.content.Context;
import android.widget.ListView;

import com.packages.joe.mathscratchandroid.adapter.EquationAdapter;
import com.packages.joe.mathscratchandroid.equation.Equation;

import java.util.ArrayList;

public class PopulateListThread extends Thread{
    ArrayList<Equation> equations;
    EquationAdapter adapter;
    Context context;
    ListView listView;
    public PopulateListThread(ArrayList<Equation> equations, Context context, ListView listView){
        this.equations = equations;
        this.context = context;
        this.listView = listView;
    }
    public void run(){
        this.adapter = new EquationAdapter(context, equations);
        listView.setAdapter(adapter);

    }
}
