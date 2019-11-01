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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainFragment extends Fragment {

    private static final String POSITION = "position";

    @Inject
    IAstronomyPictureService mService;

    private TextView mTitle;
    private TextView mExplanation;
    private ImageView mAstronomyPicture;
    private TextView mCopyright;


    public static MainFragment newInstance(@NonNull int position) {

        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mTitle = root.findViewById(R.id.title_text_view);
        mExplanation = root.findViewById(R.id.explanation_text_view);
        mAstronomyPicture = root.findViewById(R.id.astronomy_picture);
        mCopyright = root.findViewById(R.id.copyright_text_view);
        AppDelegate.getInjector().getAppComponent().inject(this);

        getInformation(getDateOffset());
        return root;
    }

    @NonNull
    private String getDateOffset() {
        int currentPosition = getArguments() != null ? getArguments().getInt(POSITION) : 0;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -currentPosition);
        Date dateWithOffset = calendar.getTime();
        return formattingDate(dateWithOffset);
    }

    @NonNull
    private String formattingDate(@NonNull Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }


    private void getInformation(@NonNull String date) {
        mService.getAstronomyPicture(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apodEntity -> fillView(apodEntity),
                        throwable -> {
                    throwable.printStackTrace();
                });
    }

    private void fillView(@NonNull APODEntity apodEntity) {
        getActivity().setTitle(apodEntity.getDate());
        mTitle.setText(apodEntity.getTitle());
        mExplanation.setText(apodEntity.getExplanation());
        Picasso.get().load(apodEntity.getUrl()).error(R.drawable.ic_image_black_96dp).into(mAstronomyPicture);
        mCopyright.setText(
                String.format(
                        getString(R.string.copyright),
                        apodEntity.getCopyright() != null ? apodEntity.getCopyright() : getString(R.string.free_access)));
    }

}
