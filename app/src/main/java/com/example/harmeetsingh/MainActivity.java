package com.example.harmeetsingh;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements FrontTab.OnFragmentInteractionListener, FrontTab2.OnFragmentInteractionListener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {


    private final String TAG = "LOC_SAMPLE_START";

    protected GoogleApiClient mGoogleApiClient;

    private TextView lat;
    private TextView lon;

    public void setLoctionFields(Location loc) {
        Log.d(TAG, "Updating");
        if (loc != null) {
            lat.setText(String.format("%f", loc.getLatitude()));
            Toast.makeText(MainActivity.this,String.valueOf("loc.getLatitude()"),Toast.LENGTH_LONG).show();
            lon.setText(String.format("%f", loc.getLongitude()));
            Toast.makeText(MainActivity.this,String.valueOf("loc.getLongitute()"),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,("hi"),Toast.LENGTH_LONG).show();
        }
    }


    private Location getLocation(){
        try {
            Location loc = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            return loc;
        }
        catch(SecurityException e){
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
//        lat = (TextView) findViewById(R.id.latu);
//        lon = (TextView) findViewById(R.id.lonu);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

        @Override
        protected void onStart () {
            super.onStart();
            mGoogleApiClient.connect();
        }

        @Override
        protected void onStop () {
            super.onStop();
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }

        @Override
        public void onRequestPermissionsResult(int reqCode, String[] perms, int results[])
        {
            if(reqCode==1){
                if(results.length==0 && results[0]== PackageManager.PERMISSION_GRANTED){
                    Location locData= getLocation();
                    setLoctionFields(locData);
                }
            }
        }
        @Override
    public void onConnected(Bundle ConnectionHint) {
          //  Toast.makeText(MainActivity.this, ("hiaku"), Toast.LENGTH_LONG).show();
            int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                Toast.makeText(this, ("hielse"), Toast.LENGTH_LONG).show();
                Location locdata = getLocation();
                setLoctionFields(locdata);
            }
        }
    @Override
    public void onConnectionFailed(ConnectionResult result){
        Log.d(TAG, "Connection Failed: ConnectionResult..getErrorCode()="+ result.getErrorCode());
    }
    @Override
    public void onConnectionSuspended(int cause){
        mGoogleApiClient.connect();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
