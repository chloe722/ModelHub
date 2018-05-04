package thhsu.chloe.jeeva.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.stepstone.stepper.StepperLayout;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.adapters.AboutMeStepperAdapter;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutMeActivity extends BaseActivity {
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_stepper_layout);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new AboutMeStepperAdapter(getSupportFragmentManager(), this));
    }
}
