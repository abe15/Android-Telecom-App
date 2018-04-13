package cse110.android.bigheroeight.com.ucsdtelecom;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;




public class MainMenuCustomerRepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_customer_rep);
    }

    /**
     * Method: onClickSignUp
     * Purpose: This function is called when the customer representative clicks on the Sign Up
     * Customer. It will display a alert dialog with options for the user to choose. They may either
     * sign up a commercial or retail customer. They will be taken to the appropriate sign up
     * activity.
     */
    public void onClickSignUp(View view)
    {
        DialogFragment alertSignUpFragment = new SignUpDialogFragment();
        alertSignUpFragment.show(getFragmentManager(), "SignUpAlert");
    }

    /**
     * Method: onClickAssist()
     * Purpose: Starts the AssistCustomerActivity. This allows the customer representative to
     * search for specific customers by username. They are also able to view the customer's
     * currently subscribed services/packages and edit their subscriptions.
     */
    public void onClickAssist(View view)
    {
        startActivity(new Intent(this, AssistCustomerActivity.class));
    }


    /**
     * Method: onMenuLogout1()
     * Purpose: This function is called when the user clicks on the logout button. It will display
     * a log out toast message. It will then log the user out.
     */
    public void onMenuLogout1(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        Database data = new Database();
        data.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_002),
                Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rep_account_main_menu, menu);
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
