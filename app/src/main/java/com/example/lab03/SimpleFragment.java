package com.example.lab03;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {

    private static final int TRANSFORMER = 1;
    private static final int AVENGERS = 0;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    private static final String CHOICE = "choice";
    private Button fButton;

    OnFragmentInteractionListener mListener;

    interface  OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }

    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE,choice);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new ClassCastException(context.toString()+getResources().getString(R.string.exception_message));
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SimpleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimpleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(String param1, String param2) {
        SimpleFragment fragment = new SimpleFragment();
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
        final View root = inflater.inflate(R.layout.fragment_simple, container, false);


        final RadioGroup radioGroup = root.findViewById(R.id.radio_group);

        if (getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice != NONE){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                TextView tv = root.findViewById(R.id.fragment_header);
                switch (index){
                    case AVENGERS:
                        tv.setText(R.string.SELECCION1);
                        mRadioButtonChoice = AVENGERS;
                        break;
                    case TRANSFORMER:
                        tv.setText(R.string.SELECCION2);
                        mRadioButtonChoice = TRANSFORMER;
                        break;
                    default:
                        mRadioButtonChoice=NONE;
                        break;
                }
            }
        });
        fButton = root.findViewById(R.id.button);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRadioButtonChoice(mRadioButtonChoice);
            }
        });
        return root;
    }
}