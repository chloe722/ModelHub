package thhsu.chloe.jeeva.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/7/2018.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener{
    private Button mBackBtn;
    private Button mSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mBackBtn = (Button) findViewById(R.id.signin_back_btn);
        mSignInBtn = (Button) findViewById(R.id.signin_signin_btn);
        mBackBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin_back_btn:
                super.onBackPressed();

            case R.id.signin_signin_btn:
        }
    }
}
