package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private Context mContext;

    public ProfileViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){

            case 0:
//                mDrawable = mContext.getDrawable(R.drawable.ic_add_black_24dp);
                title = "Info";
                break;

            case 1:
                title = "WorkBook";
                break;
        }

        return title;

    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }
}
