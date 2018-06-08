package thhsu.chloe.ModelHub;

import android.content.Intent;

import thhsu.chloe.ModelHub.activities.CoverPageActivity;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;

/**
 * Created by Chloe on 5/7/2018.
 */

public class CoverPagePresenter implements CoverPageContract.Presenter{

    private CoverPageActivity mActivity;

    public CoverPagePresenter(CoverPageActivity coverPageActivity) {
        mActivity = coverPageActivity;
    }

    @Override
    public void start() {

    }

    @Override
    public void transToModelHub() {
        Intent intent = new Intent (mActivity, ModelHubActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
