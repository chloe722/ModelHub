package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thhsu.chloe.ModelHub.R;

public class FirstPageActivity extends BaseActivity {
    private Button mGetStartedBtn, mFirstPageSignInBtn;
    private View backgroundImage;
    private Drawable background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);



        backgroundImage = (View) findViewById(R.id.firstpage_constraint);
        background = backgroundImage.getBackground();
//        background.setAlpha(90);
        mGetStartedBtn = (Button) findViewById(R.id.getStartedBtn);
        mFirstPageSignInBtn = (Button) findViewById(R.id.first_page_signIn_btn);

        mGetStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FirstPageActivity.this, ModelHubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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
