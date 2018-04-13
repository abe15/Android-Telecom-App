package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;


public class ViewBillActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow logoutPop;

    TableLayout tab;
    Database data = new Database();
    String username = data.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);
        tab = (TableLayout) findViewById(R.id.table);

        if(data.hasBill(username)) {
            List<Package> subscription = data.getCustomersSubscriptions(username);
            double bill = 0.00;
            for (Package sub : subscription) {
                String title = sub.title;
                String price = sub.price;
                bill += round(Double.parseDouble(price), 2);
                addRow(title, price);

            }
            List<Package> cancellations = data.getCustomerCanceledSubscriptions(username);
            for (Package cancel : cancellations) {
                String title = cancel.title;
                String price = "150.00";
                bill += round(Double.parseDouble(price), 2);
                addRow("Termination Fee: " + title, price);

            }

            List<Package> waivers = data.getCustomerConcessions(username);
            for (Package waive : waivers) {
                String title = waive.title;
                String price = "150.00";
                bill -= round(Double.parseDouble(price), 2);
                addRow("Waive Fee: " + title, "-" + price);

            }

            Double tax = round(tax(bill), 2);
            addRow("Tax", String.valueOf(tax));
            bill += tax;
            bill = round(bill,2);

            addRow("Total", String.valueOf(bill));
        }
        else
        {
            addNoBillMessage();


        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

       public void addNoBillMessage()
       {
           TableRow row = new TableRow(this);
           TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
           row.setLayoutParams(lp);

           TextView title = new TextView(this);
           title.setText("Currently you have to bill");
           title.setTextColor(Color.WHITE);
           title.setLayoutParams(lp);
           title.setGravity(Gravity.LEFT);
           row.addView(title);
           tab.addView(row);


       }

    public void addRow(String Title, String Price) {

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView title = new TextView(this);
        title.setText(Title);
        title.setTextColor(Color.WHITE);
        title.setLayoutParams(lp);
        title.setGravity(Gravity.CENTER);

        TextView price = new TextView(this);
        price.setText(Price);
        price.setTextColor(Color.WHITE);
        price.setLayoutParams(lp);
        price.setGravity(Gravity.CENTER);

        row.addView(title);
        row.addView(price);
        tab.addView(row);


    }

    /*This method returns the tax the customer will be billed*/
    public double tax(double price)
    {
        return .0925*price;
    }

    // Logout Popup
    public void onProfileLogout(View v){
        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) ViewBillActivity.this.
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
        getMenuInflater().inflate(R.menu.menu_view_bill, menu);
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
