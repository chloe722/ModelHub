package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/18/2018.
 */

public interface GetSavedJobsCallBack {
    void onCompleted(ArrayList<Jobs> jobs);
}
