package thhsu.chloe.ModelHub.aboutpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.activities.AboutPageActivity;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutMainFragment extends Fragment implements View.OnClickListener{
    private AboutPageContract.Presenter mPresenter;

    public static AboutMainFragment newInstance() {return new AboutMainFragment();}

    public AboutMainFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutpage_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnTermOfUse = (Button) view.findViewById(R.id.btn_about_page_termofuse);
        Button btnContactUs = (Button) view.findViewById(R.id.btn_about_page_contactus);
        Button btnPrivacyPolicy = (Button) view.findViewById(R.id.btn_about_page_privacy);
        Button btnModelHub = (Button) view.findViewById(R.id.btn_about_page_about_modelhub);

        btnModelHub.setOnClickListener(this);
        btnTermOfUse.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btnPrivacyPolicy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_about_page_about_modelhub:
                if (getActivity() != null) {
                    ((AboutPageActivity)getActivity()).transToAbout();
                }
                break;

            case R.id.btn_about_page_privacy:
                if (getActivity() != null) {
                    ((AboutPageActivity)getActivity()).transToPrivacyPolicy();
                }
                break;

            case R.id.btn_about_page_termofuse:
                if (getActivity() != null) {
                    ((AboutPageActivity)getActivity()).transToTermOfUs();
                }
                break;

            case R.id.btn_about_page_contactus:
                if (getActivity() != null) {
                    ((AboutPageActivity)getActivity()).transToContactUs();
                }
                break;
        }
    }


}
