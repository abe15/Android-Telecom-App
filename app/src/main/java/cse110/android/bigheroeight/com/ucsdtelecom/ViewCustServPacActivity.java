package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;


public class ViewCustServPacActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow logoutPop;

    ArrayList<Pair<Button, String>> myButtons = new ArrayList<Pair<Button, String>>();
    ArrayList<Pair<TableRow, String>> myRows = new ArrayList<Pair<TableRow, String>>();
    TableLayout tab;
    Database data = new Database();
    String username = data.getCurrentUser();
    PopupWindow cancelPop;
    Pair<Button, String> currentCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_package);
        tab = (TableLayout) findViewById(R.id.table);
        currentCancel = null;

        List<Package> subscription = data.getCustomersSubscriptions(username);


        for (Package sub : subscription) {
            String id = sub.id;
            String title = sub.title;
            String price = sub.price;
            addRow(id, title, price);

        }
        setDeleteClickListeners();


    }
    public void addRow(String productId, String Title, String Price) {

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView id = new TextView(this);
        id.setText(productId);
        id.setTextColor(Color.WHITE);
        id.setLayoutParams(lp);
        id.setGravity(Gravity.CENTER);

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

        Button button = new Button(this);
        button.setBackgroundResource(R.drawable.redbutton);
        button.setText("Delete");
        button.setTextAppearance(this,R.style.ButtonText);
        button.setLayoutParams(lp);

        row.addView(id);
        row.addView(title);
        row.addView(price);
        row.addView(button);

        myRows.add(Pair.create(row, productId));
        myButtons.add(Pair.create(button, productId));
        tab.addView(row);


    }
    public void setDeleteClickListeners() {
        for (final Pair<Button, String> button : myButtons) {

            button.first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    currentCancel = button;
                    createCancelPop();

                }
            });


        }
    }

    // Logout Popup
    private void createCancelPop(){

        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) ViewCustServPacActivity.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.cancel_popup,
                (ViewGroup) findViewById(R.id.cancel_popup));
        cancelPop = new PopupWindow(layout, width, height, true);
        cancelPop.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    public void onCancelProceedPopup(View v){
        Database data = new Database();
        data.deleteSubscription(currentCancel.second, username);

        for (Pair<TableRow, String> row : myRows) {
            if (row.second.equals(currentCancel.second)) {
                tab.removeView(row.first);
                myRows.remove(row.first);

            }

        }
        currentCancel = null;
        cancelPop.dismiss();
    }
    public void onCancelBackPopup(View v){
        cancelPop.dismiss();
    }

    // Back Button
    public void onViewBack(View v){
        finish();
    }

    // Logout Button
    public void onViewLogout(View v){

        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) ViewCustServPacActivity.this.
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_service_package, menu);
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
