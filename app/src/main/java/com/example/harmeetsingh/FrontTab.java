package com.example.harmeetsingh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrontTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrontTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrontTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context a;
    View v;
    int finalValue;
    EditText key;
    Spinner cat;
    EditText dis;
    EditText diis;
    String valRADki;
    RadioGroup rg;
    RadioButton rb;
    RadioButton valRad;
    EditText locationNew;
    String newKey;
    String category;
    String dist;
    RadioButton rg1;
    RadioButton rg2;
    String locate;
    double lat=34.0224;
    double lon=-118.2851;
    // TODO: Rename and change types and number of parameters
    public static FrontTab newInstance(String param1, String param2) {
        FrontTab fragment = new FrontTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    View view;
    TextView myAwesomeTextView;
    TextView myAwesomeTextVie2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        myAwesomeTextView = (TextView) getView().findViewById(R.id.error1);
//        myAwesomeTextView.setVisibility(View.GONE);
        return inflater.inflate(R.layout.fragment_front_tab, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        a= context;
    }

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    public void registerBoy(){
        initializeKre();
        if(!validate()){
            Toast.makeText(a, "Please fix all fields with errors", Toast.LENGTH_SHORT).show();
        }
        else{
            successHogya();
        }
    }

    private boolean validate() {
        boolean validOkay=true;
        if(newKey.isEmpty()){
            Toast.makeText(a, "Keyword is empty", Toast.LENGTH_SHORT).show();
            final TextView myAwesomeTextView = (TextView) getView().findViewById(R.id.error1);
           // myAwesomeTextView.setVisibility(View.VISIBLE);
            myAwesomeTextView.setText("Please enter mandatory field");
            validOkay=false;
        }
        if(rg2.isChecked()) {
            if (locate.isEmpty()) {
                Toast.makeText(a, "Location is empty", Toast.LENGTH_SHORT).show();
                final TextView myAwesomeTextView = (TextView) getView().findViewById(R.id.error2);
                // myAwesomeTextView.setVisibility(View.VISIBLE);
                myAwesomeTextView.setText("Please enter mandatory field");
                validOkay = false;
            }
        }
       return validOkay;
    }
    private void successHogya() {
    }

    public void initializeKre(){
        newKey=key.getText().toString().trim();
        locate=locationNew.getText().toString().trim();
        //keyERROR.setVisibility(view.INVISIBLE);
        if(newKey.trim().equals("")){
            //keyERROR.setVisibility(view.INVISIBLE);
        }
        category=cat.getSelectedItem().toString().trim();
        if(diis.getText().toString().equals("") || diis.getText().toString().equals(null))
        {
            finalValue=16090;
        }
        else {
            finalValue = Integer.parseInt(diis.getText().toString());

            if (finalValue < 10) {
                finalValue = 16090;
            } else {
                finalValue = finalValue * 1609;
            }
        }
        dist=Integer.toString(finalValue);
                 //   rg = ((RadioGroup)view.findViewById(R.id.RadioGrp));
//                    rg1 = ((RadioButton)view.findViewById(R.id.Rad1));
//        rg2 = ((RadioButton)view.findViewById(R.id.Rad2));
//                    valRad =(RadioButton)view.findViewById(rg.getCheckedRadioButtonId());
//                    valRADki=valRad.getText().toString();
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner=(Spinner)view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(a,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        key =   ((EditText)view.findViewById(R.id.keyId));
        cat=  ((Spinner)view.findViewById(R.id.spinner));
        diis=  ((EditText)view.findViewById(R.id.distanceId));
        rg1 = ((RadioButton)view.findViewById(R.id.Rad1));
        rg2 = ((RadioButton)view.findViewById(R.id.Rad2));
        locationNew=((EditText)view.findViewById(R.id.typeLoc));
        final EditText edit;
        edit = (EditText) view.findViewById(R.id.typeLoc);
        rg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edit = (EditText) view.findViewById(R.id.typeLoc);
                edit.setEnabled(false);
                edit.setText("");
            }
        });

        rg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edit = (EditText) view.findViewById(R.id.typeLoc);
                edit.setEnabled(true);
            }
        });
        if(rg2.isChecked()){
            //edit = (EditText) view.findViewById(R.id.typeLoc);
            edit.setEnabled(true);
        }
        else{
            //edit = (EditText) view.findViewById(R.id.typeLoc);
            edit.setEnabled(false);
        }
        //setContentView(R.l);
        final Button button = ((Button)view.findViewById(R.id.searchBut));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBoy();
                if(newKey.isEmpty()){
                    return;
                }
                if(rg2.isChecked()) {
                    if (locate.isEmpty()) {
                        return;
                    }
                }
//                else if(newKey!=""){
//                    myAwesomeTextView.setText("");
//                }
                Log.d("this",newKey);
                RequestQueue queue = Volley.newRequestQueue(a);
                String url="";
                if(rg1.isChecked()) {
                    url = "http://10.0.2.2:8081/allData?key=" + newKey + "&distance=" + dist + "&lat=" + lat + "&lon=" + lon + "&catu=" + category + "&catuvalue=Default";
                    Log.d("hhhh", url);
                }
                else{
                    url="http://10.0.2.2:8081/locWala?location="+locate+"&key=usc&distance="+dist+"&catu="+category+"&catuValue=Default";
                    Log.d("url222222222", "onClick: "+url);
                }
                //Toast.makeText(a, (newKey), Toast.LENGTH_LONG).show();
                //Toast.makeText(a, (categ), Toast.LENGTH_LONG).show();
               // Toast.makeText(a, (dist), Toast.LENGTH_LONG).show();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                final ProgressDialog progressDialog = new ProgressDialog(a);
                                progressDialog.setMessage("Fetching Data");
                                progressDialog.show();
                                Log.d("res", "Response is: " + response.substring(0, 500));

                                if (rg2.isChecked()) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray arr = jsonObject.getJSONArray("results");
                                        Log.d("lattttttt", "onClick: " + arr);
                                        JSONObject b = arr.getJSONObject(0).getJSONObject("geometry");
                                        //JSONObject c = arr.getJSONObject(0).getJSONObject("geometry");
                                        JSONObject wee = b.getJSONObject("location");
                                        //wee.getJSONObject("lat");
                                        String newLat = wee.getString("lat");
                                        //c.getJSONObject("location");
                                        String newLng = wee.getString("lng");
                                        Log.d("latttttttnnnnnnngggg", "onClick: " + newLng);
                                        RequestQueue queue2 = Volley.newRequestQueue(a);
                                        String url2 = "http://10.0.2.2:8081/allData?key=" + newKey + "&distance=" + dist + "&lat=" + newLat + "&lon=" + newLng + "&catu=" + category + "&catuvalue=Default";

                                        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response2) {
                                                        Log.d("res", "Response22222 is: " + response2.substring(0, 500));
                                                        Bundle basket2 = new Bundle();
                                                        basket2.putString("abc", response2);
                                                        Intent intu = new Intent(a, MyOtherActivity.class);
                                                        intu.putExtras(basket2);
                                                        startActivity(intu);
                                                        progressDialog.dismiss();
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                 Log.d("res","That didn't work!");
                                            }
                                        });

// Add the request to the RequestQueue.
                                        queue2.add(stringRequest2);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (rg1.isChecked()) {// Toast.makeText(a, ("hielse"), Toast.LENGTH_LONG).show();
                                    Bundle basket = new Bundle();
                                    basket.putString("abc", response);
                                    Intent intu = new Intent(a, MyOtherActivity.class);
                                    intu.putExtras(basket);
                                    startActivity(intu);
                                    progressDialog.dismiss();
                                    //startActivity(new Intent(a, MyOtherActivity.class));
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(stringRequest);

            }
        });

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
