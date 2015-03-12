package co.malm.mglam_reads.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Utility class for handling read/write operations using {@link android.content.SharedPreferences}
 *
 * @author marlonlom
 */
public final class SharedPreferenceDataUtil {

    public static final String PREF_FILE_NAME = "mglam_prefs";

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

}
