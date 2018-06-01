package thhsu.chloe.ModelHub.AboutPage;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.activities.AboutpageActivity;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutPagePresenter implements AboutPageContract.Presenter {
    private final AboutPageContract.View mAboutPageView;
    private Toolbar mToolbar;
    private FragmentManager mFragmentManager;
    private AboutpageActivity mAboutpageActivity;

    public final static String ABOUT_MAIN = "ABOUTMAIN";
    public final static String ABOUT_MODELHUB = "ABOUTMODELHUB";
    public final static String PRIVACY_POLICY = "PRIVACYPOLICY";
    public final static String TERM_OF_USE = "TERMOFUSE";
    public final static String CONTACT_US = "CONTACTUS";


    private AboutMainFragment mAboutMainFragment;
    private AboutModelHubFragment mAboutModelHubFragment;
    private AboutPrivacyPolicyFragment mAboutPrivacyPolicyFragment;
    private AboutTermOfUseFragment mAboutTermOfUseFragment;
    private AboutContactUsFragment mAboutContactUsFragment;



    public AboutPagePresenter(AboutPageContract.View aboutpageView, FragmentManager fragmentManager, Toolbar toolbar, AboutpageActivity aboutpageActivity) {
        mAboutPageView = aboutpageView;
        if(aboutpageView != null){
            mAboutPageView.setPresenter(this);
        }
        mToolbar = toolbar;
        this.mFragmentManager = fragmentManager;
        this.mAboutpageActivity = aboutpageActivity;
    }

    @Override
    public void start() {
        transToItemsMain();
    }

    @Override
    public void transToItemsMain() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if(mAboutMainFragment == null) mAboutMainFragment = AboutMainFragment.newInstance();
        if(mAboutModelHubFragment != null) transaction.hide(mAboutModelHubFragment);

        if(!mAboutMainFragment.isAdded()){
            transaction.add(R.id.aboutpage_content_container, mAboutMainFragment, ABOUT_MAIN);
        }else{
            transaction.show(mAboutMainFragment);
        }
        transaction.commit();
        mAboutPageView.showAboutUi();

    }

    @Override
    public void transToAbout() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        mAboutModelHubFragment = AboutModelHubFragment.newInstance();

        if( mAboutMainFragment!= null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, mAboutModelHubFragment, ABOUT_MODELHUB);
        transaction.commit();
        mAboutPageView.showAboutUi();

    }

    @Override
    public void transToPrivacyPolicy() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        mAboutPrivacyPolicyFragment = AboutPrivacyPolicyFragment.newInstance();

        if(mAboutMainFragment != null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, mAboutPrivacyPolicyFragment, PRIVACY_POLICY);
        transaction.commit();
        mAboutPageView.showPrivacyPolicyUi();

    }

    @Override
    public void transToTermOfUs() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        mAboutTermOfUseFragment = AboutTermOfUseFragment.newInstance();

        if(mAboutMainFragment!= null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, mAboutTermOfUseFragment, TERM_OF_USE);
        transaction.commit();
        mAboutPageView.showTermOfUsUi();
    }

    @Override
    public void transToContactUs() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        mAboutContactUsFragment = AboutContactUsFragment.newInstance();

        if(mAboutMainFragment != null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, mAboutContactUsFragment, CONTACT_US);
        transaction.commit();
        mAboutPageView.showContactUsUi();
    }


}
