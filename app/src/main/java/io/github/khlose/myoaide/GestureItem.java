package io.github.khlose.myoaide;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.security.PublicKey;

/**
 * Created by Schwin on 8/12/2016.
 */
public class GestureItem {
    public String gesture;
    public String functionality;
    public String iconFileName;

    public GestureItem(String gesture, String functionality){
        this.gesture = gesture;
        this.functionality = functionality;
        this.iconFileName = getIconID(this.gesture);

    }

    private String getIconID(String gesture){
        String imageFileName;

        if(gesture.equalsIgnoreCase("rest")){
            imageFileName = "geture_rest";
        }
        else if(gesture.equalsIgnoreCase("fist")){
            imageFileName = "geture_fist";
        }
        else if(gesture.equalsIgnoreCase("wave in")){
            imageFileName = "geture_waveIn";
        }
        else if(gesture.equalsIgnoreCase("wave out")){
            imageFileName = "geture_waveOut";
        }
        else if(gesture.equalsIgnoreCase("fingers spread")){
            imageFileName = "geture_fingersSpread";
        }
        else if(gesture.equalsIgnoreCase("double tap")){
            imageFileName = "geture_doubleTap";
        }
        else{
            imageFileName = "geture_unknown";//unknwon
        }


        return imageFileName;

    }
}
