package thhsu.chloe.ModelHub.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 4/30/2018.
 */

public class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;

//        setStatusBar();
    }
//
//    private void updateNavigationBarState() {
//        int actionId = getNavigationMenuItemId();
//        selectBottomNavigationBarItem(actionId);
//    }
//
//    void selectBottomNavigationBarItem(int itemId) {
//        Menu menu = navigationView.getMenu();
//        for (int i = 0, size = menu.size(); i < size; i++) {
//            MenuItem item = menu.getItem(i);
//            boolean shouldBeChecked = item.getItemId() == itemId;
//            if (shouldBeChecked) {
//                item.setChecked(true);
//                break;
//            }
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                startActivity(new Intent(this, LFMainActivity.class));
//                Break;
//            case R.id.navigation_camera:
//                startActivity(new Intent(this, CameraActivity.class));
//                Break;
//            case R.id.navigation_accounts:
//                startActivity(new Intent(this, AccountsActivity.class));
//                Break;
//        }
//        finish();
//        return true;
//
//
//        return true;
//    }
//
//    public abstract int getContentViewId();
//
//    public abstract int getNavigationMenuItemId();

}
