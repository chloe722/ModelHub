package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepOneFragment extends Fragment implements View.OnClickListener {
    private Bundle bundle;
    private Button mNextBtn;
    private User mUser = new User();
    private OnStepOneListener mOnStepOneListener;
    private EditText mEditTextName, mEditTextEmail, mEditTextPhone, mEditTextHeight, mEditTextAge, mEditTextWeight,
            mEditTextNationality, mEditTextLocationCountry, mEditTextLocationCity;
    private TextInputLayout mTextInputLayoutName, mTextInputLayoutEmail, mTextInputLayoutPhone;
    private String mUserName, mUserPhone, mUserHeight, mUserWeight, mUserLocationCountry, mUserLocationCity, mUserNationality,
            mUserLocation, mUserEmail, mUserToken, mUserAge;
    private SharedPreferences mSharedPreferences;

    public AboutMeStepOneFragment() {}

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
        mTextInputLayoutEmail = view.findViewById(R.id.textinputLayout_stepperone_email);
        mTextInputLayoutPhone = view.findViewById(R.id.textinputLayout_stepperone_phone);

        areFieldsEdible();

        ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                mEditTextAge.setText(mUser.getAge());
                mEditTextName.setText(mUser.getName());
                mEditTextEmail.setText(mUser.getEmail());
                mEditTextWeight.setText(mUser.getWeight());
                mEditTextHeight.setText(mUser.getHeight());
                mEditTextPhone.setText(mUser.getPhoneNumber());
                mEditTextLocationCity.setText(mUser.getCity());
                mEditTextNationality.setText(mUser.getNationality());
                mEditTextLocationCountry.setText(mUser.getCountry());
            }

            @Override
            public void onError(String errorMessage) {
                mEditTextAge.setText("");
                mEditTextName.setText("");
                mEditTextEmail.setText("");
                mEditTextPhone.setText("");
                mEditTextHeight.setText("");
                mEditTextWeight.setText("");
                mEditTextNationality.setText("");
                mEditTextLocationCity.setText("");
                mEditTextLocationCountry.setText("");

            }
        });
    }

    private boolean validateData() {
        boolean result = true;
        String name = mEditTextName.getText().toString();

        if (name == null || name.length() < 3) {
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
            if (phone == null || phone.equals("")) {
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

    public void areFieldsEdible(){
        mEditTextName.setEnabled(false);
        mEditTextEmail.setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNextBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        bundle = new Bundle();
        switch (v.getId()){
            case R.id.btn_stepperone_next:
                if(mOnStepOneListener != null && validateData()){
                    UpdateUserRequest request = new UpdateUserRequest();

                    mUserAge = mEditTextAge.getText().toString();
                    mUserPhone = mEditTextPhone.getText().toString();
                    mUserHeight = mEditTextHeight.getText().toString();
                    mUserWeight = mEditTextWeight.getText().toString();
                    mUserNationality = mEditTextNationality.getText().toString();
                    mUserLocationCity = mEditTextLocationCity.getText().toString();
                    mUserLocationCountry = mEditTextLocationCountry.getText().toString();
//                    mUserLocation = mUserLocationCity +  + mUserLocationCountry;
//                    bundle.putString("locationCityCountry", mUserLocation);
                    bundle.putString("phone", mUserPhone);
                    bundle.putString("userHeight", mUserHeight);
                    bundle.putString("userWeight", mUserWeight);
                    bundle.putString("userNationality", mUserNationality);
                    bundle.putString("locationCountry", mUserLocationCountry);
                    bundle.putString("locationCity", mUserLocationCity);

                    request.token = mUserToken;
                    request.user.setAge(mUserAge);
                    request.user.setWeight(mUserWeight);
                    request.user.setHeight(mUserHeight);
                    request.user.setPhoneNumber(mUserPhone);
                    request.user.setCity(mUserLocationCity);
                    request.user.setCountry(mUserLocationCountry);
                    request.user.setNationality(mUserNationality);
                    saveUserData();

                    ApiJobManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
                                @Override
                                public void onComplete() {
                                    Intent userInfo = new Intent();
                                    userInfo.putExtras(bundle);
                                    getActivity().setResult(Constants.RESULT_SUCCESS, userInfo);
                                    Log.d("Chloe", "profile info bundle: " + userInfo);
                                    Log.d("Chloe",  " phone num: " + mUserPhone + " jobtitle: " +
                                            mUserHeight + " locationCountry: " + mUserLocationCountry + "locationcity" + mUserLocationCity);
                                }
                                @Override
                                public void onError(String errorMessage) {
                                }
                             });

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

    public interface OnStepOneListener {
        void onNextPressed(Fragment fragment);
    }

    public void saveUserData(){

        mSharedPreferences.edit()
                .putString(Constants.USER_PHONE, mUserPhone)
                .putString(Constants.USER_HEIGHT, mUserHeight)
                .putString(Constants.USER_WEIGHT, mUserWeight)
                .putString(Constants.USER_NATIONALITY, mUserNationality)
                .putString(Constants.USER_LOCATION_CITY, mUserLocationCity)
                .putString(Constants.USER_LOCATION_COUNTRY, mUserLocationCountry)
//                .putString(Constants.USER_LOCATION, mUserLocation)
                .apply();
    }

    public void readUserData(){
        mEditTextName.setText(mSharedPreferences.getString(Constants.USER_NAME, ""));
        mEditTextEmail.setText(mSharedPreferences.getString(Constants.USER_EMAIL,""));
        mEditTextPhone.setText(mSharedPreferences.getString(Constants.USER_PHONE, ""));
        mEditTextHeight.setText(mSharedPreferences.getString(Constants.USER_HEIGHT, ""));
        mEditTextLocationCity.setText(mSharedPreferences.getString(Constants.USER_LOCATION_CITY, ""));
        mEditTextLocationCountry.setText(mSharedPreferences.getString(Constants.USER_LOCATION_COUNTRY, ""));
    }

}
