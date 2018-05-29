package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 5/13/2018.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener {
    TextInputLayout mRegisterEmailTextInputLayout, mRegisterPasswordTextInputLayout, mRegisterConfirmPasswordTextInputLayout, mRegisterNameTextInputLayout, mRegisterAgeTextInputLayout;
    EditText mRegisterEmailText, mRegisterPasswordText, mRegisterConfirmPasswordText, mRegisterNameText, mRegisterAgeText;
    Button mCreateAccountBtn, mRegisterBackBtn;
    String userToken, userEmail, userName, userAge, userGender;
    RadioButton mGenderMale, mGenderFemale, mGenderOther, mSelectedGender;
    RadioGroup mGenderRadioGroup;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mRegisterNameTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_name);
        mRegisterEmailTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_email);
        mRegisterPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_password);
        mRegisterConfirmPasswordTextInputLayout = (TextInputLayout) findViewById(R.id.signup_textinputlayout_confirmpassword);
        mRegisterAgeTextInputLayout = (TextInputLayout) findViewById(R.id.signup_age_textinputlayout);
        mRegisterAgeText = (EditText) findViewById(R.id.signup_age_textinput);
        mRegisterConfirmPasswordText = (EditText) findViewById(R.id.signup_textinput_confirmpassword);
        mRegisterEmailText = (EditText) findViewById(R.id.signup_textinput_email);
        mRegisterPasswordText = (EditText) findViewById(R.id.signup_textinput_password);
        mGenderMale = (RadioButton) findViewById(R.id.signup_gender_male_radiobtn);
        mGenderFemale = (RadioButton) findViewById(R.id.signup_gender_female_radiobtn);
        mGenderOther = (RadioButton) findViewById(R.id.signup_gender_other_radiobtn);
        mRegisterNameText = (EditText) findViewById(R.id.signup_textinput_name);
        mCreateAccountBtn = (Button) findViewById(R.id.signup_createaccount_btn);
        mRegisterBackBtn = (Button) findViewById(R.id.signup_back_btn);
        mGenderRadioGroup = (RadioGroup) findViewById(R.id.signup_gender_radio_button_group);
        mCreateAccountBtn.setOnClickListener(this);
        mRegisterBackBtn.setOnClickListener(this);

        sharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
    }


    public boolean validateRegisterData(){
        boolean result = true;

        String name = mRegisterNameText.getText().toString();
        if(name.equals("")|| name.length() < 3){
            mRegisterNameTextInputLayout.setError("invalid Name");
            result = false;
        } else {
            mRegisterNameTextInputLayout.setErrorEnabled(false);
        }

        String age = mRegisterAgeText.getText().toString();
        if(age.equals("") || age.length() > 2){
            mRegisterAgeTextInputLayout.setError("invalid number input");
        } else {
            mRegisterAgeTextInputLayout.setErrorEnabled(false);
        }


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
                    int selectedGenderId = mGenderRadioGroup.getCheckedRadioButtonId();
                    mSelectedGender = (RadioButton) findViewById(selectedGenderId);
                    Log.d("Chloe", "Gender: " + mSelectedGender);
                    final String password;
                    userName = mRegisterNameText.getText().toString();
                    userEmail = mRegisterEmailText.getText().toString();
                    userAge =  mRegisterAgeText.getText().toString();
                    userGender = mSelectedGender.getText().toString();
                    password = mRegisterPasswordText.getText().toString();
                    Log.d("Chloe", "Email: " + userEmail + " Password: " + password);
                    ApiJobManager.getInstance().getRegister(userName, userAge, userGender, userEmail, password, new PostRegisterLoginCallBack() {
                        @Override
                        public void onCompleted(String token) {
                            Intent intent = new Intent(RegisterActivity.this, ModelHubActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            userToken = token;
                            sharedPreferences.edit()
                                    .putString(Constants.USER_TOKEN, userToken)
                                    .putString(Constants.USER_EMAIL, userEmail)
                                    .putString(Constants.USER_NAME, userName)
                                    .putString(Constants.USER_AGE, userAge)
                                    .putString(Constants.USER_GENDER, userGender)
                                    .apply();
                            Log.d("Chloe", "userToken: " + token);
//                            setResult(Constants.RESULT_SUCCESS);
                            startActivity(intent);
                            finish();}

                        @Override
                        public void onError(String errorMessage) {
                        }
                    });
                }

                break;
            case R.id.signup_back_btn:
                this.onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
