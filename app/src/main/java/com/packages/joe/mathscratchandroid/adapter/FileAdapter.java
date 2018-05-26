package com.packages.joe.mathscratchandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.packages.joe.mathscratchandroid.MathActivity;
import com.packages.joe.mathscratchandroid.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<File> items;
    public FileAdapter(Context context, ArrayList<File> items){
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
        View rowView = inflater.inflate(R.layout.file_row, parent, false);
        TextView text = (TextView) rowView.findViewById(R.id.fileName);
        File f = (File)getItem(position);
        text.setText(f.getName());
        SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MathActivity.class);
                editor.putString("File", f.getName());
                editor.commit();
                editor.putString("FileName", f.getName());
                editor.commit();
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
