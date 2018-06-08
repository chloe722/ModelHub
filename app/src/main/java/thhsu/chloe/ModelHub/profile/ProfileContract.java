package thhsu.chloe.ModelHub.profile;

import android.content.Intent;
import android.net.Uri;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showUserInfoUi(User user);

        void showUserPhoto(Uri uri);
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode, Intent data);

        void takePhoto();

        void pickImage();

        void showUserInfo();

    }

}
