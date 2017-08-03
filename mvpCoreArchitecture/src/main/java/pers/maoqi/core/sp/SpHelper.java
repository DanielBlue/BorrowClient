package pers.maoqi.core.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maoqi on 2017/7/20.
 */

public class SpHelper {
    private final static String name = "config";
    private static SpHelper helper;
    private SharedPreferences sp;

    private SpHelper(Context context) {
        init(context);
    }

    public static SpHelper getInstance(Context context) {
        if (helper == null) {
            helper = new SpHelper(context);
        }
        return helper;
    }


    private void init(Context context) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 获取
    public boolean getBoolean(String key,
                              boolean defValue) {
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public int getInt(String key, int defValue) {
        int value = sp.getInt(key, defValue);
        return value;
    }

    public String getString(String key, String defValue) {
        String value = sp.getString(key, defValue);
        return value;
    }
}
