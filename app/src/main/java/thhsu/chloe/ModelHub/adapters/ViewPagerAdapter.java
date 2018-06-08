package thhsu.chloe.ModelHub.adapters;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import thhsu.chloe.ModelHub.profile.ProfileInfoFragment;
import thhsu.chloe.ModelHub.profile.ProfileWorkbookFragment;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
//        Fragment fragment = null;
//
//        if (position == 0) {
//            fragment = new ProfileInfoFragment();
//        } else if (position == 1) {
//            fragment = new ProfileWorkbookFragment();
//        }
//
//        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = "";
//        if (position == 0) {
//            title = "Info";
//        } else if (position == 1) {
//            title = "WorkBook";
//        }
//
//        return title; //super.getPageTitle(position)
//
//    }

    public void addFragement(Fragment fragment){
        mFragmentList.add(fragment);
    }
}
