package thhsu.chloe.ModelHub.Aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

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

public class AboutMeStepTwoFragment extends Fragment implements View.OnClickListener, Spinner.OnItemSelectedListener {
    private Button mNextBtn, mBackBtn;
    private EditText mFacebookUserNamelText, mGithubUserNameText, mLinkedinUserNameText, mUserExperienceEditedText, mUserBioEditedText;
    private TextInputLayout mFacebookTextLayout, mGithubTextLayout, mLinkedinTextLayout;
    private OnStepTwoListener mOnStepTwoListener;
    private String mFacebookUsername, mGithubUsername, mLinkedinUsername, mUserToken, mUserExperience, mUserBio;
    SharedPreferences sharedPreferences;
    private User mUser = new User();
    private RadioButton mLanguageGroupOneBeg, mLanguageGroupOneInt, mLanguageGroupOneFlu, mLanguageGroupTwoBeg
            , mLanguageGroupTwoInt, mLanguageGroupTwoFlu, mLanguageGroupThreeBeg, mLanguageGroupThreeInt, mLanguageGroupThreeFlu;


    public AboutMeStepTwoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        mUserExperienceEditedText = (EditText) view.findViewById(R.id.stepper_two_experience_content);
        mUserBioEditedText = (EditText) view.findViewById(R.id.stepper_two_bio_content);
        mNextBtn = view.findViewById(R.id.stepper_two_next_btn);
        mBackBtn = view.findViewById(R.id.stepper_two_back_btn);

        Spinner spinnerLanguageOne = (Spinner) view.findViewById(R.id.stepper_two_first_language_spinner);
        Spinner spinnerLanguageTwo = (Spinner) view.findViewById(R.id.stepper_two_sec_language_spinner);
        Spinner spinnerLanguageThree = (Spinner) view.findViewById(R.id.stepper_two_third_language_spinner);
        spinnerLanguageOne.setOnItemSelectedListener(this);
        spinnerLanguageTwo.setOnItemSelectedListener(this);
        spinnerLanguageThree.setOnItemSelectedListener(this);

        List<String> categoriesOne = new ArrayList<String>();
        categoriesOne.add("English");
        categoriesOne.add("Mandarin");
        categoriesOne.add("Spanish");
        categoriesOne.add("Russian");
        categoriesOne.add("German");
        categoriesOne.add("French");

        List<String> categoriesTwo = new ArrayList<String>();
        categoriesTwo.add("None");
        categoriesTwo.add("English");
        categoriesTwo.add("Mandarin");
        categoriesTwo.add("Spanish");
        categoriesTwo.add("Russian");
        categoriesTwo.add("German");
        categoriesTwo.add("French");

        ArrayAdapter<String> languageDataAdapterOne = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesOne);
        ArrayAdapter<String> languageDataAdapterThree = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesTwo);
        languageDataAdapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageDataAdapterThree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLanguageOne.setAdapter(languageDataAdapterOne);
        spinnerLanguageTwo.setAdapter(languageDataAdapterThree);
        spinnerLanguageThree.setAdapter(languageDataAdapterThree);


        ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                mUserExperienceEditedText.setText(mUser.getExperience());
                mUserBioEditedText.setText(mUser.getBio());
//                mFacebookUserNamelText.setText(mUser.getFacebookAccount());
//                mGithubUserNameText.setText(mUser.getGithubAccount());
//                mLinkedinUserNameText.setText(mUser.getLinkedinAccount());

            }

            @Override
            public void onError(String errorMessage) {
                mFacebookUserNamelText.setText("");
                mGithubUserNameText.setText("");
                mLinkedinUserNameText.setText("");
            }
        });
    }

//    public boolean validateUrl(){
//        boolean isUrlFormat = true;
//        String facebookUrl = mFacebookUrlText.getText().toString();
//        if (!(Patterns.WEB_URL.matcher(facebookUrl).matches())){
//            mFacebookTextLayout.setError(getString(R.string.invalidUrl));
//            isUrlFormat = false;
//            Log.d("Chloe", "facebook url format testing: " + Patterns.WEB_URL.matcher(facebookUrl).matches());
//        }else{
//            mFacebookTextLayout.setErrorEnabled(false);
//        }
//        return isUrlFormat;
//    }

    @Override
    public void onResume() {
        super.onResume();
        mNextBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNextBtn.setOnClickListener(null);
        mBackBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stepper_two_next_btn:
                if(mOnStepTwoListener != null){
                    UpdateUserRequest request = new UpdateUserRequest();
//                    mFacebookUsername = mFacebookUserNamelText.getText().toString();
//                    mGithubUsername = mGithubUserNameText.getText().toString();
//                    mLinkedinUsername = mLinkedinUserNameText.getText().toString();
                    mUserExperience = mUserExperienceEditedText.getText().toString();
                    mUserBio = mUserBioEditedText.getText().toString();
                    request.token = mUserToken;
                    request.user.setBio(mUserBio);
                    request.user.setExperience(mUserExperience);
//                    request.user.setFacebookAccount(mFacebookUsername);
//                    request.user.setGithubAccount(mGithubUsername);
//                    request.user.setLinkedinAccount(mLinkedinUsername);
                    saveUserData();

                    ApiJobManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
                        @Override
                        public void onComplete() {
                        }
                        @Override
                        public void onError(String errorMessage) {
                        }
                    });

                    mOnStepTwoListener.onNextPressed(this);
                }
                break;
            case R.id.stepper_two_back_btn:
                if(mOnStepTwoListener != null){
                    mOnStepTwoListener.onBackPressed(this);
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnStepTwoListener){
            mOnStepTwoListener = (OnStepTwoListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnStepTwoListener = null;
        mNextBtn = null;
        mBackBtn = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnStepTwoListener {
        void onNextPressed(Fragment fragment);
        void onBackPressed(Fragment fragment);
    }

    public void saveUserData(){
        sharedPreferences.edit()
                .putString(Constants.USER_FACEBOOK_USERNAME, mFacebookUsername)
                .putString(Constants.USER_GITHUB_USERNAME, mGithubUsername)
                .putString(Constants.USER_LINKEDIN_USERNAME, mLinkedinUsername)
                .apply();
    }
}
