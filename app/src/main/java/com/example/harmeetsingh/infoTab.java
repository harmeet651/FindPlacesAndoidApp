package com.example.harmeetsingh;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infoTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infoTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "infoTab";

    public infoTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoTab.
     */
    // TODO: Rename and change types and number of parameters
    public static infoTab newInstance(String param1, String param2) {
        infoTab fragment = new infoTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        infoSingh act = (infoSingh) getActivity();
        String bbbb = act.getMyData();

        Log.d(TAG, "onCreateView: " + bbbb);

        View view;
        view = inflater.inflate(R.layout.fragment_info_tab, container, false);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(bbbb);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String formAdd = null;
        try {
            formAdd = jsonObject.getJSONObject("result").getString("formatted_address");
            Log.d(TAG, "onCreateView: " + formAdd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(formAdd!=null) {
            final TextView mTextView2 = (TextView) view.findViewById(R.id.headingInfo);
            mTextView2.setText(formAdd);
        }
        else{
            final TextView mTextView2 = (TextView) view.findViewById(R.id.headingInfo);
            mTextView2.setText("");
        }


        String phn = null;
        try {
            phn = jsonObject.getJSONObject("result").getString("international_phone_number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        final TextView mTextView3 = (TextView) view.findViewById(R.id.phone);
//        mTextView3.setText(phn);
        if(phn!=null) {
            final TextView mTextView3 = (TextView) view.findViewById(R.id.phone);
            mTextView3.setText(phn);
        }
        else{
            final TextView mTextView3 = (TextView) view.findViewById(R.id.phone);
            mTextView3.setText("");
        }


        String price = null;
        try {
            price = jsonObject.getJSONObject("result").getString("price_level");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView mTextView4 = (TextView) view.findViewById(R.id.price2);
        mTextView4.setText("$$");


        String rate = null;
        try {
            rate = jsonObject.getJSONObject("result").getString("rating");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView mTextView5 = (TextView) view.findViewById(R.id.rating);
        mTextView5.setText(rate);


        String goog = null;
        try {
            goog = jsonObject.getJSONObject("result").getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView mTextView6 = (TextView) view.findViewById(R.id.gugu);
        mTextView6.setText(goog);

        String web = null;
        try {
            web = jsonObject.getJSONObject("result").getString("website");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView mTextView7 = (TextView) view.findViewById(R.id.website);
        mTextView7.setText(web);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
