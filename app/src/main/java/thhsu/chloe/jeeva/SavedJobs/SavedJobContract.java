package thhsu.chloe.jeeva.SavedJobs;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;
import thhsu.chloe.jeeva.JeevaContract;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface SavedJobContract {
    interface View extends BaseView<Presenter>{}

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);
    }
}
