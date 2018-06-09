package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import thhsu.chloe.ModelHub.filter.FilterContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.filter.FilterPresenter;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetFilterJobsCallBack;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener{
    private FilterContract.Presenter mPresenter;
    private CheckBox mCommercialVideo, mActing, mPhotography, mModeling, mPromotionWork, mPaid, mUnpaid;

    @Override
    public void setPresenter(FilterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mPresenter = new FilterPresenter(this);
        mPaid = (CheckBox) findViewById(R.id.filter_checkbox_paid);
        mUnpaid = (CheckBox) findViewById(R.id.filter_checkbox_unpaid);
        mActing = (CheckBox) findViewById(R.id.filter_checkbox_acting);
        mModeling = (CheckBox) findViewById(R.id.filter_checkbox_modeling);
        mPhotography = (CheckBox) findViewById(R.id.filter_checkbox_photography);
        mPromotionWork = (CheckBox) findViewById(R.id.filter_checkbox_promotion_work);
        mCommercialVideo = (CheckBox) findViewById(R.id.filter_checkbox_commercial_video);

        Toolbar toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        Button savedBtn = (Button) findViewById(R.id.filter_save_btn);
        ImageButton filterBackBtn = (ImageButton) toolbar.findViewById(R.id.filter_tool_bar_back_btn);

        Set<String> tags = getSavedTags();

        mPaid.setChecked(tags.contains(Constants.FILTER_PAID));
        mUnpaid.setChecked(tags.contains(Constants.FILTER_UNPAID));
        mActing.setChecked(tags.contains(Constants.FILTER_ACTING));
        mModeling.setChecked(tags.contains(Constants.FILTER_MODELING));
        mPhotography.setChecked(tags.contains(Constants.FILTER_PHOTOGRAPHY));
        mPromotionWork.setChecked(tags.contains(Constants.FILTER_PROMOTION_WORK));
        mCommercialVideo.setChecked(tags.contains(Constants.FILTER_COMMERCIAL_VIDEO));

        savedBtn.setOnClickListener(this);
        filterBackBtn.setOnClickListener(this);
    }



    Set<String> getTags() {
        Set<String> tags = new HashSet<String>();

        if(mCommercialVideo.isChecked()){
            tags.add(Constants.FILTER_COMMERCIAL_VIDEO);
        }

        if(mActing.isChecked()){
            tags.add(Constants.FILTER_ACTING);
        }

        if(mPromotionWork.isChecked()){
            tags.add(Constants.FILTER_PROMOTION_WORK);
        }

        if(mPhotography.isChecked()){
            tags.add(Constants.FILTER_PHOTOGRAPHY);
        }

        if(mModeling.isChecked()){
            tags.add(Constants.FILTER_MODELING);
        }

        if(mPaid.isChecked()){
            tags.add(Constants.FILTER_PAID);
        }

        if(mUnpaid.isChecked()){
            tags.add(Constants.FILTER_UNPAID);
        }

        return tags;
    }

    void saveTags(Set<String> tags) {
        getSharedPreferences(Constants.FILTER_PREFERENCES, MODE_PRIVATE)
                .edit()
                .putStringSet("tags", tags).apply();
    }

    Set<String> getSavedTags() {
        return getSharedPreferences(Constants.FILTER_PREFERENCES, MODE_PRIVATE)
                .getStringSet("tags", new HashSet<String>());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.filter_save_btn:
                Set<String> tags = getTags();
                saveTags(tags);
                mPresenter.setFilterResult(String.join(",", tags));
                break;  //Don't forget to write break in Switch!! Otherwise the code gonna continue running to next case

            case R.id.filter_tool_bar_back_btn:
                super.onBackPressed();
                break;
        }
    }


    @Override
    public void setResult(ArrayList<Jobs> jobs) {
        Bundle bundle = new Bundle();
        if (jobs.size() >= 0) {
            bundle.putSerializable("filterResult", jobs);
            Intent filterResult = new Intent();
            filterResult.putExtras(bundle);
            setResult(Constants.RESULT_SUCCESS, filterResult);  //set the result. Intent the data back to startActivityForResult;
            finish();
        }
    }
}