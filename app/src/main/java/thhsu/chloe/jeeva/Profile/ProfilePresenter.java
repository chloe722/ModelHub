package thhsu.chloe.jeeva.Profile;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    private ProfileContract.View mProfileView;

    public ProfilePresenter(ProfileContract.View profileView){
        mProfileView = profileView;
        if(profileView != null){
            mProfileView.setPresenter(this);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }
}
