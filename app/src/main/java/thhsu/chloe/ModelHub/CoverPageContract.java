package thhsu.chloe.ModelHub;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface CoverPageContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        void transToModelHub();
    }
}
