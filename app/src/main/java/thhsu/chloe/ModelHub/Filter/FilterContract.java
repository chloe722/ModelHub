package thhsu.chloe.ModelHub.Filter;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 5/2/2018.
 */

public interface FilterContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

//        void transToHome();

        void loadData();

    }
}
