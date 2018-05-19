package thhsu.chloe.jeeva;

import android.content.Intent;

import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface JeevaContract {

    interface View extends BaseView<Presenter>{

        void showHomeUi();

        void showSavedJobUi();

        void showProfileUi();

        void showSignInTabPageUi();

        void showFilterPageUi();

        void showJobDetailsUi();

        void refreshSavedJobsItemUi();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode, Intent data);

        void transToHome();

        void transToSavedJob();

        void transToProfile();

        void transToSignInTabPage();

        void transToJobDetails(Jobs job);

        void refreshSavedJobsItem();

//        void transToFilter();


    }
}
