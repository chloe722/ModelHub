package thhsu.chloe.jeeva.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.Filter.FilterContract;
import thhsu.chloe.jeeva.Filter.FilterFragment;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class FilterActivity extends BaseActivity implements FilterContract.View, View.OnClickListener {
    private FilterContract.Presenter mPresenter;

    public CheckBox mFrontend, mBackend, mFullStack, mWebD,
            mUiUxD, mProductM, mProjectM, mFullTime,
            mPartTime, mContract, mPermanent, mIntern, mRemote;
    public Button mSavedBtn;
    public TextView mFilterPositionTitle, mFilterTypeTitle;
    public HashMap<String, Boolean> filterResult;

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mContext = this;
        filterResult = new HashMap<String, Boolean>();
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

        mSavedBtn.setOnClickListener(this);
    }

//    public static FilterFragment newInstance(){return new FilterFragment();}

//    @Nullable
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.activity_filter, container,false);
////        setHasOptionsMenu(true);
//
//        filterResult = new HashMap<String, Boolean>();
//        mFrontend = (CheckBox) root.findViewById(R.id.filter_checkbox_frontend);
//        mBackend = (CheckBox) root.findViewById(R.id.filter_checkbox_backend);
//        mFullStack = (CheckBox) root.findViewById(R.id.filter_checkbox_fullstack);
//        mWebD = (CheckBox) root.findViewById(R.id.filter_checkbox_web_designer);
//        mUiUxD = (CheckBox) root.findViewById(R.id.filter_checkbox_uiux_designer);
//        mProductM = (CheckBox) root.findViewById(R.id.filter_checkbox_product_m);
//        mProjectM = (CheckBox) root.findViewById(R.id.filter_checkbox_project_m);
//        mFullTime = (CheckBox) root.findViewById(R.id.filter_checkbox_fulltime);
//        mPartTime = (CheckBox) root.findViewById(R.id.filter_checkbox_parttime);
//        mContract = (CheckBox) root.findViewById(R.id.filter_checkbox_contract);
//        mPermanent = (CheckBox) root.findViewById(R.id.filter_checkbox_permanent);
//        mIntern = (CheckBox) root.findViewById(R.id.filter_checkbox_intern);
//        mRemote = (CheckBox) root.findViewById(R.id.filter_checkbox_remote);
//        mSavedBtn = (Button) root.findViewById(R.id.filter_save_btn);
//        mFilterPositionTitle = (TextView) root.findViewById(R.id.filter_position_title);
//        mFilterTypeTitle = (TextView) root.findViewById(R.id.filter_type_title);
//
//        mSavedBtn.setOnClickListener(this);
//
//        return root;
//    }


    @Override
    public void setPresenter(FilterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    //If user checked the box, Add key and value into HashMap
    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()){
            case R.id.filter_checkbox_frontend:
                filterResult.put(mFrontend.getTag().toString(), checked);
                Log.d("Chloe", "What come out? " + filterResult.put(mFrontend.getTag().toString(), checked));
                break;
            case R.id.filter_checkbox_backend:
                filterResult.put(mBackend.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_fullstack:
                filterResult.put(mFullStack.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_web_designer:
                filterResult.put(mWebD.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_uiux_designer:
                filterResult.put(mUiUxD.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_product_m:
                filterResult.put(mProductM.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_project_m:
                filterResult.put(mProjectM.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_fulltime:
                filterResult.put(mFullTime.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_parttime:
                filterResult.put(mPartTime.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_contract:
                filterResult.put(mContract.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_permanent:
                filterResult.put(mPermanent.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_intern:
                filterResult.put(mIntern.getTag().toString(), checked);
                break;
            case R.id.filter_checkbox_remote:
                filterResult.put(mRemote.getTag().toString(), checked);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        String result = "Selected: ";
        if(mFrontend.isChecked()){
            result += "\n Frontend Chcecked!";
        }
        Toast.makeText(FilterActivity.this, result, Toast.LENGTH_SHORT).show();
        Log.d("Chloe", "save button cliked");
    }


}
