package thhsu.chloe.jeeva.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.ApiManager;
import thhsu.chloe.jeeva.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 5/7/2018.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener{
    private Button mBackBtn, mSignInBtn;
    private TextInputLayout mSignInEmailTextInputLayout, mSignInPasswordTextInputLayout;
    private EditText mSignInEmailText, mSignInPasswordText;
    private String mUserLogInToken;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mSignInEmailTextInputLayout = (TextInputLayout) findViewById(R.id.signin_textinputlayout_email);
        mSignInPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.signin_textinputlayout_password);
        mSignInEmailText = (EditText) findViewById(R.id.signin_textinput_email);
        mSignInPasswordText = (EditText) findViewById(R.id.signin_textinput_password);
        mBackBtn = (Button) findViewById(R.id.signin_back_btn);
        mSignInBtn = (Button) findViewById(R.id.signin_signin_btn);
        mSignInBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mSharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
    }

    public boolean validateLogInData(){
        boolean result = true;

        String email = mSignInEmailText.getText().toString();
        if (email.equals("") || !(email.contains("@"))) {
            mSignInEmailTextInputLayout.setError(getString(R.string.invalidEmail));
            result = false;
        } else {
            mSignInEmailTextInputLayout.setErrorEnabled(false);
        }

        String password = mSignInPasswordText.getText().toString();
        if(password.length() < 6){
            mSignInPasswordTextInputLayout.setError("At least 6 characters");
            result = false;
        }else{
            mSignInPasswordTextInputLayout.setErrorEnabled(false);
        }
        return result;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin_back_btn:
                super.onBackPressed();
                break;

                case R.id.signin_signin_btn:
                    if(validateLogInData()){
                        String email, password;
                        email = mSignInEmailText.getText().toString();
                        password = mSignInPasswordText.getText().toString();
                        ApiJobManager.getInstance().getLogInResult(email, password, new PostRegisterLoginCallBack() {
                            @Override
                            public void onCompleted(String token) {
                                mUserLogInToken = token;
                                Log.d("Chloe", "LogIn token: " + token);
                                mSharedPreferences.edit()
                                        .putString(Constants.USER_TOKEN, token)
                                        .commit();
                                Intent i = new Intent(SignInActivity.this, JeevaActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();

                            }

                            @Override
                            public void onError(String errorMessage) {

                            }
                        });
                    }
                    break;
        }
    }

}
