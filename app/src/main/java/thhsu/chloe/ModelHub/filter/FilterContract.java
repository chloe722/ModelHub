package thhsu.chloe.ModelHub.filter;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/2/2018.
 */

public interface FilterContract {

    interface View extends BaseView<Presenter>{

        void setResult(ArrayList<Jobs> jobs);

    }

    interface Presenter extends BasePresenter{

        void setFilterResult(String tags);


    }
}
