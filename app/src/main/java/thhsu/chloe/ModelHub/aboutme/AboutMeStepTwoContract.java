package thhsu.chloe.ModelHub.aboutme;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 6/9/2018.
 */

public interface AboutMeStepTwoContract {

    interface View extends BaseView<Presenter> {

        void showUserDataStepTwo(User user);

    }

    interface Presenter extends BasePresenter{

        void getUserDataStepTwo();

        void postUserInfoStepTwo(UpdateUserRequest request);
    }
}
