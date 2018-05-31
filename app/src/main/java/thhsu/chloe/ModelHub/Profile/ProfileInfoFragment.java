package thhsu.chloe.ModelHub.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileInfoFragment extends Fragment {
    public ProfileInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profile_info, container, false);

            return view;
    }
}
