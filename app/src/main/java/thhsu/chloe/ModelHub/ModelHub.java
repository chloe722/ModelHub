package thhsu.chloe.ModelHub;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
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
        Fabric.with(this, new Crashlytics());
        mContext = this;
        mModelHubSQLHelper = null;
    }

    public static Context getAppContext(){return mContext;}

    public static ModelHubSQLHelper getModelHubSQLHelper(){
        if(mModelHubSQLHelper == null) mModelHubSQLHelper = new ModelHubSQLHelper(getAppContext());
        return mModelHubSQLHelper;
    }

}
