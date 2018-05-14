package thhsu.chloe.jeeva.activities;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutpageActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton mBackBtn;
    Button mJeevaBtn, mTermOfUseBtn, mPrivacyPolicyBtn, mContactBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        mBackBtn = (ImageButton) findViewById(R.id.about_page_back_btn);
        mJeevaBtn = (Button) findViewById(R.id.about_page_about_jeeva_btn);
        mPrivacyPolicyBtn = (Button) findViewById(R.id.about_page_privacy_btn);
        mTermOfUseBtn = (Button) findViewById(R.id.about_page_termofuse_btn);
        mContactBtn = (Button) findViewById(R.id.about_page_contactus_btn);

        mBackBtn.setOnClickListener(this);
        mJeevaBtn.setOnClickListener(this);
        mTermOfUseBtn.setOnClickListener(this);
        mContactBtn.setOnClickListener(this);
        mPrivacyPolicyBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_page_back_btn:
                super.onBackPressed();
                break;
            case R.id.about_page_about_jeeva_btn:
                break;
            case R.id.about_page_privacy_btn:
                break;
            case R.id.about_page_termofuse_btn:
                break;
            case R.id.about_page_contactus_btn:
                break;
        }
    }
}
