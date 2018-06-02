package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 6/1/2018.
 */

public interface UploadImageCallBack {
    void onComplete(String url);
    void onError(String errorMessage);
}
