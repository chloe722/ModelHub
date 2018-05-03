package thhsu.chloe.jeeva.activities;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;

import thhsu.chloe.jeeva.JeevaContract;
import thhsu.chloe.jeeva.JeevaPresenter;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 4/30/2018.
 */

public class JeevaActivity extends BaseActivity implements JeevaContract.View, BottomNavigationView.OnNavigationItemSelectedListener{
    private JeevaContract.Presenter mPresenter;
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private ImageButton mFilterIcn;
    private BottomNavigationView mBottomNavigationView;
    private MenuItem mFilterItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init(){
        setContentView(R.layout.activity_main);
        setBottomNavigationView();
        setToolbar();

        mPresenter = new JeevaPresenter(this, getFragmentManager());
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int currentItem = mBottomNavigationView.getSelectedItemId();
        MenuInflater inflater = getMenuInflater();
        if (currentItem == R.id.action_home){
            inflater.inflate(R.menu.menu_filter, menu);
            mFilterItem = menu.findItem(R.id.home_filter);
            Log.d("Chloe", "currentItemId: " + currentItem);
        }else if(currentItem == R.id.action_saved_job){

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        init();
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

//    public void setToolbarVisibility(boolean isVisible){
//        if(mToolbar != null){
//            mToolbar.setVisibility(isVisible) ? View.VISIBLE: View.GONE;
//        }
//    }

    @Override
    public void setPresenter(JeevaContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void showHomeUi() {
        setToolbarTitle("Home");

    }

    @Override
    public void showSavedJobUi() {
        setToolbarTitle("Saved Jobs");
    }

    @Override
    public void showProfileUi() {
        setToolbarTitle("Profile");
    }

    @Override
    public void showSignInTabPageUi() {
        setToolbarTitle("Sign Up or Sign In");
    }

    @Override
    public void showFilterPageUi() {
        setToolbarTitle("Filter");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:

                mPresenter.transToHome();
                break;

            case R.id.action_saved_job:
                mPresenter.transToSavedJob();
                break;

//            case R.id.action_profile:
//                mPresenter.transToSignInTabPage();
//                return true;

            case R.id.action_profile:
                mPresenter.transToProfile();

                break;
        }
        return true;
    }
}
