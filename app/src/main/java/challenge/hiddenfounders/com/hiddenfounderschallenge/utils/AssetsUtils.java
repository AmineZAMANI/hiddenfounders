package challenge.hiddenfounders.com.hiddenfounderschallenge.utils;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by azamani on 04/02/2018.
 */

public class AssetsUtils {


    private static Typeface awesomeFont;

    private AssetsUtils() {
        /*
         Utilities class that can be accessed in a static way
         */
    }

    public static void makeAwesome(TextView tView) {
        if (tView != null && tView.getContext() != null) {
            if (awesomeFont == null) {
                awesomeFont = Typeface.createFromAsset(tView.getContext().getAssets(), "fonts/fa.ttf");
            }
            tView.setTypeface(awesomeFont);
        }
    }


}
