package thhsu.chloe.ModelHub.api;

/**
 * Created by Chloe on 5/13/2018.
 */

public interface PostRegisterLoginCallBack {
    void onCompleted(String token);
    void onError(String errorMessage);
}
