package com.example.facemaker;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;


/**
 * @author Taylor Fukumoto
 * @date 28 September 2019
 * Face class is the "view" class
 * this is where the face is drawn and colors are set
 */
public class Face extends SurfaceView {
    private FaceModel myFaceModel = new FaceModel();

    /** skinColor, eyeColor, hairColor, and hairStyle variables are in FaceModel class */

    protected Paint whiteEye = new Paint();
    protected Paint pupilColor = new Paint();
    protected Random random = new Random();


    /**
     * Face constructor
     *
     * @param context and attrs are required parameters for a surface view
     *
     * initializes whiteEye and pupilColor to white and black
     * calls randomize method to initially draw a random face when app opens
     */
    public Face(Context context, AttributeSet attrs){
        super(context,attrs);
        setWillNotDraw(false);

        //sets whiteEye to be white and pupil to be black - these colors do not change
        this.whiteEye.setColor(Color.WHITE);
        this.pupilColor.setColor(Color.BLACK);

        //calls randomize to initialize face with random skin, hair, and eye color and random hairstyle
        this.randomize();
    }

    /**
     * getFaceModel method returns myFaceModel object instantiated at the top of this class
     *
     * allows other classes to have access to this same myFaceModel object
     *
     * @return FaceModel object
     */
    public FaceModel getFaceModel()
    {
        return myFaceModel;
    }


    /**
     * randomly assigns skinColor, irisColor, and hairColor a random rgb color between 0 and 255
     *
     * generates a random number between 1 and 3 to randomly assign one of 3 hairstyles
     *
     */
    public void randomize(){
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

    /**
     * given a canvas, draws the Face object
     *
     * onDraw draws bare face oval and then calls helper method drawFace to draw
     * eyes, nose, mouth
     *
     * if skin button is pressed and randomize button has not been pressed
     * and if any of the RGB seekbars have been moved, set skin color to the chosen
     * RGB values from seekbars
     *
     */
    @Override
    public void onDraw(Canvas canvas){
        //changes skin color if skin button is checked and color bar has been changed
        if(myFaceModel.skin == true && myFaceModel.random == false) {
            //if skin button is checked and color has been changed, change skin color to chosen color
            //if random face button is pressed, don't set seekbar color
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


    /**
     * drawFace method is a helper method to onDraw
     *
     * onDraw passes canvas to drawFace
     *
     * drawFace draws face's eyes, nose, and mouth
     *
     * sets the face's iris color the same way it sets the skin color in
     * onDraw method
     *
     * calls another helper method, drawHair
     *
     */
    public void drawFace(Canvas canvas){
        //changes iris color if eyes button is checked and color bar has been changed
        if(myFaceModel.eyes == true && myFaceModel.random == false) {
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


    /**
     * drawHair method is a helper method
     *
     * drawFace passes canvas to drawHair
     *
     * drawHair draws one of three hair styles
     *
     * sets hair color the same way skin and eye color are chosen above
     *
     */
    public void drawHair(Canvas canvas) {
        /**
         External Citation
         Date: 27 September 2019
         Problem: Didn't know how to set a color to specific rgb values
         Resource:
         https://stackoverflow.com/questions/17761852/how-to-set-rgb-color-in-android
         Solution: I used the example code from this post.
         */

        //changes hair color if hair button is checked and color bar has been changed and random face not pressed
        if(myFaceModel.hair == true && myFaceModel.random == false) {
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
