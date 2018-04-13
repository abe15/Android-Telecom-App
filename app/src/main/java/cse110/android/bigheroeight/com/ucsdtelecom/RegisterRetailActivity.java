package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;

public class RegisterRetailActivity extends Activity {

    // Sign-Up variables/attributes
    protected String usernametxt;
    protected String passwordtxt;
    protected String repasswordtxt;
    protected String firstnametxt;
    protected String lastnametxt;
    protected String middlenametxt;
    protected String addresstxt;
    protected String citytxt;
    protected String statetxt;
    protected String ziptxt;
    protected String birthdaytxt;
    protected String emailtxt;
    protected String phonenumbertxt;


    protected EditText username;
    protected EditText password;
    protected EditText repassword;
    protected EditText firstname;
    protected EditText lastname;
    protected EditText middlename;
    protected EditText address;
    protected  EditText city;
    protected EditText state;
    protected EditText zip;
    protected EditText birthday;
    protected EditText email;
    protected EditText phonenumber;

   // View focus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.signup_screen).requestFocus();

        // Recover attributes via XML layout
        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password00);
        repassword = (EditText) findViewById(R.id.signup_password01);
        firstname = (EditText) findViewById(R.id.signup_firstname);
        lastname = (EditText) findViewById(R.id.signup_lastname);
        middlename = (EditText) findViewById(R.id.signup_middlename);
        address = (EditText) findViewById(R.id.signup_address);
        city = (EditText) findViewById(R.id.signup_city);
        state = (EditText) findViewById(R.id.signup_state);
        zip = (EditText) findViewById(R.id.signup_zip);
        birthday = (EditText) findViewById(R.id.signup_birthday);
        email = (EditText) findViewById(R.id.signup_email);
        phonenumber = (EditText) findViewById(R.id.signup_phonenum);

    }


    // This function is linked to the Button the enters to the
    // Account Menu activity
    // Verifies if all required fields are filled
    public void onSignUp_00(View v){
        boolean cancel = checkForErrors();

        /*No Errors so continue signing up*/
        if(!cancel)
        {
            Database data = new Database();
            boolean signUpSuccessful = data.signUpRetailCustomer( usernametxt, passwordtxt, repasswordtxt
                    ,firstnametxt, lastnametxt, middlenametxt,  addresstxt,
                    citytxt ,statetxt,  ziptxt,birthdaytxt,
                    emailtxt,  phonenumbertxt);

            if(signUpSuccessful)
            {
                /*User is signed up succesfully, go to log in page and print message*/
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.pre_003),
                        Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                /*User name has been taken*/
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.sign_error_002),
                        Toast.LENGTH_LONG).show();
            }

        }



    }
    /*This function checks for errors in the retail customer form
    * returns true if there is an error*/
    public boolean checkForErrors()
    {
        // Retrieve User's Input
        usernametxt = username.getText().toString();
        passwordtxt = password.getText().toString();
        repasswordtxt = repassword.getText().toString();
        firstnametxt = firstname.getText().toString();
        lastnametxt = lastname.getText().toString();
        middlenametxt = middlename.getText().toString();
        addresstxt = address.getText().toString();
        citytxt = city.getText().toString();
        statetxt = state.getText().toString();
        ziptxt = zip.getText().toString();
        birthdaytxt = birthday.getText().toString();
        emailtxt = email.getText().toString();
        phonenumbertxt = phonenumber.getText().toString();
        boolean cancel = false; // use to check any errors before signing up


        //Database coding
        // Say if all fields has been filled, use if-else

        /*Did not enter phone number*/
        if(TextUtils.isEmpty(phonenumbertxt))
        {
            phonenumber.setError(getString(R.string.sign_error_013));
          //  focus = phonenumber;
            cancel = true;
        }

        /*Did not enter email*/
        if(TextUtils.isEmpty(emailtxt))
        {
            email.setError(getString(R.string.sign_error_014));
           // focus = email ;
            cancel = true;
        }
        else
        {
            /*Regular expression for email addresses*/
            String passwordPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern pattern = Pattern.compile(passwordPattern);
            Matcher matcher = pattern.matcher(emailtxt);
            /*Check if email address entered is valid*/
            if(!matcher.matches())
            {
                email.setError(getString(R.string.sign_error_015));
               // focus = email ;
                cancel = true;

            }

        }
         /*Did not enter birthday*/
        if(TextUtils.isEmpty(birthdaytxt))
        {
            birthday.setError(getString(R.string.sign_error_012));
           // focus = birthday;
            cancel = true;
        }

        /*Did not enter zip*/
        if(TextUtils.isEmpty(ziptxt))
        {
            zip.setError(getString(R.string.sign_error_011));
           // focus = zip;
            cancel = true;
        }
        /*Did not enter state*/
        if(TextUtils.isEmpty(statetxt))
        {
            state.setError(getString(R.string.sign_error_010));
          //  focus = state ;
            cancel = true;
        }
        /*Did not enter city*/
        if(TextUtils.isEmpty(citytxt))
        {
            city.setError(getString(R.string.sign_error_009));
           // focus = city;
            cancel = true;
        }
        /*Did not enter address*/
        if(TextUtils.isEmpty(addresstxt))
        {
            address.setError(getString(R.string.sign_error_008));
            //focus = address ;
            cancel = true;
        }
        /*Did not enter last name*/
        if(TextUtils.isEmpty(lastnametxt))
        {
            lastname.setError(getString(R.string.sign_error_007));
          //  focus = lastname;
            cancel = true;
        }
        /*Did not enter middle name*/
        if(TextUtils.isEmpty(middlenametxt))
        {
            middlename.setError(getString(R.string.sign_error_006));
           // focus = middlename ;
            cancel = true;
        }
        /*Did not enter first name*/
        if(TextUtils.isEmpty(firstnametxt))
        {
            firstname.setError(getString(R.string.sign_error_005));
           // focus = firstname;
            cancel = true;
        }
        // If password wasn't inputted
        if(TextUtils.isEmpty(passwordtxt))
        {
            /*Please enter a password*/
            password.setError(getString(R.string.sign_error_001));
           // focus = password;
            cancel = true;

        }
        // If the two passwords aren't matching
        else if(repasswordtxt != null && !passwordtxt.equals(repasswordtxt))
        {
          /*password and confirm password don't match*/
            password.setError(getString(R.string.sign_error_003));
           // focus = password;
            cancel = true;

        }
        // If username wasn't inputted

        if(TextUtils.isEmpty(usernametxt))
        {
            /*Please enter a username*/
            username.setError(getString(R.string.sign_error_000));
           // focus = username;
            cancel = true;
        }

        return cancel;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
