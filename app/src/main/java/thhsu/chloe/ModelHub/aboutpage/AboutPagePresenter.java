package thhsu.chloe.ModelHub.aboutpage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutPagePresenter implements AboutPageContract.Presenter {
    private final AboutPageContract.View mAboutPageView;
    private FragmentManager mFragmentManager;

    private final static String ABOUT_MAIN = "ABOUTMAIN";
    private final static String TERM_OF_USE = "TERMOFUSE";
    private final static String CONTACT_US = "CONTACTUS";
    private final static String ABOUT_MODELHUB = "ABOUTMODELHUB";
    private final static String PRIVACY_POLICY = "PRIVACYPOLICY";


    private AboutMainFragment mAboutMainFragment;
    private AboutModelHubFragment mAboutModelHubFragment;


    public AboutPagePresenter(AboutPageContract.View aboutView, FragmentManager fragmentManager, Toolbar toolbar) {
        mAboutPageView = aboutView;
        if(aboutView != null){
            mAboutPageView.setPresenter(this);
        }
        mFragmentManager = fragmentManager;
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
        mAboutPageView.showAboutModelHubUi();

    }

    @Override
    public void transToPrivacyPolicy() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        AboutPrivacyPolicyFragment aboutPrivacyPolicyFragment =
                AboutPrivacyPolicyFragment.newInstance();

        if(mAboutMainFragment != null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, aboutPrivacyPolicyFragment, PRIVACY_POLICY);
        transaction.commit();
        mAboutPageView.showPrivacyPolicyUi();

    }

    @Override
    public void transToTermOfUs() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        AboutTermOfUseFragment aboutTermOfUseFragment =
                AboutTermOfUseFragment.newInstance();

        if(mAboutMainFragment!= null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, aboutTermOfUseFragment, TERM_OF_USE);
        transaction.commit();
        mAboutPageView.showTermOfUsUi();
    }

    @Override
    public void transToContactUs() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
        AboutContactUsFragment aboutContactUsFragment =
                AboutContactUsFragment.newInstance();

        if(mAboutMainFragment != null){
            transaction.hide(mAboutMainFragment);
            transaction.addToBackStack(ABOUT_MAIN);
        }

        transaction.add(R.id.aboutpage_content_container, aboutContactUsFragment, CONTACT_US);
        transaction.commit();
        mAboutPageView.showContactUsUi();
    }


}
