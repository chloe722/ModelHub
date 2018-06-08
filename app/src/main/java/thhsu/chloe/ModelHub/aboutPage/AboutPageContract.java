package thhsu.chloe.ModelHub.aboutPage;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 5/15/2018.
 */

public interface AboutPageContract {

    interface View extends BaseView<Presenter> {

        void showAboutModelHubUi();

        void showPrivacyPolicyUi();

        void showTermOfUsUi();

        void showContactUsUi();

        void showAboutUi();

    }

    interface Presenter extends BasePresenter{

        void transToAbout();

        void transToPrivacyPolicy();

        void transToTermOfUs();

        void transToContactUs();

        void transToItemsMain();
    }
}
