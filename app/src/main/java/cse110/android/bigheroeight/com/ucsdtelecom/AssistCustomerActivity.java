package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;


public class AssistCustomerActivity extends Activity {

    LinearLayout mainPage;
    TableLayout tab;
    TableLayout tabLower;
    TableLayout tabEvenLower;

    ArrayList<Button> myButtons = new ArrayList<Button>();
    ArrayList<TableRow> myRows = new ArrayList<TableRow>();
    ArrayList<String> myIds = new ArrayList<String>();

    ArrayList<TableRow> mySubscribeRows = new ArrayList<TableRow>();
    ArrayList<Button> mySubscribeButtons = new ArrayList<Button>();
    ArrayList<String> mySubscribeIds = new ArrayList<String>();

    ArrayList<Button> myFeeButtons = new ArrayList<Button>();
    ArrayList<TableRow> myFeeRows = new ArrayList<TableRow>();



    String username;
    Database data = new Database();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assist_customer);
        mainPage = (LinearLayout) findViewById(R.id.mainAssist);
        tab = (TableLayout) findViewById(R.id.table);
        tabLower = (TableLayout) findViewById(R.id.tableLayout);
        tabEvenLower = (TableLayout) findViewById(R.id.tableLowLayout);


    }


    public void onClickSearch(View view) {

        EditText subs = (EditText) findViewById(R.id.userNameInput);
        username = subs.getText().toString();
        //Database data = new Database();

        /*No input so give rep an error*/
        if (TextUtils.isEmpty(username)) {

            subs.setError("Please enter a username");


        }
        else {
        /*Load customer user name and subscriptions with delete button,
        * also load available packages at bottom in case customer rep wants to
        * subscribe the customer to something*/
            if (!data.doesUserExist(username)) {
                subs.setError("User name does not exists");
            } else {

            /*Load customer with username "username" 's subscriptions*/
                loadCustomerSubscriptions();

            /*load what pacakges are available for customer to subscribe ot*/
                loadAvailable();


            }
        }


    }

    public void loadCustomerSubscriptions()
    {
        removeRows();
        /*Get list of customer canceled subscriptions*/
        List<Package> fees = data.getCustomerCanceledSubscriptions(username);

        for (Package fee : fees ) {
            String id = fee.id;
            String title = fee.title;
            String price = fee.price;
            String duration = fee.duration;
            addFeeRow(id, title, price);


        }
        setFeeListeners();
        /*Set the listeneras*/


        /*Get customer subscribed subscriptions*/
        List<Package> subscription = data.getCustomersSubscriptions(username);

        for (Package sub : subscription) {
            String id = sub.id;
            String title = sub.title;
            String price = sub.price;
            String duration = sub.duration;
            addRow(id, title, price);

        }
        setDeleteClickListeners();


    }

    public void loadAvailable()
    {
        /*Load what available packages are available for the customer to subscribe to*/
        List<Package> products = data.getProductIds();

        for (Package prod : products) {
            String id = prod.id;
            String title = prod.title;
            String price = prod.price;
            String duration = prod.duration;

            if (!data.checkSubscription(id, username)) {

                addSubscribeRow(id, title, price);
            }

        }
        setSubscribeListeners();


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
        price.setText("$" + Price);
        price.setTextColor(Color.WHITE);
        price.setLayoutParams(lp);
        price.setGravity(Gravity.CENTER);

        Button button = new Button(this);
        button.setBackgroundResource(R.drawable.redbutton);
        button.setText("Delete   ");
        button.setTextAppearance(this,R.style.ButtonText);
        button.setLayoutParams(lp);

        row.addView(id);
        row.addView(title);
        row.addView(price);
        row.addView(button);

        myRows.add(row);
        myButtons.add(button);
        myIds.add(productId);
        tab.addView(row);


    }


    public void setDeleteClickListeners() {

        Iterator<Button> iter = myButtons.iterator();
        while(iter.hasNext())
        {
            Button button = iter.next();
            button.setOnClickListener(null);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = myButtons.indexOf((Button)view);
                    String id = myIds.get(index);
                    data.deleteSubscription(id, username);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadCustomerSubscriptions();
                    loadAvailable();



                }
            });

        }
    }


    public void addSubscribeRow(String productId, String Title, String Price) {

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
        button.setBackgroundResource(R.drawable.greenbutton);
        button.setTextAppearance(this,R.style.ButtonText);
        button.setText("Subscribe");
        button.setLayoutParams(lp);

        row.addView(id);
        row.addView(title);
        row.addView(price);
        row.addView(button);

        mySubscribeRows.add(row);
        mySubscribeButtons.add(button);
        mySubscribeIds.add(productId);
        tabLower.addView(row);


    }

    public void addFeeRow(String productId, String Title, String Price) {

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
        price.setText("$150");
        price.setTextColor(Color.WHITE);
        price.setLayoutParams(lp);
        price.setGravity(Gravity.CENTER);

        Button button = new Button(this);
        button.setTextAppearance(this,R.style.ButtonText);
        if(data.checkIfWaived(username,productId))
        {
            button.setText("Waived");
        }
        else {
            button.setText("Waive");

        }
        button.setLayoutParams(lp);

        row.addView(id);
        row.addView(title);
        row.addView(price);
        row.addView(button);

        myFeeRows.add(row);
        myFeeButtons.add(button);
        tabEvenLower.addView(row);


    }

    public void setSubscribeListeners() {

        Iterator<Button> iter1 = mySubscribeButtons.iterator();

        while(iter1.hasNext())
        {
            Button button = iter1.next();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int index = mySubscribeButtons.indexOf((Button)view);
                    String prodId = mySubscribeIds.get(index);
                    data.subscribe(prodId, username);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    loadCustomerSubscriptions();
                    loadAvailable();


                }
            });




        }



    }

    public void setFeeListeners() {

        Iterator<Button> iter1 = myFeeButtons.iterator();

        while(iter1.hasNext()) {
            Button button = iter1.next();
            int index = myFeeButtons.indexOf(button);
            TableRow row = (TableRow) tabEvenLower.getChildAt(index);
            TextView title = (TextView) row.getVirtualChildAt(0);
            //already waived
            if (data.checkIfWaived(username, title.getText().toString())) {

                button.setOnClickListener(null);

            } else {
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        int index = myFeeButtons.indexOf((Button) view);
                        TableRow row = (TableRow) tabEvenLower.getChildAt(index);
                        TextView title = (TextView) row.getVirtualChildAt(1);
                        TextView title2 = (TextView) row.getVirtualChildAt(0);

                        data.waive(title.getText().toString(), username, title2.getText().toString());



                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        loadCustomerSubscriptions();
                        loadAvailable();


                    }


                });
            }
        }

    }

    public void removeRows() {

        tab.removeAllViews();
        tabLower.removeAllViews();
        tabEvenLower.removeAllViews();

        myRows.clear();
        myButtons.clear();
        myIds.clear();

        mySubscribeRows.clear();
        mySubscribeIds.clear();
        mySubscribeButtons.clear();

        myFeeButtons.clear();
        myFeeRows.clear();


    }

    public void onLogOut(View v){
        data.logOut();
        Toast.makeText(getApplicationContext(),
                getString(R.string.pre_002),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SplashTitleActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assist_customer, menu);
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
