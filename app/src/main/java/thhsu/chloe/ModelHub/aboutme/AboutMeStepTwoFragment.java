package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import thhsu.chloe.ModelHub.utils.Constants;
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
    private User mUser = new User();
    private Button mNextBtn, mBackBtn;
    private OnStepTwoListener mOnStepTwoListener;
    private SharedPreferences mSharedPreferences;
    private EditText mEditTextExperience, mEditTextBio;
    private String mUserToken, mUserExperience, mUserBio;
    private Spinner mSpinnerLanguageOne, mSpinnerLanguageTwo, mSpinnerLanguageThree;
    private RadioGroup mLanguageRadioGroupOne, mLanguageRadioGroupTwo, mLanguageRadioGroupThree;
    private ArrayAdapter<String> mLanguageDataAdapterOne, mLanguageDataAdapterTwo, mLanguageDataAdapterThree;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mSharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = mSharedPreferences.getString(Constants.USER_TOKEN, "");

        mEditTextBio = (EditText) view.findViewById(R.id.editText_steppertwo_bio);
        mEditTextExperience = (EditText) view.findViewById(R.id.editText_steppertwo_experience);
        mLanguageRadioGroupOne = (RadioGroup) view.findViewById(R.id.radiongroup_steppertwo_lan1_level);
        mLanguageRadioGroupTwo = (RadioGroup) view.findViewById(R.id.radiogroup_steppertwo_lan2_level);
        mLanguageRadioGroupThree = (RadioGroup) view.findViewById(R.id.radiogroup_steppertwo_lan3_level);

        mNextBtn = view.findViewById(R.id.stepper_two_next_btn);
        mBackBtn = view.findViewById(R.id.stepper_two_back_btn);

        mSpinnerLanguageOne = (Spinner) view.findViewById(R.id.spinner_steppertwo_language_first);
        mSpinnerLanguageTwo = (Spinner) view.findViewById(R.id.stinner_steppertwo_language_second);
        mSpinnerLanguageThree = (Spinner) view.findViewById(R.id.spinner_steppertwo_language_third);
        mSpinnerLanguageOne.setOnItemSelectedListener(this);
        mSpinnerLanguageTwo.setOnItemSelectedListener(this);
        mSpinnerLanguageThree.setOnItemSelectedListener(this);

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

        mLanguageDataAdapterOne = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesOne);
        mLanguageDataAdapterTwo = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesTwo);
        mLanguageDataAdapterThree = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesTwo);

        mLanguageDataAdapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageDataAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageDataAdapterThree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerLanguageOne.setAdapter(mLanguageDataAdapterOne);
        mSpinnerLanguageTwo.setAdapter(mLanguageDataAdapterTwo);
        mSpinnerLanguageThree.setAdapter(mLanguageDataAdapterThree);


        ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mUser = user;
                List<LanguageSkill> skills = mUser.getLanguages();
                if(skills != null && skills.size() > 0){

                    int spinnerOnePosition = mLanguageDataAdapterOne.getPosition(skills.get(0).getLanguage());
                    mSpinnerLanguageOne.setSelection(spinnerOnePosition);
                    int levelIndex = getLevelIndex(skills.get(0).getLevel());
                    ((RadioButton) mLanguageRadioGroupOne.getChildAt(levelIndex)).setChecked(true);
                    mLanguageRadioGroupOne.getChildAt(levelIndex).jumpDrawablesToCurrentState();
                }

                if(skills != null && skills.size() > 1){

                    int spinnerTwoPosition = mLanguageDataAdapterTwo.getPosition(skills.get(1).getLanguage());
                    mSpinnerLanguageTwo.setSelection(spinnerTwoPosition);
                    int levelIndex = getLevelIndex(skills.get(1).getLevel());
                    ((RadioButton) mLanguageRadioGroupTwo.getChildAt(levelIndex)).setChecked(true);
                    mLanguageRadioGroupTwo.getChildAt(levelIndex).jumpDrawablesToCurrentState();

                }

                if(skills != null && skills.size() > 2){

                    int spinnerThreePosition = mLanguageDataAdapterThree.getPosition(skills.get(2).getLanguage());
                    mSpinnerLanguageThree.setSelection(spinnerThreePosition);
                    int levelIndex = getLevelIndex(skills.get(2).getLevel());
                    ((RadioButton) mLanguageRadioGroupThree.getChildAt(levelIndex)).setChecked(true);
                    mLanguageRadioGroupThree.getChildAt(levelIndex).jumpDrawablesToCurrentState();
                }
                mEditTextExperience.setText(mUser.getExperience());
                mEditTextBio.setText(mUser.getBio());

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    private int getLevelIndex(String level){
        return level.equals("Beginner") ? 0 :
                level.equals("Intermediate") ? 1 :
                        level.equals("Fluent") ? 2 :
                                0;
    }

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
                    mUserExperience = mEditTextExperience.getText().toString();
                    mUserBio = mEditTextBio.getText().toString();
                    request.token = mUserToken;
                    request.user.setBio(mUserBio);
                    request.user.setExperience(mUserExperience);
                    request.user.setLanguages(getLanguageSkill());
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
                    + "must implement OnFragmentInteractionListener");
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
        mSharedPreferences.edit()
                .putString(Constants.USER_BIO, mUserBio)
                .putString(Constants.USER_EXPERIENCE, mUserExperience)
                .putInt(Constants.USER_LANGUAGE_SKILL_ONE, mSpinnerLanguageOne.getSelectedItemPosition())
                .putInt(Constants.USER_LANGUAGE_SKILL_TWO, mSpinnerLanguageTwo.getSelectedItemPosition())
                .putInt(Constants.USER_LANGUAGE_SKILL_THREE, mSpinnerLanguageThree.getSelectedItemPosition())
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
        String selectedLanguageOne = mSpinnerLanguageOne.getSelectedItem().toString();
        String selectedLanguageTwo = mSpinnerLanguageTwo.getSelectedItem().toString();
        String selectedLanguageThree = mSpinnerLanguageThree.getSelectedItem().toString();
        RadioButton selectedProficiencyLanguageOne = (RadioButton) getView().findViewById(selectedLevelLan1Id);
        RadioButton selectedProficiencyLanguageTwo = (RadioButton) getView().findViewById(selectedLevelLan2Id);
        RadioButton selectedProficiencyLanguageThree = (RadioButton) getView().findViewById(selectedLevelLan3Id);
        if(selectedLanguageOne != null){
            skills.add(new LanguageSkill(
                    selectedLanguageOne,
                    selectedProficiencyLanguageOne.getText().toString()
            ));

        }
        if(!selectedLanguageTwo.equals("None")){
            skills.add(new LanguageSkill(
                    selectedLanguageTwo,
                    selectedProficiencyLanguageTwo.getText().toString()
            ));

        }
        if(!selectedLanguageThree.equals("None")){
            skills.add(new LanguageSkill(
                    selectedLanguageThree,
                    selectedProficiencyLanguageThree.getText().toString()
            ));
        }
    return skills;
    }
}
