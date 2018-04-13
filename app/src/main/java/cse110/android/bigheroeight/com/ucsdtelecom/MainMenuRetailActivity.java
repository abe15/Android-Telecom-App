package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;

public class MainMenuRetailActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow logoutPop;

    //creates the activity, sets up entire activity variables/ settings
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main_menu);

        // Retrieve login user from Parse.com

    }

    // This function is linked to the Button the enters to the
    // logout sequence
    public void onMenuLogout(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        createLogoutPop();
    }
    @Override
    public void onBackPressed(){
        //Database coding
        // Say if all fields has been filled, use if-else
        createLogoutPop();
    }

    // Logout Popup
    private void createLogoutPop(){

        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) MainMenuRetailActivity.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.logout_popup,
                (ViewGroup) findViewById(R.id.logout_popup));
        logoutPop = new PopupWindow(layout, width, height, true);
        logoutPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
    public void onProceedPopup(View v){
        logoutPop.dismiss();
        Database data = new Database();
        data.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_002),
                Toast.LENGTH_LONG).show();
        finish();
    }
    public void onBackPopup(View v){
        logoutPop.dismiss();
    }


    // This function is linked to the Button the enters to the
    // Account Profile activity
    public void onAccount_00(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        startActivity(new Intent(this, AccountProfileActivity.class));
    }

    // This function is linked to the Button the enters to the
    // View Current Package activity
    public void onAccount_01(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        startActivity(new Intent(this, ViewCustServPacActivity.class));
    }

    // This function is linked to the Button the enters to the
    // Add/Remove Package activity
    public void onAccount_02(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        startActivity(new Intent(this, BrowseActivity.class));
    }

    // This function is linked to the Button the enters to the
    // View/Pay Bill activity
    public void onAccount_03(View v){

        startActivity(new Intent(this, ViewBillActivity.class));
    }

    // This function is linked to the Button the enters to the
    // Customer Service activity
    public void onAccount_04(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        startActivity(new Intent(this, CustomerServiceActivity.class));
    }

    // This function is linked to the Button the enters to the
    // Settings activity
    public void onAccount_05(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
        //startActivity(new Intent(this, account_settings.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_main_menu, menu);
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
