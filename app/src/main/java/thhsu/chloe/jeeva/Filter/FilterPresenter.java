package thhsu.chloe.jeeva.Filter;

/**
 * Created by Chloe on 5/2/2018.
 */

public class FilterPresenter implements FilterContract.Presenter {
    FilterContract.View mFilterView;

    public FilterPresenter(FilterContract.View filterView){
        this.mFilterView = filterView;
        if(filterView != null){
            mFilterView.setPresenter(this);
        }
    }


    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }
}
