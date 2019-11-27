package com.example.presentation.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @SuppressWarnings("unchecked cast")
    @BindingAdapter({"bind:loadImage", "bind:errorPlaceholder", "bind:progress"})
    public static void loadImage(ImageView imageView, ObservableField<String> urlImage, Drawable error, LiveData<Boolean> progress) {
        if (imageView.getDrawable() == null) {
            Picasso.get()
                    .load(urlImage.get())
                    .error(error)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            if (progress instanceof MutableLiveData) {
                                ((MutableLiveData) progress).setValue(false);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            if (progress instanceof MutableLiveData) {
                                ((MutableLiveData) progress).setValue(false);
                            }
                        }
                    });
        }
    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout(
            SwipeRefreshLayout layout,
            Boolean isLoading,
            SwipeRefreshLayout.OnRefreshListener listener
    ) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }
}
