package thhsu.chloe.ModelHub.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import thhsu.chloe.ModelHub.Aboutme.AboutMeStepOneFragment;
import thhsu.chloe.ModelHub.Aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.ModelHub.Aboutme.AboutMeStepTwoFragment;

/**
 * Created by Chloe on 5/5/2018.
 */

public class AboutMePagerAdapter extends FragmentPagerAdapter {
    public AboutMePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new AboutMeStepOneFragment() ;
            case 1:
                return new AboutMeStepTwoFragment();
            case 2:
                return new AboutMeStepThreeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
