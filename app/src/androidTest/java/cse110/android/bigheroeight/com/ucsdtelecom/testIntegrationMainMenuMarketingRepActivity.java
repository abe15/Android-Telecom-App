package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;
import java.lang.Runnable;

import cse110.android.bigheroeight.com.ucsdtelecom.Factory.CreatePackageServiceActivity;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class testIntegrationMainMenuMarketingRepActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity mActivity;
    private Instrumentation.ActivityMonitor monitor;
    private Activity receiverMenuMarketingRepActivity; //reference to menu marketing rep activity after login
    private String mRepUser = "marketingRep";
    private String mRepPassword = "password";

    public testIntegrationMainMenuMarketingRepActivity()
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

        monitor = getInstrumentation().addMonitor(MainMenuMarketingRepActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);


        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText username = (EditText)mActivity.findViewById(R.id.input_username);
                EditText password = (EditText)mActivity.findViewById(R.id.input_password);
                username.requestFocus();
                username.setText(mRepUser);
                password.requestFocus();
                password.setText(mRepPassword);

            }
        });

        TouchUtils.clickView(this, loginButton);

        receiverMenuMarketingRepActivity = monitor.waitForActivityWithTimeout(1000);

        if(receiverMenuMarketingRepActivity == null){
            receiverMenuMarketingRepActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(receiverMenuMarketingRepActivity);
        }

        assertNotNull(receiverMenuMarketingRepActivity);

    }

    /**
     * Method: testIntegrationCreateServicesPackages()
     * Purpose: Tests whether create services packages activity is started when the create services
     * packages button is clicked
     */
    @MediumTest
    public void testIntegrationCreateServicesPackages() {

        monitor = getInstrumentation().addMonitor(CreatePackageServiceActivity.class.getName(), null, false);

        assertNotNull(receiverMenuMarketingRepActivity);

        Activity receiverActivity;

        Button createServicesButton = (Button)receiverMenuMarketingRepActivity.findViewById(R.id.button_01);

        TouchUtils.clickView(this, createServicesButton);

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
        if(receiverMenuMarketingRepActivity != null){
            receiverMenuMarketingRepActivity.finish();
            receiverMenuMarketingRepActivity = null;
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