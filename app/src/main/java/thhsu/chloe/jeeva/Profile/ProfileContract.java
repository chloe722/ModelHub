package thhsu.chloe.jeeva.Profile;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void takePhoto();

        void pickImage();

    }

}
