package com.websarva.wings.android.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nifcloud.mbaas.core.NCMBUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.input_name)
    EditText _nameText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.input_password)
    EditText _passwordText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(this::onClick);

        _loginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            finish();
        });
    }

    public void signup() {
        Log.d(TAG, "さいんあっぷ");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("あかうんとさくせいちゅう");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        //NCMBUserのインスタンスを作成
        NCMBUser user = new NCMBUser();
        //ユーザ名を設定
        user.setUserName(name);
        //パスワードを設定
        user.setPassword(password);
        //設定したユーザ名とパスワードで会員登録を行う
        user.signUpInBackground(e -> {
            if (e != null) {
                //会員登録時にエラーが発生した場合の処理
                onSignupFailed();
            } else {
                new android.os.Handler().postDelayed(
                        () -> {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }, 3000);
            }
        });
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "とうろくしっぱい", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() ) {
            _nameText.setError("ユーザー名を入力してください");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            _passwordText.setError("8文字以上で入力してください");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void onClick(View v) {
        signup();
    }
}
