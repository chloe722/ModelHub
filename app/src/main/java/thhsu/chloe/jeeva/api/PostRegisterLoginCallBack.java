package thhsu.chloe.jeeva.api;

import java.util.ArrayList;

import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/13/2018.
 */

public interface PostRegisterLoginCallBack {
    void onCompleted(String token);
    void onError(String errorMessage);
}
