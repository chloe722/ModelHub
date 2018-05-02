package thhsu.chloe.jeeva.Home;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View homeView){
        mHomeView = homeView;
        if(homeView != null){
            mHomeView.setPresenter(this);
        }
    }


    @Override
    public void start() {
        mHomeView.showJobsList();
    }

    @Override
    public void result(int requestCode, int resultCode) {}

    @Override
    public void showJobsList() {

    }

    @Override
    public void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState) {

    }

    @Override
    public void onScrolled(RecyclerView.LayoutManager layoutManager) {

    }

    @Override
    public void openJobDetails() {

    }
}
