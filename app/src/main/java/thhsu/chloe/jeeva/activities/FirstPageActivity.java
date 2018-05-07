package thhsu.chloe.jeeva.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;

import thhsu.chloe.jeeva.R;

public class FirstPageActivity extends BaseActivity {
    private Button mGetStartedBtn, mFirstPageSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        mGetStartedBtn = (Button) findViewById(R.id.getStartedBtn);
        mFirstPageSignInBtn = (Button) findViewById(R.id.first_page_signIn_btn);
        mGetStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FirstPageActivity.this, JeevaActivity.class);
                startActivity(intent);
            }
        });

        mFirstPageSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPageActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
