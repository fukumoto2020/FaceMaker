package com.example.facemaker;
import android.graphics.Paint;

/**
 * @author Taylor Fukumoto
 * @date 28 September 2019
 *
 * FaceController is the "model" class
 * this is purely a data class
 *
 * all data variables are stored here for other classes to use
 */
public class FaceModel {

    protected Paint skinColor = new Paint();
    protected Paint irisColor = new Paint();
    protected Paint hairColor = new Paint();

    //RGB values set from seekBars
    public int Red = 0;
    public int Green = 0;
    public int Blue = 0;

    //keeps track of whether face is randomized or not
    public boolean random = false;

    //keeps track of which radio button (if any) is selected
    public boolean hair = false;
    public boolean eyes = false;
    public boolean skin = false;

    //current hair style - changed with spinner
    public String hairType = "";

}
