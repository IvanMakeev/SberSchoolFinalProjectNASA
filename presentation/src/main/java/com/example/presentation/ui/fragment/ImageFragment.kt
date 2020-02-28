package com.example.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.jsibbold.zoomage.ZoomageView
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {
    companion object {
        private const val URL_KEY = "URL"
        @JvmStatic
        fun newInstance(url: String?): ImageFragment {
            val args = Bundle()
            args.putString(URL_KEY, url)
            val fragment = ImageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val url = it.getString(URL_KEY)
            val imageView: ZoomageView = view.findViewById(R.id.single_image)
            Picasso.get()
                    .load(url)
                    .error(R.drawable.ic_image_black_96dp)
                    .into(imageView)
        }
    }
}