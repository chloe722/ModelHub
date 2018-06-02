package thhsu.chloe.ModelHub.Profile;


//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
//import thhsu.chloe.ModelHub.adapters.ProfileInfoAdapter;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileInfoFragment extends Fragment {
    private TextView profileLanguageTitle, profileLanguageText, profileExperienceTitle, profileExperienceText;
//    ProfileInfoAdapter mAdapter;
    public ProfileInfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAdapter = new ProfileInfoAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);

//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.profileinfo_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
//        recyclerView.setAdapter(mAdapter);

        profileLanguageTitle = (TextView) root.findViewById(R.id.profile_user_language_title);
        profileLanguageText = (TextView) root.findViewById(R.id.profile_user_language_text);
        profileExperienceTitle = (TextView) root.findViewById(R.id.profile_user_experience_title);
        profileExperienceText = (TextView) root.findViewById(R.id.profile_user_experience_text);

        return root;
    }
}
