package thhsu.chloe.jeeva;

import android.app.Application;
import android.content.Context;

import thhsu.chloe.jeeva.database.JeevaSQLHelper;

/**
 * Created by Chloe on 5/1/2018.
 */

public class Jeeva extends Application {

    private static Context mContext;
    private static JeevaSQLHelper mJeevaSQLHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mJeevaSQLHelper = null;
    }

    public static Context getAppContext(){return mContext;}

    public static JeevaSQLHelper getJeevaSQLHelper(){
        if(mJeevaSQLHelper == null) mJeevaSQLHelper = new JeevaSQLHelper(getAppContext());
        return mJeevaSQLHelper;
    }

}
