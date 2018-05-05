package thhsu.chloe.jeeva.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;



import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/3/2018.
 */

public class AboutpageActivity extends AppCompatActivity {
    ActionMenuView actionMenuView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
//        actionMenuView = (ActionMenuView) mToolbar.findViewById(R.id.action_menu_view);
        getMenuInflater().inflate(R.menu.menu_toolbar_left_backarrow, actionMenuView.getMenu());

//        actionMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });

    }


}
