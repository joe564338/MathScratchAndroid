package com.packages.joe.mathscratchandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.drive.DriveClient;
import com.packages.joe.mathscratchandroid.equation.Equation;
import com.packages.joe.mathscratchandroid.threads.PopulateListThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MathFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MathFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MathFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;
    private DriveClient mDriveClient;
    private OnFragmentInteractionListener mListener;

    public MathFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MathFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MathFragment newInstance(String param1, String param2) {
        MathFragment fragment = new MathFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    SharedPreferences preferences;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        listView = (ListView) view.findViewById(R.id.mathQuestions);
        ArrayList<Equation> equations = new ArrayList<Equation>();
        if(preferences.getString("File", "NONE").equals("NONE")) {
            if (preferences.getString("SheetType", "ADDSUB").equals("ADDSUB") && preferences.getString("Difficulty", "EASY").equals("EASY")) {
                for (int i = 0; i < 20; i++) {
                    equations.add(new Equation(Equation.Difficulty.EASY, 2, false));
                }
            } else if (preferences.getString("SheetType", "ADDSUB").equals("ADDSUB") && preferences.getString("Difficulty", "EASY").equals("HARD")) {
                for (int i = 0; i < 20; i++) {
                    equations.add(new Equation(Equation.Difficulty.HARD, 2, false));
                }
            } else if (preferences.getString("SheetType", "ADDSUB").equals("MULT")) {
                System.out.println("MULT");
                for (int i = 0; i < 12; i++) {
                    System.out.println("Mult" + i);
                    Equation e = new Equation(true, preferences.getInt("Multiple", 0), i + 1);
                    equations.add(e);
                }
            } else {
                System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        } else{
            BufferedReader br = null;
            FileReader fr = null;

            try {

                String fileName = preferences.getString("File", "NONE");
                File file = getActivity().getFilesDir();
                File[] files = file.listFiles();
                File fileToLoad = null;
                for (File f: files) {
                    if(f.getName().equals(fileName)){
                        fileToLoad = f;
                    }
                }
                fr = new FileReader(fileToLoad);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    if(sCurrentLine.contains("Equation")){
                        sCurrentLine = br.readLine();
                        String[] parts = sCurrentLine.split("=");
                        for(int i = 0; i < parts.length; i++){
                            System.out.println(parts[i]);
                            parts[i].replaceAll("=", "");
                        }
                        String equation = parts[0];
                        String guessedAnswer = "";

                        try{
                            parts[1] = parts[1].replaceAll("\\s+", "");
                            System.out.println(parts[1]);
                            guessedAnswer = Integer.toString(Integer.parseInt(parts[1]));
                        } catch (Exception e){
                            System.out.println("NO ANSWER");
                            guessedAnswer = "";
                        }

                        Equation e = new Equation(equation, guessedAnswer);
                        equations.add(e);
                    }
                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null)
                        br.close();

                    if (fr != null)
                        fr.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }
        }
        PopulateListThread populateListThread = new PopulateListThread(equations, getContext(), listView);
        populateListThread.run();
    }

}
