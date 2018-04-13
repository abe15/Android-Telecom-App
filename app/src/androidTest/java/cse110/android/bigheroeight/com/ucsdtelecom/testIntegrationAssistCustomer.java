package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.w3c.dom.Text;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class testIntegrationAssistCustomer extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo solo;

    public testIntegrationAssistCustomer()
    {
        // We start at login activity so we can open parse connection
        super(LoginActivity.class);
    }

    /**
     * Method: setUp()
     * Purpose: Sets up environment in order to perform integration tests on Main Menu Retail
     * Activity
     */
    @Override
    protected void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
        EditText First = (EditText) solo.getView(R.id.input_username);
        EditText Second = (EditText) solo.getView(R.id.input_password);
        Button login = (Button) solo.getView(R.id.button_00);
        solo.typeText(First, "customerRep");
        solo.typeText(Second, "password");
        solo.clickOnView(login);
        Button assist = (Button) solo.getView(R.id.button_03);
        solo.clickOnView(assist);
        solo.assertCurrentActivity("", AssistCustomerActivity.class);
    }

    /**
     * Method: testIntegrationAssistCustomer()
     * Purpose: Tests whether Assist Customer activity is started when the Assist Customer button
     * is clicked on
     */
    @MediumTest
    public void testIntegrationAssistCustomer() {



    }


    @MediumTest
    public void testIntegrationLogout() {
        Button logout = (Button) solo.getView(R.id.button_14);

        solo.clickOnView(logout);

    }

    @Override
    protected void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }
}