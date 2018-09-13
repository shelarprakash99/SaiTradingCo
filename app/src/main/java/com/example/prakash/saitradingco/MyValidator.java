package com.example.prakash.saitradingco;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Prakash on 4/7/2017.
 */

public class MyValidator {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String REQUIRED_MSG = "Field required";


    // validating email id
    public static boolean isValidEmail(EditText editText) {
        Log.d("tag", "validate method call");
        String email = editText.getText().toString().trim();
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            editText.setError("Enter valid UserName");
            return false;
        }
        editText.setError(null);
        return true;
    }

    // validating password
    public static boolean isValidPassword(EditText editText) {
        String pass = editText.getText().toString().trim();
        if (pass != null && pass.length() > 3) {
            editText.setError(null);
            return true;
        }
        editText.setError("Enter valid Password");
        return false;
    }

    public static boolean isValidField(TextView textView) {
        String txtValue = textView.getText().toString().trim();

        if (txtValue.length() == 0) {
            textView.setError(REQUIRED_MSG);
            return false;
        }
        textView.setError(null);
        return true;
    }

    public static boolean isValidField(EditText editText) {
        String txtValue = editText.getText().toString().trim();

        if (txtValue.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        editText.setError(null);
        return true;
    }



  /*  public static boolean isValidSpinner_flatNo(Spinner sp_select_flat) {

        View view = sp_select_flat.getSelectedView();
        TextView textView = (TextView) view;
        if (Buildings_list.size() == 0) {
            // textView.setError("None selected");
            return false;
        }
        // textView.setError(null);
        return true;

    }*/


    public static boolean isValidMobile(EditText editText) {
        String mob = editText.getText().toString().trim();
        if (mob != null && mob.length() == 10) {
            editText.setError(null);
            return true;
        }
        editText.setError(REQUIRED_MSG + " Enter 10 digits");
        return false;
    }

}

