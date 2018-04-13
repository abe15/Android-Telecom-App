package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;
import java.lang.Runnable;



/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class testIntegrationMainMenuRetailActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity mActivity;
    private Instrumentation.ActivityMonitor monitor;
    private Activity receiverMenuRetailActivity; //reference to menu retail activity after login
    private String vUser = "tester";
    private String vPassword = "tester";


    public testIntegrationMainMenuRetailActivity()
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

        monitor = getInstrumentation().addMonitor(MainMenuRetailActivity.class.getName(), null, false);

        mActivity = getActivity();
        assertNotNull(mActivity);

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

        receiverMenuRetailActivity = monitor.waitForActivityWithTimeout(1000);

        if(receiverMenuRetailActivity == null){
            receiverMenuRetailActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(receiverMenuRetailActivity);
        }

        assertNotNull(receiverMenuRetailActivity);

    }


    /**
     * Method: testIntegrationAccountProfile()
     * Purpose: Tests whether retail user's Account Profile Activity is opened when the Account
     * Profile button is pressed. This test fails if the Account Profile Activity is not opened.
     */
    @MediumTest
    public void testIntegrationAccountProfile() {

        monitor = getInstrumentation().addMonitor(AccountProfileActivity.class.getName(), null, false);

        assertNotNull(receiverMenuRetailActivity);

        Activity receiverActivity;

        Button accountProfileButton = (Button)receiverMenuRetailActivity.findViewById(R.id.button_04);

        TouchUtils.clickView(this, accountProfileButton);

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
        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
        }

    }

    /**
     * Method: testIntegrationViewServices()
     * Purpose: Tests whether retail user's ViewCustServPac Activity is opened when the View
     * Services button is pressed. This test fails if the ViewCustServPac Activity is not opened.
     */
    @MediumTest
    public void testIntegrationViewServices() {

        monitor = getInstrumentation().addMonitor(ViewCustServPacActivity.class.getName(), null, false);

        assertNotNull(receiverMenuRetailActivity);

        Activity servicesActivity;

        Button button = (Button)receiverMenuRetailActivity.findViewById(R.id.button_05);

        TouchUtils.clickView(this, button);

        // Checks if monitor picked up an activity intent to View Services Activity
        servicesActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNotNull() should return true. False means the test
        // failed. Here we do a pretest. If it gets through the if statement, we want to wait
        // a bit longer to see if the activity pops up
        if(servicesActivity == null) {
            servicesActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(servicesActivity);
        }

        assertNotNull(servicesActivity);

        // If the test passed, end the activity
        if(servicesActivity != null) {
            servicesActivity.finish();
            servicesActivity = null;
        }

        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
        }

    }

    /**
     * Method: testIntegrationSubscribeServices()
     * Purpose: Tests whether retail user's Browse Activity is opened when the Subscribe Services/
     * Packages button is pressed. This test fails if the Browse Activity is not opened.
     */
    @MediumTest
    public void testIntegrationSubscribeServices() {
        monitor = getInstrumentation().addMonitor(BrowseActivity.class.getName(), null, false);

        assertNotNull(receiverMenuRetailActivity);

        Activity browseActivity;

        Button button = (Button)receiverMenuRetailActivity.findViewById(R.id.button_06);

        TouchUtils.clickView(this, button);

        // Checks if monitor picked up an activity intent to Browse Services Activity
        browseActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNotNull() should return true. False means the test
        // failed. Here we do a pretest. If it gets through the if statement, we want to wait
        // a bit longer to see if the activity pops up
        if(browseActivity == null) {
            browseActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(browseActivity);
        }

        // If the test passed, end the activity
        if(browseActivity != null) {
            browseActivity.finish();
            browseActivity = null;
        }

        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
        }

    }

    /**
     * Method: testIntegrationViewBill()
     * Purpose: Tests whether retail user's View Bill Activity is opened when the View Bill
     *  button is pressed. This test fails if the View Bill Activity is not opened.
     */
    @MediumTest
    public void testIntegrationViewBill() {
        monitor = getInstrumentation().addMonitor(ViewBillActivity.class.getName(), null, false);

        assertNotNull(receiverMenuRetailActivity);

        Activity billActivity;

        Button button = (Button)receiverMenuRetailActivity.findViewById(R.id.button_07);

        TouchUtils.clickView(this, button);
        // Checks if monitor picked up an activity intent to View Bill Activity
        billActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNotNull() should return true. False means the test
        // failed. Here we do a pretest. If it gets through the if statement, we want to wait
        // a bit longer to see if the activity pops up
        if(billActivity == null) {
            billActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(billActivity);
        }

        assertNotNull(billActivity);

        // If the test passed, end the activity
        if ( billActivity != null ) {
            billActivity.finish();
            billActivity = null;
        }

        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
        }
    }

    /**
     * Method: testIntegrationCustomerService()
     * Purpose: Tests whether retail user's Customer Service Activity is opened when the Customer
     * Service button is pressed. This test fails if the Customer Service Activity is not opened.
     */

    @MediumTest
    public void testIntegrationCustomerService() {
        monitor = getInstrumentation().addMonitor(CustomerServiceActivity.class.getName(), null, false);

        assertNotNull(receiverMenuRetailActivity);

        Activity assistCustomerActivity;

        Button button = (Button)receiverMenuRetailActivity.findViewById(R.id.button_08);

        TouchUtils.clickView(this, button);
        // Checks if monitor picked up an activity intent to Customer Service Activity
        assistCustomerActivity = monitor.waitForActivityWithTimeout(1000);

        // If monitor picked up something, assertNotNull() should return true. False means the test
        // failed. Here we do a pretest. If it gets through the if statement, we want to wait
        // a bit longer to see if the activity pops up
        if(assistCustomerActivity == null) {
            assistCustomerActivity = monitor.waitForActivityWithTimeout(1000);
            assertNotNull(assistCustomerActivity);
        }

        assertNotNull(assistCustomerActivity);

        // If the test passed, end the activity
        if(assistCustomerActivity != null) {
            assistCustomerActivity.finish();
            assistCustomerActivity = null;
        }

        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
        }
    }

    /**
     * Method: testIntegrationLogout()
     * Purpose: Tests whether a Logout confirmation popup appears when the user clicks the logout
     * button. This also tests whether the user is taken back to the main menu when they click
     * the back button of the popup. This also tests whether the user is taken back to the login
     * activity if they click the logout button.
     */
/*
    @MediumTest
    public void testIntegrationLogout() {

        solo.assertCurrentActivity("Wrong Starting Activity", MainMenuRetailActivity.class);
        solo.clickOnButton("Logout");
        assertTrue(solo.waitForText("Are you sure you want to logout from your account?"));
        solo.clickOnButton("Back");
        solo.assertCurrentActivity("Wrong Activity. Supposed to send user back to main menu after pressing back button.", MainMenuRetailActivity.class);
        solo.clickOnButton("Logout");
        solo.clickOnButton("Logout");
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);


        if(receiverMenuRetailActivity != null){
            receiverMenuRetailActivity.finish();
            receiverMenuRetailActivity = null;
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