package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int mRadioButtomChoice = 2;
    int votos[] = new int[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if (isFragmentDisplayed){
                mButton.setText(R.string.close);
                displayNewFragment();
            }
        }

        mButton = (Button)findViewById(R.id.open_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: llamada a un nuevo fragmento / cerrar el Fragment
                if (!isFragmentDisplayed){
                    displayNewFragment();
                }else {
                    closeFragment();
                }
            }
        });
    }

    public void displayNewFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtomChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment!=null){
            fragmentManager.beginTransaction().remove(simpleFragment).commit();
            mButton.setText(R.string.open);
            isFragmentDisplayed=false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtomChoice = choice;
        if (mRadioButtomChoice == 1){
            votos[1] ++;
        }else{
            votos[0]++;
        }
        if (Integer.parseInt(mayor(votos))==0){
            Toast.makeText(this,getResources().getString(R.string.title2)+" Tiene "+votos[1]+"                                        "+getResources().getString(R.string.title1)+" Tiene"+votos[0]+"                                                            El mas votado es: "+ getResources().getString(R.string.title1)+" con "+votos[0]+" Votos",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,getResources().getString(R.string.title2)+" Tiene "+votos[1]+"                                        "+getResources().getString(R.string.title1)+" Tiene"+votos[0]+"                                                            El mas votado es: "+ getResources().getString(R.string.title2)+" con "+votos[1]+" Votos",Toast.LENGTH_LONG).show();
        }

    }

    private String mayor(int[] votos) {
        int numeromayor = votos[0];
        int posicion = 0;
        for (int i=0 ;i<2;i++){
            if (votos[i]>numeromayor){
                posicion   = i;
                numeromayor = votos[i];
            }
        }
        return String.valueOf(posicion);
    }
}