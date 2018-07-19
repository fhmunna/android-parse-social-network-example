package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static com.example.back4app.userregistrationexample.PreferenceKey.REGISTARED;

public class Locationactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView location;
    Locale loc;

    Spinner countrySpinner;

    String deviceLocation;
    Button location_continue;
    ArrayList<String> countries = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationactivity);

        SharedPref.init(getApplicationContext());


        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);

        location = (TextView) findViewById(R.id.location);
        deviceLocation = (getUserCountry(getApplicationContext()));
//        location.setText(deviceLocation);
        location_continue = (Button) findViewById(R.id.location_continue);


        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries_ISO = new ArrayList<String>();
        String country;
        String country_ISO;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
//            country_ISO = loc.getISO3Country();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
//                countries_ISO.add( country_ISO );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        try {
             loc = new Locale("", deviceLocation);



        int j = 0;
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).equals(loc.getDisplayCountry())) {
                final int finalI = i;
                countrySpinner.post(new Runnable() {
                    @Override
                    public void run() {
                        countrySpinner.setSelection(finalI);
                    }
                });
                Log.d("loc_check_00", countries.get(i));
//                return;
                break;
            }
        }
        }catch (NullPointerException e){
            e.printStackTrace();

        }


//        loc.getDisplayCountry();

//        Log.d("loc_check", loc.getDisplayCountry());


        countrySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
//set the spinners adapter to the previously created one.
        countrySpinner.setAdapter(adapter);


        location_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ParseUser currentUser = ParseUser.getCurrentUser();
                ParseQuery<ParseUser> query = ParseUser.getQuery();
//                query.whereEqualTo("username",currentUser.getUsername());
                query.getInBackground(currentUser.getObjectId(), new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser object, ParseException e) {

                        if (e == null) {


                        /*    object.put("firstName", et_first_name.getText().toString());
                            object.put("lastName", et_last_name.getText().toString());
                            object.put("gender", gender_value);
                            object.put("phone", et_phone.getText().toString());
                            object.put("dob", et_dob.getText().toString());*/
                            object.put("location", deviceLocation);
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    SharedPref.write(REGISTARED, "true");
                                    Intent intent = new Intent(Locationactivity.this, ProfileActivity.class);
                                    startActivity(intent);


                                }
                            });

                            Log.d("size ", "Ok");
                        } else {

                            Log.d("size ", String.valueOf(e));
                            e.printStackTrace();


                        }
                    }

                });


            }
        });


    }


    /**
     * Get ISO 3166-1 alpha-2 country code for this device (or null if not available)
     *
     * @param context Context reference to get the TelephonyManager instance from
     * @return country code or null
     */
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        //TODO after Selecting country


        deviceLocation = countries.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
