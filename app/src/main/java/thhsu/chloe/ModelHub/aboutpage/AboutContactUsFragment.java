package thhsu.chloe.ModelHub.aboutpage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.activities.AboutPageActivity;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutContactUsFragment extends Fragment {

    public static AboutContactUsFragment newInstance () { return new AboutContactUsFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutpage_contactus, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            ((AboutPageActivity) getActivity()).showAboutUi();
        }
    }
}
