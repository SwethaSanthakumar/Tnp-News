package com.example.tnpnewsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Gps extends AppCompatActivity {

    TextInputEditText address;
    TextInputEditText city;
    TextInputEditText country;

    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        country=findViewById(R.id.country);

        locationManager= (LocationManager) this.getSystemService(LOCATION_SERVICE);
        boolean isGPS_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPS_enabled)
        {
            locationListener = new LocationListener()
            {
                @Override
                public void onLocationChanged(Location location)
                {

                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();

                    try
                    {
                        Geocoder geocoder = new Geocoder(Gps.this, Locale.getDefault());
                        List<Address> addressesList = geocoder.getFromLocation(latitude,longitude,1);
                        address.setText(addressesList.get(0).getAddressLine(0));
                        city.setText(addressesList.get(0).getAdminArea());
                        country.setText(addressesList.get(0).getCountryName());

                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                address.setText("Getting Location");
                city.setText("Getting Location");
                country.setText("Getting Location");
            }
            else
            {
                address.setText("Access not granted");
                city.setText("Access not granted");
                country.setText("Access not granted");
            }
        }
    }
}


