package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.signIn.SignInContract;
import thhsu.chloe.ModelHub.signIn.SignInPresenter;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 5/7/2018.
 */

public class SignInActivity extends BaseActivity implements SignInContract.View, View.OnClickListener{
    private TextInputLayout mTextInputLayoutSignInEmail, mTextInputLayoutSignInPassword;
    private EditText mEditTextSignInEmail, mEditTextSignInPassword;
    private SharedPreferences mSharedPreferences;
    private SignInContract.Presenter mPresenter;

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mPresenter = new SignInPresenter(this);
        mSharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        mEditTextSignInEmail = (EditText) findViewById(R.id.editText_signin_email);
        mEditTextSignInPassword = (EditText) findViewById(R.id.editText_signin_password);
        mTextInputLayoutSignInEmail = (TextInputLayout) findViewById(R.id.textinputLayout_signin_email);
        mTextInputLayoutSignInPassword = (TextInputLayout) findViewById(R.id.textinputLayout_signin_password);
        Button backBtn = (Button) findViewById(R.id.btn_signin_back);
        Button signInBtn = (Button) findViewById(R.id.btn_signin_signin);
        signInBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin_back:

                super.onBackPressed();
                break;

            case R.id.btn_signin_signin:
                if (validateLogInData()) {
                    String email, password;
                    email = mEditTextSignInEmail.getText().toString();
                    password = mEditTextSignInPassword.getText().toString();

                    mPresenter.onClickSignIn(email, password);

                }
                break;
        }
    }


    public boolean validateLogInData() {

        boolean result = true;

        String email = mEditTextSignInEmail.getText().toString();
        if (email.equals("") || !(email.contains("@"))) {
            mTextInputLayoutSignInEmail.setError(getString(R.string.invalidEmail));
            result = false;
        } else {
            mTextInputLayoutSignInEmail.setErrorEnabled(false);
        }

        String password = mEditTextSignInPassword.getText().toString();
        if (password.length() < 6) {
            mTextInputLayoutSignInPassword.setError("At least 6 characters");
            result = false;
        } else {
            mTextInputLayoutSignInPassword.setErrorEnabled(false);
        }
        return result;
    }



    @Override
    public void onClickSignInUi(String token) {
        mSharedPreferences.edit()
                .putString(Constants.USER_TOKEN, token)
                .apply();
        Intent i = new Intent(SignInActivity.this, ModelHubActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
