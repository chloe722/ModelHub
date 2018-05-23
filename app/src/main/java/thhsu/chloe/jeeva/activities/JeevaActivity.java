package thhsu.chloe.jeeva.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import thhsu.chloe.jeeva.Filter.FilterFragment;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import thhsu.chloe.jeeva.JeevaContract;
import thhsu.chloe.jeeva.JeevaPresenter;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class JeevaActivity extends BaseActivity implements JeevaContract.View, BottomNavigationView.OnNavigationItemSelectedListener{
    private JeevaContract.Presenter mPresenter;
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private ImageButton mFilterIcn;
    private BottomNavigationView mBottomNavigationView;
    private MenuItem mFilterItem, mBtnNavProfile, mBtnNavSignIn, mLogoutBtn, mAboutBtn, mEditedBtn;
//    private FilterFragment filterFragment;
    private boolean isFilterInHome = true;
    private boolean shouldShowFilter = false;
    private Fragment currentFragment;
    private Button mToolBarBackBtn;
    JeevaActivity jeevaActivity;
    private SharedPreferences mSharePref;
    String token;
    private ProgressBar mProgressBar;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            init();
    }

    private void init(){
        setContentView(R.layout.activity_main);
        setBottomNavigationView();
        setToolbar();
        mSharePref = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        token = mSharePref.getString(Constants.USER_TOKEN, "");
        mProgressBar = (ProgressBar) this.findViewById(R.id.loading_progressBar);
        Log.d("Chloe", "if progressbar loaded?" + mProgressBar);
        mPresenter = new JeevaPresenter(this, getFragmentManager(), this, mBottomNavigationView, mToolbar, mProgressBar);
        mPresenter.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        jeevaActivity = this;
        int currentItem = mBottomNavigationView.getSelectedItemId();
        MenuInflater inflater = getMenuInflater();
        if(!(token.equals(""))){
            mBottomNavigationView.getMenu().getItem(2).setTitle("Profile");
        }

        if (currentItem == R.id.action_home){
            inflater.inflate(R.menu.menu_filter, menu);
                mFilterItem = menu.findItem(R.id.home_filter).setVisible(true);
                Log.d("Chloe", "currentItemId: " + currentItem);
                mFilterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(jeevaActivity, FilterActivity.class);
                        startActivityForResult(intent, Constants.FILTER_REQUEST); // start the activity for requesting result from next activity
                        return true;
                    }
                });
        }else if(currentItem == R.id.action_profile){
            inflater.inflate(R.menu.menu_more_member, menu);
            mAboutBtn = menu.findItem(R.id.more_menu_about_item);
            mLogoutBtn = menu.findItem(R.id.more_menu_logout_item);
            mEditedBtn = menu.findItem(R.id.more_menu_about_edit);
            mAboutBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(jeevaActivity, AboutpageActivity.class);
                    startActivity(intent);
                    return true;
                }
            });

            if(!(token.equals(""))){

                mEditedBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(JeevaActivity.this, AboutMeActivity.class);
                        startActivityForResult(intent, Constants.USER_INFO_REQUEST);
                        return false;
                    }
                });

                mLogoutBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mSharePref.edit().remove(Constants.USER_TOKEN)
                                .apply();
                        Log.d("Chloe", "shared status: " + mSharePref.getString(Constants.USER_TOKEN, ""));
                        Toast.makeText(getApplicationContext(), "log out btn click", Toast.LENGTH_SHORT).show();
                        init();
                        return true;
                    }
                });
            }
            else{
                mLogoutBtn.setVisible(false);
                mEditedBtn.setVisible(false);
            }
        }else if(currentItem == R.id.action_saved_job){

        }

//        if(currentFragment == getFragmentManager().findFragmentByTag(JOBDETAILS)){
//            mToolBarBackBtn.setVisibility(View.VISIBLE);
//            mToolBarBackBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "Button click", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
        return true;
    }

//    }


    @Override
    protected void onResume() {super.onResume();}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Chloe", "requestCode" + requestCode + "resultCode" + resultCode);
        if(requestCode == Constants.FILTER_REQUEST && resultCode == Constants.RESULT_SUCCESS){
            mPresenter.result(Constants.FILTER_REQUEST, Constants.RESULT_SUCCESS, data);
        }
        else{
            init();
        }
    }

    private void setBottomNavigationView(){
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void setToolbar() {
        // Retrieve the AppCompact Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("Home");

    }

    private void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

//    public void hideFilter(){
//        shouldShowFilter = false;
//        invalidateOptionsMenu();
//    }
//
//    public void showFilter(){
//        shouldShowFilter = true;
//        invalidateOptionsMenu();
//    }

    @Override
    public void setPresenter(JeevaContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void showHomeUi() {
        setToolbarTitle("");
        isFilterInHome = true;
//        showFilter();

    }

    @Override
    public void showSavedJobUi() {
        setToolbarTitle("");
        isFilterInHome = false;
//        hideFilter();
    }

    @Override
    public void showProfileUi() {
        setToolbarTitle("Profile");
        isFilterInHome = false;
//        hideFilter();
    }

    @Override
    public void showSignInTabPageUi() {
        setToolbarTitle("Join Jeeva!");
        isFilterInHome = false;
//        hideFilter();
    }

    @Override
    public void showFilterPageUi() {
        invalidateOptionsMenu();
        setToolbarTitle("");
        isFilterInHome = false;
//        hideFilter();
    }

    @Override
    public void showJobDetailsUi() {
        setToolbarTitle("");
    }

    @Override
    public void refreshSavedJobsItemUi() {
        mPresenter.refreshSavedJobsItem();
    }

    public void transToJobDetails(Jobs job){ // Need to pass ID here after connect API
        mPresenter.transToJobDetails(job); // Need to pass ID here after connect API
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mSharePref = JeevaActivity.this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        token = mSharePref.getString(Constants.USER_TOKEN, "");
        invalidateOptionsMenu();
        switch (item.getItemId()) {
            case R.id.action_home:
                mPresenter.transToHome();
                break;

            case R.id.action_saved_job:
                mPresenter.transToSavedJob();
                break;

            case R.id.action_profile:
                if(token.equals("")){
                    mPresenter.transToSignInTabPage();
                    break;
                }else {
                    mPresenter.transToProfile();
                    break;
                }
        }
        return true;
    }


    public void showBtnNavView(){
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    public void showFilterIcn(){
        mToolbar.findViewById(R.id.home_filter).setVisibility(View.VISIBLE);
    }

    public void hideToolbarBackBtn(){
        this.findViewById(R.id.tool_bar_back_btn).setVisibility(View.GONE);
    }

    private void clearData(){
        mSharePref.edit()
                .remove(Constants.FILTER_FRONTEND)
                .remove(Constants.FILTER_BACKEND)
                .remove(Constants.FILTER_FULLSTACK)
                .remove(Constants.FILTER_PROJECTMANAGER)
                .remove(Constants.FILTER_PRODUCTMANAGER)
                .remove(Constants.FILTER_WEBDESIGNER)
                .remove(Constants.FILTER_UIUXDESIGNER)
                .remove(Constants.FILTER_FULLTIME)
                .remove(Constants.FILTER_PARTTIME)
                .remove(Constants.FILTER_PERMANENT)
                .remove(Constants.FILTER_REMOTE)
                .remove(Constants.FILTER_CONTRACT)
                .remove(Constants.FILTER_INTERNSHIP)
                .apply();

    }


}
