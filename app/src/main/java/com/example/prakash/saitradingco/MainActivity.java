package com.example.prakash.saitradingco;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    static final int PICK_CONTACT_REQUEST = 1;
    static final int PICK_CONTACT_REQUEST_VAGAN = 2;
    ImageButton select_contact;
    EditText edt_gadi_no, edt_loding_bags, edt_mark, edt_loading_weight, edt_rate, edt_sub_total, edt_advance, edt_total_amount, edt_driver_name, edt_driver_mobile_no, edt_Mobile_no_send_sms;
    Button btn_send_sms,btn_send_v_sms;
    TextView tv_current_Date, tv_current_Date1;
    String sms_bd,sms_bd_vagan;
    String company_name = "Sai Trading Company, Nandgaon", date_time = "", driver_name = "";
    String gadi_no, loding_bags, mark, loading_weight, rate, Sub_total, advance, total_amount, driver_mobile_no;
    String box_v_no, loding_v_station, marka_v, loading_v_weight, loding_v_bags, rate_v, loding_v_date, total_v_amount;
    LinearLayout linear_gadi, linear_vagan;
    //---------------
    EditText edt_v_marka, edt_v_box_no, edt_v_loding_date, edt_v_loding_bags, edt_v_loding_weight, edt_v_rate, edt_v_total_amount, edt_v_Mobile_no_send_sms;
    Spinner sp_v_loding_station;
    ImageButton select_v_contact;
    DatePickerDialog datePickerDialog;


    double Sub_total_Db, total_v_Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        InatializeUi();

        List<String> listGrade = new ArrayList<String>();
        listGrade.add("Manmad");
        listGrade.add("Nandgaon");
        listGrade.add("Nagarsol");
        listGrade.add("Yeola");
        listGrade.add("Lasalgaon");
        listGrade.add("Dhule");
        listGrade.add("Aurangabad");
        listGrade.add("Niphad");
        listGrade.add("kherwadi");

        ArrayAdapter<String> dataAdapterGrade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGrade);
        dataAdapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_v_loding_station.setAdapter(dataAdapterGrade);

       Calculation();

        Calculation_for_vagan();

        linear_gadi.setVisibility(View.VISIBLE);
        linear_gadi.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linear_vagan.setVisibility(View.INVISIBLE);
        linear_vagan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));


        select_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickContact();


            }
        });

        select_v_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickContact_vagan();


            }
        });

        edt_v_loding_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickStartDate();
            }
        });

        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFields()) {

                    driver_mobile_no = edt_driver_mobile_no.getText().toString();
                    driver_name = edt_driver_name.getText().toString();
                    sms_bd = company_name + "\n" + date_time + "\nGadi No: " + gadi_no + "\nLoading Bags: " + loding_bags + "\n" + mark + "\nLoading Weight: " + loading_weight + "\nRate: " + rate + "\n" + loading_weight + "x" + rate + "=" + Sub_total + "\nAdvance: " + advance + "\nTotal Amount: " + total_amount + "\nDriver Name" + driver_name + "\nDriver Mobile No: " + driver_mobile_no;

                    String no_txt = edt_Mobile_no_send_sms.getText().toString();
                    if (no_txt.isEmpty()) {
                        //Please Select Phone No
                    } else {

                        Intent intentt = new Intent(Intent.ACTION_SEND);
                        intentt.setData(Uri.parse("sms: from Sai trading Company Nandgaon"));
                        intentt.setType("text/plain");
                        intentt.putExtra(Intent.EXTRA_TEXT, sms_bd);
                        // intentt.putExtra("sms_body", "The SMS text");
                        intentt.putExtra("address", no_txt);
                        startActivityForResult(Intent.createChooser(intentt, ""), 0);

                    }

                }
            }
        });


        btn_send_v_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFields_Vagan()) {

                    sms_bd_vagan = company_name + "\n" + date_time + "\nBox No: " + box_v_no + "\nLoading station: " + loding_v_station + "\n"+"Loding Date:" +loding_v_date +"\n"+"Loding bags:" +loding_v_bags +"\n" + marka_v + "\nLoading Weight: " + loading_v_weight + "\nRate: " + rate_v + "\n" +"Total Amount: " + total_v_amount;

                    String no_txt = edt_v_Mobile_no_send_sms.getText().toString();
                    if (no_txt.isEmpty()) {
                        //Please Select Phone No
                    } else {

                        Intent intentt = new Intent(Intent.ACTION_SEND);
                        intentt.setData(Uri.parse("sms: from Sai trading Company Nandgaon"));
                        intentt.setType("text/plain");
                        intentt.putExtra(Intent.EXTRA_TEXT, sms_bd_vagan);
                        // intentt.putExtra("sms_body", "The SMS text");
                        intentt.putExtra("address", no_txt);
                        startActivityForResult(Intent.createChooser(intentt, ""), 0);

                    }

                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload_photos, menu);

        MenuItem itemBrows = menu.findItem(R.id.action_brows);
        itemBrows.setEnabled(true);

        MenuItem itemCamera = menu.findItem(R.id.action_camera);
        itemCamera.setEnabled(true);
        // }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.action_brows) {

            linear_gadi.setVisibility(View.VISIBLE);
            linear_gadi.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linear_vagan.setVisibility(View.INVISIBLE);
            linear_vagan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
        }
        if (id == R.id.action_camera) {

            linear_vagan.setVisibility(View.VISIBLE);
            linear_vagan.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linear_gadi.setVisibility(View.INVISIBLE);
            linear_gadi.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));

        }
        return super.onOptionsItemSelected(item);
    }

    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    private void pickContact_vagan() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST_VAGAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                edt_Mobile_no_send_sms.setText(number);

                // Do something with the phone number...
            }
        }
        else {
            if (requestCode == PICK_CONTACT_REQUEST_VAGAN) {
                // Make sure the request was successful
                if (resultCode ==  RESULT_OK) {
                    // Get the URI that points to the selected contact
                    Uri contactUri = data.getData();
                    // We only need the NUMBER column, because there will be only one row in the result
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                    // Perform the query on the contact to get the NUMBER column
                    // We don't need a selection or sort order (there's only one result for the given URI)
                    // CAUTION: The query() method should be called from a separate thread to avoid blocking
                    // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                    // Consider using CursorLoader to perform the query.
                    Cursor cursor = getContentResolver()
                            .query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    // Retrieve the phone number from the NUMBER column
                    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(column);
                    edt_v_Mobile_no_send_sms.setText(number);

                    // Do something with the phone number...
                }


            }
        }}

    public void InatializeUi() {

        linear_gadi = (LinearLayout) findViewById(R.id.linear_gadi);
        linear_vagan = (LinearLayout) findViewById(R.id.linear_vagan);
        select_contact = (ImageButton) findViewById(R.id.select_contact);
        btn_send_sms = (Button) findViewById(R.id.btn_send_sms);
        btn_send_v_sms = (Button) findViewById(R.id.btn_send_v_sms);
        edt_Mobile_no_send_sms = (EditText) findViewById(R.id.edt_Mobile_no_send_sms);
        edt_gadi_no = (EditText) findViewById(R.id.edt_gadi_no);
        edt_loding_bags = (EditText) findViewById(R.id.edt_loding_bags);
        edt_mark = (EditText) findViewById(R.id.edt_mark);
        edt_loading_weight = (EditText) findViewById(R.id.edt_loading_weight);
        edt_rate = (EditText) findViewById(R.id.edt_rate);
        edt_sub_total = (EditText) findViewById(R.id.edt_sub_total);
        edt_advance = (EditText) findViewById(R.id.edt_advance);
        edt_total_amount = (EditText) findViewById(R.id.edt_total_amount);
        edt_driver_name = (EditText) findViewById(R.id.edt_driver_name);
        edt_driver_mobile_no = (EditText) findViewById(R.id.edt_driver_mobile_no);
        tv_current_Date1 = (TextView) findViewById(R.id.tv_current_Date1);


        sp_v_loding_station = (Spinner) findViewById(R.id.sp_v_loding_station);
        select_v_contact = (ImageButton) findViewById(R.id.select_v_contact);
        edt_v_marka = (EditText) findViewById(R.id.edt_v_marka);
        edt_v_box_no = (EditText) findViewById(R.id.edt_v_box_no);
        edt_v_loding_date = (EditText) findViewById(R.id.edt_v_loding_date);
        edt_v_loding_bags = (EditText) findViewById(R.id.edt_v_loding_bags);
        edt_v_loding_weight = (EditText) findViewById(R.id.edt_v_loding_weight);
        edt_v_rate = (EditText) findViewById(R.id.edt_v_rate);
        edt_v_total_amount = (EditText) findViewById(R.id.edt_v_total_amount);
        edt_v_Mobile_no_send_sms = (EditText) findViewById(R.id.edt_v_Mobile_no_send_sms);

        edt_mark.setText("Sai Marka");
        // Display current date time u
        Thread myThread = null;
        Runnable myRunnableThread = new CountDownRunner(this, tv_current_Date1);
        myThread = new Thread(myRunnableThread);
        myThread.start();
    }

    public void PickStartDate() {
        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        edt_v_loding_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

     public void Calculation() {

        edt_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
                //Get Edit Text Values
                date_time = tv_current_Date1.getText().toString();
                gadi_no = edt_gadi_no.getText().toString();
                loding_bags = edt_loding_bags.getText().toString();
                mark = edt_mark.getText().toString();
                loading_weight = edt_loading_weight.getText().toString();
                String rate1 = edt_rate.getText().toString();
                try{
                    //Calculate Sub_total //----------------
                    double loading_WT = Double.parseDouble(loading_weight);
                    double rate_Db = Double.parseDouble(rate1);
                    Sub_total_Db = (loading_WT * rate_Db);

                    //rate in rupies
                    double d2 = rate_Db;
                    NumberFormat formatter1 = NumberFormat.getNumberInstance();
                    formatter1.setMinimumFractionDigits(2);
                    formatter1.setMaximumFractionDigits(2);
                    String output3 = formatter1.format(d2);
                    rate = output3;


                    double d = Sub_total_Db;
                    NumberFormat formatter = NumberFormat.getNumberInstance();
                    formatter.setMinimumFractionDigits(2);
                    formatter.setMaximumFractionDigits(2);
                    String output = formatter.format(d);
                    Sub_total = output;
                    edt_sub_total.setText(output);

                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }

            }
        });

        edt_advance.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
                String advance1 = edt_advance.getText().toString();
                double advance_Db = Double.parseDouble(advance1);

                //Advance in rupies
                double d2 = advance_Db;
                NumberFormat formatter1 = NumberFormat.getNumberInstance();
                formatter1.setMinimumFractionDigits(2);
                formatter1.setMaximumFractionDigits(2);
                String output2 = formatter1.format(d2);
                advance = output2;


                // Calculate Total Amount //------------------

                double total_amount_db = (Sub_total_Db + advance_Db);

                //total amount in rupies
                double d1 = total_amount_db;
                NumberFormat formatter = NumberFormat.getNumberInstance();
                formatter.setMinimumFractionDigits(2);
                formatter.setMaximumFractionDigits(2);
                String output1 = formatter.format(d1);
                total_amount = output1;
                edt_total_amount.setText(output1);


                //-----------------------------------------------------


            }
        });


    }

    public void Calculation_for_vagan() {


        String Rate_Field = edt_v_rate.getText().toString();

        edt_v_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
                //Get Edit Text Values
                ;

                date_time = tv_current_Date1.getText().toString();
                box_v_no = edt_v_box_no.getText().toString();
                loding_v_bags = edt_v_loding_bags.getText().toString();
                loding_v_date = edt_v_loding_date.getText().toString();
                marka_v = edt_v_marka.getText().toString();
                loading_v_weight = edt_v_loding_weight.getText().toString();
                rate_v = edt_v_rate.getText().toString();
                loding_v_station = sp_v_loding_station.getSelectedItem().toString();

                try{
                    //Calculate Sub_total //----------------
                    double loading_v_WT = Double.parseDouble(loading_v_weight);
                    double rate_Db = Double.parseDouble(rate_v);
                    total_v_Db = Double.parseDouble(String.valueOf((loading_v_WT * rate_Db)));


                    double d = total_v_Db;
                    NumberFormat formatter = NumberFormat.getNumberInstance();
                    formatter.setMinimumFractionDigits(2);
                    formatter.setMaximumFractionDigits(2);
                    String output = formatter.format(d);
                    total_v_amount = output;
                    edt_v_total_amount.setText(output);

                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }
            }
            //----------------------------

        });
    }

    private boolean validateFields() {
        boolean result = true;

        if (!MyValidator.isValidField(edt_gadi_no)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_loding_bags)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_mark)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_loading_weight)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_rate)) {
            result = false;
        }


        if (!MyValidator.isValidField(edt_advance)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_Mobile_no_send_sms)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_driver_mobile_no)) {
            result = false;
        }

        return result;
    }

    private boolean validateFields_Vagan() {
        boolean result = true;

        if (!MyValidator.isValidField(edt_v_box_no)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_v_loding_date)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_v_loding_bags)) {
            result = false;
        }
        if (!MyValidator.isValidField(edt_v_marka)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_v_loding_weight)) {
            result = false;
        }


        if (!MyValidator.isValidField(edt_v_rate)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_v_total_amount)) {
            result = false;
        }

        if (!MyValidator.isValidField(edt_v_Mobile_no_send_sms)) {
            result = false;
        }


        return result;
    }
}
