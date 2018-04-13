package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AccountSettingsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);


    }

/*
    // This function is linked to the Button the enters to the
    // back of the user
    public void onProfileBack(View v){
        finish();
    }

    // This function is linked to the Button the enters to the
    // logout the user
    public void onProfileLogout(View v){
        createLogoutPop();
    }

    // Logout Popup
    private void createLogoutPop(){
        LayoutInflater inflater = (LayoutInflater) view_bill.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.logout_popup,
                (ViewGroup) findViewById(R.id.logout_popup));
        logoutPop = new PopupWindow(layout, 1000, 800, true);
        logoutPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
    public void onProceedPopup(View v){
        logoutPop.dismiss();
        ParseUser.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_002),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, splash_title.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    public void onBackPopup(View v){
        logoutPop.dismiss();
    }
*/
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
