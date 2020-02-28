package com.example.presentation.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object CustomBindingAdapter {
    /**
     * @param imageView экземпляр текущего ImageView
     * @param urlImage  url для скачивания картинки
     * @param error     заглушка на случай возникновения ошибок, при загрузке картинки
     * @param progress  состояние progress bar'а, используется что бы задать Visibility=GONE,
     * на случай успеха или ошибки скачивания
     */
    @JvmStatic
    @BindingAdapter("bind:loadImage", "bind:errorPlaceholder", "bind:progress")
    fun loadImage(imageView: ImageView,
                  urlImage: ObservableField<String?>,
                  error: Drawable,
                  progress: LiveData<Boolean?>) {

        imageView.drawable?.let {
            if (progress is MutableLiveData) {
                progress.value = false
            }
            return
        }

        Picasso.get()
                .load(urlImage.get())
                .error(error)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        if (progress is MutableLiveData) {
                            progress.value = false
                        }
                    }

                    override fun onError(e: Exception) {
                        if (progress is MutableLiveData) {
                            progress.value = false
                        }
                    }
                })
    }

    /**
     * @param layout    экземпляр для работы со свайпом
     * @param isLoading состояние загрузки данных на экране
     * @param listener  экземпляр слушателя для обновления данных
     */
    @JvmStatic
    @BindingAdapter("bind:refreshState", "bind:onRefresh")
    fun configureSwipeRefreshLayout(
            layout: SwipeRefreshLayout,
            isLoading: Boolean,
            listener: OnRefreshListener
    ) {
        layout.setOnRefreshListener(listener)
        layout.post { layout.isRefreshing = isLoading }
    }
}