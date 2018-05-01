package thhsu.chloe.jeeva;

import android.app.Application;
import android.content.Context;

/**
 * Created by Chloe on 5/1/2018.
 */

public class Jeeva extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext(){return mContext;}

}
