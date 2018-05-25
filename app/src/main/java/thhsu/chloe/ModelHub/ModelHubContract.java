package thhsu.chloe.ModelHub;

import android.content.Intent;

import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface ModelHubContract {

    interface View extends BaseView<Presenter>{

        void showHomeUi();

        void showInterestUi();

        void showProfileUi();

        void showSignInTabPageUi();

        void showFilterPageUi();

        void showCaseDetailsUi();

        void refreshInterestItemUi();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode, Intent data);

        void transToHome();

        void transToInterest();

        void transToProfile();

        void transToSignInTabPage();

        void transToCaseDetails(Cases acase);

        void refreshInterestItem();

//        void transToFilter();


    }
}
