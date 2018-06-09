package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepOneFragment extends Fragment implements AboutMeStepOneContract.View, View.OnClickListener {
    private Button mNextBtn;
    private SharedPreferences mSharedPreferences;
    private OnStepOneListener mOnStepOneListener;
    private AboutMeStepOneContract.Presenter mPresenter;
    private TextInputLayout mTextInputLayoutName, mTextInputLayoutPhone;
    private String mUserName, mUserToken, mUserPhone, mUserHeight, mUserWeight,
            mUserNationality, mUserLocationCountry, mUserLocationCity;
    private EditText mEditTextName, mEditTextEmail, mEditTextPhone, mEditTextHeight,
            mEditTextAge, mEditTextWeight, mEditTextNationality, mEditTextLocationCountry, mEditTextLocationCity;

    public AboutMeStepOneFragment() {}

    @Override
    public void setPresenter(AboutMeStepOneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = mSharedPreferences.getString(Constants.USER_TOKEN, "");

        mNextBtn = view.findViewById(R.id.btn_stepperone_next);
        mEditTextAge = view.findViewById(R.id.editText_stepperone_age);
        mEditTextName = view.findViewById(R.id.editText_stepperone_name);
        mEditTextEmail = view.findViewById(R.id.editText_stepperone_email);
        mEditTextPhone = view.findViewById(R.id.editText_stepperone_phone);
        mEditTextHeight =view.findViewById(R.id.editText_stepperone_height);
        mEditTextWeight = view.findViewById(R.id.editText_stepperone_weight);
        mEditTextLocationCity =view.findViewById(R.id.editText_stepperone_city);
        mEditTextNationality = view.findViewById(R.id.editText_stepperone_nationality);
        mEditTextLocationCountry = view.findViewById(R.id.editText_stepperone_country);
        mTextInputLayoutName = view.findViewById(R.id.textinputLayout_stepperone_name);
        mTextInputLayoutPhone = view.findViewById(R.id.textinputLayout_stepperone_phone);

        areFieldsEdible();
        mPresenter.showUserInfoStepOne();
    }

    private void areFieldsEdible(){
        mEditTextEmail.setEnabled(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNextBtn.setOnClickListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_stepperone_next:
                if (mOnStepOneListener != null && validateData()) {

                    getUserInfoFromForm();
                    mOnStepOneListener.onNextPressed(this);
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStepOneListener) {
            mOnStepOneListener = (OnStepOneListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNextBtn = null;
        mOnStepOneListener = null;
    }

    @Override
    public void showUserInfoStepOneUi(User user) {
        mEditTextAge.setText(user.getAge());
        mEditTextName.setText(user.getName());
        mEditTextEmail.setText(user.getEmail());
        mEditTextWeight.setText(user.getWeight());
        mEditTextHeight.setText(user.getHeight());
        mEditTextPhone.setText(user.getPhoneNumber());
        mEditTextLocationCity.setText(user.getCity());
        mEditTextNationality.setText(user.getNationality());
        mEditTextLocationCountry.setText(user.getCountry());

    }

    public interface OnStepOneListener {
        void onNextPressed(Fragment fragment);
    }

    private void saveUserDataInSp(){

        mSharedPreferences.edit()
                .putString(Constants.USER_NAME, mUserName)
                .putString(Constants.USER_PHONE, mUserPhone)
                .putString(Constants.USER_HEIGHT, mUserHeight)
                .putString(Constants.USER_WEIGHT, mUserWeight)
                .putString(Constants.USER_NATIONALITY, mUserNationality)
                .putString(Constants.USER_LOCATION_CITY, mUserLocationCity)
                .putString(Constants.USER_LOCATION_COUNTRY, mUserLocationCountry)
                .apply();
    }


    private void getUserInfoFromForm(){
        UpdateUserRequest request = new UpdateUserRequest();
        String userAge = mEditTextAge.getText().toString();
        mUserName = mEditTextName.getText().toString();
        mUserPhone = mEditTextPhone.getText().toString();
        mUserHeight = mEditTextHeight.getText().toString();
        mUserWeight = mEditTextWeight.getText().toString();
        mUserNationality = mEditTextNationality.getText().toString();
        mUserLocationCity = mEditTextLocationCity.getText().toString();
        mUserLocationCountry = mEditTextLocationCountry.getText().toString();

        request.token = mUserToken;
        request.user.setName(mUserName);
        request.user.setAge(userAge);
        request.user.setWeight(mUserWeight);
        request.user.setHeight(mUserHeight);
        request.user.setPhoneNumber(mUserPhone);
        request.user.setCity(mUserLocationCity);
        request.user.setCountry(mUserLocationCountry);
        request.user.setNationality(mUserNationality);

        saveUserDataInSp();

        mPresenter.postUserInfoStepOne(request);

    }

    private boolean validateData() {
        boolean result = true;
        String name = mEditTextName.getText().toString();
        if (name.length() < 3) {
            //set the error message
            mTextInputLayoutName.setError(getString(R.string.invalidName));
            result = false;
        } else {
            // remove error message
            mTextInputLayoutName.setErrorEnabled(false);
        }

        String phone = mEditTextPhone.getText().toString();
        boolean digitsOnly = TextUtils.isDigitsOnly(phone);
        // Check if enter text is mUserPhone/ digit. However since the inputtype is phone so it's validated itself no need this line
        if (digitsOnly) {
            if (phone.equals("")) {
                mTextInputLayoutPhone.setError(getString(R.string.invalid_phone_number));
                result = false;
            } else if (phone.equals("0") || phone.length() < 8) {
                mTextInputLayoutPhone.setError(getString(R.string.invalid_phone_number));
                result = false;
            } else {
                mTextInputLayoutPhone.setErrorEnabled(false);
            }
        }
        return result;
    }

}
