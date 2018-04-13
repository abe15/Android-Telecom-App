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

import com.parse.ParseUser;

public class CustomerServiceActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow logoutPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
    }

    // This function is linked to the Button the enters to the
    // send report sequence activity
    public void onServiceSend(View v){
        //Database coding
        //Verification, use if-else
        //startActivity(new Intent(this, account_main_menu.class));
    }

    // This function is linked to the Button the enters to the
    // back sequence activity
    public void onServiceBack(View v){
        finish();
    }

    // This function is linked to the Button the enters to the
    // logout sequence activity
    public void onServiceLogout(View v){
        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) CustomerServiceActivity.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.logout_popup,
                (ViewGroup) findViewById(R.id.logout_popup));
        logoutPop = new PopupWindow(layout, width, height, true);
        logoutPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    public void onProceedPopup(View v){
        logoutPop.dismiss();
        ParseUser.logOut();
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
        getMenuInflater().inflate(R.menu.customer_service, menu);
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
