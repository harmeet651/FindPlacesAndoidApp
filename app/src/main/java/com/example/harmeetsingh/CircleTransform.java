package com.example.harmeetsingh;

/**
 * Created by akash on 4/23/2018.
 */

import android.graphics.Bitmap;
import com.squareup.picasso.Transformation;
public class CircleTransform implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        return ImageUtils.getCircularBitmapImage(source);
    }
    @Override
    public String key() {
        return "circle-image";
    }
}