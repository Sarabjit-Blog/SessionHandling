package blog.sarabjit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import blog.sarabjit.R;
import blog.sarabjit.util.Constants;
import blog.sarabjit.util.Keystore;
import blog.sarabjit.util.Utils;

/**
 * Created by sarbagga on 01/10/18.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText edUserName;
    private EditText edPassword;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        autoLogin();
    }

    private void initialize() {
        edUserName = findViewById(R.id.userName);
        edPassword = findViewById(R.id.password);
        button = findViewById(R.id.button);
    }

    private void initializeListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCredentialsAndlogin();
            }
        });
    }

    private void saveCredentialsAndlogin() {
        if (!TextUtils.isEmpty(edUserName.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            Keystore keystore = Keystore.getInstance(this);
            keystore.put(Constants.USERNAME, edUserName.getText().toString());
            keystore.put(Constants.PASSWORD, edPassword.getText().toString());
            Utils.navigateToHomeActivity(this);
        }
    }

    private void autoLogin() {
        if (!Utils.isUserNamePasswordValid()) {
            Utils.navigateToHomeActivity(this);
        } else {
            initialize();
            initializeListener();
        }
    }
}
