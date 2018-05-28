package thhsu.chloe.ModelHub.activities;

import android.content.Context;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import thhsu.chloe.ModelHub.Filter.FilterContract;
//import thhsu.chloe.jeeva.Filter.FilterFragment;
import thhsu.chloe.ModelHub.Home.HomeContract;
import thhsu.chloe.ModelHub.Home.HomeFragment;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetFilterJobsCallBack;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FilterContract.Presenter mPresenter;
    private HomeContract.View mHomeView;
    private HomeContract.Presenter mHomePresenter;

    public CheckBox mCommercialVideo, mActing, mFullStack, mPhotography,
            mModeling, mPromotionWork, mProjectManager, mPaid,
            mUnpaid, mContract, mPermanent, mIntern, mRemote;
    public Button mSavedBtn;
    public ImageButton mFilterBackBtn;
    public Toolbar mToolbar;
    public TextView mFilterPositionTitle, mFilterTypeTitle;
    public HashMap<String, Boolean> filterResult;
    public HashMap<String, Boolean> filterResultTest;
//    public ArrayList<FilterJobs> mFilterJobs;
    public List<CheckBox> items;
    public boolean checked = false;
    HomeFragment homeFragment;
    SharedPreferences sharedPreferences;
    HashSet<String> tagListSet;
    ArrayList<String> tagListResult;
    String tags;
    ArrayList<Jobs> mCases;
    public Bundle bundle;

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getIntent();
        homeFragment = new HomeFragment();
        mContext = this;
        items = new ArrayList<CheckBox>();
        tagListSet = new HashSet<String>();
        mCases = new ArrayList<Jobs>();
        mCommercialVideo = (CheckBox) findViewById(R.id.filter_checkbox_commercial_video);
        mActing = (CheckBox) findViewById(R.id.filter_checkbox_acting);
//        mFullStack = (CheckBox) findViewById(R.id.filter_checkbox_fullstack);
        mPhotography = (CheckBox) findViewById(R.id.filter_checkbox_photography);
        mModeling = (CheckBox) findViewById(R.id.filter_checkbox_modeling);
        mPromotionWork = (CheckBox) findViewById(R.id.filter_checkbox_promotion_work);
//        mProjectManager = (CheckBox) findViewById(R.id.filter_checkbox_project_m);
        mPaid = (CheckBox) findViewById(R.id.filter_checkbox_paid);
        mUnpaid = (CheckBox) findViewById(R.id.filter_checkbox_unpaid);
//        mContract = (CheckBox) findViewById(R.id.filter_checkbox_contract);
//        mPermanent = (CheckBox) findViewById(R.id.filter_checkbox_permanent);
//        mIntern = (CheckBox) findViewById(R.id.filter_checkbox_intern);
//        mRemote = (CheckBox) findViewById(R.id.filter_checkbox_remote);
        mSavedBtn = (Button) findViewById(R.id.filter_save_btn);
        mFilterPositionTitle = (TextView) findViewById(R.id.filter_position_title);
        mFilterTypeTitle = (TextView) findViewById(R.id.filter_type_title);
        mToolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        mFilterBackBtn = (ImageButton) mToolbar.findViewById(R.id.filter_tool_bar_back_btn);
        sharedPreferences = this.getSharedPreferences(Constants.FILTER_STATUS, MODE_PRIVATE);

        mCommercialVideo.setOnCheckedChangeListener(this);
        mCommercialVideo.setChecked(getPreferences(Constants.FILTER_COMMERCIAL_VIDEO));
        Log.d("Chloe", "mCommercialVideo: getSP: " + getPreferences(Constants.FILTER_COMMERCIAL_VIDEO));
        mActing.setOnCheckedChangeListener(this);
        mActing.setChecked(getPreferences(Constants.FILTER_ACTING));
        Log.d("Chloe", "mActing getSP: " + getPreferences(Constants.FILTER_ACTING));
//        mFullStack.setOnCheckedChangeListener(this);
//        mFullStack.setChecked(getPreferences(Constants.FILTER_FULLSTACK));
//        Log.d("Chloe", "mFullstack getSP: " + getPreferences(Constants.FILTER_FULLSTACK));
        mPhotography.setOnCheckedChangeListener(this);
        mPhotography.setChecked(getPreferences(Constants.FILTER_PHOTOGRAPHY));
        mModeling.setOnCheckedChangeListener(this);
        mModeling.setChecked(getPreferences(Constants.FILTER_MODELING));
//        mPromotionWork.setOnCheckedChangeListener(this);
        mPromotionWork.setChecked(getPreferences(Constants.FILTER_PROMOTION_WORK));
//        mProjectManager.setOnCheckedChangeListener(this);
//        mProjectManager.setChecked(getPreferences(Constants.FILTER_PROJECTMANAGER));
        mPaid.setOnCheckedChangeListener(this);
        mPaid.setChecked(getPreferences(Constants.FILTER_PAID));
        mUnpaid.setOnCheckedChangeListener(this);
        mUnpaid.setChecked(getPreferences(Constants.FILTER_UNPAID));
//        mContract.setOnCheckedChangeListener(this);
//        mContract.setChecked(getPreferences(Constants.FILTER_CONTRACT));
//        mPermanent.setOnCheckedChangeListener(this);
//        mPermanent.setChecked(getPreferences(Constants.FILTER_PERMANENT));
//        mIntern.setOnCheckedChangeListener(this);
//        mIntern.setChecked(getPreferences(Constants.FILTER_INTERNSHIP));
//        mRemote.setOnCheckedChangeListener(this);
//        mRemote.setChecked(getPreferences(Constants.FILTER_REMOTE));
        mSavedBtn.setOnClickListener(this);
        mFilterBackBtn.setOnClickListener(this);
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
        String checkedResult = "On checked Selected: ";

        switch(buttonView.getId()){

            case R.id.filter_checkbox_commercial_video:
                if(mCommercialVideo.isChecked()){
                    tagListSet.add("commercial_video");
                    checkedResult += "\n  Commercial Video Checked!";
                    Log.d("Chloe", "mCommercialVideo is checked? " + mCommercialVideo.isChecked());

                }else{
                    tagListSet.remove("commercial_video");
                }
                savePreferences(Constants.FILTER_COMMERCIAL_VIDEO, isChecked);
            break;

            case R.id.filter_checkbox_acting:
                if(mActing.isChecked()){
                    tagListSet.add("acting");
                    checkedResult += "\n  Acting Checked!";
                    Log.d("Chloe", "mActing is checked? " + mActing.isChecked());
                }else{
                    tagListSet.remove("acting");
                }
                savePreferences(Constants.FILTER_ACTING, isChecked);
                break;

//            case R.id.filter_checkbox_fullstack:
//                if(mFullStack.isChecked()){
//                    tagListSet.add("fullstack");
//                    checkedResult += "\n  FullStack Checked!";
//                    Log.d("Chloe", "mFullStack is checked? " + mFullStack.isChecked());
//                }else{
//                    tagListSet.remove("fullstack");
//                }
//                savePreferences(Constants.FILTER_FULLSTACK, isChecked);
//                break;
//
            case R.id.filter_checkbox_promotion_work:
                if(mPromotionWork.isChecked()){
                    tagListSet.add("promotion_work");
                    checkedResult += "\n  Promotion work Checked!";
                }else{
                    tagListSet.remove("promotion_work");
                }
                savePreferences(Constants.FILTER_PROMOTION_WORK, isChecked);
                break;
//
//            case R.id.filter_checkbox_project_m:
//                if(mProjectManager.isChecked()){
//                    tagListSet.add("project_manager");
//                    checkedResult += "\n  Project Manager Checked!";
//                }else{
//                    tagListSet.remove("project_manager");
//                }
//                savePreferences(Constants.FILTER_PROJECTMANAGER, isChecked);
//                break;

            case R.id.filter_checkbox_photography:
                if(mPhotography.isChecked()){
                    tagListSet.add("photography");
                    checkedResult += "\n  Photography Checked!";
                }else{
                    tagListSet.remove("photography");
                }
                savePreferences(Constants.FILTER_PHOTOGRAPHY, isChecked);
                break;

            case R.id.filter_checkbox_modeling:
                if(mModeling.isChecked()){
                    tagListSet.add("modeling");
                    checkedResult += "\n  Modeling Checked!";
                }else{
                    tagListSet.remove("modeling");
                }
                savePreferences(Constants.FILTER_MODELING, isChecked);
                break;

            case R.id.filter_checkbox_paid:
                if(mPaid.isChecked()){
                    tagListSet.add("paid");
                    checkedResult += "\n  Paid Checked!";
                }else{
                    tagListSet.remove("paid");
                }
                savePreferences(Constants.FILTER_PAID, isChecked);
                break;

            case R.id.filter_checkbox_unpaid:
                if(mUnpaid.isChecked()){
                    tagListSet.add("unpaid");
                    checkedResult += "\n  Unpaid Checked!";
                }else{
                    tagListSet.remove("unpaid");
                }
                savePreferences(Constants.FILTER_UNPAID, isChecked);
                break;

//            case R.id.filter_checkbox_contract:
//                if(mContract.isChecked()){
//                    tagListSet.add("contract");
//                    checkedResult += "\n  Contract Checked!";
//                }else{
//                    tagListSet.remove("contract");
//                }
//                savePreferences(Constants.FILTER_CONTRACT, isChecked);
//                break;
//
//            case R.id.filter_checkbox_permanent:
//                if(mPermanent.isChecked()){
//                    tagListSet.add("permanent");
//                    checkedResult += "\n  Permanent Checked!";
//                }else{
//                    tagListSet.remove("permanent");
//                }
//                savePreferences(Constants.FILTER_PERMANENT, isChecked);
//                break;
//
//            case R.id.filter_checkbox_intern:
//                if(mIntern.isChecked()){
//                    tagListSet.add("intern");
//                    checkedResult += "\n  Intern Checked!";
//                }else{
//                    tagListSet.remove("intern");
//                }
//                savePreferences(Constants.FILTER_INTERNSHIP, isChecked);
//                break;
//
//            case R.id.filter_checkbox_remote:
//                if(mRemote.isChecked()){
//                    tagListSet.add("remote");
//                    checkedResult += "\n  Remote Checked!";
//                }else{
//                    tagListSet.remove("remote");
//                }
//                savePreferences(Constants.FILTER_REMOTE, isChecked);
//                break;
        }
        tagListResult = new ArrayList<>(tagListSet);
        Log.d("Chloe", "Tag list set: " + tagListSet);
        Log.d("Chloe", "Tag list result: " + tagListResult);
        tags = String.join(",", tagListResult);
        Log.d("Chloe", "Tags: " + tags);
//        Toast.makeText(FilterActivity.this, checkedResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_save_btn:
                Log.d("Chloe", "In onclick scope");
                sentResult(tags);
                break;  //Dont forget to write break in Switch!! Otherwise the code gonna continue running to next case
            case R.id.filter_tool_bar_back_btn:
                super.onBackPressed();
                break;
        }
    }

    private void sentResult(String tags){
        bundle = new Bundle();
        Log.d("Chloe", "Where is tags? " + tags);
        ApiJobManager.getInstance().getFilterCases(tags, new GetFilterJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                mCases = jobs;
                if(jobs.size() >= 0){
                    Log.d("Chloe", "Success!");
                    bundle.putSerializable("filterResult", jobs);
                    Intent filterResult = new Intent();
                    filterResult.putExtras(bundle);
                    setResult(Constants.RESULT_SUCCESS, filterResult );  //set the result. Intent the data back to startActivityForResult;
                    Log.d("Chloe", "jobs in filter result: " + bundle);
                    finish();
                }

            }
            @Override
            public void onError(String errorMessage) {
            }
        });
    }
}