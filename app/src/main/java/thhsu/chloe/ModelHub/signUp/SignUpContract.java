package thhsu.chloe.ModelHub.signUp;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 6/14/2018.
 */

public interface SignUpContract {

    interface View extends BaseView<Presenter> {

        void onClickSignUpUi(String token);

        void  showNameError(Boolean isError);

        void  showAgeError(Boolean isError);

        void  showEmailError(Boolean isError);

        void  showPasswordError(Boolean isError);

        void showConfirmPasswordError(Boolean isError);

    }

    interface Presenter extends BasePresenter{

        void onClickSignUp(String name, String age, String gender, String email, String password, String confirmPassword);

    }
}
