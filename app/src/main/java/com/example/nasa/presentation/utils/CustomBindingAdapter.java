package com.example.nasa.presentation.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:loadImage", "bind:placeholder"})
    public static void loadImage(ImageView imageView, String urlImage, Drawable placeholder) {
        Picasso.get().load(urlImage).error(placeholder).into(imageView);
    }
}
