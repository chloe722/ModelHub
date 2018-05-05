package thhsu.chloe.jeeva.Aboutme;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepTwoFragment extends Fragment implements View.OnClickListener {
    private Button mNextBtn, mBackBtn;
    private EditText mFacebookUrlText, mGithubUrlText, mLinkedinUrlText;
    private TextInputLayout mFacebookTextLayout, mGithubTextLayout, mLinkedinTextLayout;
    private OnStepTwoListener mOnStepTwoListener;

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
        mNextBtn = view.findViewById(R.id.stepper_two_next_btn);
        mBackBtn = view.findViewById(R.id.stepper_two_back_btn);
        mFacebookUrlText = view.findViewById(R.id.stepper_two_textinput_fb_url);
        mGithubUrlText = view.findViewById(R.id.stepper_two_textinput_githuburl);
        mLinkedinUrlText = view.findViewById(R.id.stepper_two_textinput_linkedin_url);
        mFacebookTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_fb);
        mGithubTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_github);
        mLinkedinTextLayout = view.findViewById(R.id.stepper_two_textinputlayout_linkedin);
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
}
