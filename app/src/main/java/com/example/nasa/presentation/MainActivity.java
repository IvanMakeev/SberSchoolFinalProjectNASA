package com.example.nasa.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.Log;

import com.example.nasa.AppDelegate;
import com.example.nasa.R;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.service.IAstronomyPictureService;
import com.example.nasa.presentation.di.AppComponent;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Inject
    IAstronomyPictureService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDelegate injector = AppDelegate.getInjector();
        injector.getAppComponent().inject(this);
        mService.getAstronomyPicture("2019-10-27")
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(apodJson -> {
            Log.d("TAG", apodJson.getExplanation());
        }, throwable -> {

        });
    }

//    FragmentPagerAdapter
}
