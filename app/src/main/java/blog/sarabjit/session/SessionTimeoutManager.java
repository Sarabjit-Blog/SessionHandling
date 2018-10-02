package blog.sarabjit.session;

import android.os.CountDownTimer;

import java.lang.ref.WeakReference;

import blog.sarabjit.activities.BaseActivity;
import blog.sarabjit.application.BaseApplication;
import blog.sarabjit.util.Constants;
import blog.sarabjit.util.Keystore;
import blog.sarabjit.util.Utils;

public class SessionTimeoutManager {
    private static final long INTERVAL = 1 * 1000;
    private static final long START_TIME = 1 * 60 * 1000; // 1 MINS IDLE TIME
    private static SessionTimeoutManager mSessionTimeoutManager;
    private CountDownTimer timer;
    private WeakReference<BaseActivity> mBaseActivityWeakReference;

    private SessionTimeoutManager() {
        timer = new CustomCountDownTimer(START_TIME, INTERVAL);
        timer = timer.start();
    }

    public static SessionTimeoutManager getInstance() {
        synchronized (SessionTimeoutManager.class) {
            if (mSessionTimeoutManager == null) {
                mSessionTimeoutManager = new SessionTimeoutManager();

            }
        }
        return mSessionTimeoutManager;
    }


    public void resetTimer() {
        if (timer != null) {
            timer.cancel();
            timer = timer.start();
        }
    }

    public void setBaseActivityWeakReference(BaseActivity baseActivityWeakReference) {
        mBaseActivityWeakReference = new WeakReference<>(baseActivityWeakReference);
    }

    private void signOutUser(BaseActivity activity) {
        Utils.logoutUser(activity);
    }

    public class CustomCountDownTimer extends CountDownTimer {
        CustomCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            logoutUser();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        private void logoutUser() {
            Keystore.getInstance(BaseApplication.getInstance()).putBoolean(Constants.SESSION_EXPIRED, true);
            if (null != mBaseActivityWeakReference) {
                BaseActivity baseActivity = mBaseActivityWeakReference.get();
                if (null != baseActivity) {
                    if (!BaseApplication.getInstance().isApplicationInBackground()) {
                        signOutUser(baseActivity);
                    }
                }
            }
        }
    }
}