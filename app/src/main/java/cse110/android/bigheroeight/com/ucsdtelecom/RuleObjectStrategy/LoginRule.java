package cse110.android.bigheroeight.com.ucsdtelecom.RuleObjectStrategy;

/**
 * Created by abraham on 2/28/2015.
 */
public class LoginRule {


    public LoginRule(){
    }

    /*returns which activity to open*/
    public String applyRule(String permissionNumber){
        if((new AccessorCustomer()).evaluate(permissionNumber))
            return (new CustomerAction()).performAction();
        else if ((new AccessorMarketingRep()).evaluate(permissionNumber))
            return (new MarketingRepAction()).performAction();
        else if ((new AccessorCustomerRep()).evaluate(permissionNumber))
            return (new CustomerRepAction()).performAction();

        return "";
    }
}
