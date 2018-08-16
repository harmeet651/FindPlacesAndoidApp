package com.example.harmeetsingh;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


/**
 * Created by akash on 4/20/2018.
 */

public class ReviewsFragment extends Fragment {

    private Spinner reviewModeSpinner;
    private Spinner orderModeSpinner;
    private Context mContext;
    private TextView mNoReview;
    private ArrayList<ReviewResultItem> mReviewResultList;
    private ArrayList<ReviewResultItem> mDefaultGoogleReview;
    private ArrayList<ReviewResultItem> mDefaultYelpReview;
    private ArrayList<ReviewResultItem> mHighestGoogle;
    private ArrayList<ReviewResultItem> mHighestYelp;
    private ArrayList<ReviewResultItem> mLowestGoogle;
    private ArrayList<ReviewResultItem> mLowestYelp;
    private ArrayList<ReviewResultItem> mMostRecentGoogle;
    private ArrayList<ReviewResultItem> mMostRecentYelp;
    private ArrayList<ReviewResultItem> mLeastRecentGoogle;
    private ArrayList<ReviewResultItem> mLeastRecentYelp;
    private OnFragmentInteractionListener mListener;
    public static String currentSelectedReview;
    public static String currentSelectedOrder;
    private ReviewResultsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ReviewResultItem item;

    public static ReviewsFragment newInstance()
    {
        return new ReviewsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reviews,container,false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reviewModeSpinner = (Spinner) view.findViewById(R.id.review_dropdown);
        orderModeSpinner = (Spinner) view.findViewById(R.id.order_review_dropdown);
        ArrayAdapter<CharSequence> reviewNameAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.reviews_name_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> orderReviewAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.reviews_order_array, android.R.layout.simple_spinner_item);
        reviewModeSpinner.setAdapter(reviewNameAdapter);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.reviews_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        orderModeSpinner.setAdapter(orderReviewAdapter);
        mReviewResultList = new ArrayList<ReviewResultItem>();
        mNoReview = (TextView) view.findViewById(R.id.no_review_found);
        currentSelectedReview = getResources().getStringArray(R.array.reviews_name_array)[0];
        currentSelectedOrder = getResources().getStringArray(R.array.reviews_order_array)[0];
        //mPlaceInfo = PlaceInfoData.getInstance();
        mDefaultGoogleReview = new ArrayList<>();
        mDefaultYelpReview = new ArrayList<>();
        infoSingh activity = (infoSingh) getActivity();
        String myDataFromActivity = activity.getMyData();
        parseJSONGoogle(myDataFromActivity);
        if(mDefaultGoogleReview != null && mDefaultGoogleReview.size() > 0)
        {
            setUpGoogleReview();
        }
        mAdapter = new ReviewResultsAdapter(mContext, mReviewResultList, new ReviewResultsAdapter.ReviewResultsListener() {

            @Override
            public void authorNameOnClick(View v, int position) {
                item = mReviewResultList.get(position);
                String authorUrl = item.getAuthorUrl();
                if(authorUrl != null)
                {
                    Intent i2=new Intent(Intent.ACTION_VIEW, Uri.parse(authorUrl));
                    startActivity(i2);
                }
            }

            @Override
            public void imageUrlOnClick(View v, int position) {
                item = mReviewResultList.get(position);
                String imageUrl = item.getImageUrl();
                if(imageUrl != null)
                {
                    String authorUrl = item.getAuthorUrl();
                    if(authorUrl != null)
                    {
                        Intent i2=new Intent(Intent.ACTION_VIEW, Uri.parse(authorUrl));
                        startActivity(i2);
                    }

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        reviewModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelectedReview = parent.getSelectedItem().toString();
                if(currentSelectedReview.equalsIgnoreCase("Google reviews"))
                {
                    clearData();
                    if(mDefaultGoogleReview != null && mDefaultGoogleReview.size() > 0)
                    {
                        mNoReview.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);

                        switch (currentSelectedOrder)
                        {
                            case "Default order":
                                mReviewResultList.addAll(mDefaultGoogleReview);
                                mAdapter.notifyDataSetChanged();
                                break;
                            case "Highest rating":
                                mReviewResultList.addAll(mHighestGoogle);
                                mAdapter.notifyDataSetChanged();
                                break;
                            case "Lowest rating":

                                mReviewResultList.addAll(mLowestGoogle);
                                mAdapter.notifyDataSetChanged();
                                break;
                            case "Most recent":
                                mReviewResultList.addAll(mMostRecentGoogle);
                                mAdapter.notifyDataSetChanged();
                                break;
                            case "Least recent":
                                mReviewResultList.addAll(mLeastRecentGoogle);
                                mAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                    else
                    {
                        mRecyclerView.setVisibility(View.GONE);
                        mNoReview.setVisibility(View.VISIBLE);
                    }

                }
                else if(currentSelectedReview.equalsIgnoreCase("Yelp reviews"))
                {



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        orderModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelectedOrder = parent.getSelectedItem().toString();

                if(currentSelectedReview.equalsIgnoreCase("Google reviews") && mDefaultGoogleReview != null && mDefaultGoogleReview.size() > 0)
                {

                    clearData();
                    switch (currentSelectedOrder)
                    {
                        case "Default order":
                            mReviewResultList.addAll(mDefaultGoogleReview);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Highest rating":
                            mReviewResultList.addAll(mHighestGoogle);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Lowest rating":

                            mReviewResultList.addAll(mLowestGoogle);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Most recent":

                            mReviewResultList.addAll(mMostRecentGoogle);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Least recent":
                            mReviewResultList.addAll(mLeastRecentGoogle);
                            mAdapter.notifyDataSetChanged();
                            break;

                    }
                }
                else if(currentSelectedReview.equalsIgnoreCase("Yelp reviews") && mDefaultYelpReview != null && mDefaultYelpReview.size() > 0)
                {

                    clearData();
                    switch (currentSelectedOrder)
                    {
                        case "Default order":
                            mReviewResultList.addAll(mDefaultYelpReview);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Highest rating":
                            mReviewResultList.addAll(mHighestYelp);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Lowest rating":

                            mReviewResultList.addAll(mLowestYelp);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Most recent":
                            mReviewResultList.addAll(mMostRecentYelp);
                            mAdapter.notifyDataSetChanged();
                            break;
                        case "Least recent":
                            mReviewResultList.addAll(mLeastRecentYelp);
                            mAdapter.notifyDataSetChanged();
                            break;
                    }
                }
                else
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(mReviewResultList == null || mReviewResultList.size() == 0)
        {
            mNoReview.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        else
        {
            mNoReview.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }






    }
    private String toDate(long timestamp) {
        Date date = new Date (timestamp * 1000);
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public void clearData() {
        mReviewResultList.clear(); //clear list
        mAdapter.notifyDataSetChanged(); //let your adapter know about the changes and reload view.
    }



    private void setUpGoogleReview()
    {
        mHighestGoogle = new ArrayList<>();
        mLowestGoogle = new ArrayList<>();
        mLeastRecentGoogle = new ArrayList<>();
        mMostRecentGoogle = new ArrayList<>();
        mHighestGoogle.addAll(mDefaultGoogleReview);
        mMostRecentGoogle.addAll(mDefaultGoogleReview);
        mLowestGoogle.addAll(mDefaultGoogleReview);
        mLeastRecentGoogle.addAll(mDefaultGoogleReview);
        Collections.sort(mLowestGoogle, new Comparator<ReviewResultItem>(){
            public int compare(ReviewResultItem o1, ReviewResultItem o2)
            {
                return (Integer.parseInt(o1.getRating()) - Integer.parseInt(o2.getRating()));
            }
        });
        Collections.sort(mHighestGoogle, new Comparator<ReviewResultItem>(){
            public int compare(ReviewResultItem o1, ReviewResultItem o2)
            {
                return (Integer.parseInt(o2.getRating()) - Integer.parseInt(o1.getRating()));
            }
        });
        Collections.sort(mMostRecentGoogle, new Comparator<ReviewResultItem>(){
            public int compare(ReviewResultItem o1, ReviewResultItem o2)
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date r1Date = null;
                Date r2Date = null;
                try {
                    r1Date = simpleDateFormat.parse(o1.getDatePosted());
                    r2Date = simpleDateFormat.parse(o2.getDatePosted());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return r2Date.compareTo(r1Date);
            }
        });
        Collections.sort(mLeastRecentGoogle, new Comparator<ReviewResultItem>(){
            public int compare(ReviewResultItem o1, ReviewResultItem o2)
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date r1Date = null;
                Date r2Date = null;
                try {
                    r1Date = simpleDateFormat.parse(o1.getDatePosted());
                    r2Date = simpleDateFormat.parse(o2.getDatePosted());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return r1Date.compareTo(r2Date);
            }
        });
    }


    private void parseJSONGoogle(String myDataFromActivity) {
        try
        {
            mReviewResultList.clear();
            JSONObject jsonObject=new JSONObject(myDataFromActivity);
            jsonObject = jsonObject.getJSONObject("result");
            JSONArray reviewArray=jsonObject.getJSONArray("reviews");
            if(reviewArray != null)
            {

                for(int i = 0; i < reviewArray.length(); i++)
                {
                    JSONObject row = reviewArray.getJSONObject(i);
                    ReviewResultItem resultItem = new ReviewResultItem();
                    if(row.has("author_name"))
                    {

                        resultItem.setAuthorName(row.getString("author_name"));
                    }
                    if(row.has("profile_photo_url"))
                    {
                        resultItem.setImageUrl(row.getString("profile_photo_url"));
                    }
                    if(row.has("author_url"))
                    {
                        resultItem.setAuthorUrl(row.getString("author_url"));
                    }
                    if(row.has("rating"))
                    {
                        resultItem.setRating(row.getString("rating"));
                    }
                    if(row.has("text"))
                    {
                        resultItem.setReviewText(row.getString("text"));
                    }
                    if(row.has("time"))
                    {
                        String dateTime = toDate(row.getInt("time"));
                        resultItem.setDatePosted(dateTime);
                    }
                    mReviewResultList.add(resultItem);
                    mDefaultGoogleReview.add(resultItem);

                }
            }
            else
            {


            }

        }
        catch (JSONException e)
        {

        }


    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof photoTab.OnFragmentInteractionListener) {
            mListener = (ReviewsFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
