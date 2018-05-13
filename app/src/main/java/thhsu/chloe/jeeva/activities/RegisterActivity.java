package thhsu.chloe.jeeva.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.ApiManager;
import thhsu.chloe.jeeva.api.PostRegisterCallBack;

/**
 * Created by Chloe on 5/13/2018.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    TextInputLayout mRegisterEmailTextInputLayout, mRegisterPasswordTextInputLayout, mRegisterConfirmPasswordTextInputLayout;
    EditText mRegisterEmailText, mRegisterPasswordText, mRegisterConfirmPasswordText;
    Button mCreateAccountBtn, mRegisterBackBtn;
    String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mRegisterEmailTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_email);
        mRegisterPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_password);
        mRegisterConfirmPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_confirmpassword);
        mRegisterConfirmPasswordText = (EditText) findViewById(R.id.signup_textinput_confirmpassword);
        mRegisterEmailText = (EditText) findViewById(R.id.signup_textinput_email);
        mRegisterPasswordText = (EditText) findViewById(R.id.signup_textinput_password);
        mCreateAccountBtn = (Button) findViewById(R.id.signup_createaccount_btn);
        mRegisterBackBtn = (Button) findViewById(R.id.signup_back_btn);

        mCreateAccountBtn.setOnClickListener(this);
        mRegisterBackBtn.setOnClickListener(this);

    }


    public boolean validateRegisterData(){
        boolean result = true;

        String email = mRegisterEmailText.getText().toString();
        if (email.equals("") || !(email.contains("@"))) {
            mRegisterEmailTextInputLayout.setError(getString(R.string.invalidEmail));
            result = false;
        } else {
            mRegisterEmailTextInputLayout.setErrorEnabled(false);
        }

        String password = mRegisterPasswordText.getText().toString();
        if(password.length() < 6){
            mRegisterPasswordTextInputLayout.setError("At least 6 characters");
            result = false;
        }else{
            mRegisterPasswordTextInputLayout.setErrorEnabled(false);
        }

        String confirmPassword = mRegisterConfirmPasswordText.getText().toString();
        if(!(confirmPassword.equals(password))){
            mRegisterConfirmPasswordTextInputLayout.setError("Password must match");
            result = false;
        }else{
            mRegisterConfirmPasswordTextInputLayout.setErrorEnabled(false);
        }

        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_createaccount_btn:
                if(validateRegisterData()){
                    String email, password;
                    email = mRegisterEmailText.getText().toString();
                    password = mRegisterPasswordText.getText().toString();
                    Log.d("Chloe", "Email: " + email + " Password: " + password);
                    ApiJobManager.getInstance().getRegister(email, password, new PostRegisterCallBack() {
                        @Override
                        public void onCompleted(String token) {
                            userToken = token;
                            Log.d("Chloe", "userToken: " + token);
                        }

                        @Override
                        public void onError(String errorMessage) {

                        }
                    });
                }

                break;
            case R.id.signin_back_btn:
                break;
        }
    }
}
