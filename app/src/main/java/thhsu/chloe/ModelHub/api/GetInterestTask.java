package thhsu.chloe.ModelHub.api;

import android.os.AsyncTask;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/18/2018.
 */

public class GetInterestTask extends AsyncTask<Object, Void, ArrayList<Cases>>{

    private GetInterestCallBack mCallback;

    public GetInterestTask(GetInterestCallBack callBack){
        mCallback = callBack;
    }

    @Override
    protected ArrayList<Cases> doInBackground(Object[] objects) {
        return ModelHub.getModelHubSQLHelper().getInterest();
    }

    @Override
    protected void onPostExecute(ArrayList<Cases> cases) {
        super.onPostExecute(cases);
        mCallback.onCompleted(cases);
    }
}
