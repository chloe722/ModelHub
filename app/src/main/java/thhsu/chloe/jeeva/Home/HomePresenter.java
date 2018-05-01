package thhsu.chloe.jeeva.Home;

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
    public void start() {}

    @Override
    public void result(int requestCode, int resultCode) {}
}
