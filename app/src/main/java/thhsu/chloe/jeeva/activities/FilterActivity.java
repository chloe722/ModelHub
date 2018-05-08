package thhsu.chloe.jeeva.activities;

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
import java.util.HashMap;
import java.util.List;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.Filter.FilterContract;
import thhsu.chloe.jeeva.Filter.FilterFragment;
import thhsu.chloe.jeeva.Home.HomeFragment;
import thhsu.chloe.jeeva.JeevaContract;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FilterContract.Presenter mPresenter;

    public CheckBox mFrontend, mBackend, mFullStack, mWebD,
            mUiUxD, mProductM, mProjectM, mFullTime,
            mPartTime, mContract, mPermanent, mIntern, mRemote;
    public Button mSavedBtn;
    public ImageButton mFilterBackBtn;
    public Toolbar mToolbar;
    public TextView mFilterPositionTitle, mFilterTypeTitle;
    public HashMap<String, Boolean> filterResult;
    public HashMap<String, Boolean> filterResultTest;
    public List<CheckBox> items;
    public boolean checked = false;
    HomeFragment homeFragment;
    SharedPreferences sharedPreferences;

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        homeFragment = new HomeFragment();

        mContext = this;
        filterResult = new HashMap<String, Boolean>();
        filterResultTest = new HashMap<String, Boolean>();
        items = new ArrayList<CheckBox>();
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
            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFrontend.getId()), mFrontend.isChecked());
            checkedResult += "\n  Frontend Chcecked!";
//            savePreferences(mFrontend.getText().toString(),mFrontend.isChecked());
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mBackend.getId()), mBackend.isChecked());
            checkedResult += "\n  Backend Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFullStack.getId()), mFullStack.isChecked());
            checkedResult += "\n  FullStack Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mUiUxD.getId()), mUiUxD.isChecked());
            checkedResult += "\n  UiUxD Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mWebD.getId()), mWebD.isChecked());
            checkedResult += "\n  mWebD Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mProductM.getId()), mProductM.isChecked());
            checkedResult += "\n  ProductM Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mProjectM.getId()), mProjectM.isChecked());
            checkedResult += "\n  ProjectM Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mFullTime.getId()), mFullTime.isChecked());
            checkedResult += "\n  FullTime Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mPartTime.getId()), mPartTime.isChecked());
            checkedResult += "\n  PartTime Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mPermanent.getId()), mPermanent.isChecked());
            checkedResult += "\n  Permanent Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mContract.getId()), mContract.isChecked());
            checkedResult += "\n  Contract Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mIntern.getId()), mIntern.isChecked());
            checkedResult += "\n  Intern Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

            filterResultTest.put(getApplicationContext().getResources().getResourceEntryName(mRemote.getId()), mRemote.isChecked());
            checkedResult += "\n  Remote Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);

        Toast.makeText(FilterActivity.this, checkedResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
//        filterResult.putAll(filterResult);
        switch (v.getId()){
            case R.id.filter_save_btn:
                Log.d("Chloe", "saved clicked filter Result: " + filterResultTest);
                //post value with API to backend
                Intent intent = new Intent(FilterActivity.this, JeevaActivity.class);
                startActivity(intent);
            case R.id.filter_tool_bar_back_btn:
                super.onBackPressed();
        }

    }

}
