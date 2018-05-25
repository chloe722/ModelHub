package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface GetCasesCallBack {
     void onCompleted(ArrayList<Cases> cases);
     void onError(String errorMessage);
}
