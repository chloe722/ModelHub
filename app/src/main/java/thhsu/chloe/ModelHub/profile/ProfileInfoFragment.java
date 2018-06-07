package thhsu.chloe.ModelHub.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.LanguageSkill;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileInfoFragment extends Fragment {

    private String mUserBio;
    private String mUserExperience;
    private User mUser = new User();
    private List<LanguageSkill> mUserLanguage;
    private TextView  mTextViewProfileLanguageText, mTextViewProfileExperienceText, mTextViewProfileBioTitle, mTextViewProfileBioText;
//            mTextViewProfileExperienceTitle,mTextViewProfileLanguageTitle,


    public static ProfileInfoFragment newInstance() { return new ProfileInfoFragment();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);

        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        mTextViewProfileBioText = (TextView) root.findViewById(R.id.textview_profile_bio_text);
        mTextViewProfileBioTitle = (TextView) root.findViewById(R.id.textview_profile_bio_title);
        mTextViewProfileLanguageText = (TextView) root.findViewById(R.id.textview_profile_language_text);
        mTextViewProfileExperienceText = (TextView) root.findViewById(R.id.textview_profile_experience_text);
//        mTextViewProfileLanguageTitle = (TextView) root.findViewById(R.id.textview_profile_language_title);
//        mTextViewProfileExperienceTitle = (TextView) root.findViewById(R.id.textview_profile_experience_title);

        if(!userToken.equals("")){
            ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
                @Override
                public void onCompleted(User user) {
                    mUser = user;
                    mUserBio = mUser.getBio();
                    mUserExperience = mUser.getExperience();
                    mUserLanguage = mUser.getLanguages();

                    if (mUserLanguage == null) {
                        mTextViewProfileLanguageText.setText("");
                    } else {
                        String firstLanguage = mUserLanguage.get(0).getLanguage();
                        String firstLevel = mUserLanguage.get(0).getLevel();
                        String firstLanguageAndLevel = firstLanguage + " - " + firstLevel;
                        mTextViewProfileLanguageText.setText(firstLanguageAndLevel);
                    }

//                    if(mUserLanguage.get(1).getLanguage() != null){
//                        String secondLanguage = mUserLanguage.get(1).getLanguage();
//                        String secondLevel = mUserLanguage.get(1).getLevel();
//                        String secondLanguageAndLevel = secondLanguage + " - " + secondLevel;
//                        mTextViewProfileLanguageText.setText(firstLanguageAndLevel + "\n" + secondLanguageAndLevel);
//
//                    }

//                    if(mUserLanguage.get(2).getLanguage() != null){
//                        String thirdLanguage = mUserLanguage.get(2).getLanguage();
//                        String thirdLevel = mUserLanguage.get(2).getLevel();
//                        String thirdLanguageAndLevel = thirdLanguage + " - " + thirdLevel;
//                    }

                    if (mUserBio == null) {
                        mTextViewProfileBioTitle.setVisibility(View.GONE);
                        mTextViewProfileBioText.setVisibility(View.GONE);
                    } else {
                        mTextViewProfileBioText.setText(mUserBio);
                    }
                    mTextViewProfileExperienceText.setText(mUserExperience);
                }

                @Override
                public void onError(String errorMessage) {

                }
            });
        }

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
