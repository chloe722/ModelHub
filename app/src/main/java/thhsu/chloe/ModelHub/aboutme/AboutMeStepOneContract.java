package thhsu.chloe.ModelHub.aboutme;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 6/9/2018.
 */

public interface AboutMeStepOneContract {

    interface View extends BaseView<Presenter>{

        void showUserInfoStepOneUi(User user);
    }

    interface Presenter extends BasePresenter{

        void showUserInfoStepOne();


        void postUserInfoStepOne(UpdateUserRequest request);

    }
}
