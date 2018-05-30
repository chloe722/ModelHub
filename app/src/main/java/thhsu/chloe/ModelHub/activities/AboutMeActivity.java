package thhsu.chloe.ModelHub.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
//import com.stepstone.stepper.StepperLayout;

import thhsu.chloe.ModelHub.Aboutme.AboutMeStepOneFragment;
import thhsu.chloe.ModelHub.Aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.ModelHub.Aboutme.AboutMeStepTwoFragment;

import thhsu.chloe.ModelHub.Aboutme.NonSwipeableViewPager;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.adapters.AboutMePagerAdapter;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutMeActivity extends AppCompatActivity implements AboutMeStepOneFragment.OnStepOneListener, AboutMeStepTwoFragment.OnStepTwoListener, AboutMeStepThreeFragment.OnStepThreeListener {
//    private StepperLayout mStepperLayout;
    private AboutMePagerAdapter mAboutMePagerAdapter;
    private StepperIndicator stepperIndicator;
    private NonSwipeableViewPager mViewPager;
    ImageButton mAboutMeCloseBtn;
    SharedPreferences sharedPreferences;
    String userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_stepper_layout);
        mAboutMePagerAdapter = new AboutMePagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.nonSwapeable_viewpager_container);
        mViewPager.setAdapter(mAboutMePagerAdapter);
        stepperIndicator = findViewById(R.id.stepper_Indicater_layout);
        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);
        sharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
    }

    @Override
    public void onNextPressed(Fragment fragment) {
        Log.d("Chloe", "Nextpress viewpager currentitem: " + mViewPager.getCurrentItem());
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1,true);
    }

    @Override
    public void onBackPressed(Fragment fragment) {
        Log.d("Chloe", "backpress viewpager currentitem: " + mViewPager.getCurrentItem());
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1,true);

    }

    @Override
    public void onSwitchPressedOn(Fragment fragment) {
        Toast.makeText(this, "Notfication on! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwitchPressedOff(Fragment fragment) {
        Toast.makeText(this, "Notification off. You'll not receive notification from WeTogether system", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletePressed(Fragment fragment) {
        if(fragment instanceof AboutMeStepThreeFragment){
            Toast.makeText(this, "Sweet! You've completed the profile! ", Toast.LENGTH_SHORT).show();
            clearUserData();
            setResult(Constants.RESULT_SUCCESS);
            finish();
        }
    }

    public void clearUserData(){
        sharedPreferences.edit()
                .remove(Constants.USER_PHONENUM)
                .remove(Constants.USER_LOCATION_COUNTRY)
                .remove(Constants.USER_LOCATION_CITY)
                .remove(Constants.USER_CASE_TITLE)
                .remove(Constants.USER_NATIONALITY)
                .remove(Constants.USER_HEIGHT)
                .remove(Constants.USER_WEIGHT)
                .remove(Constants.USER_LANGUAGE_LEVEL_ONE)
                .remove(Constants.USER_LANGUAGE_LEVEL_TWO)
                .remove(Constants.USER_LANGUAGE_LEVEL_THREE)
                .remove(Constants.USER_LANGUAGE_SKILL_ONE)
                .remove(Constants.USER_LANGUAGE_SKILL_TWO)
                .remove(Constants.USER_LANGUAGE_SKILL_THREE)
                .apply();
    }

//    @Override
//    public void onClick(View v) {
//        if(v.getId() == R.id.about_me_toolbar_close_btn){
//            super.onBackPressed();
//        }
//    }
}
