package blog.sarabjit.application;

import android.app.Application;

import blog.sarabjit.session.AppLifeCycleHandler;
import blog.sarabjit.session.SessionTimeoutManager;

public class BaseApplication extends Application implements AppLifeCycleHandler.AppLifeCycleCallback {
    private static BaseApplication sInstance;
    private boolean isAppInBackground;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AppLifeCycleHandler appLifeCycleHandler = new AppLifeCycleHandler(this);
        registerActivityLifecycleCallbacks(appLifeCycleHandler);
        registerComponentCallbacks(appLifeCycleHandler);
    }

    @Override
    public void onAppBackground() {
        isAppInBackground = true;
    }


    public boolean isApplicationInBackground() {
        return isAppInBackground;
    }

    @Override

    public void onAppForeground() {
        isAppInBackground = false;
        SessionTimeoutManager.getInstance().resetTimer();
    }
}