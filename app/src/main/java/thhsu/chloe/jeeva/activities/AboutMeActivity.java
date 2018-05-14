package thhsu.chloe.jeeva.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.stepstone.stepper.StepperLayout;

import thhsu.chloe.jeeva.Aboutme.AboutMeStepOneFragment;
import thhsu.chloe.jeeva.Aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.jeeva.Aboutme.AboutMeStepTwoFragment;

import thhsu.chloe.jeeva.Aboutme.NonSwipeableViewPager;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.adapters.AboutMePagerAdapter;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutMeActivity extends AppCompatActivity implements AboutMeStepOneFragment.OnStepOneListener, AboutMeStepTwoFragment.OnStepTwoListener, AboutMeStepThreeFragment.OnStepThreeListener, View.OnClickListener {
    private StepperLayout mStepperLayout;
    private AboutMePagerAdapter mAboutMePagerAdapter;
    private StepperIndicator stepperIndicator;
    private NonSwipeableViewPager mViewPager;
    ImageButton mAboutMeCloseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_stepper_layout);
        mAboutMePagerAdapter = new AboutMePagerAdapter(getSupportFragmentManager());

        mAboutMeCloseBtn = findViewById(R.id.about_me_toolbar_close_btn);
        mViewPager = findViewById(R.id.nonSwapeable_viewpager_container);
        mViewPager.setAdapter(mAboutMePagerAdapter);

        stepperIndicator = findViewById(R.id.stepper_Indicater_layout);
        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);
        mAboutMeCloseBtn.setOnClickListener(this);


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
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.about_me_toolbar_close_btn){
            super.onBackPressed();
        }
    }
}
