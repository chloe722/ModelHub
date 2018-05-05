package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

//import thhsu.chloe.jeeva.Aboutme.AboutMeFragment;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/4/2018.
 */

//public class AboutMeStepperAdapter extends AbstractFragmentStepAdapter {
//    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
//
//
//    public AboutMeStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
//        super(fm, context);
//    }
//
//    @Override
//    public Step createStep(int position) {
//        final AboutMeFragment step = new AboutMeFragment();
//        Bundle b = new Bundle();
//        b.putInt(CURRENT_STEP_POSITION_KEY, position);
////        step.setArgements(b);
//        return step;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }
//
//    @NonNull
//    @Override
//    public StepViewModel getViewModel(@IntRange(from=0) int position) {
//        return new StepViewModel.Builder(context)
////                    .setTitle(R.string.tab_title)
//                    .create();
//    }
//
//
//}
