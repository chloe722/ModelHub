package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashSet;

import thhsu.chloe.ModelHub.Filter.FilterContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetFilterJobsCallBack;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FilterContract.Presenter mPresenter;
    private CheckBox mCommercialVideo, mActing, mPhotography, mModeling, mPromotionWork, mPaid, mUnpaid;
    private SharedPreferences sharedPreferences;
    private HashSet<String> tagListSet;
    private String tags;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getIntent();

        tagListSet = new HashSet<String>();

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

        sharedPreferences = this.getSharedPreferences(Constants.FILTER_STATUS, MODE_PRIVATE);

        mCommercialVideo.setOnCheckedChangeListener(this);
        mCommercialVideo.setChecked(getPreferences(Constants.FILTER_COMMERCIAL_VIDEO));
        mActing.setOnCheckedChangeListener(this);
        mActing.setChecked(getPreferences(Constants.FILTER_ACTING));
        mPhotography.setOnCheckedChangeListener(this);
        mPhotography.setChecked(getPreferences(Constants.FILTER_PHOTOGRAPHY));
        mModeling.setOnCheckedChangeListener(this);
        mModeling.setChecked(getPreferences(Constants.FILTER_MODELING));
        mPromotionWork.setOnCheckedChangeListener(this);
        mPromotionWork.setChecked(getPreferences(Constants.FILTER_PROMOTION_WORK));
        mPaid.setOnCheckedChangeListener(this);
        mPaid.setChecked(getPreferences(Constants.FILTER_PAID));
        mUnpaid.setOnCheckedChangeListener(this);
        mUnpaid.setChecked(getPreferences(Constants.FILTER_UNPAID));

        savedBtn.setOnClickListener(this);
        filterBackBtn.setOnClickListener(this);
    }

    @Override
    public void setPresenter(FilterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void savePreferences(String key, Boolean value){
       sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private boolean getPreferences(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){

            case R.id.filter_checkbox_commercial_video:
                if(mCommercialVideo.isChecked()){
                    tagListSet.add("commercial_video");
                }else{
                    tagListSet.remove("commercial_video");
                }
                savePreferences(Constants.FILTER_COMMERCIAL_VIDEO, isChecked);
            break;

            case R.id.filter_checkbox_acting:
                if(mActing.isChecked()){
                    tagListSet.add("acting");
                }else{
                    tagListSet.remove("acting");
                }
                savePreferences(Constants.FILTER_ACTING, isChecked);
                break;

            case R.id.filter_checkbox_promotion_work:
                if(mPromotionWork.isChecked()){
                    tagListSet.add("promotion_work");
                }else{
                    tagListSet.remove("promotion_work");
                }
                savePreferences(Constants.FILTER_PROMOTION_WORK, isChecked);
                break;

            case R.id.filter_checkbox_photography:
                if(mPhotography.isChecked()){
                    tagListSet.add("photography");
                }else{
                    tagListSet.remove("photography");
                }
                break;

            case R.id.filter_checkbox_modeling:
                if(mModeling.isChecked()){
                    tagListSet.add("modeling");
                }else{
                    tagListSet.remove("modeling");
                }
                savePreferences(Constants.FILTER_MODELING, isChecked);
                break;

            case R.id.filter_checkbox_paid:
                if(mPaid.isChecked()){
                    tagListSet.add("paid");
                }else{
                    tagListSet.remove("paid");
                }
                savePreferences(Constants.FILTER_PAID, isChecked);
                break;

            case R.id.filter_checkbox_unpaid:
                if(mUnpaid.isChecked()){
                    tagListSet.add("unpaid");

                }else{
                    tagListSet.remove("unpaid");
                }
                savePreferences(Constants.FILTER_UNPAID, isChecked);
                break;

        }
        ArrayList<String> tagListResult = new ArrayList<>(tagListSet);
        tags = String.join(",", tagListResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.filter_save_btn:
                sentResult(tags);
                break;  //Don't forget to write break in Switch!! Otherwise the code gonna continue running to next case

            case R.id.filter_tool_bar_back_btn:
                super.onBackPressed();
                break;
        }
    }

    private void sentResult(String tags) {
        bundle = new Bundle();
        ApiJobManager.getInstance().getFilterJobs(tags, new GetFilterJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                if (jobs.size() >= 0) {
                    bundle.putSerializable("filterResult", jobs);
                    Intent filterResult = new Intent();
                    filterResult.putExtras(bundle);
                    setResult(Constants.RESULT_SUCCESS, filterResult);  //set the result. Intent the data back to startActivityForResult;
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
    }
}