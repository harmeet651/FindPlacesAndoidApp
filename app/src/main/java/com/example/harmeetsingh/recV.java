package com.example.harmeetsingh;

public class recV {
    private String head;
    private String desc;
    private String imageUrl;
    private String place_id;

    public recV(String head, String desc, String imageUrl, String place_id) {
        this.head = head;
        this.desc = desc;
        this.imageUrl=imageUrl;
        this.place_id=place_id;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPlace_id() {
        return place_id;
    }

}
