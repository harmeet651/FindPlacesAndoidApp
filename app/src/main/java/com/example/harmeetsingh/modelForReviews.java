package com.example.harmeetsingh;

public class modelForReviews {
    private String imgRev;
    private String nameAuthor;
    private String rate;
    private String time;
    private  String reviewItself;

    public modelForReviews(String profile_photo_url, String imgRev, String nameAuthor, String rate, String time) {
        this.imgRev = imgRev;
        this.nameAuthor = nameAuthor;
        this.rate = rate;
        this.time = time;
        this.reviewItself = reviewItself;
    }

    public String getImgRev() {
        return imgRev;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getRate() {
        return rate;
    }

    public String getTime() {
        return time;
    }

    public String getReviewItself() {
        return reviewItself;
    }
}
