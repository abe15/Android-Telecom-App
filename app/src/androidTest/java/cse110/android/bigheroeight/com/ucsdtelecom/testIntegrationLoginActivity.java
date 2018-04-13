package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;
import java.lang.Runnable;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class testIntegrationLoginActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity mActivity;
    private ActivityMonitor monitor;
    private String iUser = "invalidUsername";
    private String iPassword = "invalidPassword";
    private String vUser = "tester";
    private String vPassword = "tester";
    private String cRepUser = "customerRep";
    private String cRepPassword = "password";
    private String mRepUser = "marketingRep";
    private String mRepPassword = "password";


    public testIntegrationLoginActivity()
    {
        super(LoginActivity.class);

    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        setActivityInitialTouchMode(true);

    }

    /**
     * Method: testPreconditions()
     * Purpose: Tests for null in the username and password input boxes.
     */
    @LargeTest
    public void testPreconditions() {
        Activity mActivity = getActivity();
        EditText username = (EditText)mActivity.findViewById(R.id.input_username);
        EditText password = (EditText)mActivity.findViewById(R.id.input_password);
        assertNotNull(username);
        assertNotNull(password);

    }

    /**
     * Method: testInvalidUserNamePassword()
     * Purpose: Tests for fail attempt to login with invalid username and password. Test should
     * pass if the activity to the main menu for retail customer is not opened.
     */
    @MediumTest
    public void testInvalidUserNamePassword() throws Exception {

        monitor = getInstrumentation().addMonitor(MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        // Finds the login button so we can click later
        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);

        // Input invalid password and password into the respective fields
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText username = (EditText)mActivity.findViewById(R.id.input_username);
                EditText password = (EditText)mActivity.findViewById(R.id.input_password);
                username.requestFocus();
                username.setText(iUser);
                password.requestFocus();
                password.setText(iPassword);

            }
        });

        // Press the login button
        TouchUtils.clickView(this, loginButton);

        // Checks if monitor picked up an activity intent to retail main menu
        receiverActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNull() should return false. False means the test
        // failed
        assertNull(receiverActivity);

        // If the test passed
        if(receiverActivity == null) {
            // Check if activity comes up later and test again
            receiverActivity = monitor.waitForActivityWithTimeout(1000);
            assertNull(receiverActivity);
        }

        // If test fails, end the activity
        if(receiverActivity != null) {
            receiverActivity.finish();
            receiverActivity = null;
        }

    }

    /**
     * Method: testNullUserName()
     * Purpose: Tests for fail attempt to login with null username but valid password. Test should
     * pass if the activity to the main menu for retail customer is not opened.
     */
    @MediumTest
    public void testNullUserName() {

        monitor = getInstrumentation().addMonitor(MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText password = (EditText)mActivity.findViewById(R.id.input_password);
                password.requestFocus();
                password.setText(vPassword);
            }
        });

        TouchUtils.clickView(this, loginButton);

        // Checks if monitor picked up an activity intent to retail main menu
        receiverActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNull() should return false. False means the test
        // failed
        assertNull(receiverActivity);

        // If test passes, test again just in case activity pops up later
        if(receiverActivity == null) {
            receiverActivity = monitor.waitForActivityWithTimeout(1000);
            assertNull(receiverActivity);
        }

        // If test fails, end the activity
        if(receiverActivity != null) {
            receiverActivity.finish();
            receiverActivity = null;
        }
    }

    /**
     * Method: testNullPassword()
     * Purpose: Tests for fail attempt to login with null password but a valid username. Test should
     * pass if the activity to the main menu for retail customer is not opened.
     */
    @MediumTest
    public void testNullPassword() {

        monitor = getInstrumentation().addMonitor(MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);


        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText username = (EditText)mActivity.findViewById(R.id.input_username);
                username.requestFocus();
                username.setText(vUser);

            }
        });

        TouchUtils.clickView(this, loginButton);
        // Checks if monitor picked up an activity intent to retail main menu
        receiverActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNull() should return false. False means the test
        // failed
        assertNull(receiverActivity);

        // If test passes, test again just in case activity pops up later
        if(receiverActivity == null) {
            receiverActivity = monitor.waitForActivityWithTimeout(1000);
            assertNull(receiverActivity);
        }

        // If test fails, end the activity
        if(receiverActivity != null) {
            receiverActivity.finish();
            receiverActivity = null;
        }
    }

    /**
     * Method: testNullUserAndPassword()
     * Purpose: Tests for fail attempt to login with null password and username. Test should
     * pass if the activity to the main menu for retail customer is not opened.
     */
    @MediumTest
    public void testNullUserAndPassword() {

        monitor = getInstrumentation().addMonitor( MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);
        TouchUtils.clickView(this, loginButton);
        // Checks if monitor picked up an activity intent to retail main menu
        receiverActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNull() should return false. False means the test
        // failed
        assertNull(receiverActivity);

        // If test passes, test again just in case activity pops up later
        if(receiverActivity == null) {
            receiverActivity = monitor.waitForActivityWithTimeout(1000);
            assertNull(receiverActivity);
        }

        // If test fails, end the activity
        if(receiverActivity != null) {
            receiverActivity.finish();
            receiverActivity = null;
        }
    }

    /**
     * Method: testRegisterRetailButton()
     * Purpose: Tests whether the user is actually taken to the register retail activity when
     * the register retail button is clicked on.
     */
    @MediumTest
    public void testRegisterRetailButton() {

        monitor = getInstrumentation().addMonitor(RegisterRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        Button registerRetailButton = (Button)mActivity.findViewById(R.id.button_01);
        TouchUtils.clickView(this, registerRetailButton);

        // Checks if monitor picked up an activity intent to Register Retail Activity
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
    }

    /**
     * Method: testRegisterCommercialButton()
     * Purpose: Tests whether the user is actually taken to the register commercial activity when
     * the register commercial button is clicked on.
     */
    @MediumTest
    public void testRegisterCommercialButton() {

        monitor = getInstrumentation().addMonitor(RegisterCommercialActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity ;

        Button registerCommercialButton = (Button)mActivity.findViewById(R.id.button_02);
        TouchUtils.clickView(this, registerCommercialButton);

        // Checks if monitor picked up an activity intent to Register Commercial Activity
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

    }

    /**
     * Method: testValidRetailUserAndPassword()
     * Purpose: Tests for valid attempt to login with valid password and username. Test should
     * pass if the activity to the main menu for retail customer is opened.
     */
    @MediumTest
    public void testValidRetailUserNamePassword() {

        monitor = getInstrumentation().addMonitor(MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

        Button loginButton = (Button)mActivity.findViewById(R.id.button_00);

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                EditText username = (EditText)mActivity.findViewById(R.id.input_username);
                EditText password = (EditText)mActivity.findViewById(R.id.input_password);
                username.requestFocus();
                username.setText(vUser);
                password.requestFocus();
                password.setText(vPassword);
            }
        });

        TouchUtils.clickView(this, loginButton);
        // Checks if monitor picked up an activity intent to Retail Main Menu
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
    }

    /**
     * Method: testValidCustomerRepUserNamePassword()
     * Purpose: Tests for successful attempt to login with customer rep password and username.
     * Test should pass if the activity to the main menu for customer rep is opened.
     */
    @MediumTest
    public void testValidCustomerRepUserNamePassword() {

        monitor = getInstrumentation().addMonitor(MainMenuCustomerRepActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

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
        // Checks if monitor picked up an activity intent to Customer Rep Main Menu
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
    }

    /**
     * Method: testValidMarketingRepUserNamePassword()
     * Purpose: Tests for successful attempt to login with marketing rep password and username.
     * Test should pass if the activity to the main menu for marketing rep is opened.
     */
    @MediumTest
    public void testValidMarketingRepUserNamePassword() {

        monitor = getInstrumentation().addMonitor(MainMenuMarketingRepActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

        Activity receiverActivity;

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
        // Checks if monitor picked up an activity intent to Marketing Rep Main Menu
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
    }


    @Override
    public void tearDown() throws Exception {
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