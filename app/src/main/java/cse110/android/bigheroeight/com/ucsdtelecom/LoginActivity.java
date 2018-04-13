package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.Parse;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;
import cse110.android.bigheroeight.com.ucsdtelecom.RuleObjectStategy.LoginRule;


public class LoginActivity extends Activity {

    // Variables used for logging in, if the username exists

    // Data from user
    String username_login;
    String password_login;
    EditText input_username;
    EditText input_password;
    Database data = new Database();
    LoginRule rule = new LoginRule();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Parse.initialize(this, "aBcINx5eJC0vSRRMF44eHmOIorKirWtz5UjH9MCh",
                "0EQMZSTKc2DgE1lQx4l6WOe3qgE8LL2OaONvBPFW");

        // Locates the EditText on the XML layout
        input_username = (EditText)findViewById(R.id.input_username);
        input_password = (EditText)findViewById(R.id.input_password);


    }

    // This function is linked to the Button the enters to the
    // Account Menu activity
    // Verifies login credibility, goes to Account Main Menu is credible
    public void onButton_00(View v){

        //Database coding
        //Verification, use if-else
        // Retrieves the input
        username_login = input_username.getText().toString();
        password_login = input_password.getText().toString();


        boolean isSuccessful = data.logIn(username_login,password_login);
      /*User signed up successfully*/
        if(isSuccessful)
        {
            String permission = data.getPermission(username_login);

            try {
                startActivity(new Intent(this, Class.forName(rule.applyRule(permission))));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.pre_000),
                    Toast.LENGTH_LONG).show();
            finish();
        }
            /*Error username taken*/
        else
        {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.pre_001),
                    Toast.LENGTH_LONG).show();
        }

    }

    // This function is linked to the Button the enters to the
    // Retail Sign Up activity
    public void onButton_01(View v){
        // Heads to the retail Sign Up page
        startActivity(new Intent(this, RegisterRetailActivity.class));
    }
    // This function is linked to the Button the enters to the
    // Commercial Sign Up activity
    public void onButton_02(View v)
    {
        //Heads to the commercial sign up page
        startActivity(new Intent(this,RegisterCommercialActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
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