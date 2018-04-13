package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class SplashTitleActivity extends Activity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_title);

        // Uses the LinearLayout as a touch sensor
        LinearLayout touchSense = (LinearLayout) findViewById(R.id.touch_screen);
        touchSense.setOnTouchListener(this);
    }

    // Proceeds to the Login Page after any touch gesture
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        startActivity(new Intent(this, LoginActivity.class));
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_title, menu);
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
