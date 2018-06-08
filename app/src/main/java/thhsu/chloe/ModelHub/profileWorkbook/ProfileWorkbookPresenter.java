package thhsu.chloe.ModelHub.profileWorkbook;

/**
 * Created by Chloe on 6/8/2018.
 */

public class ProfileWorkbookPresenter implements ProfileWorkbookContract.Presenter {

    private ProfileWorkbookContract.View mView;

    public ProfileWorkbookPresenter(ProfileWorkbookContract.View profileWorkbookView) {
        mView = profileWorkbookView;
        if(profileWorkbookView != null){
            mView.setPresenter(this);
        }

    }

    @Override
    public void start() {

    }
}
