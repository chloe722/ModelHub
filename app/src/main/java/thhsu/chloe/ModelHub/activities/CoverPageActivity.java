package thhsu.chloe.ModelHub.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thhsu.chloe.ModelHub.R;

public class CoverPageActivity extends BaseActivity {
    private Button mGetStartedBtn, mCoverPageSignInBtn;
    private View backgroundImage;
    private Drawable background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_page);



        backgroundImage = (View) findViewById(R.id.coverpage_constraint);
        background = backgroundImage.getBackground();
//        background.setAlpha(90);
        mGetStartedBtn = (Button) findViewById(R.id.getStartedBtn);
        mCoverPageSignInBtn = (Button) findViewById(R.id.cover_page_signIn_btn);

        mGetStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CoverPageActivity.this, ModelHubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        mCoverPageSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoverPageActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
