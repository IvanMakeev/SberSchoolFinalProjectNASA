package com.example.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.presentation.AppDelegate;
import com.example.presentation.R;
import com.example.presentation.databinding.MainBinding;
import com.example.presentation.ui.ZoomClickListener;
import com.example.presentation.ui.viewmodel.MainViewModel;
import com.example.presentation.ui.viewmodel.MainViewModelFactory;
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    private static final String POSITION = "position";

    @Inject
    IAstronomyPictureInteractor mInteractor;
    @Inject
    IBaseSchedulerProvider mSchedulerProvider;
    private MainViewModel mViewModel;

    public static MainFragment newInstance(int position) {
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

        int currentPositionPageAdapter = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        MainViewModelFactory factory = new MainViewModelFactory(mInteractor, mSchedulerProvider, currentPositionPageAdapter);
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        mViewModel.setClickListener(v -> {
            String url = mViewModel.getUrlPicture().get();
            ((ZoomClickListener) requireActivity()).onZoomImage(url);
        });

        initYouTubePlayer(binding.getRoot());

        binding.setMainScreen(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.showInformation();
    }

    private void initYouTubePlayer(View view) {
        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = mViewModel.getUrlPicture().get();
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0f);
                    youTubePlayer.pause();
                }
            }
        });
    }
}
