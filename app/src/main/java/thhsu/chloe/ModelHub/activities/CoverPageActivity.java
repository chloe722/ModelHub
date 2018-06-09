package thhsu.chloe.ModelHub.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import thhsu.chloe.ModelHub.coverpage.CoverPageContract;
import thhsu.chloe.ModelHub.coverpage.CoverPagePresenter;
import thhsu.chloe.ModelHub.R;

public class CoverPageActivity extends BaseActivity implements CoverPageContract.View{
    private CoverPageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_page);
        Button getStartedBtn = (Button) findViewById(R.id.btn_coverpage_getStarted);
        mPresenter = new CoverPagePresenter(this);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.transToModelHub();
            }
        });

    }

    @Override
    public void setPresenter(CoverPageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
