package thhsu.chloe.ModelHub.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import thhsu.chloe.ModelHub.Profile.ProfileContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.adapters.ViewPagerAdapter;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private ViewPagerAdapter mViewPagerAdapter;
    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn, mCameraBtn;
    TextView mUserName, mUserEmail, mUserNumber, mUserHeight, mUserLocation, mUserWeight, mUserNationality, mUserBio, mUserLanguage, mUserExperience;
    ImageView mUserPhotoView, mUserCoverImage;
    private Uri mImageUri;
    Context mContext;
    String userToken, userName, userEmail, userHeight, userLocationCountry, userLocationCity, userLocation, userWeight, userNationality, userBio, userLanguage, userExperience;
    BottomSheetDialog mBottomSheetDialog;
    SharedPreferences sharedPreferences;
    private User mUser = new User();
    private String mCurrentPhotoPath;
    boolean isIconActivate = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_testing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cor);
        setSupportActionBar(toolbar);

         mViewPager = (ViewPager) findViewById(R.id.viewpager_cor);
         mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
         mViewPager.setAdapter(mViewPagerAdapter);
         mTablayout = (TabLayout) findViewById(R.id.profile_tablayout);
         mTablayout.setupWithViewPager(mViewPager);
        mCameraBtn = (Button) findViewById(R.id.profile_camera_btn);
        mUserPhotoView = (ImageView) findViewById(R.id.profile_user_photo);
        mUserName = (TextView) findViewById(R.id.profile_user_name);
        mUserHeight = (TextView) findViewById(R.id.profile_user_height_text);
        mUserLocation = (TextView) findViewById(R.id.profile_user_location);
        mUserWeight = (TextView) findViewById(R.id.profile_user_weight_text);
        mUserNationality = (TextView) findViewById(R.id.profile_user_nationality_text);
        mUserBio = (TextView) findViewById(R.id.profile_user_bio_text);
        mUserExperience = (TextView) findViewById(R.id.profile_user_experience_text);
        mUserLanguage = (TextView) findViewById(R.id.profile_user_language_text);
    }


}
