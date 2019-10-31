package com.example.nasa.presentation.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nasa.AppDelegate;
import com.example.nasa.R;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.service.IAstronomyPictureService;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainFragment extends Fragment {

    @Inject
    IAstronomyPictureService mService;

    private TextView title;
    private TextView explanation;
    private ImageView astronomyPicture;

    static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        title = root.findViewById(R.id.title_text_view);
        explanation = root.findViewById(R.id.explanation_text_view);
        astronomyPicture = root.findViewById(R.id.astronomy_picture);

        AppDelegate injector = AppDelegate.getInjector();
        injector.getAppComponent().inject(this);
        mService.getAstronomyPicture("2019-10-30")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apodEntity -> {
                    Log.d("TAG", apodEntity.getExplanation());
                    fillView(apodEntity);
                }, throwable -> {
                    throwable.printStackTrace();
                });
        return root;
    }

    private void fillView(APODEntity apodEntity) {
        getActivity().setTitle(apodEntity.getDate());
        title.setText(apodEntity.getTitle());
        explanation.setText(apodEntity.getExplanation());
        Picasso.get().load(apodEntity.getUrl()).into(astronomyPicture);
    }
}
