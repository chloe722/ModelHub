package thhsu.chloe.jeeva.AboutPage;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 5/15/2018.
 */

public interface AboutPageContract {

    interface View extends BaseView<Presenter> {

        void showAboutJeevaUi();

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
