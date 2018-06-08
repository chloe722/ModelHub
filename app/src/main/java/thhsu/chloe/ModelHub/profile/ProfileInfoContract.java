package thhsu.chloe.ModelHub.profile;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 6/8/2018.
 */

public interface ProfileInfoContract {

    interface View extends BaseView<Presenter>{

        void showUserMoreInfoUi(User user);
    }

    interface Presenter extends BasePresenter{

        void showUserMoreInfo();

    }
}
