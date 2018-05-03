package thhsu.chloe.jeeva.activities;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
            filterResultTest.put(mFrontend.getText().toString(), true);
            checkedResult += "\n  Frontend Chcecked!";
            savePreferences(mFrontend.getText().toString(), true);
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mFrontend.getText().toString());
//            checkedResult += "\n  Fronted unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mBackend.isChecked()){
            filterResultTest.put(mBackend.getText().toString(), true);
            checkedResult += "\n  Backend Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mBackend.getText().toString());
//            checkedResult += "\n  Backend unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mFullStack.isChecked()){
            filterResultTest.put(mFullStack.getText().toString(), true);
            checkedResult += "\n  FullStack Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mFullStack.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mUiUxD.isChecked()){
            filterResultTest.put(mUiUxD.getText().toString(), true);
            checkedResult += "\n  UiUxD Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mUiUxD.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mWebD.isChecked()){
            filterResultTest.put(mWebD.getText().toString(), true);
            checkedResult += "\n  mWebD Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mWebD.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mProductM.isChecked()){
            filterResultTest.put(mProductM.getText().toString(), true);
            checkedResult += "\n  ProductM Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mProductM.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mProjectM.isChecked()){
            filterResultTest.put(mProjectM.getText().toString(), true);
            checkedResult += "\n  ProjectM Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mProjectM.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mFullTime.isChecked()){
            filterResultTest.put(mFullTime.getText().toString(), true);
            checkedResult += "\n  FullTime Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mFullTime.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mPartTime.isChecked()){
            filterResultTest.put(mPartTime.getText().toString(), true);
            checkedResult += "\n  PartTime Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mPartTime.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mPermanent.isChecked()){
            filterResultTest.put(mPermanent.getText().toString(), true);
            checkedResult += "\n  Permanent Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mPermanent.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mContract.isChecked()){
            filterResultTest.put(mContract.getText().toString(), true);
            checkedResult += "\n  Contract Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mContract.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mIntern.isChecked()){
            filterResultTest.put(mIntern.getText().toString(), true);
            checkedResult += "\n  Intern Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mIntern.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        if(mRemote.isChecked()){
            filterResultTest.put(mRemote.getText().toString(), true);
            checkedResult += "\n  Remote Chcecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }else{
            filterResultTest.remove(mRemote.getText().toString());
//            checkedResult += "\n  FullStack unchecked!";
            Log.d("Chloe", "filterResult: " + filterResultTest);
        }

        Log.d("Chloe", "Total result: " + filterResultTest);
        Toast.makeText(FilterActivity.this, checkedResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
//        filterResult.putAll(filterResult);
        Log.d("Chloe", "saved clicked filter Result: " + filterResultTest);
        //post value with API to backend
        Intent intent = new Intent(FilterActivity.this, JeevaActivity.class);
        startActivity(intent);

    }


//    @Override
//    public void onClick(View v) {
//        String result = "Selected: ";
//        if(mFrontend.isChecked()){
//            filterResult.put(mFrontend.getText().toString(), true);
//            result += "\n Frontend Chcecked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mBackend.isChecked()){
//            filterResult.put(mBackend.getText().toString(), true);
//            result += "\n Backend Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mFullStack.isChecked()){
//            filterResult.put(mFullStack.getText().toString(), true);
//            result += "\n FullStack Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mWebD.isChecked()){
//            filterResult.put(mWebD.getText().toString(), true);
//            result += "\n WebD Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mUiUxD.isChecked()){
//            filterResult.put(mUiUxD.getText().toString(), true);
//            result += "\n UiUx Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mProductM.isChecked()){
//            filterResult.put(mProductM.getText().toString(), true);
//            result += "\n ProductM Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mProjectM.isChecked()){
//            filterResult.put(mProjectM.getText().toString(), true);
//            result += "\n ProjectM Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mFullTime.isChecked()){
//            filterResult.put(mFullTime.getText().toString(), true);
//            result += "\n FullTime Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mPartTime.isChecked()){
//            filterResult.put(mPartTime.getText().toString(), true);
//            result += "\n PartTime Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mContract.isChecked()){
//            filterResult.put(mContract.getText().toString(), true);
//            result += "\n Contract Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mPermanent.isChecked()){
//            filterResult.put(mPermanent.getText().toString(), true);
//            result += "\n Permenent Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mIntern.isChecked()){
//            filterResult.put(mIntern.getText().toString(), true);
//            result += "\n Intern Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }else if(mRemote.isChecked()){
//            filterResult.put(mRemote.getText().toString(), true);
//            result += "\n Remote Checked!";
//            Log.d("Chloe", "filterResult: " + filterResult);
//        }
//
//        Toast.makeText(FilterActivity.this, result, Toast.LENGTH_SHORT).show();
//        Log.d("Chloe", "save button cliked");
//        Log.d("Chloe", "final result " + filterResult);
//
//    }

//    public static FilterFragment newInstance(){return new FilterFragment();}

}
