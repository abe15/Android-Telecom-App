package cse110.android.bigheroeight.com.ucsdtelecom;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.robotium.solo.Solo;

import cse110.android.bigheroeight.com.ucsdtelecom.Factory.CreatePackageServiceActivity;


public class testIntegrationCreatePackageServiceActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public testIntegrationCreatePackageServiceActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        EditText First = (EditText) solo.getView(R.id.input_username);
        EditText Second = (EditText) solo.getView(R.id.input_password);
        Button login = (Button) solo.getView(R.id.button_00);
        solo.typeText(First, "marketingRep");
        solo.typeText(Second, "password");
        solo.clickOnView(login);
        Button create = (Button) solo.getView(R.id.button_01);
        solo.clickOnView(create);
    }

    public void testCheckBoxes() throws Exception {


        //Unlock the lock screen
        //boolean buttonFound = solo.searchButton("Create Package/Service");
        //assertTrue("Button to create packages found", buttonFound);

        solo.takeScreenshot();

    }



    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }









}