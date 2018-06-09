package thhsu.chloe.ModelHub.coverpage;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface CoverPageContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void transToModelHub();
    }
}
