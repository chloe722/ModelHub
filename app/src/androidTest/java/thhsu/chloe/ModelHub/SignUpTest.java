package thhsu.chloe.ModelHub;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import thhsu.chloe.ModelHub.signUp.SignUpContract;
import thhsu.chloe.ModelHub.signUp.SignUpPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Chloe on 6/14/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class SignUpTest {


    private
    SignUpPresenter signUpPresenter;

    private SignUpContract.View view;

    @Before
    public void setUpPresenter() throws Exception{
        view = mock(SignUpContract.View.class);
//        MockitoAnnotations.initMocks(this);

        Context appContext = InstrumentationRegistry.getTargetContext();
        signUpPresenter = new SignUpPresenter(view,  appContext);

    }

    @Test
    public void registerInvalidInput() throws Exception{
        String name = "";
        String age = "";
        String gender = "";
        String email = "";
        String password = "";
        String confirmPassword = "1";
        signUpPresenter.onClickSignUp(name,age,gender,email,password, confirmPassword);
        verify(view).showNameError(true);
        verify(view).showAgeError(true);
        verify(view).showEmailError(true);
        verify(view).showPasswordError(true);
        verify(view).showConfirmPasswordError(true);
    }

}
