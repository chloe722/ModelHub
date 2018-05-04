package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStapperAdapter extends AbstractFragmentStepAdapter {

    
    public AboutMeStapperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
