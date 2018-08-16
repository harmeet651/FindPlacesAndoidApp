package com.example.harmeetsingh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akash on 4/22/2018.
 */

public class ReviewResultsAdapter extends RecyclerView.Adapter<ReviewResultsAdapter.ReviewResultViewHolder> {

    Context mContext;
    private ArrayList<ReviewResultItem> reviewresultlist;
    public ReviewResultsListener onClickListener;

    public interface ReviewResultsListener{
        void authorNameOnClick(View v, int position);

        void imageUrlOnClick(View v, int position);
    }

    @Override
    public ReviewResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.review_result_item,parent,false);
        return  new ReviewResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewResultViewHolder holder, int position) {
        ReviewResultItem currentItem = reviewresultlist.get(position);
        String userProfilePicture = currentItem.getImageUrl();
        String authorName = currentItem.getAuthorName();
        String reviewText = currentItem.getReviewText();
        String rating = currentItem.getRating();
        String datePosted = currentItem.getDatePosted();
        holder.mReviewText.setText(reviewText);
        holder.mReviewDate.setText(datePosted);
        holder.mAuthorRating.setRating(Float.parseFloat(rating));
        holder.mAuthorName.setText(authorName);
        if(userProfilePicture != null);
        {
            Picasso.with(mContext)
                    .load(userProfilePicture)
                    .transform(new CircleTransform())
                    .into(holder.mUserProfileImage);
        }

    }

    @Override
    public int getItemCount() {
        return reviewresultlist.size();
    }




    public  ReviewResultsAdapter(Context mContext, ArrayList<ReviewResultItem> list, ReviewResultsListener listener)
    {
        this.mContext = mContext;
        this.reviewresultlist = list;
        this.onClickListener = listener;
    }

    public class ReviewResultViewHolder extends RecyclerView.ViewHolder {
        public ImageView mUserProfileImage;
        public TextView mAuthorName;
        public RatingBar mAuthorRating;
        public TextView mReviewDate;
        public TextView mReviewText;


        public ReviewResultViewHolder(final View itemView) {
            super(itemView);
            mUserProfileImage = (ImageView) itemView.findViewById(R.id.profile_image);
            mAuthorName = (TextView) itemView.findViewById(R.id.review_author_name);
            mAuthorRating = (RatingBar) itemView.findViewById(R.id.rating_review_rating);
            mReviewDate = (TextView) itemView.findViewById(R.id.review_date);
            mReviewText = (TextView) itemView.findViewById(R.id.review_text_value);

            mAuthorName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.authorNameOnClick(v,getAdapterPosition());
                }
            });

            mUserProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.imageUrlOnClick(v,getAdapterPosition());
                }
            });

        }

    }

}
