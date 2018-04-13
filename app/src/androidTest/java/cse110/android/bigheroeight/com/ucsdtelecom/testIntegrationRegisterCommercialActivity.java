package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrew on 2/24/2015.
 */
public class testIntegrationRegisterCommercialActivity extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public testIntegrationRegisterCommercialActivity(){
        super(LoginActivity.class);
    }
    public EditText businessname, usrname, pw0, pw1, firstn, midn, lastn, bfirstn, bmidn, blastn;
    public EditText add, city, state, zip, bday, phone, email;
    @Override
    protected void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

        EditText First = (EditText) solo.getView(R.id.input_username);
        EditText Second = (EditText) solo.getView(R.id.input_password);
        Button reg = (Button) solo.getView(R.id.button_02);
        solo.clickOnView(reg);

        businessname = (EditText) solo.getView(R.id.signup_businessname);
        usrname = (EditText) solo.getView(R.id.signup_username);
        pw0 = (EditText) solo.getView(R.id.signup_password00);
        pw1 = (EditText) solo.getView(R.id.signup_password01);
        firstn = (EditText) solo.getView(R.id.signup_firstname);
        midn = (EditText) solo.getView(R.id.signup_middlename);
        lastn = (EditText) solo.getView(R.id.signup_lastname);
        bfirstn = (EditText) solo.getView(R.id.signup_billing_firstname);
        bmidn = (EditText) solo.getView(R.id.signup_billing_middlename);
        blastn = (EditText) solo.getView(R.id.signup_billing_lastname);
        add = (EditText) solo.getView(R.id.signup_address);
        city = (EditText) solo.getView(R.id.signup_city);
        state = (EditText) solo.getView(R.id.signup_state);
        zip = (EditText) solo.getView(R.id.signup_zip);
        bday = (EditText) solo.getView(R.id.signup_birthday);
        email = (EditText) solo.getView(R.id.signup_email);
        phone = (EditText) solo.getView(R.id.signup_phonenum);
    }

    /**
     * Method: testRegisterCommercialAllNullFields()
     * Purpose: Tests whether user is sent to LoginActivity because of null input. Test should
     * fail if user is sent to LoginActivity because they shouldn't be allowed
     */
    @MediumTest
    public void testRegisterCommercialAllNullFields() {

        Button register = (Button) solo.getView(R.id.button_03);
        solo.clickOnView(register);


        solo.assertCurrentActivity("", RegisterCommercialActivity.class);
    }

    /**
     * Method: testRegisterCommercialAllFieldsFilled()
     * Purpose: Tests whether user is sent to LoginActivity because of valid input. Test should
     * fail if user was not sent to LoginActivity because they entered valid input.
     */
    @MediumTest
    public void testRegisterCommercialAllFieldsFilled() {

        Random r = new Random();
        r.setSeed(Time.SECOND);
        int extra = Math.abs(r.nextInt()-3);
        solo.typeText(businessname, "Luthorcorp" + extra);
        solo.typeText(usrname, "warriorangel" + extra);
        solo.typeText(pw0, "julian");
        solo.typeText(pw1, "julian");
        solo.typeText(firstn, "Lex");
        solo.typeText(midn, "Joseph");
        solo.typeText(lastn, "Luthor");
        solo.typeText(bfirstn, "Lex");
        solo.typeText(bmidn, "Joseph");
        solo.typeText(blastn, "Luthor");
        solo.typeText(add, "Luthorcorp Plaza");
        solo.typeText(city, "Metropolis");
        solo.typeText(state, "Kansas");
        solo.typeText(zip, "66632");
        solo.typeText(bday, "9281980");
        solo.typeText(email, "evil" + extra + "@gmail.com");
        solo.typeText(phone, "5555555555");

        Button register = (Button) solo.getView(R.id.button_03);
        solo.clickOnView(register);

        solo.assertCurrentActivity("", LoginActivity.class);

        EditText First = (EditText) solo.getView(R.id.input_username);
        EditText Second = (EditText) solo.getView(R.id.input_password);
        Button login = (Button) solo.getView(R.id.button_00);
        solo.typeText(First, "warriorangel" + extra);
        solo.typeText(Second, "julian");
        solo.clickOnView(login);
        solo.assertCurrentActivity("", MainMenuRetailActivity.class);

    }

    @Override
    protected void tearDown() throws Exception
    {
        solo.finishOpenedActivities();

    }

}