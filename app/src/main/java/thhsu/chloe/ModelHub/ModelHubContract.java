package thhsu.chloe.ModelHub;

import android.content.Intent;

import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface ModelHubContract {

    interface View extends BaseView<Presenter>{

        void showHomeUi();

        void showInterestUi();

        void showProfileUi();

        void showSignInTabPageUi();

        void showJobDetailsUi();

        void refreshInterestItemUi();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode, Intent data);

        void transToHome();

        void transToInterest();

        void transToProfile();

        void transToSignInTabPage();

        void transToJobDetails(Jobs jobs);

        void refreshInterestItem();


    }
}
