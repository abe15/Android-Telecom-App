package cse110.android.bigheroeight.com.ucsdtelecom.Factory;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.*;
import cse110.android.bigheroeight.com.ucsdtelecom.Mediator.Database;
import cse110.android.bigheroeight.com.ucsdtelecom.Package;

/*This is the concrete creator*/
public class CreatePackageServiceActivity extends Creator {

    private EditText priceBox;
    private EditText durationBox;
    private CheckBox wireless;
    private CheckBox landline;
    private CheckBox internet;
    Database data = new Database();
    LinearLayout line;
    LinearLayout inAppHolder;
    boolean editingPackage = false;
    boolean unloading = false;
    String previousProductId = "";

    //myButtons holds buttons that are labeled "Load" price
    ArrayList<Button> myButtons = new ArrayList<Button>();
    ArrayList<String> myButtonIds= new ArrayList<String>();
    ArrayList<String> myButtonPrices = new ArrayList<String>();

    //myDeleteButtons holds buttons that are labeled "Delete"
    ArrayList<Button> myDeleteButtons = new ArrayList<Button>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_package_service);
        wireless = (CheckBox) findViewById(R.id.checkBox1);
        landline = (CheckBox) findViewById(R.id.checkBox2);
        internet = (CheckBox) findViewById(R.id.checkBox3);
        priceBox = (EditText) findViewById(R.id.enterPrice);
        durationBox = (EditText) findViewById(R.id.enterDuration);
        line = (LinearLayout) findViewById(R.id.mainLinearLayout);
        inAppHolder = new LinearLayout(this);
        inAppHolder.setOrientation(LinearLayout.VERTICAL);
        inAppHolder.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        inAppHolder.setGravity(Gravity.CENTER);
        line.addView(inAppHolder);
        /*Add products that are already available so the marketing rep can view them*/
        loadInAppProducts();
    }



    public void loadInAppProducts()
    {
        /*Remove all rows before adding anything*/
        clear();
        List<Package> products = data.getProductIds();

        for( Package product : products)
        {

            String id = product.id;
            String title = product.title;
            String price = product.price;
            String duration = product.duration;

            LinearLayout layout = new LinearLayout(this);

             /*Set up size*/
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setGravity(Gravity.CENTER);
            layout.setBackgroundResource(R.drawable.blu);

             /*Set up textview to hold title of product*/
            TextView titleView = new TextView(this);

            /*Set size of textview*/
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lparams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            titleView.setLayoutParams(lparams1);
            titleView.setGravity(Gravity.CENTER);

            /*set title*/
            titleView.setText(title + "  ");
            titleView.setTextColor(Color.WHITE);
            titleView.setTextSize(15);
            layout.addView(titleView);


             /*Set up textview to hold price of product*/
            TextView priceView = new TextView(this);
            priceView.setLayoutParams(lparams1);
            priceView.setText("$" + price + " ");

            myButtonPrices.add(price);

            priceView.setTextColor(Color.WHITE);
            priceView.setTextSize(15);
            priceView.setGravity(Gravity.CENTER);
            layout.addView(priceView);

            TextView durationView = new TextView(this);
            durationView.setLayoutParams(lparams1);
            durationView.setText("Duration: " + duration + " months");
            durationView.setTextColor(Color.WHITE);
            durationView.setTextSize(15);
            durationView.setGravity(Gravity.CENTER);
            layout.addView(durationView);


            /*Add An update button*/
            Button myButton = new Button(this);
            myButton.setLayoutParams(lparams1);
            myButton.setBackgroundResource(R.drawable.bluebutton);
            myButton.setTextAppearance(this,R.style.ButtonText);
            myButton.setText("Load to Edit");
            layout.addView(myButton);
            myButtons.add(myButton);
            myButtonIds.add(id);

            /*Add An update button*/
            Button button = new Button(this);
            button.setLayoutParams(lparams1);
            button.setBackgroundResource(R.drawable.redbutton);
            button.setTextAppearance(this,R.style.ButtonText);
            button.setText("Delete");
            layout.addView(button);
            myDeleteButtons.add(button);

            inAppHolder.addView(layout);


        }
        loadListeners();
        deleteListeners();


    }

    public void clear()
    {
        inAppHolder.removeAllViews();
    }

    public void clearCheckBoxes()
    {

        wireless.setChecked(false);
        landline.setChecked(false);
        internet.setChecked(false);
        priceBox.setText("");
        durationBox.setText("");


    }


    public void loadListeners()
    {


        for(Button button : myButtons)
        {

           button.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    int index = myButtons.indexOf(view);
                    Button b = myButtons.get(index);
                    String idd = myButtonIds.get(index);
                    String price = myButtonPrices.get(index);
                    /*Check the checkboxes
                     * on the package that the marketing rep
                      * wants to edit and add price to the price box*
                      */

                    wireless.setChecked(false);
                    landline.setChecked(false);
                    internet.setChecked(false);

                    if (idd.charAt(0) == '1') {
                        wireless.setChecked(true);
                    }
                    if (idd.charAt(1) == '1') {
                        landline.setChecked(true);
                    }
                    if (idd.charAt(2) == '1') {
                        internet.setChecked(true);
                    }

                    priceBox.setText(price);
                    priceBox.setSelection(price.length());

                    editingPackage = true;
                    previousProductId = idd;




                    for(Button b2 : myButtons)
                    {
                        if(b != b2) {
                            b2.setBackgroundResource(R.drawable.bluebutton);
                            b2.setText("Load to Edit");
                        }

                    }

                    if (!b.getText().toString().equals("Editing... Click to unload"))
                    {
                        b.setBackgroundResource(R.drawable.yellowbutton);
                        b.setText("Editing... Click to unload");
                    }
                    else
                    {
                        b.setBackgroundResource(R.drawable.bluebutton);
                        b.setText("Load to Edit");
                        editingPackage = false;
                        previousProductId = "";
                        clearCheckBoxes();
                    }





                }
            });


        }


    }


    public void deleteListeners()
    {
        for(Button button : myDeleteButtons)
        {

            button.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    int index = myDeleteButtons.indexOf(view);
                    String idd = myButtonIds.get(index);

                    data.deleteInAppPackage(idd);
                    loadInAppProducts();


                }
            });


        }




    }




    /*Here the marketing representative clicked create package add it to in app
    * products so customers can view it. Also is factory method*/
    public void onClickCreatePackage(View view)
    {

        /*Used to get product id from parse*/
        String productId = "";
        boolean cancel = checkForErrors();
        /*1 will indicate a service is being added
        * any other then a package being added*/
        int typeOfProduct = 0;
        /*If there were no errors do if statement*/
        if(!cancel)
        {


            final double price = Double.parseDouble(priceBox.getText().toString());
            final int duration = Integer.parseInt(durationBox.getText().toString());
            /*See which services are trying to be added to package*/
            /*I will use 3 bits, in order wireless,landline,internet*/
            if(wireless.isChecked())
            {
                productId =  productId+ "1";
                typeOfProduct++;
            }
            else
            {
                productId =  productId+ "0";

            }

            if(landline.isChecked())
            {
                typeOfProduct++;
                productId =  productId+ "1";
            }
            else
            {
                productId =  productId+ "0";
            }
            if(internet.isChecked())
            {
                productId =  productId+ "1";
                typeOfProduct++;
            }
            else
            {
                productId =  productId+ "0";
            }


            if(editingPackage)
            {
                data.deleteInAppPackage(previousProductId);
                editingPackage = false;
                previousProductId = "";

            }
            /*Finally create package*/
            boolean success = data.addPackage(productId, typeOfProduct, price,duration);
            if(success)
            {

                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.create_006),
                        Toast.LENGTH_LONG).show();
                //finish();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadInAppProducts();
                clearCheckBoxes();


            }
            else{//no successful
            }



        }
        else{
            //errors in the marketing rep input

        }

    }

    /*Checking for errors, at least one service is added to package and price bpx not empty,
    * Returns false is there were no errors else true if there were errors*/
    public boolean checkForErrors()
    {
        boolean cancel = false;

        String price = priceBox.getText().toString();
        String duration = durationBox.getText().toString();
        if(TextUtils.isEmpty(price))
        {
            cancel = true;
            /*Set error notification*/
            priceBox.setError(getString(R.string.error_create_002));

        }
        if(TextUtils.isEmpty(duration))
        {
            cancel = true;
            /*Set error notification*/
            durationBox.setError(getString(R.string.error_create_003));

        }
        /*Check if at least one check box checked*/
        if(!wireless.isChecked() && !landline.isChecked() && !internet.isChecked())
        {
            cancel = true;
            /*set error notification*/
            Toast.makeText(getApplicationContext(),
                    getString(R.string.Error),
                    Toast.LENGTH_LONG).show();


        }


        return cancel;

    }

    public void onMenuLogout(View v){
        //Database coding
        // Say if all fields has been filled, use if-else
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
        getMenuInflater().inflate(R.menu.menu_create_package, menu);
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
