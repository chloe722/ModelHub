package thhsu.chloe.ModelHub.Aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiCaseManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.UpdataUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepTwoFragment extends Fragment implements View.OnClickListener {
    private Button mNextBtn, mBackBtn;
    private EditText mFacebookUserNamelText, mGithubUserNameText, mLinkedinUserNameText;
    private TextInputLayout mFacebookTextLayout, mGithubTextLayout, mLinkedinTextLayout;
    private OnStepTwoListener mOnStepTwoListener;
    private String mFacebookUsername, mGithubUsername, mLinkedinUsername, mUserToken;
    SharedPreferences sharedPreferences;
    private User mUser = new User();

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
        mNextBtn = view.findViewById(R.id.stepper_two_next_btn);
        mBackBtn = view.findViewById(R.id.stepper_two_back_btn);
        mFacebookUserNamelText = view.findViewById(R.id.stepper_two_textinput_fb_url);
        mGithubUserNameText = view.findViewById(R.id.stepper_two_textinput_githuburl);
        mLinkedinUserNameText = view.findViewById(R.id.stepper_two_textinput_linkedin_url);
        mFacebookTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_fb);
        mGithubTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_github);
        mLinkedinTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_linkedin);

        ApiCaseManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                mFacebookUserNamelText.setText(mUser.getFacebookAccount());
                mGithubUserNameText.setText(mUser.getGithubAccount());
                mLinkedinUserNameText.setText(mUser.getLinkedinAccount());
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
                    UpdataUserRequest request = new UpdataUserRequest();
                    mFacebookUsername = mFacebookUserNamelText.getText().toString();
                    mGithubUsername = mGithubUserNameText.getText().toString();
                    mLinkedinUsername = mLinkedinUserNameText.getText().toString();
                    request.token = mUserToken;
                    request.user.setFacebookAccount(mFacebookUsername);
                    request.user.setGithubAccount(mGithubUsername);
                    request.user.setLinkedinAccount(mLinkedinUsername);
                    saveUserData();

                    ApiCaseManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
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
