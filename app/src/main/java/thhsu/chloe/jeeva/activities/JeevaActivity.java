package thhsu.chloe.jeeva.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import thhsu.chloe.jeeva.JeevaContact;
import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 4/30/2018.
 */

public class JeevaActivity extends BaseActivity implements JeevaContact.View, NavigationView.OnNavigationItemSelectedListener{


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:

            case R.id.action_saved_job:

            case R.id.action_profile:
        }
        return false;
    }



    @Override
    public void setPresenter(JeevaContact.Presenter presenter) {

    }

    @Override
    public void showHomeUi() {

    }

    @Override
    public void showSavedJobUi() {

    }

    @Override
    public void showProfileUi() {

    }
}
