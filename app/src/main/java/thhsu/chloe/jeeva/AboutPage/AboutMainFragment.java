package thhsu.chloe.jeeva.AboutPage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.activities.AboutpageActivity;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutMainFragment extends Fragment implements View.OnClickListener{
    Button mJeevaBtn, mTermOfUseBtn, mPrivacyPolicyBtn, mContactBtn;
    AboutPageContract.Presenter mPresenter;

    public static AboutMainFragment newInstance() {return new AboutMainFragment();}

    public AboutMainFragment() {}



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutpage_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mJeevaBtn = (Button) view.findViewById(R.id.about_page_about_jeeva_btn);
        mPrivacyPolicyBtn = (Button) view.findViewById(R.id.about_page_privacy_btn);
        mTermOfUseBtn = (Button) view.findViewById(R.id.about_page_termofuse_btn);
        mContactBtn = (Button) view.findViewById(R.id.about_page_contactus_btn);
        mJeevaBtn.setOnClickListener(this);
        mPrivacyPolicyBtn.setOnClickListener(this);
        mTermOfUseBtn.setOnClickListener(this);
        mContactBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_page_about_jeeva_btn:
                ((AboutpageActivity)getActivity()).transToAbout();
                break;

            case R.id.about_page_privacy_btn:
                ((AboutpageActivity)getActivity()).transToPrivacyPolicy();
                break;

            case R.id.about_page_termofuse_btn:
                ((AboutpageActivity)getActivity()).transToTermOfUs();
                break;

            case R.id.about_page_contactus_btn:
                ((AboutpageActivity)getActivity()).transToContactUs();
                break;
        }
    }


}
