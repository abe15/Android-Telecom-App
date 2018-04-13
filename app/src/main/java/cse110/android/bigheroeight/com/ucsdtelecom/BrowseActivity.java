package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;

public class BrowseActivity extends Activity {

    // Variables used for the Pop Up
    PopupWindow logoutPop;
    PopupWindow subscribePop;
    boolean flagButton = false;

    ScrollView services;
    ScrollView packages;
    LinearLayout layoutMainServices;
    LinearLayout layoutMainPackages;
    ArrayList<Button> myButtons = new ArrayList<Button>();
    ArrayList<String> myIds = new ArrayList<String>();
    Button whichButtonWasClicked = null;

    final Database data = new Database();
    String username = data.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        services = (ScrollView) findViewById(R.id.Services);
        packages = (ScrollView) findViewById(R.id.Packages);

                /*Set up the TabHost*/
        TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

                /*Add products to tabs*/
        addProducts();

        services.addView(layoutMainServices);
        packages.addView(layoutMainPackages);

                 /* Add services tab*/
        TabHost.TabSpec servicesTab = tabs.newTabSpec("Services");
        servicesTab.setContent(R.id.Services);
        servicesTab.setIndicator("Services");
        tabs.addTab(servicesTab);

                /*Add packages tab*/
        TabHost.TabSpec packagesTab = tabs.newTabSpec("Packages");
        packagesTab.setContent(R.id.Packages);
        packagesTab.setIndicator("Packages");
        tabs.addTab(packagesTab);


    }

    /*This creates the product layout, Title,Picture,Price,button*/
    public void addProducts() {


        /*Layout to hold all services*/
        layoutMainServices = new LinearLayout(BrowseActivity.this);
        layoutMainServices.setOrientation(LinearLayout.VERTICAL);
        layoutMainServices.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        layoutMainPackages = new LinearLayout(BrowseActivity.this);
        layoutMainPackages.setOrientation(LinearLayout.VERTICAL);
        layoutMainPackages.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));


        List<Package> ids = data.getProductIds();//get list of product ids
        for (Package products: ids) {

            String id = products.id;
            String title = products.title;
            String price = products.price;
            Bitmap image = products.bitmap;
             /*Adding services/ packages to respective scrollview tabs*/
             /*Create new linear layout to hold product info*/
            LinearLayout layout = new LinearLayout(BrowseActivity.this);

             /*Set up size*/
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            layout.setGravity(Gravity.CENTER);

             /*Set up textview to hold title of product*/
            TextView titleView = new TextView(BrowseActivity.this);

            /*Set size of textview*/
            LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            LayoutParams lparams1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            titleView.setLayoutParams(lparams);
            titleView.setGravity(Gravity.CENTER);

            /*set title*/
            titleView.setText(title);
            titleView.setTextColor(Color.WHITE);
            titleView.setTextSize(25);
            layout.addView(titleView);

             /*Create container to hold picture*/
            ImageView imageFile = new ImageView(BrowseActivity.this);
            imageFile.setImageBitmap(image);
            layout.addView(imageFile);


             /*Set up textview to hold price of product*/
            TextView priceView = new TextView(BrowseActivity.this);
            priceView.setLayoutParams(lparams);
            priceView.setText("$" + price);
            priceView.setTextColor(Color.WHITE);
            priceView.setTextSize(25);
            priceView.setGravity(Gravity.CENTER);
            layout.addView(priceView);

            /*Add A Subscribe Button*/
            Button myButton = new Button(BrowseActivity.this);
            myButton.setLayoutParams(lparams1);
            layout.addView(myButton);
            myButtons.add(myButton);
            myIds.add(id);


            /*If current product is a service , add to service tab view*/
            if (count(id)) {
                layoutMainServices.addView(layout);

            } else {
                layoutMainPackages.addView(layout);

            }
        }

        /*Set of button colors/subscribed*/
        for( Button button : myButtons)
        {

            int index = myButtons.indexOf(button);
            String id = myIds.get(index);
            if (data.checkSubscription(id,username)) {
                /*Customer is subscribed, make it blue with "Subscribed" text, not clickable*/

                button.setBackgroundResource(R.drawable.bluebutton);
                button.setOnClickListener(null);
                button.setTextAppearance(this,R.style.ButtonText);
                button.setText("Subscribed");
            }
            else
            {
                button.setBackgroundResource(R.drawable.greenbutton);
                button.setTextAppearance(this,R.style.ButtonText);
                button.setText("Subscribe");
                button.setOnClickListener(new OnClickListener() {
                /*When the user clicks a subscribe button subscribe him to service*/
                    public void onClick(View view) {
                        /*Add "Are you sure Method?" pop up*/

                        // Obtains the screen dimensions
                        DisplayMetrics screenSize = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
                        int height = (4 * (screenSize.heightPixels)) / 5;
                        int width = (4 * (screenSize.widthPixels)) / 5;

                        LayoutInflater inflater = (LayoutInflater) BrowseActivity.this.
                                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = inflater.inflate(R.layout.subscribe_popup,
                                (ViewGroup) findViewById(R.id.subscribe_popup));
                        subscribePop = new PopupWindow(layout, width, height, true);
                        subscribePop.showAtLocation(layout, Gravity.CENTER, 0, 0);
                        whichButtonWasClicked = (Button)view;

                    }
                });

            }



        }



    }
    public void onProceedPopup2(View v){

        int index = myButtons.indexOf(whichButtonWasClicked);
        Button button = myButtons.get(index);
        String id = myIds.get(index);

        data.subscribe(id, username);
        button.setBackgroundResource(R.drawable.bluebutton);
        button.setText("Subscribed");
        button.setOnClickListener(null);
        whichButtonWasClicked = null;
        subscribePop.dismiss();
    }
    public void onBackPopup2(View v){
        subscribePop.dismiss();
    }

    /*Returns true if product is a service else it returns false and is package*/
    public boolean count(String s)
    {
        int counter = 0;
        for (int i = 0; i < s.length(); i++){
            if((s.charAt(i))== '1')
                counter++;

        }
        return (counter == 1);

    }

    public void onProfileLogout(View v){
        // Obtains the screen dimensions
        DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        int height = (4 * (screenSize.heightPixels)) / 5;
        int width = (4 * (screenSize.widthPixels)) / 5;

        LayoutInflater inflater = (LayoutInflater) BrowseActivity.this.
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
public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse,menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.action_settings){
        return true;
        }

        return super.onOptionsItemSelected(item);
        }
        }
