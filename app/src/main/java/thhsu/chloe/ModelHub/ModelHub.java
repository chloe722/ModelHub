package thhsu.chloe.ModelHub;

import android.app.Application;
import android.content.Context;

import thhsu.chloe.ModelHub.database.ModelHubSQLHelper;

/**
 * Created by Chloe on 5/1/2018.
 */

public class ModelHub extends Application {

    private static Context mContext;
    private static ModelHubSQLHelper mModelHubSQLHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mModelHubSQLHelper = null;
    }

    public static Context getAppContext(){return mContext;}

    public static ModelHubSQLHelper getModelHubSQLHelper(){
        if(mModelHubSQLHelper == null) mModelHubSQLHelper = new ModelHubSQLHelper(getAppContext());
        return mModelHubSQLHelper;
    }

}
