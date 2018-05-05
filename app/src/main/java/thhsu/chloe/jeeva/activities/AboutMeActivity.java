package thhsu.chloe.jeeva.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.stepstone.stepper.StepperLayout;

import thhsu.chloe.jeeva.Aboutme.AboutMeStepOneFragment;
import thhsu.chloe.jeeva.Aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.jeeva.Aboutme.AboutMeStepTwoFragment;
import thhsu.chloe.jeeva.Aboutme.NonSwipeableViewPager;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutMeActivity extends AppCompatActivity implements AboutMeStepOneFragment.OnStepOneListener, AboutMeStepTwoFragment.OnStepTwoListener, AboutMeStepThreeFragment.OnStepThreeListener {
    private StepperLayout mStepperLayout;
    private SectionPagerAdapter mSectionPagerAdapter;
    private StepperIndicator stepperIndicator;
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_stepper_layout);
        mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.nonSwapeable_viewpager_container);
        mViewPager.setAdapter(mSectionPagerAdapter);
        stepperIndicator = findViewById(R.id.stepper_Indicater_layout);

        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);

    }

    @Override
    public void onNextPressed(Fragment fragment) {
        if(fragment instanceof AboutMeStepOneFragment){
            mViewPager.setCurrentItem(1,true);
        }else if(fragment instanceof AboutMeStepTwoFragment){
            mViewPager.setCurrentItem(2, true);
        }
    }

    @Override
    public void onCompletePressed(Fragment fragment) {
        if(fragment instanceof AboutMeStepThreeFragment){
            Toast.makeText(this, "Swett! You've completed the profile! ", Toast.LENGTH_SHORT).show();;
            finish();
        }
    }

    @Override
    public void onBackPressed(Fragment fragment) {
        if(fragment instanceof AboutMeStepTwoFragment){
            mViewPager.setCurrentItem(0, true);
        }else if (fragment instanceof AboutMeStepThreeFragment){
            mViewPager.setCurrentItem(1, true);
        }
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter{

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return AboutMeStepOneFragment.newInstance(" "," ");
                case  1:
                    return AboutMeStepTwoFragment.newInstance(" ", " ");
                case 2:
                    return AboutMeStepThreeFragment.newInstance(" ", " ");
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Finish";
            }
            return null;
        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
