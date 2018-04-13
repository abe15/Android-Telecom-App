package cse110.android.bigheroeight.com.ucsdtelecom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;

/**
 * Created by Andrew on 2/28/2015.
 * Class: SignUpDialogFragment
 * Purpose: This class implements the construction of a popup alert dialog. We use this dialog
 * in the MainMenuCustomerRepActivity page when the user clicks on the Sign Up Customer button.
 * The dialog provides two options. 1) Sign up a commercial customer 2) Sign up a retail customer.
 * Clicking on either options will send the user to a new activity.
 */
public class SignUpDialogFragment extends DialogFragment {

    /**
     * Method: onCreateDialog
     * Purpose: When SignUpDialogFragment called to be constructed, onCreateDialog is also called
     * to construct an AlertDialog with the options of sending the user to the sign up activity for
     * either retail or commercial customers.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_dialog_000).setItems(R.array.alert_dialog_array, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                // which indicates which option the user selected
                if(which == 0){
                    // send them to the register retail activity
                    startActivity(new Intent(getActivity(), RegisterRetailActivity.class));
                } else if(which == 1){
                    // send them to the register commercial activity
                    startActivity(new Intent(getActivity(), RegisterCommercialActivity.class));
                }
            }
        });
        return builder.create();
    }
}