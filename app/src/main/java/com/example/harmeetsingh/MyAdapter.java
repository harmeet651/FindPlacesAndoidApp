package com.example.harmeetsingh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<recV> listItems;
    private Context context;
    String myString;

    public MyAdapter(List<recV> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v,context, (ArrayList<recV>) listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final recV listItem=listItems.get(position);

        holder.heading.setText(listItem.getHead());
        holder.desc.setText(listItem.getDesc());
//        holder.place_id.setText(listItem.getPlace_id());
        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView heading;
        public TextView desc;
        public ImageView icon;
        public TextView place_id;
        public LinearLayout linearLayout;
        ArrayList<recV>recVArrayList=new ArrayList<recV>();
        Context context;
        public ViewHolder(View itemView, Context context, ArrayList<recV> recVArrayList) {
            super(itemView);
            this.recVArrayList=recVArrayList;
            this.context=context;
            itemView.setOnClickListener(this);
            heading=(TextView) itemView.findViewById(R.id.heading);
            desc=(TextView) itemView.findViewById(R.id.desc);
            icon=(ImageView) itemView.findViewById(R.id.icon);
            //place_id=(TextView) itemView.findViewById(R.id.place_id);
        }
        @Override
        public void onClick(View v){
            int position=getAdapterPosition();
            recV newObj=this.recVArrayList.get(position);
//            TextView place_id=(TextView) findViewById(R.id.place_id);
//            String place=place_id.getText().toString();
//            Toast.makeText(this,place, Toast.LENGTH_LONG).show();

//            Bundle bundle2 = new Bundle();
//            bundle2.putString("edttext", String.valueOf(newObj.getPlace_id()));
//            photoTab fragobj = new photoTab();
//            fragobj.setArguments(bundle2);
//            Log.d("this is myada2", "onClick: "+newObj.getPlace_id());

            RequestQueue queue = Volley.newRequestQueue(this.context);
            String url ="http://10.0.2.2:8081/final?placeIdio="+newObj.getPlace_id();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            myString=response;
                            Bundle basket= new Bundle();
                            basket.putString("sdsd", response);
                            Intent bhai=new Intent(context,infoSingh.class);
                            bhai.putExtras(basket);
                            //Toast.makeText(this,newObj.getPlace_id(), Toast.LENGTH_LONG).show();
                            Log.d("jjjj", "onResponse: " + myString);
                            context.startActivity(bhai);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mTextView.setText("That didn't work!");
                }
            });
            queue.add(stringRequest);
        }

        }
}
