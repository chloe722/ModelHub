package thhsu.chloe.ModelHub.AboutPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutModelHubFragment extends Fragment {

    public static AboutModelHubFragment newInstance () { return new AboutModelHubFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutpage_about_modelhub, container, false);
    }
}
