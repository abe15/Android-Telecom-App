package cse110.android.bigheroeight.com.ucsdtelecom;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;


public class RegisterCommercialActivity extends RegisterRetailActivity {

    protected String businessnametxt;
    protected String responsible_billing_person_first_name_txt;
    protected String responsible_billing_person_middle_name_txt;
    protected String responsible_billing_person_last_name_txt;

    protected EditText businessname;
    protected EditText responsibleBillingFirstName;
    protected EditText responsibleBillingMiddleName;
    protected EditText responsibleBillingLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_sign_up);
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

        businessname = (EditText) findViewById(R.id.signup_businessname);
        responsibleBillingFirstName = (EditText) findViewById(R.id.signup_billing_firstname);
        responsibleBillingLastName = (EditText) findViewById(R.id.signup_billing_lastname);
        responsibleBillingMiddleName = (EditText) findViewById(R.id.signup_billing_middlename);

    }


    public void onSignUp_01(View v){
        boolean cancel = checkForErrors();
        /*No Errors so continue signing up*/
        if(!cancel)
        {
            Database data = new Database();
            boolean isSuccessful = data.signUpCommercialCustomer(usernametxt,  passwordtxt,  repasswordtxt
                    ,firstnametxt,lastnametxt,middlenametxt, addresstxt,
                    citytxt ,statetxt, ziptxt, birthdaytxt,
                    emailtxt,  phonenumbertxt,
                     businessnametxt, responsible_billing_person_first_name_txt
                    ,  responsible_billing_person_middle_name_txt,
                     responsible_billing_person_last_name_txt);
            /*User signed up successfully*/
            if(isSuccessful)
            {
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.pre_003),
                        Toast.LENGTH_LONG).show();
                finish();
            }
            /*Error username taken*/
            else
            {
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.sign_error_002),
                        Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    public boolean checkForErrors()
    {
        /*Check for errors for same information asked for retail customers*/
        boolean cancel = super.checkForErrors();
        /*Now check for errors is additional fields :Business name, responsible person@ billing
        * department*/

        businessnametxt = businessname.getText().toString();
        responsible_billing_person_first_name_txt = responsibleBillingFirstName.getText().toString();
        responsible_billing_person_middle_name_txt = responsibleBillingMiddleName.getText().toString();
        responsible_billing_person_last_name_txt = responsibleBillingLastName.getText().toString();
          /*Did not enter business name*/
        if(TextUtils.isEmpty(businessnametxt))
        {
            businessname.setError(getString(R.string.sign_error_016));
            //focus = businessname;
            cancel = true;
        }

        /*Did not enter Billing person first name*/
        if(TextUtils.isEmpty(responsible_billing_person_first_name_txt))
        {
            responsibleBillingFirstName.setError(getString(R.string.sign_error_005));
            //focus = responsibleBillingFirstName;
            cancel = true;
        }
        /*Did not enter Billing person middle name*/
        if(TextUtils.isEmpty(responsible_billing_person_middle_name_txt))
        {
            responsibleBillingMiddleName.setError(getString(R.string.sign_error_006));
            //focus = responsibleBillingMiddleName ;
            cancel = true;
        }
          /*Did not enter Billing person last name*/
        if(TextUtils.isEmpty(responsible_billing_person_last_name_txt))
        {
            responsibleBillingLastName.setError(getString(R.string.sign_error_007));
            //focus = responsibleBillingLastName ;
            cancel = true;
        }

        return cancel;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commercial_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
