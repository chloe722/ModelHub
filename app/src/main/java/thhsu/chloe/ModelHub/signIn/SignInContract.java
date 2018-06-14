package thhsu.chloe.ModelHub.signIn;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 6/14/2018.
 */

public interface SignInContract {

    interface View extends BaseView<Presenter> {

        void onClickSignInUi(String token);
    }

    interface Presenter extends BasePresenter{

        void onClickSignIn(String email, String password);
    }
}
