package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.signUp.SignUpContract;
import thhsu.chloe.ModelHub.signUp.SignUpPresenter;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 5/13/2018.
 */

public class SignUpActivity extends BaseActivity implements SignUpContract.View, View.OnClickListener{
    private TextInputLayout mTextInputLayoutRegisterEmail, mTextInputLayoutRegisterPassword, mTextInputLayoutRegisterConfirmPassword,
            mTextInputLayoutRegisterName, mTextInputLayoutRegisterAge;
    private EditText mEditTextRegisterEmail, mEditTextRegisterPassword, mEditTextRegisterConfirmPassword,
            mEditTextRegisterName, mEditTextRegisterAge;
    private String mUserEmail;
    private String mUserName;
    private String mUserAge;
    private String mUserGender;
    private RadioGroup mRadioGroupGender;
    private SharedPreferences mSharedPreferences;
    private SignUpContract.Presenter mPresenter;


    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mPresenter = new SignUpPresenter(this, getApplicationContext());
        mSharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);

        mTextInputLayoutRegisterAge = (TextInputLayout) findViewById(R.id.textinputLayout_signup_age_);
        mTextInputLayoutRegisterName = (TextInputLayout) findViewById(R.id.textinputLayout_signup_name);
        mTextInputLayoutRegisterEmail = (TextInputLayout) findViewById(R.id.textinputLayout_signup__email);
        mTextInputLayoutRegisterPassword = (TextInputLayout) findViewById(R.id.textinputLayout_signup_password);
        mTextInputLayoutRegisterConfirmPassword = (TextInputLayout) findViewById(R.id.textinputLayout_signup_confirmpassword);
        mEditTextRegisterAge = (EditText) findViewById(R.id.editText_signup_age);
        mEditTextRegisterName = (EditText) findViewById(R.id.editText_signup_name);
        mEditTextRegisterEmail = (EditText) findViewById(R.id.editText_signup_email);
        mEditTextRegisterPassword = (EditText) findViewById(R.id.editText_signup_password);
        mEditTextRegisterConfirmPassword = (EditText) findViewById(R.id.editText_signup_confirmpassword);

        Button registerBackBtn = (Button) findViewById(R.id.btn_signup_back);
        Button createAccountBtn = (Button) findViewById(R.id.btn_signup_createaccount);

        mRadioGroupGender = (RadioGroup) findViewById(R.id.radiogroup_signup_gender);

        createAccountBtn.setOnClickListener(this);
        registerBackBtn.setOnClickListener(this);
    }

    @Override
    public void showNameError(Boolean isError){
            if(isError)
            mTextInputLayoutRegisterName.setError("invalid Name");
            else{
                mTextInputLayoutRegisterName.setErrorEnabled(false);
            }
    }

    @Override
    public void showAgeError(Boolean isError){
        if(isError){
            mTextInputLayoutRegisterAge.setError("invalid number input");
        }else{
            mTextInputLayoutRegisterAge.setErrorEnabled(false);
        }
    }

    @Override
    public void showEmailError(Boolean isError){
        if(isError){
            mTextInputLayoutRegisterEmail.setError(getString(R.string.invalidEmail));
        }else{
            mTextInputLayoutRegisterEmail.setErrorEnabled(false);
        }
    }

    @Override
    public void showPasswordError(Boolean isError){
        if(isError){
            mTextInputLayoutRegisterPassword.setError("At least 6 characters");
        }else{
            mTextInputLayoutRegisterPassword.setErrorEnabled(false);
        }
    }

    @Override
    public boolean showConfirmPasswordError(Boolean isError){
        if(isError){
            mTextInputLayoutRegisterConfirmPassword.setError("Password must match");
            return true;
        }else{
            mTextInputLayoutRegisterConfirmPassword.setErrorEnabled(false);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup_createaccount:
                    int selectedGenderId = mRadioGroupGender.getCheckedRadioButtonId();
                    RadioButton selectedGender = (RadioButton) findViewById(selectedGenderId);
                    final String password, confirmPassword;
                    mUserName = mEditTextRegisterName.getText().toString();
                    mUserEmail = mEditTextRegisterEmail.getText().toString();
                    mUserAge =  mEditTextRegisterAge.getText().toString();
                    mUserGender = selectedGender.getText().toString();
                    password = mEditTextRegisterPassword.getText().toString();
                    confirmPassword = mEditTextRegisterConfirmPassword.getText().toString();

                    mPresenter.onClickSignUp(mUserName, mUserAge, mUserGender, mUserEmail, password, confirmPassword);

                break;
            case R.id.btn_signup_back:
                this.onBackPressed();
                break;
        }
    }


    @Override
    public void onClickSignUpUi(String token) {
        Intent intent = new Intent(SignUpActivity.this, ModelHubActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mSharedPreferences.edit()
                .putString(Constants.USER_TOKEN, token)
                .putString(Constants.USER_EMAIL, mUserEmail)
                .putString(Constants.USER_NAME, mUserName)
                .putString(Constants.USER_AGE, mUserAge)
                .putString(Constants.USER_GENDER, mUserGender)
                .apply();
        setResult(Constants.RESULT_SUCCESS);
        startActivity(intent);
        finish();
    }
}
