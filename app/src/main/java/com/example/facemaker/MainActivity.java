package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * @author Taylor Fukumoto
 * @date 28 September 2019
 * MainActivity sets all components to the FaceController class
 */
public class MainActivity extends AppCompatActivity {
    //these seekbars and spinner are used in faceController
    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;
    Spinner spinner;

    //uses instance to reference MainActivity from FaceController class
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face theSV = (Face)findViewById(R.id.surfaceView);

        //Passes FaceController the surface view
        FaceController myFaceListener = new FaceController(theSV);

        //sets FaceController to be the spinner, seekbars, and radio buttons listener
        spinner = (Spinner)findViewById(R.id.hairType);
        spinner.setOnItemSelectedListener(myFaceListener);

        redBar = (SeekBar) findViewById(R.id.redSeek);
        redBar.setOnSeekBarChangeListener(myFaceListener);

        greenBar = (SeekBar) findViewById(R.id.greenSeek);
        greenBar.setOnSeekBarChangeListener(myFaceListener);

        blueBar = (SeekBar) findViewById(R.id.blueSeek);
        blueBar.setOnSeekBarChangeListener(myFaceListener);

        RadioButton hairButton = (RadioButton) findViewById(R.id.hairButton);
        hairButton.setOnClickListener(myFaceListener);

        RadioButton skinButton = (RadioButton) findViewById(R.id.skinButton);
        skinButton.setOnClickListener(myFaceListener);

        RadioButton eyeBotton = (RadioButton) findViewById(R.id.eyeButton);
        eyeBotton.setOnClickListener(myFaceListener);

        //can radio buttons and buttons use the same listener?
        Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(myFaceListener);

        //instance of MainActivity
        instance = this;

    }

    //to get reference to MainActivity from other classes
    public static MainActivity getInstance() {
        return instance;
    }
}
