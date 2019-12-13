package com.example.presentation.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class CustomBindingAdapter {

    /**
     * @param imageView экземпляр текущего ImageView
     * @param urlImage  url для скачивания картинки
     * @param error     заглушка на случай возникновения ошибок, при загрузке картинки
     * @param progress  состояние progress bar'а, используется что бы задать Visibility=GONE,
     *                  на случай успеха или ошибки скачивания
     */
    @SuppressWarnings("unchecked cast")
    @BindingAdapter({"bind:loadImage", "bind:errorPlaceholder", "bind:progress"})
    public static void loadImage(@NotNull ImageView imageView,
                                 @NotNull ObservableField<String> urlImage,
                                 @NotNull Drawable error,
                                 @NotNull LiveData<Boolean> progress) {
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
        } else {
            if (progress instanceof MutableLiveData) {
                ((MutableLiveData) progress).setValue(false);
            }
        }
    }

    /**
     * @param layout    экземпляр для работы со свайпом
     * @param isLoading состояние загрузки данных на экране
     * @param listener  экземпляр слушателя для обновления данных
     */
    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout(
            @NotNull SwipeRefreshLayout layout,
            @NotNull Boolean isLoading,
            @NotNull SwipeRefreshLayout.OnRefreshListener listener
    ) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }

    /**
     * @param imageView     экземпляр ImageView
     * @param clickListener слушатель для ImageView запускающий новый фрагмент с ImageView
     */

    @BindingAdapter({"bind:clickListener"})
    public static void configureClickListener(
            @NotNull ImageView imageView,
            @NotNull View.OnClickListener clickListener
    ) {
        imageView.setOnClickListener(clickListener);
    }
}
