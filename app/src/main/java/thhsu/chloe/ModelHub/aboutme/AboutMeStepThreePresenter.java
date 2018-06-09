package thhsu.chloe.ModelHub.aboutme;

import android.support.v4.app.Fragment;

/**
 * Created by Chloe on 6/10/2018.
 */

public class AboutMeStepThreePresenter implements AboutMeStepThreeContract.Presenter {
   private AboutMeStepThreeContract.View mView;

    public AboutMeStepThreePresenter(AboutMeStepThreeContract.View view) {

        mView = view;
        if (view != null){
            mView.setPresenter(this);
        }
    }

    @Override
    public void start() {}


}
