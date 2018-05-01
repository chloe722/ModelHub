package thhsu.chloe.jeeva.Home;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 5/1/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

    }
}
