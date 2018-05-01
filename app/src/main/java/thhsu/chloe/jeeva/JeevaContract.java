package thhsu.chloe.jeeva;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface JeevaContract {

    interface View extends BaseView<Presenter>{

        void showHomeUi();

        void showSavedJobUi();

        void showProfileUi();

        void showSignInTabPageUi();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void transToHome();

        void transToSavedJob();

        void transToProfile();

        void transToSignInTabPage();


    }
}
