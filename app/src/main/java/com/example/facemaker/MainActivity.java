package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face theSV = (Face)findViewById(R.id.surfaceView);

        //Passes FaceController the surface view
        FaceController myFaceListener = new FaceController(theSV);

        //sets FaceController to be the spinner, seekbars, and radio buttons listener
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(myFaceListener);

        redBar = (SeekBar) findViewById(R.id.seekBar);
        redBar.setOnSeekBarChangeListener(myFaceListener);

        greenBar = (SeekBar) findViewById(R.id.seekBar2);
        greenBar.setOnSeekBarChangeListener(myFaceListener);

        blueBar = (SeekBar) findViewById(R.id.seekBar3);
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

        instance = this;

    }

    public static MainActivity getInstance() {
        return instance;
    }
}
