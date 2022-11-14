package com.websarva.wings.android.home;

import static com.websarva.wings.android.home.R.id.link_signup;
import static com.websarva.wings.android.home.R.style.AppTheme_Dark_Dialog;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.input_name)
    EditText _nameText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.input_password) EditText _passwordText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_login)
    Button _loginButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(this::onClick);

        _signupLink.setOnClickListener(v -> {
            // Start the Signup activity
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
        });
    }

    public void login() {
        Log.d(TAG, "ろぐいん");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("なうろーでぃんぐ");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        //ユーザ名とパスワードを指定してログインを実行
        try {
            NCMBUser.loginInBackground(name, password, (user, e) -> {
                if (e != null) {
                    //エラー時の処理
                    onLoginFailed();
                    progressDialog.dismiss();
                } else {
                    new Handler().postDelayed(
                            () -> {
                                // On complete call either onLoginSuccess or onLoginFailed
                                onLoginSuccess();
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }, 3000);
                }
            });
        } catch (NCMBException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "ろぐいんしっぱい", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError("ユーザー名を入力してください");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            _passwordText.setError("８文字以上で入力してください");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void onClick(View v) {
        login();
    }
}
