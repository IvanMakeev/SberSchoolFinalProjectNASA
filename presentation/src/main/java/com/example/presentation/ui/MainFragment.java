package com.example.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.domain.service.IAstronomyPictureService;
import com.example.presentation.AppDelegate;
import com.example.presentation.databinding.MainBinding;
import com.example.presentation.ui.viewmodel.MainViewModel;
import com.example.presentation.ui.viewmodel.MainViewModelFactory;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    private static final String POSITION = "position";

    @Inject
    IAstronomyPictureService mService;
    private MainViewModel mViewModel;
    private int mCurrentPositionPageAdapter;

    static MainFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainBinding binding = MainBinding.inflate(inflater, container, false);
        AppDelegate.getInjector().getAppComponent().inject(this);

        mCurrentPositionPageAdapter = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        MainViewModelFactory factory = new MainViewModelFactory(mService);
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        binding.setMainScreen(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.showInformation(mCurrentPositionPageAdapter);
    }
}
