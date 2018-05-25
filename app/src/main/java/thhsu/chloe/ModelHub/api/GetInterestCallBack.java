package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/18/2018.
 */

public interface GetInterestCallBack {
    void onCompleted(ArrayList<Cases> cases);
}
