package thhsu.chloe.ModelHub.api;

import android.os.AsyncTask;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/18/2018.
 */

public class GetInterestTask extends AsyncTask<Object, Void, ArrayList<Jobs>>{

    private GetInterestCallBack mCallback;

    public GetInterestTask(GetInterestCallBack callBack){
        mCallback = callBack;
    }

    @Override
    protected ArrayList<Jobs> doInBackground(Object[] objects) {
        return ModelHub.getModelHubSQLHelper().getInterest();
    }

    @Override
    protected void onPostExecute(ArrayList<Jobs> cases) {
        super.onPostExecute(cases);
        mCallback.onCompleted(cases);
    }
}
