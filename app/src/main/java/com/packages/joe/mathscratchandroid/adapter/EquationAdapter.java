package com.packages.joe.mathscratchandroid.adapter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.packages.joe.mathscratchandroid.R;
import com.packages.joe.mathscratchandroid.equation.Equation;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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

        final Equation equation = (Equation) getItem(position);
        equationText.setText(equation.getEquation());
        final TextView sorry = (TextView) rowView.findViewById(R.id.wrongAnswer);
        final EditText answer = (EditText) rowView.findViewById(R.id.answer);
        answer.setText(equation.getGuessedAnswer());
        final ImageView check = (ImageView) rowView.findViewById(R.id.checkMark);
        if(equation.isCorrect()) check.setVisibility(View.VISIBLE);
        else check.setVisibility(View.INVISIBLE);
        if (equation.isWrong()) sorry.setVisibility(View.VISIBLE);
        else  sorry.setVisibility(View.INVISIBLE);
        answer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        double d = Double.parseDouble(answer.getText().toString());
                        System.out.println(equation.getAnswer());
                        try {
                            if (!answer.getText().toString().matches("-?\\d+(\\.\\d+)?"))
                                return true;
                            if (equation.getAnswer().compareTo(BigDecimal.valueOf(d)) == 0) {
                                check.setVisibility(View.VISIBLE);
                                answer.setFocusable(false);
                                sorry.setVisibility(View.INVISIBLE);
                                equation.setCorrect(true);
                                equation.setWrong(false);
                                equation.setGuessedAnswer(answer.getText().toString());

                                File file = context.getFilesDir();
                                File[] files = file.listFiles();
                                int sheetNum = 1;
                                for (File f: files) {
                                    if(f.getName().contains("MathScratch")){
                                        sheetNum++;
                                    }
                                }
                                String filename = "MathScratch" + Integer.toString(sheetNum)+".txt";
                                String fileContents = "";
                                int i = 1;
                                for (Equation e: items) {

                                    fileContents += "<Equation "  + Integer.toString(i) +">\n";
                                    fileContents += e.getEquation() + " = " + e.getGuessedAnswer() + " \n";
                                    i++;
                                }
                                FileOutputStream outputStream;

                                try {
                                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                                    outputStream.write(fileContents.getBytes());
                                    outputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return true;
                            } else {
                                sorry.setVisibility(View.VISIBLE);
                                equation.setWrong(true);
                                equation.setGuessedAnswer(answer.getText().toString());
                                equation.setCorrect(false);
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    }
                    return false;
                } catch (NumberFormatException e) {
                    return true;
                }
            }
        });
        if(equation.getDifficulty() == Equation.Difficulty.EASY){
            //get image for easy
        }
        return rowView;
    }
}
