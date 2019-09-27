package com.example.facemaker;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class FaceModel {
   // public int skinColor;
    //public int eyeColor;
    //public int hairColor;

    protected Paint skinColor = new Paint();
    protected Paint irisColor = new Paint();
    protected Paint hairColor = new Paint();


    public int Red = 0;
    public int Green = 0;
    public int Blue = 0;


    public boolean hair = false;
    public boolean eyes = false;
    public boolean skin = false;

    public String hairType = "";

}
