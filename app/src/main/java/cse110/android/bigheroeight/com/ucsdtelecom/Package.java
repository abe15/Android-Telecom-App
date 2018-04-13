package cse110.android.bigheroeight.com.ucsdtelecom;

import android.graphics.Bitmap;

/**
 * Created by abraham on 2/6/2015.
 */
public class Package {

    public String id;
    public String title;
    public String price;
    public Bitmap bitmap;
    public String duration;
    public Package(String id, String title, String price, Bitmap bitmap,String duration)
    {
        this.id= id;
        this.title = title;
        this.price = price;
        this.bitmap = bitmap;
        this.duration = duration;


    }



}
