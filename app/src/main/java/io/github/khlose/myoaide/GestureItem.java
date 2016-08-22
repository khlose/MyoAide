package io.github.khlose.myoaide;

import android.util.Log;

/**
 * Created by Schwin on 8/12/2016.
 */
public class GestureItem {
    public String gesture;
    public String functionality;
    public int iconDrawable;


    public GestureItem(String gesture, String functionality){
        this.gesture = gesture;
        this.functionality = functionality;
        this.iconDrawable = getIconID(this.gesture);

    }

    private int getIconID(String gesture){
        int imgDrawable = R.drawable.gesture_blank;


        if(gesture.equalsIgnoreCase("rest")){
            imgDrawable = R.drawable.gesture_blank;


        }
        if(gesture.equalsIgnoreCase("fist")){
            imgDrawable = R.drawable.gesture_fist;
        }
        else if(gesture.equalsIgnoreCase("wave in")){
            Log.d("Icon used:  ","WAVE IN");
            imgDrawable = R.drawable.gesture_wavein;
        }
        else if(gesture.equalsIgnoreCase("wave out")){
            imgDrawable = R.drawable.gesture_waveout;
        }
        else if(gesture.equalsIgnoreCase("fingers spread")){
            imgDrawable = R.drawable.gesture_fingersspread;
        }
        else if(gesture.equalsIgnoreCase("double tap")){
            imgDrawable = R.drawable.gesture_doubletap;
        }

        else{
            imgDrawable = R.drawable.gesture_blank;//unknwon
        }

        return imgDrawable;

    }

}
