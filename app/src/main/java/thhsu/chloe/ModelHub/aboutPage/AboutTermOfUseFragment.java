package thhsu.chloe.ModelHub.aboutPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.R;
//import thhsu.chloe.ModelHub.adapters.AboutPageTermOfUseAdapter;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutTermOfUseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AboutTermOfUseFragment newInstance () { return new AboutTermOfUseFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_aboutpage_termofuse_recyyclerview, container, false);
        return root;
    }
}
