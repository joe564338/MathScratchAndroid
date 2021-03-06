package com.packages.joe.mathscratchandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.packages.joe.mathscratchandroid.view.DrawingView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScratchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScratchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * Drawing from https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-essential-functionality--mobile-19328*/
public class ScratchFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private float smallBrush, mediumBrush, largeBrush;
    private DrawingView drawView;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScratchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScratchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScratchFragment newInstance(String param1, String param2) {
        ScratchFragment fragment = new ScratchFragment();
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
        return inflater.inflate(R.layout.fragment_scratch, container, false);
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
    ImageButton paint0, paint1, paint2, paint3, paint4, paint5, paint6, paint7, paint8, paint9, paint10, paint11;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawView = (DrawingView) view.findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) view.findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        smallBrush = getResources().getInteger(R.integer.small_size) - 5;
        mediumBrush = getResources().getInteger(R.integer.medium_size) - 10;
        largeBrush = getResources().getInteger(R.integer.large_size) - 10;
        paint0 = (ImageButton) view.findViewById(R.id.paint0);
        paint0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint1 = (ImageButton) view.findViewById(R.id.paint1);
        paint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint2 = (ImageButton) view.findViewById(R.id.paint2);
        paint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint3 = (ImageButton) view.findViewById(R.id.paint3);
        paint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint4 = (ImageButton) view.findViewById(R.id.paint4);
        paint4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint5 = (ImageButton) view.findViewById(R.id.paint5);
        paint5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint6 = (ImageButton) view.findViewById(R.id.paint6);
        paint6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint7 = (ImageButton) view.findViewById(R.id.paint7);
        paint7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint8 = (ImageButton) view.findViewById(R.id.paint8);
        paint8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint9 = (ImageButton) view.findViewById(R.id.paint9);
        paint9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint10 = (ImageButton) view.findViewById(R.id.paint10);
        paint10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        paint11 = (ImageButton) view.findViewById(R.id.paint11);
        paint11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintClicked(v);
            }
        });
        drawBtn = (ImageButton) view.findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);
        eraseBtn = (ImageButton) view.findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);
        newBtn = (ImageButton) view.findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

    }
    public void paintClicked(View view){
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }
    }

    @Override
    public void onClick(View v) {
        final Context context = getContext();
        if(v.getId()==R.id.draw_btn){
            //draw button clicked
            final Dialog brushDialog = new Dialog(context);
            brushDialog.setTitle("Tip Size");
            brushDialog.setContentView(R.layout.size_chooser);
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
        else if(v.getId()==R.id.erase_btn){
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(context);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.size_chooser);
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
        else if(v.getId()==R.id.new_btn){
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(context);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Clear Scratch Page? (You cannot get it back)");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }
    }
}
