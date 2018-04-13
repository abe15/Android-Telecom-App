package cse110.android.bigheroeight.com.ucsdtelecom.RuleObjectStrategy;

/**
 * Created by abraham on 3/8/2015.
 */
public class AccessorCustomer {
    public boolean evaluate(String permissionNumber)
    {
        if(permissionNumber.equals("00"))
        {
            return true;
        }
        return false;


    }
}
