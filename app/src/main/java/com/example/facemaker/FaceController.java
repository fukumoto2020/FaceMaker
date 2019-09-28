package com.example.facemaker;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;


/**
 * @author Taylor Fukumoto
 * @date 28 September 2019
 *
 * FaceController is the "controller" class
 * this is where buttons, seekbars, and spinners are handled
 *
 * all listener methods are in this class
 */
public class FaceController extends Activity implements AdapterView.OnItemSelectedListener, android.widget.SeekBar.OnSeekBarChangeListener, View.OnClickListener{
    private Face myFace;
    private FaceModel myFaceModel;


    /**
     * constructor method
     *
     * @param face is the surface view passed in from MainActivity
     *
     * creates Face object with face parameter
     * creates FaceModel object using getFaceModel method in Face class
     *             getFaceModel returns its FaceModel object
     */
    public FaceController(Face face){
        myFace = face;
        myFaceModel = myFace.getFaceModel();
    }

    //Spinner stuff --------------------------------------------------------
    /**
     * required spinner method
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * required spinner method
     * this is where selected spinner value (hair style) is handled
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //Spinner dropdown was populated from strings.xml file
        //sets chosen spinner item to the hairType string

        /**
         External Citation
         Date: 27 September 2019
         Problem: Didn't know how to get chosen spinner value
         Resource:
         https://stackoverflow.com/questions/18288270/what-type-of-object-does-spinner-getitematposition-return
         Solution: I used the example code from this post.
         */
        myFaceModel.hairType = (String)parent.getItemAtPosition(pos);

        //redraws face after hair type is selected
        myFace.invalidate();
    }

    /**
     * required spinner method
     */
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //Radio and button stuff --------------------------------------------------------
    /**
     * required radio button/button method
     * handles both radio button (hair, skin, eyes) clicks and random face button clicks
     */
    public void onClick(View v) {
        //use if statements to check which radio button was checked
        if (v.getId() == R.id.hairButton) {
            //if hair button was pressed, sets hair to true and skin and eyes to false
            myFaceModel.hair = true;
            myFaceModel.skin = false;
            myFaceModel.eyes = false;

            //gets the rgb values of the current hair color
            int redHair = Color.red(myFaceModel.hairColor.getColor());
            int greenHair = Color.green(myFaceModel.hairColor.getColor());
            int blueHair = Color.blue(myFaceModel.hairColor.getColor());

            /**
             External Citation
             Date: 27 September 2019
             Problem: Didn't know how to change seekBar from a button method- needed to get seekBar from MainActivity
             Resource:
             https://stackoverflow.com/questions/17315842/how-to-call-a-method-in-mainactivity-from-another-class
             Solution: I used the example code from this post.
             */
            //sets respective progress bar colors to hair's rgb values
            MainActivity.getInstance().redBar.setProgress(redHair);
            MainActivity.getInstance().greenBar.setProgress(greenHair);
            MainActivity.getInstance().blueBar.setProgress(blueHair);

        }
        else if (v.getId() == R.id.skinButton) {
            myFaceModel.skin = true;
            myFaceModel.hair = false;
            myFaceModel.eyes = false;

            int redSkin = Color.red(myFaceModel.skinColor.getColor());
            int greenSkin = Color.green(myFaceModel.skinColor.getColor());
            int blueSkin = Color.blue(myFaceModel.skinColor.getColor());

            MainActivity.getInstance().redBar.setProgress(redSkin);
            MainActivity.getInstance().greenBar.setProgress(greenSkin);
            MainActivity.getInstance().blueBar.setProgress(blueSkin);

        }
        else if(v.getId() == R.id.eyeButton){
            myFaceModel.eyes = true;
            myFaceModel.hair = false;
            myFaceModel.skin = false;

            int redEyes = Color.red(myFaceModel.irisColor.getColor());
            int greenEyes = Color.green(myFaceModel.irisColor.getColor());
            int blueEyes = Color.blue(myFaceModel.irisColor.getColor());

            MainActivity.getInstance().redBar.setProgress(redEyes);
            MainActivity.getInstance().greenBar.setProgress(greenEyes);
            MainActivity.getInstance().blueBar.setProgress(blueEyes);

        }

        //random face button calls randomize method from Face class to make a random face
        //also needs to set seekBar values respectively to color of selected radio button
        //also needs to set spinner value to the random hairstyle it generated
        else if(v.getId() == R.id.randomButton){
            //boolean variable to keep track of whether face is being randomly created or if user is creating face
            myFaceModel.random = true;
            myFace.randomize();

            //if hair radio button is pressed, gets the RGB value of hair color and sets the seekBar's progress to that color
            if(myFaceModel.hair == true){
                //gets RGB value from hair color
                int redHair = Color.red(myFaceModel.hairColor.getColor());
                int greenHair = Color.green(myFaceModel.hairColor.getColor());
                int blueHair = Color.blue(myFaceModel.hairColor.getColor());

                //sets respective progress bar colors to hair's rgb values
                MainActivity.getInstance().redBar.setProgress(redHair);
                MainActivity.getInstance().greenBar.setProgress(greenHair);
                MainActivity.getInstance().blueBar.setProgress(blueHair);
            }
            else if(myFaceModel.skin == true){
                int redSkin = Color.red(myFaceModel.skinColor.getColor());
                int greenSkin = Color.green(myFaceModel.skinColor.getColor());
                int blueSkin = Color.blue(myFaceModel.skinColor.getColor());

                MainActivity.getInstance().redBar.setProgress(redSkin);
                MainActivity.getInstance().greenBar.setProgress(greenSkin);
                MainActivity.getInstance().blueBar.setProgress(blueSkin);
            }
            else if (myFaceModel.eyes == true) {
                int redEyes = Color.red(myFaceModel.irisColor.getColor());
                int greenEyes = Color.green(myFaceModel.irisColor.getColor());
                int blueEyes = Color.blue(myFaceModel.irisColor.getColor());

                MainActivity.getInstance().redBar.setProgress(redEyes);
                MainActivity.getInstance().greenBar.setProgress(greenEyes);
                MainActivity.getInstance().blueBar.setProgress(blueEyes);
            }

            //sets spinner value to the current hairType
            if(myFaceModel.hairType.equals("Bangs")) {
                MainActivity.getInstance().spinner.setSelection(0);
            }
            else if(myFaceModel.hairType.equals("Mohawk")) {
                MainActivity.getInstance().spinner.setSelection(1);
            }
            else if(myFaceModel.hairType.equals("Buzzcut")) {
                MainActivity.getInstance().spinner.setSelection(2);
            }

            myFace.invalidate();
        }

    }

    //Seekbar stuff --------------------------------------------------------------
    /**
     * required seekBar method
     * handles the progress of the three (RGB) seekbars
     */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        //if seekBar was changed, random variable is false since face is no longer "random"
        myFaceModel.random = false;

        //uses switch statement to see which seekbar was changed
        //sets red, green, and blue values to the seekbar's progress
        //those red, green, blue values will change the color of whatever element button is clicked
        switch (seekBar.getId()) {
            case R.id.redSeek:
                myFaceModel.Red = progress;
                myFace.invalidate();
                break;

            case R.id.greenSeek:
                myFaceModel.Green = progress;
                myFace.invalidate();
                break;
            case R.id.blueSeek:
                myFaceModel.Blue = progress;
                myFace.invalidate();
                break;
        }
    }

    /**
     * required unused seekBar method
     */
    public void onStartTrackingTouch(SeekBar seekBar){}
    public void onStopTrackingTouch(SeekBar seekBar){}
}
