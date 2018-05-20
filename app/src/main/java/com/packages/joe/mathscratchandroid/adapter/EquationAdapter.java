package com.packages.joe.mathscratchandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.packages.joe.mathscratchandroid.R;
import com.packages.joe.mathscratchandroid.equation.Equation;

import java.util.ArrayList;

public class EquationAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Equation> items;
    public EquationAdapter(Context context, ArrayList<Equation> items){
        this.context = context;
        this.items = items;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.math_equation, parent, false);
        TextView equationText = (TextView) rowView.findViewById(R.id.equation);
        ImageView difficultyImage = (ImageView) rowView.findViewById(R.id.difficultyImage);
        Equation equation = (Equation) getItem(position);
        equationText.setText(equation.getEquation());
        if(equation.getDifficulty() == Equation.Difficulty.EASY){
            //get image for easy
        }
        return rowView;
    }
}
