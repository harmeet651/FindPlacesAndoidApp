package com.example.harmeetsingh;

/**
 * Created by akash on 4/22/2018.
 */

public class ReviewResultItem {
    String imageUrl;
    String authorUrl;
    String reviewText;
    String authorName;
    String rating;
    String datePosted;

    public ReviewResultItem()
    {

    }
    public void resetParams()
    {
        imageUrl = null;
        authorUrl = null;
        reviewText = null;
        authorName = null;
        rating = null;
        datePosted = null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
