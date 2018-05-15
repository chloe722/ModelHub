package thhsu.chloe.jeeva.Aboutme;

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

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.PostUserInfoCallBack;
import thhsu.chloe.jeeva.api.model.UpdataUserRequest;
import thhsu.chloe.jeeva.api.model.User;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepOneFragment extends Fragment implements View.OnClickListener {
    private Button mNextBtn;
    private OnStepOneListener mOnStepOneListener;
    EditText mFullName, mEmail,mPhone, mJobTitle, mCurrentWorkCompany, mLocationCountry, mLocationCity;
    TextInputLayout mFullNameLayout;
    TextInputLayout mEmailLayout;
    TextInputLayout mPhoneLayout;
    String fullName, number, email, jobTitle, userLocationCountry, userLocationCity,userLocation, userEmail, userToken;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    User user;

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
        mJobTitle =view.findViewById(R.id.stepper_one_textinput_jobtitle);
        mCurrentWorkCompany = view.findViewById(R.id.stepper_one_textinput_companu);
        mLocationCountry = view.findViewById(R.id.stepper_one_textinput_country);
        mLocationCity =view.findViewById(R.id.stepper_one_textinput_city);
        mFullNameLayout = view.findViewById(R.id.stepper_one_textinputlayout_fullname);
        mEmailLayout = view.findViewById(R.id.stepper_one_textinputlayout_email);
        mPhoneLayout = view.findViewById(R.id.stepper_one_textinputlayout_phone);
        isEmailFieldEdiable();
        sharedPreferences = Jeeva.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        userEmail = sharedPreferences.getString(Constants.USER_EMAIL,"");
        userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        mEmail.setText(userEmail);
    }

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

//        String email = mEmail.getText().toString();
//        if (email == null || !(email.contains("@"))) {
//            mEmailLayout.setError(getString(R.string.invalidEmail));
//            result = false;
//        } else {
//            mEmailLayout.setErrorEnabled(false);
//        }

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

    public void isEmailFieldEdiable(){
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
//                    HashMap<String, String> userdata = new HashMap<>();
                    JSONObject user;
                    UpdataUserRequest request = new UpdataUserRequest();
                    fullName = mFullName.getText().toString();
                    number = mPhone.getText().toString();
//                    email = mEmail.getText().toString();
                    jobTitle = mJobTitle.getText().toString();
                    userLocationCountry = mLocationCountry.getText().toString();
                    userLocationCity = mLocationCity.getText().toString();
                    userLocation = userLocationCity + ", " + userLocationCountry;
                    bundle.putString("fullName", fullName);
                    bundle.putString("phone", number);
//                    bundle.putString("email", userEmail);
                    bundle.putString("jobtitle", jobTitle);
                    bundle.putString("locationCityCountry", userLocation);
                    bundle.putString("locationCountry", userLocationCountry);
                    bundle.putString("locationCity", userLocationCity);
                    request.token = userToken;
                    request.user.name = fullName;
                    request.user.country = userLocationCountry;
                    request.user.city = userLocationCity;
                    request.user.phone_number = number;

                    Log.d("Chloe", "user json object:" + request);

                    ApiJobManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
                                @Override
                                public void onComplete() {
                                    Intent userInfo = new Intent();
                                    userInfo.putExtras(bundle);
                                    getActivity().setResult(Constants.RESULT_SUCCESS, userInfo);
                                    Log.d("Chloe", "profile info bundle: " + userInfo);
                                    Log.d("Chloe", " full name: " + fullName + " phone num: " + number + " email: " + userEmail +
                                            " jobtitle: " + jobTitle + " locationCountry: " + userLocationCountry + "locationcity" + userLocationCity);
                                }

                                @Override
                                public void onError(String errorMessage) {

                                }
                            });
//                    mOnStepOneListener.onNextPressed(this);
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
}
