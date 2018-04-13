package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;
import java.lang.Runnable;
import android.app.DialogFragment;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class testIntegrationMainMenuCustomerRepActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity mActivity;
    private Instrumentation.ActivityMonitor monitor;
    private Activity receiverMenuCustomerRepActivity; //reference to menu customer rep activity after login
    private String cRepUser = "customerRep";
    private String cRepPassword = "password";

    public testIntegrationMainMenuCustomerRepActivity()
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
        super.setUp();
        setActivityInitialTouchMode(true);

        monitor = getInstrumentation().addMonitor(MainMenuCustomerRepActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);


        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText username = (EditText)mActivity.findViewById(R.id.input_username);
                EditText password = (EditText)mActivity.findViewById(R.id.input_password);
                username.requestFocus();
                username.setText(cRepUser);
                password.requestFocus();
                password.setText(cRepPassword);

            }
        });

        TouchUtils.clickView(this, loginButton);

        receiverMenuCustomerRepActivity = monitor.waitForActivityWithTimeout(1000);

        if(receiverMenuCustomerRepActivity == null){
            receiverMenuCustomerRepActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(receiverMenuCustomerRepActivity);
        }

        assertNotNull(receiverMenuCustomerRepActivity);

    }


    /**
     * Method: testIntegrationSignUpCustomerDialog()
     * Purpose: Tests whether SignUpCustomer dialog popup shows up when the Sign Up Customer button
     * is clicked on.
     */
    /*
    @MediumTest
    public void testIntegrationSignUpCustomerDialog() {

        monitor = getInstrumentation().addMonitor(AccountProfileActivity.class.getName(), null, false);

        assertNotNull(receiverMenuCustomerRepActivity);

        Activity receiverActivity;

        Button signUpCustomerButton = (Button)receiverMenuCustomerRepActivity.findViewById(R.id.button_09);

        TouchUtils.clickView(this, signUpCustomerButton);




        // NOTE: For some reason, cannot put this code in tear down. Wouldn't close down retail
        // main menu in order to start from login activity again
        if(receiverMenuCustomerRepActivity != null){
            receiverMenuCustomerRepActivity.finish();
            receiverMenuCustomerRepActivity = null;
        }

    }
*/
    /**
     * Method: testIntegrationAssistCustomer()
     * Purpose: Tests whether Assist Customer activity is started when the Assist Customer button
     * is clicked on
     */
    @MediumTest
    public void testIntegrationAssistCustomer() {

        monitor = getInstrumentation().addMonitor(AssistCustomerActivity.class.getName(), null, false);

        assertNotNull(receiverMenuCustomerRepActivity);

        Activity receiverActivity;

        Button assistCustomerButton = (Button)receiverMenuCustomerRepActivity.findViewById(R.id.button_03);

        TouchUtils.clickView(this, assistCustomerButton);

        // Checks if monitor picked up an activity intent to Account Profile Activity
        receiverActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNotNull() should return true. False means the test
        // failed. Here we do a pretest. If it gets through the if statement, we want to wait
        // a bit longer to see if the activity pops up
        if(receiverActivity == null) {
            receiverActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(receiverActivity);
        }

        assertNotNull(receiverActivity);

        // If the test passed, end the activity
        if(receiverActivity != null) {
            receiverActivity.finish();
            receiverActivity = null;
        }

        // NOTE: For some reason, cannot put this code in tear down. Wouldn't close down retail
        // main menu in order to start from login activity again
        if(receiverMenuCustomerRepActivity != null){
            receiverMenuCustomerRepActivity.finish();
            receiverMenuCustomerRepActivity = null;
        }

    }

    /*
        @MediumTest
        public void testIntegrationLogout() {
            monitor = getInstrumentation().addMonitor(login_screen.class.getName(), null, false);

            mActivity = getActivity();
            assertNotNull(mActivity);

            Activity receiverActivity = null;

            Button button = (Button)mActivity.findViewById(R.id.button_09a);

            TouchUtils.clickView(this, button);

            receiverActivity = monitor.waitForActivityWithTimeout(1000);

            assertNotNull(receiverActivity);

            if(receiverActivity == null) {

                receiverActivity = monitor.waitForActivityWithTimeout(1000);
            }
            if(receiverActivity != null) {
                receiverActivity.finish();
                receiverActivity = null;
            }
        }
    */
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        if(mActivity != null) {
            mActivity.finish();
            mActivity = null;
        }
        if(monitor != null) {
            getInstrumentation().removeMonitor(monitor);
            monitor = null;
        }

    }
}