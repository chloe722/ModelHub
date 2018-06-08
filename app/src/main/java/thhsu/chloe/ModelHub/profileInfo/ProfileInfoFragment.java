package thhsu.chloe.ModelHub.profileInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.api.model.LanguageSkill;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileInfoFragment extends Fragment implements ProfileInfoContract.View {

    private ProfileInfoContract.Presenter mPresenter;
    private TextView  mTextViewProfileLanguageText, mTextViewProfileExperienceText, mTextViewProfileBioTitle, mTextViewProfileBioText;


    public static ProfileInfoFragment newInstance() { return new ProfileInfoFragment();}

    @Override
    public void setPresenter(ProfileInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);

        mTextViewProfileBioText = (TextView) root.findViewById(R.id.textview_profile_bio_text);
        mTextViewProfileBioTitle = (TextView) root.findViewById(R.id.textview_profile_bio_title);
        mTextViewProfileLanguageText = (TextView) root.findViewById(R.id.textview_profile_language_text);
        mTextViewProfileExperienceText = (TextView) root.findViewById(R.id.textview_profile_experience_text);

        mPresenter.showUserMoreInfo();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showUserMoreInfo();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showUserMoreInfoUi(User user) {
        String userBio = user.getBio();
        String userExperience = user.getExperience();
        List<LanguageSkill> userLanguage;
        userLanguage = user.getLanguages();

        if (userLanguage == null) {
            mTextViewProfileLanguageText.setText("");
        } else {
            String firstLanguage = userLanguage.get(0).getLanguage();
            String firstLevel = userLanguage.get(0).getLevel();
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

        if (userBio == null) {
            mTextViewProfileBioTitle.setVisibility(View.GONE);
            mTextViewProfileBioText.setVisibility(View.GONE);
        } else {
            mTextViewProfileBioText.setText(userBio);
        }
        mTextViewProfileExperienceText.setText(userExperience);
    }
}
