package com.example.facemaker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import java.util.Random;

public class Face extends SurfaceView {
    private static final String TAG = "MyActivity";

    private FaceModel myFaceModel = new FaceModel();


    //protected int skinColor;
   // protected int eyeColor;
   // protected int hairColor;
  //  protected int hairStyle;

    //protected Paint skin = new Paint();
    protected Paint whiteEye = new Paint();
    protected Paint pupilColor = new Paint();
   // protected Paint irisColor = new Paint();
    //protected Paint hairPaint = new Paint();

    protected Random random = new Random();

    public Face(Context context, AttributeSet attrs){

        super(context,attrs);
        setWillNotDraw(false);

        //sets whiteEye to be white and pupil to be black - these colors do not change
        this.whiteEye.setColor(Color.WHITE);
        this.pupilColor.setColor(Color.BLACK);

        //calls randomize to initialize face with random skin, hair, and eye color and random hairstyle
        this.randomize();
    }

    public FaceModel getFaceModel()
    {
        return myFaceModel;
    }


    //initializes all the variables to randomly selected valid values. This method should be called by the constructor
    public void randomize(){
        myFaceModel.hair = false;
        myFaceModel.skin = false;
        myFaceModel.eyes = false;

        //chooses random skin, iris, and hair color
        myFaceModel.skinColor.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        myFaceModel.irisColor.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        myFaceModel.hairColor.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));


        //generates random number between 1 and 3 to randomly assign one of three hairstyles
        int randomHairstyle = random.nextInt(3)+1;

        if(randomHairstyle == 1){
            myFaceModel.hairType = "Bangs";
        }
        else if(randomHairstyle == 2){
            myFaceModel.hairType = "Mohawk";
        }
        else if(randomHairstyle == 3){
            myFaceModel.hairType = "Buzzcut";
        }
    }

    //draws this Face object upon a given Canvas
    @Override
    public void onDraw(Canvas canvas){
        //should try to change face to depend on screen size- test this
       /* int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        Log.e("Width", "" + screenWidth);
        Log.e("height", "" + screenHeight);*/

        //changes skin color if skin button is checked and color bar has been changed
        if(myFaceModel.skin == true) {
            //if skin button is checked and color has been changed, change skin color to chosen color
            //if skin button is checked but color wasn't changed yet, keep original random skin color
            if(myFaceModel.Red != 0 || myFaceModel.Green != 0 || myFaceModel.Blue != 0) {
                myFaceModel.skinColor.setColor(Color.rgb(myFaceModel.Red, myFaceModel.Green, myFaceModel.Blue));
            }
        }

        //horizontal axis is 900
        //vertical axis is 1125
        //draws bare face shape of the chosen skin color above
        canvas.drawOval(100, 250, 1000, 1375, myFaceModel.skinColor);

        //calls draw face to draw features on bare face - eyes, nose, mouth
        this.drawFace(canvas);
    }

    public void drawFace(Canvas canvas){
        //changes iris color if eyes button is checked and color bar has been changed
        if(myFaceModel.eyes == true) {
            if(myFaceModel.Red != 0 || myFaceModel.Green != 0 || myFaceModel.Blue != 0) {
                myFaceModel.irisColor.setColor(Color.rgb(myFaceModel.Red, myFaceModel.Green, myFaceModel.Blue));
            }
        }

        //draws outer white of eye
        canvas.drawCircle(400, 675, 65, this.whiteEye);
        canvas.drawCircle(700, 675, 65, this.whiteEye);

        //draws colored part of eye - either chosen randomly or user chosen
        canvas.drawCircle(400, 675, 45, myFaceModel.irisColor);
        canvas.drawCircle(700, 675, 45, myFaceModel.irisColor);

        //draws black pupil
        canvas.drawCircle(400, 675, 30, this.pupilColor);
        canvas.drawCircle(700, 675, 30, this.pupilColor);

        //draws eyelashes
        for(double a = -Math.PI;  a<=0; a+= Math.PI/20){
            double r = 65;
            //center of eye coordinates
            double cx = 400;
            double cy = 675;

            double cx2 = 700;

            //coordinates along the edge of the eye where lashes start
            float startX = (float)(cx + r * Math.cos(a));
            float startY = (float)(cy + r * Math.sin(a));

            float startX2 = (float)(cx2 + r * Math.cos(a));

            //line continues from start point at angle a for a length of eye's diameter/6
            float endX = (float)(startX + (130/6) * Math.cos(a));
            float endY = (float)(startY + (130/6) * Math.sin(a));

            float endX2 = (float)(startX2 + (130/6) * Math.cos(a));

            pupilColor.setStrokeWidth(2);

            canvas.drawLine(startX, startY, endX, endY, this.pupilColor);
            canvas.drawLine(startX2, startY, endX2, endY, this.pupilColor);
        }

        //draw nostrils
        canvas.drawCircle(500, 850, 20, this.pupilColor);
        canvas.drawCircle(600, 850, 20, this.pupilColor);

        //draw mouth
        canvas.drawArc(300,900, 850, 1150, 0F, 180F, false, this.pupilColor);

        //after basic facial features are drawn (eyes, nose, mouth), calls drawHair to draw hair
        this.drawHair(canvas);
    }

    public void drawHair(Canvas canvas) {

//to set rbg color: https://stackoverflow.com/questions/17761852/how-to-set-rgb-color-in-android
        //changes hair color if hair button is checked and color bar has been changed
        if(myFaceModel.hair == true) {
            if(myFaceModel.Red != 0 || myFaceModel.Green != 0 || myFaceModel.Blue != 0) {
                myFaceModel.hairColor.setColor(Color.rgb(myFaceModel.Red, myFaceModel.Green, myFaceModel.Blue));
            }
        }

        myFaceModel.hairColor.setStrokeWidth(4);

        //center of face coordinates (550, 812.5)
        if (myFaceModel.hairType.equals("Bangs")) {
            for (double a = -Math.PI; a <= 0; a += Math.PI / 30) {
                float startX = (float) (550 + 450 * Math.cos(a));
                float startY = (float) (812.5 + 562.5 * Math.sin(a));
                float endY = startY + 200;
                canvas.drawLine(startX, startY, startX, endY, myFaceModel.hairColor);
            }
        } else if (myFaceModel.hairType.equals("Mohawk")) {
            for (double a = -2.09439510239; a <= -1.0471975512; a += 0.10471975512) {
                float startX = (float) (550 + 450 * Math.cos(a));
                float startY = (float) (812.5 + 562.5 * Math.sin(a));

                canvas.drawLine(startX, startY, 550, 100, myFaceModel.hairColor);
            }
        }
        else if (myFaceModel.hairType.equals("Buzzcut")){
            for (double a = -Math.PI; a <= 0; a += Math.PI / 60) {
                float startX = (float) (550 + 450 * Math.cos(a));
                float startY = (float) (812.5 + 562.5 * Math.sin(a));

                float endX = (float)(startX + 450/8 * Math.cos(a));
                float endY = (float)(startY + 562.5/8 * Math.sin(a));
                canvas.drawLine(startX, startY, endX, endY, myFaceModel.hairColor);
            }
        }
    }
}
