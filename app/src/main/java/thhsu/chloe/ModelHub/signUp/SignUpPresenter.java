package thhsu.chloe.ModelHub.signUp;

import android.content.Context;
import android.widget.Toast;

import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 6/14/2018.
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.View mView;
    private Context mContext;


    public SignUpPresenter(SignUpContract.View view, Context context) {
        mView = view;
        if(view != null){
            mView.setPresenter(this);
        }
        mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void onClickSignUp(String name, String age, String gender, String email, String password) {

        ApiJobManager.getInstance().getRegister(name, age, gender, email, password, new PostRegisterLoginCallBack() {
            @Override
            public void onCompleted(String token) {
                mView.onClickSignUpUi(token);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
