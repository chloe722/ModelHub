package thhsu.chloe.ModelHub.Profile;


//import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.LanguageSkill;
import thhsu.chloe.ModelHub.api.model.User;
//import thhsu.chloe.ModelHub.adapters.ProfileInfoAdapter;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileInfoFragment extends Fragment {

    private String mUserToken, mUserBio, mUserExperience;
    private List<LanguageSkill> mUserLanguage;
    private SharedPreferences mSharedPreferences;
    private User mUser = new User();
    private TextView mProfileLanguageTitle, mProfileLanguageText,
            mProfileExperienceTitle, mProfileExperienceText, mProfileBioTitle, mProfileBioText;

    public static ProfileInfoFragment newInstance() { return new ProfileInfoFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);

        mSharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = mSharedPreferences.getString(Constants.USER_TOKEN,"");
        mProfileBioTitle = (TextView) root.findViewById(R.id.profile_user_bio_title);
        mProfileBioText = (TextView) root.findViewById(R.id.profile_user_bio_text);
        mProfileLanguageTitle = (TextView) root.findViewById(R.id.profile_user_language_title);
        mProfileLanguageText = (TextView) root.findViewById(R.id.profile_user_language_text);
        mProfileExperienceTitle = (TextView) root.findViewById(R.id.profile_user_experience_title);
        mProfileExperienceText = (TextView) root.findViewById(R.id.profile_user_experience_text);

        if(!mUserToken.equals("")){
            ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
                @Override
                public void onCompleted(User user) {
                    mUser = user;
                    mUserBio = mUser.getBio();
                    mUserExperience = mUser.getExperience();
                    mUserLanguage = mUser.getLanguages();

                    if(mUserLanguage == null){
                        mProfileLanguageText.setText("");
                    } else{
                        String firstLanguage = mUserLanguage.get(0).getLanguage();
                        String firstLevel = mUserLanguage.get(0).getLevel();
                        String firstLanguageAndLevel = firstLanguage + " - " + firstLevel;
                        mProfileLanguageText.setText(firstLanguageAndLevel);
                    }


//                    if(mUserLanguage.get(1).getLanguage() != null){
//                        String secondLanguage = mUserLanguage.get(1).getLanguage();
//                        String secondLevel = mUserLanguage.get(1).getLevel();
//                        String secondLanguageAndLevel = secondLanguage + " - " + secondLevel;
//                        mProfileLanguageText.setText(firstLanguageAndLevel + "\n" + secondLanguageAndLevel);
//
//                    }

//                    if(mUserLanguage.get(2).getLanguage() != null){
//                        String thirdLanguage = mUserLanguage.get(2).getLanguage();
//                        String thirdLevel = mUserLanguage.get(2).getLevel();
//                        String thirdLanguageAndLevel = thirdLanguage + " - " + thirdLevel;
//                    }

                    if(mUserBio == null){
                        mProfileBioTitle.setVisibility(View.GONE);
                        mProfileBioText.setVisibility(View.GONE);
                    }else{
                        mProfileBioText.setText(mUserBio);
                    }
                    mProfileExperienceText.setText(mUserExperience);

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
