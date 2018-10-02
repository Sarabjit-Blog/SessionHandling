package blog.sarabjit.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import blog.sarabjit.activities.BaseActivity;
import blog.sarabjit.activities.HomeActivity;
import blog.sarabjit.activities.LoginActivity;
import blog.sarabjit.application.BaseApplication;

/**
 * Created by sarbagga on 01/10/18.
 */

public class Utils {

    public static void logoutUser(BaseActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        Keystore keystore = Keystore.getInstance(activity);
        keystore.clear();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void navigateToHomeActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public static boolean isUserNamePasswordValid() {
        Keystore keystore = Keystore.getInstance(BaseApplication.getInstance());
        return keystore.get(Constants.USERNAME) == null && keystore.get(Constants.PASSWORD) == null;
    }

    public static boolean isSessionExpired() {
        Keystore keystore = Keystore.getInstance(BaseApplication.getInstance());
        return keystore.getBoolean(Constants.SESSION_EXPIRED);
    }
}

