package blog.sarabjit.activities;

import android.support.v7.app.AppCompatActivity;

import blog.sarabjit.session.SessionTimeoutManager;
import blog.sarabjit.util.Utils;

/**
 * Created by sarbagga on 01/10/18.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isSessionExpired()) {
            Utils.logoutUser(this);
        }
    }

    @Override
    public void onUserInteraction() {
        SessionTimeoutManager.getInstance().resetTimer();
    }
}
