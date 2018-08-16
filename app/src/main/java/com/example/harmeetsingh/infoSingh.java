package com.example.harmeetsingh;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class infoSingh extends AppCompatActivity implements infoTab.OnFragmentInteractionListener, photoTab.OnFragmentInteractionListener, mapTab.OnFragmentInteractionListener, ReviewsFragment.OnFragmentInteractionListener{
    TextView place_id;
    String place;
    public String myString;
    private static final String TAG = "infoSingh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_singh);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tablayoutInfoKa);
        tabLayout2.addTab(tabLayout2.newTab().setText("INFO"));
        tabLayout2.addTab(tabLayout2.newTab().setText("PHOTO"));
        tabLayout2.addTab(tabLayout2.newTab().setText("MAP"));
        tabLayout2.addTab(tabLayout2.newTab().setText("REVIEW"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager2 = (ViewPager) findViewById(R.id.pagerInfoKa);
        final infoAdapter adapter2 = new infoAdapter(getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPager2.setAdapter(adapter2);
        viewPager2.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Bundle gt=getIntent().getExtras();
        String place=gt.getString("sdsd");
                    myString=place;
                        Log.d("hi", "onResponse: " + myString);
    }
    public String getMyData() {
        return myString;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
