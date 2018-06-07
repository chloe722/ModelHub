package thhsu.chloe.ModelHub.signInTab;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabPresenter implements SignInTabContract.Presenter {

    private SignInTabContract.View mSignInTabView;

    public SignInTabPresenter(SignInTabContract.View signInTabView){
        mSignInTabView = signInTabView;
        if(signInTabView != null){
            mSignInTabView.setPresenter(this);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }
}
