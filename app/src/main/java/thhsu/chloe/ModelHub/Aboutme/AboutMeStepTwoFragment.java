package thhsu.chloe.ModelHub.Aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.LanguageSkill;
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
    private RadioButton mLanguageGroupOneBeg, mLanguageGroupOneInt, mLanguageGroupOneFlu,
            mLanguageGroupTwoBeg, mLanguageGroupTwoInt, mLanguageGroupTwoFlu, mLanguageGroupThreeBeg,
            mLanguageGroupThreeInt, mLanguageGroupThreeFlu, mSelectedProficiencyLanguageOne, mSelectedProficiencyLanguageTwo, mSelectedProficiencyLanguageThree;
    private RadioGroup mLanguageRadioGroupOne, mLanguageRadioGroupTwo, mLanguageRadioGroupThree;
    private Spinner spinnerLanguageOne, spinnerLanguageTwo, spinnerLanguageThree;
    private ArrayAdapter<String> languageDataAdapterOne, languageDataAdapterTwo, languageDataAdapterThree;


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
        mLanguageGroupOneBeg = (RadioButton) view.findViewById(R.id.radiogroup_one_beg);
        mLanguageGroupOneInt = (RadioButton) view.findViewById(R.id.radiogroup_one_int);
        mLanguageGroupOneFlu = (RadioButton) view.findViewById(R.id.radiogroup_one_flu);
        mLanguageGroupTwoBeg = (RadioButton) view.findViewById(R.id.radiogroup_two_beg);
        mLanguageGroupTwoInt = (RadioButton) view.findViewById(R.id.radiogroup_two_int);
        mLanguageGroupTwoFlu = (RadioButton) view.findViewById(R.id.radiogroup_two_flu);
        mLanguageGroupThreeBeg = (RadioButton) view.findViewById(R.id.radiogroup_three_beg);
        mLanguageGroupThreeInt = (RadioButton) view.findViewById(R.id.radiogroup_three_int);
        mLanguageGroupThreeFlu = (RadioButton) view.findViewById(R.id.radiogroup_three_flu);
        mLanguageRadioGroupOne = (RadioGroup) view.findViewById(R.id.stepper_two_lan1_level_radiogroup);
        mLanguageRadioGroupTwo = (RadioGroup) view.findViewById(R.id.stepper_two_lan2_level_radiogroup);
        mLanguageRadioGroupThree = (RadioGroup) view.findViewById(R.id.stepper_two_lan3_level_radiogroup);

        mNextBtn = view.findViewById(R.id.stepper_two_next_btn);
        mBackBtn = view.findViewById(R.id.stepper_two_back_btn);

        spinnerLanguageOne = (Spinner) view.findViewById(R.id.stepper_two_first_language_spinner);
        spinnerLanguageTwo = (Spinner) view.findViewById(R.id.stepper_two_sec_language_spinner);
        spinnerLanguageThree = (Spinner) view.findViewById(R.id.stepper_two_third_language_spinner);
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

        languageDataAdapterOne = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesOne);
        languageDataAdapterTwo = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesTwo);
        languageDataAdapterThree = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesTwo);

        languageDataAdapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageDataAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageDataAdapterThree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLanguageOne.setAdapter(languageDataAdapterOne);
        spinnerLanguageTwo.setAdapter(languageDataAdapterTwo);
        spinnerLanguageThree.setAdapter(languageDataAdapterThree);
//        spinnerLanguageTwo.setOnItemSelectedListener(this);


        ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                List<LanguageSkill> skills = mUser.getLanguages();
                Log.d("Chloe", "skills" + skills);
                if(skills != null && skills.size() > 0){
                    Log.d("Chloe", "is here 1?");
                    int spinnerOnePosition =languageDataAdapterOne.getPosition(skills.get(0).getLanguage());
                    spinnerLanguageOne.setSelection(spinnerOnePosition);
                    int levelIndex = getLevelIndex(skills.get(0).getLevel());
                    Log.d("Chloe", "level" +levelIndex);
                    Log.d("Chloe", "testtttttttttttt: " + mLanguageRadioGroupOne.getChildAt(levelIndex).toString());
                    ((RadioButton) mLanguageRadioGroupOne.getChildAt(0)).setChecked(false);
                    ((RadioButton) mLanguageRadioGroupOne.getChildAt(levelIndex)).setChecked(true);
                    mLanguageRadioGroupOne.check(mLanguageRadioGroupOne.getChildAt(levelIndex).getId());

                }
                if(skills != null && skills.size() > 1){
                    Log.d("Chloe", "is here 2?");

                    int spinnerTwoPosition =languageDataAdapterTwo.getPosition(skills.get(1).getLanguage());
                    spinnerLanguageTwo.setSelection(spinnerTwoPosition);
                    int levelIndex = getLevelIndex(skills.get(1).getLevel());
                    Log.d("Chloe", "level" +levelIndex);
                    ((RadioButton) mLanguageRadioGroupTwo.getChildAt(0)).setChecked(false);
                    ((RadioButton) mLanguageRadioGroupTwo.getChildAt(levelIndex)).setChecked(true);

                }
                if(skills != null && skills.size() > 2){
                    Log.d("Chloe", "is here 3?");

                    int spinnerThreePosition =languageDataAdapterThree.getPosition(skills.get(2).getLanguage());
                    spinnerLanguageThree.setSelection(spinnerThreePosition);
                    int levelIndex = getLevelIndex(skills.get(2).getLevel());
                    Log.d("Chloe", "level" +levelIndex);
                    ((RadioButton) mLanguageRadioGroupThree.getChildAt(0)).setChecked(false);
                    ((RadioButton) mLanguageRadioGroupThree.getChildAt(levelIndex)).setChecked(true);

                }
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

    private int getLevelIndex(String level){
        return level.equals("Beginner") ? 0 :
                level.equals("Intermediate") ? 1 :
                        level.equals("Fluent") ? 2 :
                                0;
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

                    mUserExperience = mUserExperienceEditedText.getText().toString();
                    mUserBio = mUserBioEditedText.getText().toString();
                    request.token = mUserToken;
                    request.user.setBio(mUserBio);
                    request.user.setExperience(mUserExperience);
                    request.user.setLanguages(getLanguageSkill());
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
        Log.d("Chloe", "item:" + item);

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
                .putString(Constants.USER_EXPERIENCE, mUserExperience)
                .putString(Constants.USER_BIO, mUserBio)
                .putInt(Constants.USER_LANGUAGE_SKILL_ONE, spinnerLanguageOne.getSelectedItemPosition())
                .putInt(Constants.USER_LANGUAGE_SKILL_TWO, spinnerLanguageTwo.getSelectedItemPosition())
                .putInt(Constants.USER_LANGUAGE_SKILL_THREE, spinnerLanguageThree.getSelectedItemPosition())
                .putInt(Constants.USER_LANGUAGE_LEVEL_ONE, mLanguageRadioGroupOne.getCheckedRadioButtonId())
                .putInt(Constants.USER_LANGUAGE_LEVEL_TWO, mLanguageRadioGroupTwo.getCheckedRadioButtonId())
                .putInt(Constants.USER_LANGUAGE_LEVEL_THREE, mLanguageRadioGroupThree.getCheckedRadioButtonId())
                .apply();
    }

    private List<LanguageSkill> getLanguageSkill(){
        List<LanguageSkill> skills = new ArrayList<LanguageSkill>();
        int selectedLevelLan1Id = mLanguageRadioGroupOne.getCheckedRadioButtonId();
        int selectedLevelLan2Id = mLanguageRadioGroupTwo.getCheckedRadioButtonId();
        int selectedLevelLan3Id = mLanguageRadioGroupThree.getCheckedRadioButtonId();
        String selectedLanguageOne = spinnerLanguageOne.getSelectedItem().toString();
        String selectedLanguageTwo = spinnerLanguageTwo.getSelectedItem().toString();
        String selectedLanguageThree = spinnerLanguageThree.getSelectedItem().toString();
        mSelectedProficiencyLanguageOne = (RadioButton) getView().findViewById(selectedLevelLan1Id);
        mSelectedProficiencyLanguageTwo = (RadioButton) getView().findViewById(selectedLevelLan2Id);
        mSelectedProficiencyLanguageThree = (RadioButton) getView().findViewById(selectedLevelLan3Id);
        Log.d("Chloe", "selectedLevelLan1Id: " + mSelectedProficiencyLanguageOne);
        Log.d("Chloe", "selectedLeve2Lan1Id: " + mSelectedProficiencyLanguageTwo);
        Log.d("Chloe", "selectedLeve3Lan1Id: " + mSelectedProficiencyLanguageThree);
        Log.d("Chloe", "selected Language1: " + selectedLanguageOne);
        Log.d("Chloe", "selected Language2: " + selectedLanguageTwo);
        Log.d("Chloe", "selected Language3: " + selectedLanguageThree);
        if(selectedLanguageOne != null){
            skills.add(new LanguageSkill(
                    selectedLanguageOne,
                    mSelectedProficiencyLanguageOne.getText().toString()
            ));
            Log.d("Chloe", "skills" + skills);

        }
        if(!selectedLanguageTwo.equals("None")){
            skills.add(new LanguageSkill(
                    selectedLanguageTwo,
                    mSelectedProficiencyLanguageTwo.getText().toString()
            ));
            Log.d("Chloe", "skills" + skills);

        }
        if(!selectedLanguageThree.equals("None")){
            skills.add(new LanguageSkill(
                    selectedLanguageThree,
                    mSelectedProficiencyLanguageThree.getText().toString()
            ));
            Log.d("Chloe", "skills" + skills);
        }
        Log.d("Chloe", "skills" + skills);
    return skills;
    }
}
