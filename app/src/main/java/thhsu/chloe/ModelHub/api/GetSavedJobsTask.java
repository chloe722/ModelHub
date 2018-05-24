package thhsu.chloe.ModelHub.api;

import android.os.AsyncTask;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/18/2018.
 */

public class GetSavedJobsTask extends AsyncTask<Object, Void, ArrayList<Jobs>>{

    private GetSavedJobsCallBack mCallback;

    public GetSavedJobsTask(GetSavedJobsCallBack callBack){
        mCallback = callBack;
    }

    @Override
    protected ArrayList<Jobs> doInBackground(Object[] objects) {
        return ModelHub.getModelHubSQLHelper().getSavedJobs();
    }

    @Override
    protected void onPostExecute(ArrayList<Jobs> jobs) {
        super.onPostExecute(jobs);
        mCallback.onCompleted(jobs);
    }
}
