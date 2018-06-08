package thhsu.chloe.ModelHub.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import thhsu.chloe.ModelHub.aboutPage.AboutPageContract;
import thhsu.chloe.ModelHub.aboutPage.AboutPagePresenter;
import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutPageActivity extends AppCompatActivity implements AboutPageContract.View, View.OnClickListener {
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private AboutPageContract.Presenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    public void init(){
        setContentView(R.layout.activity_aboutpage);
        setToolbar();
        mPresenter = new AboutPagePresenter(this, this.getSupportFragmentManager(), mToolbar);
        mPresenter.start();
        ImageButton backBtn = (ImageButton) findViewById(R.id.btn_aboutpage_back);
        backBtn.setOnClickListener(this);
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aboutpage_back:
                super.onBackPressed();
                break;
        }
    }

    private void setToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar_aboutpage);
        getSupportActionBar();
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_aboutpage_title);
        mToolbarTitle.setText("About");
    }

    public void setToolbarTitle(String title){
        mToolbarTitle.setText(title);
    }

    @Override
    public void setPresenter(AboutPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAboutUi() {
        setToolbarTitle("About");
    }

    @Override
    public void showAboutModelHubUi() {
        setToolbarTitle("About ModelHub");
    }

    @Override
    public void showPrivacyPolicyUi() {
        setToolbarTitle("Privacy Policy");
    }

    @Override
    public void showTermOfUsUi() {
        setToolbarTitle("Terms Of Use");
    }

    @Override
    public void showContactUsUi() {
        setToolbarTitle("Contact us");
    }

    public void transToAbout(){
        mPresenter.transToAbout();
    }

    public void transToPrivacyPolicy(){
        mPresenter.transToPrivacyPolicy();
    }

    public void transToTermOfUs(){
        mPresenter.transToTermOfUs();
    }

    public void transToContactUs(){
        mPresenter.transToContactUs();
    }
}
