package thhsu.chloe.ModelHub.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import thhsu.chloe.ModelHub.aboutme.AboutMeStepOneFragment;
import thhsu.chloe.ModelHub.aboutme.AboutMeStepThreeFragment;
import thhsu.chloe.ModelHub.aboutme.AboutMeStepTwoFragment;

/**
 * Created by Chloe on 5/5/2018.
 */

public class AboutMeViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    public AboutMeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position){
//            case 0:
//                return new AboutMeStepOneFragment() ;
//            case 1:
//                return new AboutMeStepTwoFragment();
//            case 2:
//                return new AboutMeStepThreeFragment();
//        }
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }

}
