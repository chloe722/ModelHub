package thhsu.chloe.ModelHub.Aboutme;

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
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepOneFragment extends Fragment implements View.OnClickListener {
    private Button mNextBtn;
    private OnStepOneListener mOnStepOneListener;
    EditText mFullName, mEmail,mPhone, mCaseTitle, mCurrentWorkCompany, mLocationCountry, mLocationCity;
    TextInputLayout mFullNameLayout;
    TextInputLayout mEmailLayout;
    TextInputLayout mPhoneLayout;
    String fullName, number, email, caseTitle, userLocationCountry, userLocationCity,userLocation, userEmail, userToken, userJobTitle, userCurrentCompany;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    private User mUser = new User();

    public AboutMeStepOneFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNextBtn = view.findViewById(R.id.stepper_one_next_btn);
        mFullName = view.findViewById(R.id.stepper_one_textinput_fullname);
        mEmail = view.findViewById(R.id.stepper_one_textinput_email);
        mPhone = view.findViewById(R.id.stepper_one_textinput_phone);
        mCaseTitle =view.findViewById(R.id.stepper_one_textinput_casetitle);
        mCurrentWorkCompany = view.findViewById(R.id.stepper_one_textinput_companu);
        mLocationCountry = view.findViewById(R.id.stepper_one_textinput_country);
        mLocationCity =view.findViewById(R.id.stepper_one_textinput_city);
        mFullNameLayout = view.findViewById(R.id.stepper_one_textinputlayout_fullname);
        mEmailLayout = view.findViewById(R.id.stepper_one_textinputlayout_email);
        mPhoneLayout = view.findViewById(R.id.stepper_one_textinputlayout_phone);
        areFieldsEdiable();
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");


        ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                mFullName.setText(mUser.getName());
                mEmail.setText(mUser.getEmail());
                mPhone.setText(mUser.getPhoneNumber());
                mLocationCity.setText(mUser.getCity());
                mLocationCountry.setText(mUser.getCountry());
                mCaseTitle.setText(mUser.getCaseTitle());
                mCurrentWorkCompany.setText(mUser.getCurrentCompany());

            }

            @Override
            public void onError(String errorMessage) {
                mFullName.setText("");
                mEmail.setText("");
                mPhone.setText("");
                mLocationCity.setText("");
                mLocationCountry.setText("");
                mCaseTitle.setText("");
                mCurrentWorkCompany.setText("");
            }
        });


    }
//        readUserData();

    private boolean validateData() {
        boolean result = true;
        String name = mFullName.getText().toString();
        if (name == null || name.length() < 3) {
            //set the error message
            mFullNameLayout.setError(getString(R.string.invalidName));
            result = false;
        } else {
            // remove error message
            mFullNameLayout.setErrorEnabled(false);
        }

        String phone = mPhone.getText().toString();
        boolean digitsOnly = TextUtils.isDigitsOnly(phone); // Check if enter text is number/ digit. However since the inputtype is phone so it's validated itself no need this line
        if(digitsOnly){
            if (phone == null || phone.equals("")) {
                mPhoneLayout.setError(getString(R.string.invalidPhoneNumber));
                result = false;
            } else if (phone.equals("0") || phone.length() < 8) {
                mPhoneLayout.setError(getString(R.string.invalidPhoneNumber));
                result = false;
            } else {
                mPhoneLayout.setErrorEnabled(false);
            }
        }
        return result;
    }

    public void areFieldsEdiable(){
        mFullName.setEnabled(false);
         mEmail.setEnabled(false);
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
            case R.id.stepper_one_next_btn:
                if(mOnStepOneListener != null && validateData()){
                    UpdateUserRequest request = new UpdateUserRequest();
                    number = mPhone.getText().toString();
                    caseTitle = mCaseTitle.getText().toString();
                    userLocationCountry = mLocationCountry.getText().toString();
                    userLocationCity = mLocationCity.getText().toString();
                    userLocation = userLocationCity + ", " + userLocationCountry;
                    userJobTitle = mCaseTitle.getText().toString();
                    userCurrentCompany = mCurrentWorkCompany.getText().toString();
                    bundle.putString("phone", number);
                    bundle.putString("jobtitle", caseTitle);
                    bundle.putString("locationCityCountry", userLocation);
                    bundle.putString("locationCountry", userLocationCountry);
                    bundle.putString("locationCity", userLocationCity);
                    request.token = userToken;
                    request.user.setPhoneNumber(number);
                    request.user.setCity(userLocationCity);
                    request.user.setCountry(userLocationCountry);
                    request.user.setJobTitle(userJobTitle);
                    request.user.setCurrentCompany(userCurrentCompany);
                    saveUserData();

                    Log.d("Chloe", "user json object:" + request);
                    ApiJobManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
                                @Override
                                public void onComplete() {
                                    Intent userInfo = new Intent();
                                    userInfo.putExtras(bundle);
                                    getActivity().setResult(Constants.RESULT_SUCCESS, userInfo);
                                    Log.d("Chloe", "profile info bundle: " + userInfo);
                                    Log.d("Chloe",  " phone num: " + number + " jobtitle: " + caseTitle + " locationCountry: " + userLocationCountry + "locationcity" + userLocationCity);
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
        if(context instanceof OnStepOneListener){
            mOnStepOneListener = (OnStepOneListener) context;
        }else{
            throw new RuntimeException(context.toString()
                        + "must implement OnFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnStepOneListener = null;
        mNextBtn = null;
    }

    public interface OnStepOneListener {
        void onNextPressed(Fragment fragment);
    }

    public void saveUserData(){
        sharedPreferences.edit()
                .putString(Constants.USER_PHONENUM, number)
                .putString(Constants.USER_CASE_TITLE, caseTitle)
                .putString(Constants.USER_LOCATION_COUNTRY, userLocationCountry)
                .putString(Constants.USER_LOCATION_CITY, userLocationCity)
                .putString(Constants.USER_LOCATION, userLocation)
                .putString(Constants.USER_CASE_TITLE, userJobTitle)
                .putString(Constants.USER_CURRENT_COMPANY, userCurrentCompany)
                .apply();
    }

    public void readUserData(){
        mFullName.setText(sharedPreferences.getString(Constants.USER_NAME, ""));
        mEmail.setText(sharedPreferences.getString(Constants.USER_EMAIL,""));
        mPhone.setText(sharedPreferences.getString(Constants.USER_PHONENUM, ""));
        mLocationCountry.setText(sharedPreferences.getString(Constants.USER_LOCATION_COUNTRY, ""));
        mLocationCity.setText(sharedPreferences.getString(Constants.USER_LOCATION_CITY, ""));
        mCaseTitle.setText(sharedPreferences.getString(Constants.USER_CASE_TITLE, ""));
    }

}
