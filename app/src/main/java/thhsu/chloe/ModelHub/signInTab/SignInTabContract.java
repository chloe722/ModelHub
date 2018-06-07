package thhsu.chloe.ModelHub.signInTab;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface SignInTabContract  {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

    }
}
