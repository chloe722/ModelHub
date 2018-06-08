package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

import thhsu.chloe.ModelHub.aboutme.AboutMeStepOneFragment;
import thhsu.chloe.ModelHub.aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.ModelHub.aboutme.AboutMeStepTwoFragment;

import thhsu.chloe.ModelHub.aboutme.NonSwipeableViewPager;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.adapters.AboutMePagerAdapter;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutMeActivity extends AppCompatActivity implements AboutMeStepOneFragment.OnStepOneListener, AboutMeStepTwoFragment.OnStepTwoListener, AboutMeStepThreeFragment.OnStepThreeListener {
    private NonSwipeableViewPager mViewPager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        AboutMePagerAdapter aboutMePagerAdapter
                = new AboutMePagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.nonSwapeable_viewpager_container);
        mViewPager.setAdapter(aboutMePagerAdapter);

        StepperIndicator stepperIndicator
                = findViewById(R.id.layout_stepperIndicater);
        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);

        sharedPreferences = this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
    }

    @Override
    public void onNextPressed(Fragment fragment) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }

    @Override
    public void onBackPressed(Fragment fragment) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);

    }

    @Override
    public void onSwitchPressedOn(Fragment fragment) {
        Toast.makeText(this, "Notification on! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwitchPressedOff(Fragment fragment) {
        Toast.makeText(this,
                "Notification off. You'll not receive notification from WeTogether system", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletePressed(Fragment fragment) {
        if (fragment instanceof AboutMeStepThreeFragment) {
            Toast.makeText(this, "Sweet! You've completed the profile! ", Toast.LENGTH_SHORT).show();
            clearUserData();
            finish();
        }
    }

    public void clearUserData() {
        sharedPreferences.edit()
                .remove(Constants.USER_PHONE)
                .remove(Constants.USER_HEIGHT)
                .remove(Constants.USER_WEIGHT)
                .remove(Constants.USER_NATIONALITY)
                .remove(Constants.USER_LOCATION_CITY)
                .remove(Constants.USER_LOCATION_COUNTRY)
                .remove(Constants.USER_LANGUAGE_LEVEL_ONE)
                .remove(Constants.USER_LANGUAGE_LEVEL_TWO)
                .remove(Constants.USER_LANGUAGE_LEVEL_THREE)
                .remove(Constants.USER_LANGUAGE_SKILL_ONE)
                .remove(Constants.USER_LANGUAGE_SKILL_TWO)
                .remove(Constants.USER_LANGUAGE_SKILL_THREE)
                .apply();
    }
}
