package thhsu.chloe.jeeva.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.Filter.FilterContract;
//import thhsu.chloe.jeeva.Filter.FilterFragment;
import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.Home.HomeFragment;
import thhsu.chloe.jeeva.JeevaContract;
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
            mUiUxD, mProductM, mProjectM, mFullTime,
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
//        filterResult = new HashMap<String, Boolean>();
//        filterResultTest = new HashMap<String, Boolean>();
        items = new ArrayList<CheckBox>();
        tagListSet = new HashSet<String>();
        mJobs = new ArrayList<Jobs>();
        mFrontend = (CheckBox) findViewById(R.id.filter_checkbox_frontend);
        mBackend = (CheckBox) findViewById(R.id.filter_checkbox_backend);
        mFullStack = (CheckBox) findViewById(R.id.filter_checkbox_fullstack);
        mWebD = (CheckBox) findViewById(R.id.filter_checkbox_web_designer);
        mUiUxD = (CheckBox) findViewById(R.id.filter_checkbox_uiux_designer);
        mProductM = (CheckBox) findViewById(R.id.filter_checkbox_product_m);
        mProjectM = (CheckBox) findViewById(R.id.filter_checkbox_project_m);
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

        mFrontend.setOnCheckedChangeListener(this);
        mBackend.setOnCheckedChangeListener(this);
        mFullStack.setOnCheckedChangeListener(this);
        mWebD.setOnCheckedChangeListener(this);
        mUiUxD.setOnCheckedChangeListener(this);
        mProductM.setOnCheckedChangeListener(this);
        mProjectM.setOnCheckedChangeListener(this);
        mFullTime.setOnCheckedChangeListener(this);
        mPartTime.setOnCheckedChangeListener(this);
        mContract.setOnCheckedChangeListener(this);
        mPermanent.setOnCheckedChangeListener(this);
        mIntern.setOnCheckedChangeListener(this);
        mRemote.setOnCheckedChangeListener(this);
        mSavedBtn.setOnClickListener(this);
        mFilterBackBtn.setOnClickListener(this);

    }

    @Override
    public void setPresenter(FilterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    //If user checked the box, Add key and value into HashMap

    private void savePreferences(String key, Boolean value){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String checkedResult = "On checked Selected: ";

        if(mFrontend.isChecked()){
            tagListSet.add("frontend");
            checkedResult += "\n  Frontend Checked!";
        }else{
            tagListSet.remove("frontend");
        }

        if(mBackend.isChecked()){
            tagListSet.add("backend");
            checkedResult += "\n  Backend Checked!";
        }else{
            tagListSet.remove("backend");
        }

        if(mFullStack.isChecked()){
            tagListSet.add("fullstack");
            checkedResult += "\n  FullStack Checked!";
        }else{
            tagListSet.remove("fullstack");
        }

        if(mProductM.isChecked()){
            tagListSet.add("product_manager");
            checkedResult += "\n  Product manager Checked!";
        }else{
            tagListSet.remove("product_manager");
        }

        if(mProjectM.isChecked()){
            tagListSet.add("project_manager");
            checkedResult += "\n  Project Manager Checked!";
        }else{
            tagListSet.remove("project_manager");
        }

        if(mWebD.isChecked()){
            tagListSet.add("web_designer");
            checkedResult += "\n  Web designer Checked!";
        }else{
            tagListSet.remove("web_designer");
        }
        if(mUiUxD.isChecked()){
            tagListSet.add("uiux_designer");
            checkedResult += "\n  UIUX Checked!";
        }else{
            tagListSet.remove("uiux_designer");
        }

        if(mFullTime.isChecked()){
            tagListSet.add("fulltime");
            checkedResult += "\n  FullTime Checked!";
        }else{
            tagListSet.remove("fulltime");
        }

        if(mPartTime.isChecked()){
            tagListSet.add("parttime");
            checkedResult += "\n  Part time Checked!";
        }else{
            tagListSet.remove("parttime");
        }

        if(mContract.isChecked()){
            tagListSet.add("contract");
            checkedResult += "\n  Contract Checked!";
        }else{
            tagListSet.remove("contract");
        }

        if(mPermanent.isChecked()){
            tagListSet.add("permanent");
            checkedResult += "\n  Permanent Checked!";
        }else{
            tagListSet.remove("permanent");
        }

        if(mIntern.isChecked()){
            tagListSet.add("intern");
            checkedResult += "\n  Intern Checked!";
        }else{
            tagListSet.remove("intern");
        }

        if(mRemote.isChecked()){
            tagListSet.add("remote");
            checkedResult += "\n  Remote Checked!";
        }else{
            tagListSet.remove("remote");
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
                bundle = new Bundle();
                ApiJobManager.getInstance().getFilterJobs(tags, new GetFilterJobsCallBack() {
                    @Override
                    public void onCompleted(ArrayList<Jobs> jobs) {
                        mJobs = jobs;
                        if(jobs.size() > 0){
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

            break;  //Dont forget to write break in Switch!! Otherwise the code gonna continue running to next case
            case R.id.filter_tool_bar_back_btn:
                super.onBackPressed();
                break;
        }

    }

}






//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFrontend.getId()), mFrontend.isChecked());
//        checkedResult += "\n  Frontend Checked!";
////            savePreferences(mFrontend.getText().toString(),mFrontend.isChecked());
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mBackend.getId()), mBackend.isChecked());
//            checkedResult += "\n  Backend Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFullStack.getId()), mFullStack.isChecked());
//
//
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mUiUxD.getId()), mUiUxD.isChecked());
//            checkedResult += "\n  UiUxD Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mWebD.getId()), mWebD.isChecked());
//            checkedResult += "\n  mWebD Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mProductM.getId()), mProductM.isChecked());
//            checkedResult += "\n  ProductM Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mProjectM.getId()), mProjectM.isChecked());
//            checkedResult += "\n  ProjectM Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFullTime.getId()), mFullTime.isChecked());
//            checkedResult += "\n  FullTime Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mPartTime.getId()), mPartTime.isChecked());
//            checkedResult += "\n  PartTime Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mPermanent.getId()), mPermanent.isChecked());
//
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mContract.getId()), mContract.isChecked());
//
//            checkedResult += "\n  Contract Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mIntern.getId()), mIntern.isChecked());
//            checkedResult += "\n  Intern Chcecked!";
//            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mRemote.getId()), mRemote.isChecked());
//            checkedResult += "\n  Remote Chcecked!";
