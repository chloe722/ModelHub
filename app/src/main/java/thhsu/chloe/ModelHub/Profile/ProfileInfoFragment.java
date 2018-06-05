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
    private TextView mProfileLanguageTitle, mProfileLanguageText, mProfileExperienceTitle, mProfileExperienceText;

    public static ProfileInfoFragment newInstance() { return new ProfileInfoFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);

        mSharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = mSharedPreferences.getString(Constants.USER_TOKEN,"");
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
