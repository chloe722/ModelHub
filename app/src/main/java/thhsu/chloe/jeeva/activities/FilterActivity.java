package thhsu.chloe.jeeva.activities;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import thhsu.chloe.jeeva.Filter.FilterContract;
//import thhsu.chloe.jeeva.Filter.FilterFragment;
import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.Home.HomeFragment;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.GetFilterJobsCallBack;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FilterContract.Presenter mPresenter;
    private HomeContract.View mHomeView;
    private HomeContract.Presenter mHomePresenter;

    public CheckBox mFrontend, mBackend, mFullStack, mWebD,
            mUiUxD, mProductManager, mProjectManager, mFullTime,
            mPartTime, mContract, mPermanent, mIntern, mRemote;
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
    ArrayList<Jobs> mJobs;
    public Bundle bundle;

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getIntent();

//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        homeFragment = new HomeFragment();
        mContext = this;
        items = new ArrayList<CheckBox>();
        tagListSet = new HashSet<String>();
        mJobs = new ArrayList<Jobs>();
        mFrontend = (CheckBox) findViewById(R.id.filter_checkbox_frontend);
        mBackend = (CheckBox) findViewById(R.id.filter_checkbox_backend);
        mFullStack = (CheckBox) findViewById(R.id.filter_checkbox_fullstack);
        mWebD = (CheckBox) findViewById(R.id.filter_checkbox_web_designer);
        mUiUxD = (CheckBox) findViewById(R.id.filter_checkbox_uiux_designer);
        mProductManager = (CheckBox) findViewById(R.id.filter_checkbox_product_m);
        mProjectManager = (CheckBox) findViewById(R.id.filter_checkbox_project_m);
        mFullTime = (CheckBox) findViewById(R.id.filter_checkbox_fulltime);
        mPartTime = (CheckBox) findViewById(R.id.filter_checkbox_parttime);
        mContract = (CheckBox) findViewById(R.id.filter_checkbox_contract);
        mPermanent = (CheckBox) findViewById(R.id.filter_checkbox_permanent);
        mIntern = (CheckBox) findViewById(R.id.filter_checkbox_intern);
        mRemote = (CheckBox) findViewById(R.id.filter_checkbox_remote);
        mSavedBtn = (Button) findViewById(R.id.filter_save_btn);
        mFilterPositionTitle = (TextView) findViewById(R.id.filter_position_title);
        mFilterTypeTitle = (TextView) findViewById(R.id.filter_type_title);
        mToolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        mFilterBackBtn = (ImageButton) mToolbar.findViewById(R.id.filter_tool_bar_back_btn);
        sharedPreferences = this.getSharedPreferences(Constants.FILTER_STATUS, MODE_PRIVATE);

        mFrontend.setOnCheckedChangeListener(this);
        mFrontend.setChecked(getPreferences(Constants.FILTER_FRONTEND));
        Log.d("Chloe", "mFrontend: getSP: " + getPreferences(Constants.FILTER_FRONTEND));
        mBackend.setOnCheckedChangeListener(this);
        mBackend.setChecked(getPreferences(Constants.FILTER_BACKEND));
        Log.d("Chloe", "mBackend getSP: " + getPreferences(Constants.FILTER_BACKEND));
        mFullStack.setOnCheckedChangeListener(this);
        mFullStack.setChecked(getPreferences(Constants.FILTER_FULLSTACK));
        Log.d("Chloe", "mFullstack getSP: " + getPreferences(Constants.FILTER_FULLSTACK));
        mWebD.setOnCheckedChangeListener(this);
        mWebD.setChecked(getPreferences(Constants.FILTER_WEBDESIGNER));
        mUiUxD.setOnCheckedChangeListener(this);
        mUiUxD.setChecked(getPreferences(Constants.FILTER_UIUXDESIGNER));
        mProductManager.setOnCheckedChangeListener(this);
        mProductManager.setChecked(getPreferences(Constants.FILTER_PRODUCTMANAGER));
        mProjectManager.setOnCheckedChangeListener(this);
        mProjectManager.setChecked(getPreferences(Constants.FILTER_PROJECTMANAGER));
        mFullTime.setOnCheckedChangeListener(this);
        mFullTime.setChecked(getPreferences(Constants.FILTER_FULLTIME));
        mPartTime.setOnCheckedChangeListener(this);
        mPartTime.setChecked(getPreferences(Constants.FILTER_PARTTIME));
        mContract.setOnCheckedChangeListener(this);
        mContract.setChecked(getPreferences(Constants.FILTER_CONTRACT));
        mPermanent.setOnCheckedChangeListener(this);
        mPermanent.setChecked(getPreferences(Constants.FILTER_PERMANENT));
        mIntern.setOnCheckedChangeListener(this);
        mIntern.setChecked(getPreferences(Constants.FILTER_INTERNSHIP));
        mRemote.setOnCheckedChangeListener(this);
        mRemote.setChecked(getPreferences(Constants.FILTER_REMOTE));
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

    private void removeFromPreferences(String key){
        sharedPreferences.edit().remove(key).apply();
    }

    private boolean getPreferences(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String checkedResult = "On checked Selected: ";

        if(mFrontend.isChecked()){
            tagListSet.add("frontend");
            checkedResult += "\n  Frontend Checked!";
            savePreferences(Constants.FILTER_FRONTEND, isChecked);
            Log.d("Chloe", "mFrontend is checked? " + mFrontend.isChecked());

        }else{
            tagListSet.remove("frontend");
//            removeFromPreferences(Constants.FILTER_FRONTEND);
        }

        if(mBackend.isChecked()){
            tagListSet.add("backend");
            checkedResult += "\n  Backend Checked!";
            savePreferences(Constants.FILTER_BACKEND, isChecked);
            Log.d("Chloe", "mBackend is checked? " + mBackend.isChecked());
        }else{
            tagListSet.remove("backend");
//            removeFromPreferences(Constants.FILTER_BACKEND);
        }

        if(mFullStack.isChecked()){
            tagListSet.add("fullstack");
            checkedResult += "\n  FullStack Checked!";
            savePreferences(Constants.FILTER_FULLSTACK, isChecked);
            Log.d("Chloe", "mFullStack is checked? " + mFullStack.isChecked());
        }else{
            tagListSet.remove("fullstack");
//            removeFromPreferences(Constants.FILTER_FULLSTACK);
        }

        if(mProductManager.isChecked()){
            tagListSet.add("product_manager");
            checkedResult += "\n  Product manager Checked!";
            savePreferences(Constants.FILTER_PRODUCTMANAGER, isChecked);
        }else{
            tagListSet.remove("product_manager");
//            removeFromPreferences(Constants.FILTER_PRODUCTMANAGER);
        }

        if(mProjectManager.isChecked()){
            tagListSet.add("project_manager");
            checkedResult += "\n  Project Manager Checked!";
            savePreferences(Constants.FILTER_PROJECTMANAGER, isChecked);
        }else{
            tagListSet.remove("project_manager");
//            removeFromPreferences(Constants.FILTER_PROJECTMANAGER);
        }

        if(mWebD.isChecked()){
            tagListSet.add("web_designer");
            checkedResult += "\n  Web designer Checked!";
            savePreferences(Constants.FILTER_WEBDESIGNER, isChecked);
        }else{
            tagListSet.remove("web_designer");
//            removeFromPreferences(Constants.FILTER_WEBDESIGNER);
        }
        if(mUiUxD.isChecked()){
            tagListSet.add("uiux_designer");
            checkedResult += "\n  UIUX Checked!";
            savePreferences(Constants.FILTER_UIUXDESIGNER, isChecked);
        }else{
            tagListSet.remove("uiux_designer");
//            removeFromPreferences(Constants.FILTER_UIUXDESIGNER);
        }

        if(mFullTime.isChecked()){
            tagListSet.add("fulltime");
            checkedResult += "\n  FullTime Checked!";
            savePreferences(Constants.FILTER_FULLTIME, isChecked);
        }else{
            tagListSet.remove("fulltime");
//            removeFromPreferences(Constants.FILTER_FULLTIME);
        }

        if(mPartTime.isChecked()){
            tagListSet.add("parttime");
            checkedResult += "\n  Part time Checked!";
            savePreferences(Constants.FILTER_PARTTIME, isChecked);
        }else{
            tagListSet.remove("parttime");
//            removeFromPreferences(Constants.FILTER_PARTTIME);
        }

        if(mContract.isChecked()){
            tagListSet.add("contract");
            checkedResult += "\n  Contract Checked!";
            savePreferences(Constants.FILTER_CONTRACT, isChecked);
        }else{
            tagListSet.remove("contract");
//            removeFromPreferences(Constants.FILTER_CONTRACT);
        }

        if(mPermanent.isChecked()){
            tagListSet.add("permanent");
            checkedResult += "\n  Permanent Checked!";
            savePreferences(Constants.FILTER_PERMANENT, isChecked);
        }else{
            tagListSet.remove("permanent");
//            removeFromPreferences(Constants.FILTER_PERMANENT);
        }

        if(mIntern.isChecked()){
            tagListSet.add("intern");
            checkedResult += "\n  Intern Checked!";
            savePreferences(Constants.FILTER_INTERNSHIP, isChecked);
        }else{
            tagListSet.remove("intern");
//            removeFromPreferences(Constants.FILTER_INTERNSHIP);
        }

        if(mRemote.isChecked()){
            tagListSet.add("remote");
            checkedResult += "\n  Remote Checked!";
            savePreferences(Constants.FILTER_REMOTE, isChecked);
        }else{
            tagListSet.remove("remote");
//            removeFromPreferences(Constants.FILTER_REMOTE);
        }

        tagListResult = new ArrayList<>(tagListSet);
        Log.d("Chloe", "Tag list set: " + tagListSet);
        Log.d("Chloe", "Tag list result: " + tagListResult);
        tags = String.join(",", tagListResult);
        Log.d("Chloe", "Tags: " + tags);
        Toast.makeText(FilterActivity.this, checkedResult, Toast.LENGTH_SHORT).show();
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
        ApiJobManager.getInstance().getFilterJobs(tags, new GetFilterJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                mJobs = jobs;
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