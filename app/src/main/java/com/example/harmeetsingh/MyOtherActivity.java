package com.example.harmeetsingh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyOtherActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    private List<recV> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_other);
                mRecyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                listItems=new ArrayList<>();
                loadRecyclerViewData();
    }
    private void loadRecyclerViewData(){

        Bundle gt=getIntent().getExtras();
        String str=gt.getString("abc");
        //Toast.makeText(MyOtherActivity.this, str, Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray arr = jsonObject.getJSONArray("results");
            if (arr == null) {
                Toast.makeText(MyOtherActivity.this, "NO RESULTS", Toast.LENGTH_LONG).show();
            } else {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject cur = arr.getJSONObject(i);
                    recV item = new recV(cur.getString("name"), cur.getString("vicinity"), cur.getString("icon"), cur.getString("place_id"));
                    listItems.add(item);
                }
                mAdapter = new MyAdapter(listItems, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

