package cse110.android.bigheroeight.com.ucsdtelecom.Mediator;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize parse account
        // Application ID, Client Key
        Parse.initialize(this, "aBcINx5eJC0vSRRMF44eHmOIorKirWtz5UjH9MCh",
                "0EQMZSTKc2DgE1lQx4l6WOe3qgE8LL2OaONvBPFW");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // Sets all objects to pubic, private if removed
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }

}
