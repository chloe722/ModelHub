package thhsu.chloe.jeeva.Filter;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 5/2/2018.
 */

public interface FilterContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

    }
}
