package blog.sarabjit.activities;

import android.os.Bundle;
import android.widget.TextView;

import blog.sarabjit.R;
import blog.sarabjit.session.SessionTimeoutManager;
import blog.sarabjit.util.Constants;
import blog.sarabjit.util.Keystore;

public class HomeActivity extends BaseActivity {
    private TextView mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setData();
        SessionTimeoutManager.getInstance().resetTimer();
    }

    private void initializeViews() {
        mUserName = findViewById(R.id.userName);
    }

    private void setData() {
        Keystore keystore = Keystore.getInstance(this);
        mUserName.setText(String.format("%s %s", getString(R.string.hello), keystore.
                get(Constants.USERNAME)));
        SessionTimeoutManager.getInstance().setBaseActivityWeakReference(this);
    }
}
