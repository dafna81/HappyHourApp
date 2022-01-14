package ohad.dafna.happyhourapp.utils;

import android.content.Context;

public class Conversion {
    public static float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
