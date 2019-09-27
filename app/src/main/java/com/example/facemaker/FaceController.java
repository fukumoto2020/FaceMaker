package com.example.facemaker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;


public class FaceController extends Activity implements AdapterView.OnItemSelectedListener, android.widget.SeekBar.OnSeekBarChangeListener, View.OnClickListener{
    private Face myFace;
    private FaceModel myFaceModel;


    private static final String TAG = "MyActivity";

//surface view was passed into FaceController in MainActivity
    public FaceController(Face face){
        myFace = face;
        myFaceModel = myFace.getFaceModel();
       // myMain = new MainActivity();
    }

    //Spinner stuff --------------------------------------------------------
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);

    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //Spinner dropdown was populated from strings.xml file

        //sets chosen spinner item to the hairType string
        myFaceModel.hairType = (String)parent.getItemAtPosition(pos);

        //redraws face after hair type is selected
        myFace.invalidate();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //Radio and button stuff --------------------------------------------------------
    public void onClick(View v) {
        //use if statements to check which radio button was checked
        if (v.getId() == R.id.hairButton) {
            myFaceModel.hair = true;
            myFaceModel.skin = false;
            myFaceModel.eyes = false;

            int red = Color.red(myFaceModel.hairColor.getColor());
            int green = Color.green(myFaceModel.hairColor.getColor());
            int blue = Color.blue(myFaceModel.hairColor.getColor());


           //https://stackoverflow.com/questions/17315842/how-to-call-a-method-in-mainactivity-from-another-class
            MainActivity.getInstance().redBar.setProgress(red);
            MainActivity.getInstance().greenBar.setProgress(green);
            MainActivity.getInstance().blueBar.setProgress(blue);


            myFace.invalidate();

           // this.redBar.setProgress(Color.red(myFace.getHairPaint()));
        }
        else if (v.getId() == R.id.skinButton) {
            myFaceModel.skin = true;
            myFaceModel.hair = false;
            myFaceModel.eyes = false;

            int red = Color.red(myFaceModel.skinColor.getColor());
            int green = Color.green(myFaceModel.skinColor.getColor());
            int blue = Color.blue(myFaceModel.skinColor.getColor());

            MainActivity.getInstance().redBar.setProgress(red);
            MainActivity.getInstance().greenBar.setProgress(green);
            MainActivity.getInstance().blueBar.setProgress(blue);

            myFace.invalidate();
        }
        else if(v.getId() == R.id.eyeButton){
            myFaceModel.eyes = true;
            myFaceModel.hair = false;
            myFaceModel.skin = false;

            int red = Color.red(myFaceModel.irisColor.getColor());
            int green = Color.green(myFaceModel.irisColor.getColor());
            int blue = Color.blue(myFaceModel.irisColor.getColor());

            MainActivity.getInstance().redBar.setProgress(red);
            MainActivity.getInstance().greenBar.setProgress(green);
            MainActivity.getInstance().blueBar.setProgress(blue);

            myFace.invalidate();
        }
        else if(v.getId() == R.id.randomButton){
            myFace.randomize();
            myFace.invalidate();
        }
       /* else{
            myFaceModel.eyes = false;
            myFaceModel.hair = false;
            myFaceModel.skin = false;
        }*/

    }

    //Seekbar stuff --------------------------------------------------------------
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        //uses switch statement to see which seekbar was changed
        switch (seekBar.getId()) {
            case R.id.seekBar:
                myFaceModel.Red = progress;
                myFace.invalidate();
                break;

            case R.id.seekBar2:
                Log.i(TAG, "seek bar green is at: " + progress);
                myFaceModel.Green = progress;
                myFace.invalidate();
                break;
            case R.id.seekBar3:
                Log.i(TAG, "seek bar blue is at: " + progress);
                myFaceModel.Blue = progress;
                myFace.invalidate();
                break;
        }

       // myFace.invalidate();
    }

    public void onStartTrackingTouch(SeekBar seekBar){}

    public void onStopTrackingTouch(SeekBar seekBar){}


}
