package thhsu.chloe.jeeva.api;

import java.util.ArrayList;

import thhsu.chloe.jeeva.api.model.FilterJobs;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface GetFilterJobsCallBack {
    void onCompleted(ArrayList<FilterJobs> filterJobs);
    void onError(String errorMessage);
}
