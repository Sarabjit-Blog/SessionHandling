package blog.sarabjit.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Keystore {
    private static final String FILE = "blog.sarabjit";
    private static Keystore store;
    private SharedPreferences sharedPreferences;

    private Keystore(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    public static Keystore getInstance(Context context) {
        if (store == null) {
            store = new Keystore(context);
        }
        return store;
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }

    public int getInt(String key) {//Log.v("Keystore","GET INT from "+key);
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {//Log.v("Keystore","GET INT from "+key);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putInt(String key, int num) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, num);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void remove() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(FILE);
        editor.apply();
    }
}
