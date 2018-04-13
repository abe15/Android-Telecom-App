package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;

public class AccountProfileActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow deletePop;
    PopupWindow logoutPop;

    // Variables used for the account profile
    /*String username;
    TextView inUsername;
    Parse getUsername;*/
    Database data = new Database();
    String username = data.getCurrentUser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        TextView uN = (TextView) findViewById(R.id.usernameDisplay);
        TextView fN = (TextView) findViewById(R.id.firstNameDisplay);
        TextView mN = (TextView) findViewById(R.id.middleNameDisplay);
        TextView lN = (TextView) findViewById(R.id.lastNameDisplay);
        TextView address = (TextView) findViewById(R.id.addressDisplay);
        TextView city = (TextView) findViewById(R.id.cityDisplay);
        TextView zip = (TextView) findViewById(R.id.zipDisplay);
        TextView state = (TextView) findViewById(R.id.stateDisplay);

        /*DISPLAYS THE CUSTOMER INFO ON THE SCREEN*/
        List<String> info = data.UserInfo(username);
        uN.setText(getString(R.string.profile_001a)+info.get(0));
        fN.setText(getString(R.string.profile_000a)+info.get(1));
        mN.setText(getString(R.string.profile_000b)+info.get(2));
        lN.setText(getString(R.string.profile_000c)+info.get(3));
        address.setText(getString(R.string.profile_000d)+info.get(4));
        city.setText(getString(R.string.profile_000e)+info.get(5));
        zip.setText(getString(R.string.profile_000g)+info.get(6));
        state.setText(getString(R.string.profile_000f)+info.get(7));



    }


    // This function is linked to the Button the enters to the
    // Change Password activity
    public void onChangePassword(View v){
        //Database coding
        // Exit from the profile page
        //startActivity(new Intent(this, account_main_menu.class));
    }

    /*This function registers the user as an observer to receive notifications
    * when his bill exceeds his threshold value*/
    public void setThreshold(View v)
    {

        EditText amo = (EditText) findViewById(R.id.reg);

        String txt = amo.getText().toString();

        if(TextUtils.isEmpty(txt))
        {

            amo.setError(getString(R.string.error_empty_field));
            return;

        }
        else
        {
            Double d = Double.valueOf(txt);
            data.register(username,d);
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.profile_002),
                    Toast.LENGTH_LONG).show();


        }



    }

  /* This function deletes the current user account and logs out using CancelCustomerAccount()
   * To prevent any errors
   */
    // Delete Popup
  public void onDeleteAccount(View v) {
      // Obtains the screen dimensions
      DisplayMetrics screenSize = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(screenSize);
      int height = (4 * (screenSize.heightPixels)) / 5;
      int width = (4 * (screenSize.widthPixels)) / 5;

      LayoutInflater inflater = (LayoutInflater) AccountProfileActivity.this.
              getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View layout = inflater.inflate(R.layout.delete_popup,
              (ViewGroup) findViewById(R.id.delete_popup));
      deletePop = new PopupWindow(layout, width, height, true);
      deletePop.showAtLocation(layout, Gravity.CENTER, 0, 0);
  }

    public void onDeleteProceedPopup(View v){
        deletePop.dismiss();
        /*delete account*/
        Database AccountData = new Database();
        data.CancelCustomerAccount(data.getCurrentUser());

    /*logout after deleted account*/
        //TODO: delete repetition of code: refactor
        data.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_004),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SplashTitleActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    public void onDeleteBackPopup(View v){
        deletePop.dismiss();
    }

    // This function is linked to the Button the enters to the
    // back of the user
    public void onProfileBack(View v){
        finish();
    }

    // This function is linked to the Button the enters to the
    // logout the user
    // Logout Popup
    public void onProfileLogout(View v){
        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) AccountProfileActivity.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.logout_popup,
                (ViewGroup) findViewById(R.id.logout_popup));
        logoutPop = new PopupWindow(layout, width, height, true);
        logoutPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
    public void onProceedPopup(View v){
        logoutPop.dismiss();
        data.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_002),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SplashTitleActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    public void onBackPopup(View v){
        logoutPop.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_profile, menu);
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
