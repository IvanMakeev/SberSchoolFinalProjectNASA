package com.example.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.presentation.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    private static final String URL_KEY = "URL";
    private ZoomageView mImageView;

    public static ImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(URL_KEY, url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String url = getArguments().getString(URL_KEY);
            mImageView = view.findViewById(R.id.single_image);
            Picasso.get()
                    .load(url)
                    .error(R.drawable.ic_image_black_96dp)
                    .into(mImageView);
        }
    }
}
