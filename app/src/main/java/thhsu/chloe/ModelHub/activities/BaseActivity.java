package thhsu.chloe.ModelHub.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

//    private void setStatusBar() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////            window.setStatusBarColor(Color.YELLOW);//calculateStatusColor(Color.WHITE, (int) alphaValue)
//        }
//    }
}
