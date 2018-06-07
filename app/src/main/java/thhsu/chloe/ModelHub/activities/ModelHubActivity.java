package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import thhsu.chloe.ModelHub.ModelHubContract;
import thhsu.chloe.ModelHub.ModelHubPresenter;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ModelHubActivity extends BaseActivity implements ModelHubContract.View, BottomNavigationView.OnNavigationItemSelectedListener{
    private ModelHubContract.Presenter mPresenter;
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;
    private ModelHubActivity mModelHubActivity;
    private SharedPreferences mSharePref;
    private String mToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            init();
    }

    private void init(){
        setContentView(R.layout.activity_main);
        setToolbar();
        setBottomNavigationView();
        mSharePref = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        mToken = mSharePref.getString(Constants.USER_TOKEN, "");
        ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.progressBar_loading);
        mPresenter = new ModelHubPresenter(this, getSupportFragmentManager(), this, mBottomNavigationView, mToolbar, progressBar);
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mModelHubActivity = this;
        int currentItem = mBottomNavigationView.getSelectedItemId();
        MenuInflater inflater = getMenuInflater();
        if (!(mToken.equals(""))) {
            mBottomNavigationView.getMenu().getItem(2).setTitle("Profile");
        }

        if (currentItem == R.id.action_home) {
            inflater.inflate(R.menu.menu_filter, menu);
            MenuItem filterItem = menu.findItem(R.id.menu_home_filter).setVisible(true);
            filterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(mModelHubActivity, FilterActivity.class);
                    startActivityForResult(intent, Constants.FILTER_REQUEST); // start the activity for requesting result from next activity
                    return true;
                }
            });

        } else if (currentItem == R.id.action_profile) {

            inflater.inflate(R.menu.menu_more, menu);
            MenuItem aboutBtn = menu.findItem(R.id.menu_more_about_item);
            MenuItem logoutBtn = menu.findItem(R.id.menu_more_logout_item);
            MenuItem editedBtn = menu.findItem(R.id.menu_edit_profile);
            aboutBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(mModelHubActivity, AboutPageActivity.class);
                    startActivity(intent);
                    return true;
                }
            });

            if (!(mToken.equals(""))) {

                editedBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(ModelHubActivity.this, AboutMeActivity.class);
                        startActivityForResult(intent, Constants.USER_INFO_REQUEST);
                        return false;
                    }
                });

                logoutBtn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mSharePref.edit().remove(Constants.USER_TOKEN).apply();
                        Toast.makeText(getApplicationContext(), "You've logged out", Toast.LENGTH_SHORT).show();
                        init();
                        return true;
                    }
                });
            } else {
                logoutBtn.setVisible(false);
                editedBtn.setVisible(false);
            }
        }
        return true;
    }

    @Override
    protected void onResume() {super.onResume();}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        clearData();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Chloe", "main activity requestCode " + requestCode + " , resultCode: " + resultCode);
        if (requestCode == Constants.FILTER_REQUEST) {
            if (resultCode == Constants.RESULT_SUCCESS) {
                mPresenter.result(Constants.FILTER_REQUEST, Constants.RESULT_SUCCESS, data);
            } else {
                init();
            }
        }
    }

    private void setBottomNavigationView(){
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

    }

    private void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }


    @Override
    public void setPresenter(ModelHubContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showHomeUi() {
        setToolbarTitle("Opportunities");
    }

    @Override
    public void showInterestUi() {
        setToolbarTitle("Interests");
    }

    @Override
    public void showProfileUi() {
        setToolbarTitle("Profile");
    }

    @Override
    public void showSignInTabPageUi() {
        setToolbarTitle("Join ModelHub");
    }

    @Override
    public void showJobDetailsUi() {
        setToolbarTitle("Details");
    }

    @Override
    public void refreshInterestItemUi() {
        mPresenter.refreshInterestItem();
    }

    public void transToJobDetails(Jobs job){ // Need to pass ID here after connect API
        mPresenter.transToJobDetails(job); // Need to pass ID here after connect API
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mSharePref = ModelHubActivity.this.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        mToken = mSharePref.getString(Constants.USER_TOKEN, "");
        invalidateOptionsMenu();
        switch (item.getItemId()) {

            case R.id.action_home:
                mPresenter.transToHome();
                break;

            case R.id.action_interest:
                mPresenter.transToInterest();
                break;

            case R.id.action_profile:
                if(mToken.equals("")){
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
        mToolbar.findViewById(R.id.menu_home_filter).setVisibility(View.VISIBLE);
    }

    public void hideToolbarBackBtn(){
        this.findViewById(R.id.btn_toolbar_back).setVisibility(View.GONE);
    }

    private void clearData(){
        getSharedPreferences(Constants.FILTER_PREFERENCES, MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

}
