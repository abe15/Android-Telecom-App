package cse110.android.bigheroeight.com.ucsdtelecom.Mediator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.List;

import cse110.android.bigheroeight.com.ucsdtelecom.Package;

/**
 * Created by abraham on 2/6/2015.
 */

/*Putting all Writing and reading from parse in this class in order to satisfy the single
* responsibily principle*/
public class Database {
    //Mediator pattern all writing and reading request from database need to pass through
    //this mediator

    public void Database() {}


    /*Attempts to log in user if successful returns true else returns false */
    public boolean logIn(final String username_login, final String password_login) {
        boolean checkLogIn = false;
        // Parse Log-In activity
        try {
            /*Attempt to login, if it fails go to catch*/
            ParseUser.logIn(username_login, password_login);
            checkLogIn = true;

        } catch (ParseException e) {
            checkLogIn = false;

        }
        return checkLogIn;
    }

    public boolean signUpRetailCustomer(String usernametxt, String passwordtxt, String repasswordtxt
            , String firstnametxt, String lastnametxt, String middlenametxt, String addresstxt,
                                        String citytxt, String statetxt, String ziptxt, String birthdaytxt,
                                        String emailtxt, String phonenumbertxt) {

        boolean success = false;
        // Creates a new user in the Parse Database
        ParseUser user = new ParseUser();
        user.setUsername(usernametxt);
        user.setPassword(passwordtxt);
        user.setEmail(emailtxt);
        user.put("FirstName", firstnametxt);
        user.put("MiddleName", middlenametxt);
        user.put("LastName", lastnametxt);
        user.put("Address", addresstxt);
        user.put("City", citytxt);
        user.put("State", statetxt);
        user.put("ZipCode", ziptxt);
        user.put("Birthday", birthdaytxt);
        user.put("PhoneNumber", phonenumbertxt);
        user.put("permission","00");
        user.put("Bill",0);

        try {
            user.signUp();
            success = true;

        } catch (ParseException e) {
            success = false;
        }
        return success;
    }

    public boolean signUpCommercialCustomer(String usernametxt, String passwordtxt, String repasswordtxt
            , String firstnametxt, String lastnametxt, String middlenametxt, String addresstxt,
                                            String citytxt, String statetxt, String ziptxt, String birthdaytxt,
                                            String emailtxt, String phonenumbertxt,
                                            String businessnametxt, String responsible_billing_person_first_name_txt
            , String responsible_billing_person_middle_name_txt,
                                            String responsible_billing_person_last_name_txt) {

        boolean success = false;
        // Creates a new user in the Parse Database
        ParseUser user = new ParseUser();
        user.setUsername(usernametxt);
        user.setPassword(passwordtxt);
        user.setEmail(emailtxt);
        user.put("FirstName", firstnametxt);
        user.put("MiddleName", middlenametxt);
        user.put("LastName", lastnametxt);
        user.put("Address", addresstxt);
        user.put("City", citytxt);
        user.put("State", statetxt);
        user.put("ZipCode", ziptxt);
        user.put("Birthday", birthdaytxt);
        user.put("PhoneNumber", phonenumbertxt);
        user.put("BusinessName", businessnametxt);
        user.put("BillingFirstName", responsible_billing_person_first_name_txt);
        user.put("BillingMiddleName", responsible_billing_person_middle_name_txt);
        user.put("BillingLastName", responsible_billing_person_last_name_txt);
        user.put("permission","00");
        user.put("Bill",0);
        try {
            user.signUp();
            success = true;
        } catch (ParseException e) {
            success = false;
        }
        return success;
    }

    public List<String> UserInfo(String username){
        String username1 = ParseUser.getCurrentUser().getUsername();
        ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username", username1);

        List<ParseUser> list;
        List<String> info = new ArrayList<String>();

        try {
            list = query.find();
            for(ParseObject obj : list)
            {

                info.add(0,obj.getString("username"));
                info.add(1,obj.getString("FirstName"));
                info.add(2,obj.getString("MiddleName"));
                info.add(3,obj.getString("LastName"));
                info.add(4,obj.getString("Address"));
                info.add(5,obj.getString("City"));
                info.add(6,obj.getString("ZipCode"));
                info.add(7,obj.getString("State"));
                //
                info.add(8,obj.getString("Birthday"));
                info.add(9,obj.getString("PhoneNumber"));
                //
                info.add(10,obj.getString("BusinessName"));
                info.add(11,obj.getString("BillingFirstName"));
                info.add(12,obj.getString("BillingMiddleName"));
                info.add(13,obj.getString("BillingLastName"));

            }
        } catch (ParseException e) {

        }

        return info;
    }


    public void logOut() {
        ParseUser.logOut();

    }


    /*This gets the users permission number, 00 customer, 11 marketing rep, 10 customer rep*/
    public String getPermission(String username)
    {
        ParseQuery<ParseUser> users = ParseQuery.getQuery("_User");
        users.whereEqualTo("username", username);
        List<ParseUser> list;
        try
        {
            list = users.find();
            String p = (String) list.get(0).get("permission");
            return p;
        }
        catch(ParseException e)
        {
            //no such user but this error should never happen
        }

        return"";
    }



    /*This will check if the customer is already subscribed to package/service, if he is
   * return true else return false*/
    public boolean checkSubscription(final String id, String username) {

        ParseQuery<ParseObject> customerSubscriptions = ParseQuery.getQuery("CustomerSubscriptions");
        customerSubscriptions.whereEqualTo("username", username);

        try {
            List<ParseObject> products = customerSubscriptions.find();

            for (ParseObject product : products) {
                String productId = (String) product.get("ProductId");
                if (productId.equals(id)) {
                    return true;
                }

            }

        } catch (ParseException e) {
            /*Customer isn't subscribed to anything*/
            return false;
        }
        return false;
    }

    /*Returns a list of products that are available for purchase with title,price,image,id*/
    public List<Package> getProductIds() {
        List<Package> ids = new ArrayList<Package>();
            /*Get all services and packages from parse database*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("InAppProducts");
        try {
            List<ParseObject> list = query.find();
            for (ParseObject l : list) {
                Bitmap bitmap;
                try {
                    /*Download image,decode it*/
                    byte[] data = ((ParseFile) l.get("Picture")).getData();
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                    ids.add(new Package((String) l.get("ProductID"), (String) l.get("Title")
                            , String.valueOf(l.get("Price")), bitmap,String.valueOf(l.get("Duration"))));


                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (ParseException e) {
              /*There are no products*/
        }
        return ids;
    }



    /*Gets a list of canceled subscriptions that the customer with username canceled*/
    public List<Package> getCustomerCanceledSubscriptions(String username) {
        List<Package> ids = new ArrayList<Package>();
            /*Get all services and packages from parse database*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CanceledSubscriptions");
        query.whereEqualTo("username", username);
        try {
            List<ParseObject> list = query.find();
            for (ParseObject l : list) {
                ids.add(new Package((String) l.get("ProductId"), (String) l.get("Title")
                        , String.valueOf(l.get("Price")), null,null));
            }
        } catch (ParseException e) {
              /*There are no products*/
        }

        return ids;
    }

    /*Gets a list of concessions that the customer has */
    public List<Package> getCustomerConcessions(String username) {
        List<Package> ids = new ArrayList<Package>();
            /*Get all services and packages from parse database*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Concessions");
        query.whereEqualTo("username", username);
        try {
            List<ParseObject> list = query.find();
            for (ParseObject l : list) {
                ids.add(new Package((String) l.get("ProductID"), (String) l.get("Title")
                        , String.valueOf(l.get("Price")), null,null));
            }
        } catch (ParseException e) {
              /*There are no products*/
        }

        return ids;
    }


    /*Returns a list of products that customer with username is subscribed to*/
    public List<Package> getCustomersSubscriptions(String username) {
        List<Package> ids = new ArrayList<Package>();
            /*Get all services and packages from parse database*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CustomerSubscriptions");
        query.whereEqualTo("username", username);
        try {
            List<ParseObject> list = query.find();
            for (ParseObject l : list) {
                ids.add(new Package((String) l.get("ProductId"), (String) l.get("Title")
                        , String.valueOf(l.get("Price")), null,null));
            }

        } catch (ParseException e) {
              /*There are no products*/
        }
        return ids;
    }


    /* Subscribe user to service or package given by id and username*/
    public void subscribe(String id, String username) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("InAppProducts");
        query.whereEqualTo("ProductID", id);
        List<ParseObject> list;
        try {
            list = query.find();
            ParseObject subscribePackage = new ParseObject("CustomerSubscriptions");
            subscribePackage.put("username", username);
            subscribePackage.put("Title", list.get(0).get("Title"));
            subscribePackage.put("Price", list.get(0).get("Price"));
            subscribePackage.put("ProductId", id);
            subscribePackage.saveInBackground();

        } catch (ParseException e) {
              /*There are no products*/

        }
    }

    /* waive fee for user */
    public void waive(String title, String username, String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CanceledSubscriptions");
        query.whereEqualTo("username", username);
        List<ParseObject> list;
        try {
            list = query.find();
            ParseObject waive = new ParseObject("Concessions");
            waive.put("username", username);
            waive.put("Title", title);
            waive.put("ProductID", id);
            waive.put("Price", 150 );

            waive.saveInBackground();

        } catch (ParseException e) {
              /*There are no products*/

        }
    }


    /*Given product id and username, deletes customer subscription */

    public void deleteSubscription(String id, String username) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CustomerSubscriptions");
        query.whereEqualTo("username", username);
        query.whereEqualTo("ProductId", id);

        ParseObject cancel = new ParseObject("CanceledSubscriptions");
        cancel.put("username", username);
        cancel.put("ProductId", id);
        List<ParseObject> list;
        try {
            list = query.find();
            cancel.put("Title", list.get(0).get("Title"));
            cancel.put("Price", list.get(0).get("Price"));
            list.get(0).delete();
            cancel.saveInBackground();

        } catch (ParseException e) {
              /*in case the customer is not subscribed to product w/ id*/

        }
    }



    public String getCurrentUser() {
        return ParseUser.getCurrentUser().getUsername();

    }


    /*Checks if there is a customer with user name */
    public boolean doesUserExist(String username) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username", username);

        List<ParseObject> list = new ArrayList<ParseObject>();
        try {
            list = query.find();



        } catch (ParseException e) {
              /*in case the customer is not subscribed to product w/ id*/

        }

        return (!list.isEmpty());

    }



    /*This method will add create packages for the marketing rep*/
    public boolean addPackage(String finalproductId, int typeOfProduct, double price,int duration) {
            /*Get Product from Parse*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.whereEqualTo("ProductId", finalproductId);
        List<ParseObject> list = null;
        try {

            list = query.find();
            ParseObject product = new ParseObject("InAppProducts");
            product.put("Title", list.get(0).get("Title"));
            product.put("Picture", list.get(0).get("Picture"));
            product.put("Price", price);
            product.put("ProductID", finalproductId);
            product.put("Duration",duration);

            if (typeOfProduct == 1) {
                product.put("ServiceOrPackage", false);
            } else {
                product.put("ServiceOrPackage", true);
            }
            /*Add product to in app products*/
            product.saveInBackground();
                        /*Successfully added product to database*/
            return true;
        } catch (ParseException e) {
        }

        return false;

    }




     /*Deletes in app product associated with given id parameter*/

    public void deleteInAppPackage(String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("InAppProducts");
        query.whereEqualTo("ProductID", id);
        List<ParseObject> list;
        try {
            list = query.find();
            list.get(0).delete();

        } catch (ParseException e) {
              /*product with id does not exists*/

        }
    }

    public void CancelCustomerAccount(String username) {
        String username1 = ParseUser.getCurrentUser().getUsername();
        ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username", username1);

        List<ParseUser> list;
        try {
            list = query.find();
            list.get(0).delete();
        } catch (ParseException e) {
            //in case errors happen
        }

        //TODO: Delete Subscriptions and Cancelled Subscriptions
        /*
        query = ParseQuery.getQuery("CustomerSubscriptions");
        query.whereEqualTo("username", username);

        list = null;

        try {
            list = query.find();
            int i = 0;
            for(ParseObject obj : list){
                list.get(0).delete();
                i++;
            }
        } catch (ParseException e) {

        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("CustomerSubscriptions");
        query.whereEqualTo("username", username);
        query.whereEqualTo("ProductId", id);

        ParseObject cancel = new ParseObject("CanceledSubscriptions");
        cancel.put("username", username);
        cancel.put("ProductId", id);
        List<ParseObject> list;
        try {
            list = query.find();
            cancel.put("Title", list.get(0).get("Title"));
            cancel.put("Price", list.get(0).get("Price"));
            list.get(0).delete();
            cancel.saveInBackground();

        } catch (ParseException e) {
              //in case the customer is not subscribed to product w/ id

        }
        */
    }



    /*This method will register the customer as an observer to receive notifications
    * when is bill is above a certain threshold*/
    public void register(String username,double threshold)
    {

        /*First check if the customer is already an observer*/
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Observers");
        query2.whereEqualTo("username",username);
        List<ParseObject> list2 = new ArrayList<ParseObject>();
        try {
            list2 = query2.find();

        } catch (ParseException e) {
            //in case errors happen
        }
        /*New observer*/
        if(list2.isEmpty())
        {
            ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
            query.whereEqualTo("username",username);
            List<ParseUser> list = null;
            try {
                list = query.find();

            } catch (ParseException e) {
                //in case errors happen
            }

            String email = list.get(0).getEmail();

            ParseObject observer = new ParseObject("Observers");
            observer.put("username", username);
            observer.put("threshold", threshold);
            observer.put("Bill",0);
            observer.put("email",email);
            observer.saveInBackground();



        }
        /*Old observer, just update the old threshold value*/
        else
        {
            list2.get(0).put("threshold",threshold);
            list2.get(0).saveInBackground();

        }



    }

    public boolean hasBill(String username)
    {
        ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username",username);
        List<ParseUser> list = new ArrayList<ParseUser>();
        try {
            list = query.find();
            String b = String.valueOf(list.get(0).get("Bill"));
            Double bill = Double.valueOf(b);
            return bill != 0.0;
        } catch (ParseException e) {
            //in case errors happen
        }
        return false;

    }

    public boolean checkIfWaived(String username,String id)
    { ParseQuery<ParseObject> customerSubscriptions = ParseQuery.getQuery("Concessions");
        customerSubscriptions.whereEqualTo("username", username);

        try {
            List<ParseObject> products = customerSubscriptions.find();

            for (ParseObject product : products) {
                String productId = (String) product.get("ProductID");
                if (productId.equals(id)) {
                    return true;
                }

            }

        } catch (ParseException e) {
            /*Customer isn't subscribed to anything*/
            return false;
        }
        return false;}


  }






