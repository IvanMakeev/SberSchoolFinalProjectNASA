package com.example.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.domain.interactor.IAstronomyPictureInteractor
import com.example.presentation.AppDelegate.Companion.injector
import com.example.presentation.R
import com.example.presentation.databinding.MainBinding
import com.example.presentation.ui.ZoomClickListener
import com.example.presentation.ui.viewmodel.MainViewModel
import com.example.presentation.ui.viewmodel.MainViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.concurrent.Executors
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        private const val POSITION = "position"
        private const val JPG = ".jpg"
        private const val PNG = ".png"
        @JvmStatic
        fun newInstance(position: Int): MainFragment {
            val args = Bundle()
            args.putInt(POSITION, position)
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mInteractor: IAstronomyPictureInteractor
    private lateinit var mViewModel: MainViewModel
    private lateinit var mYouTubePlayerView: YouTubePlayerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MainBinding.inflate(inflater, container, false)
        injector.getAppComponent().inject(this)
        val currentPositionPageAdapter = arguments?.getInt(POSITION) ?: 0
        val factory = MainViewModelFactory(mInteractor, currentPositionPageAdapter)
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mViewModel.clickListener = View.OnClickListener {
            val url = mViewModel.urlPicture.get()
            if (url != null && (url.endsWith(JPG) || url.endsWith(PNG))) {
                (requireActivity() as ZoomClickListener).onZoomImage(url)
            }
        }
        initYouTubePlayer(binding.root)
        binding.mainScreen = mViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel.showInformation()
    }

    override fun onDestroy() {
        super.onDestroy()
        mYouTubePlayerView.release()
    }

    private fun initYouTubePlayer(view: View) {
        mYouTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(mYouTubePlayerView)
        mYouTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = mViewModel.urlPicture.get()
                videoId?.let {
                    Executors.newSingleThreadExecutor().submit { youTubePlayer.cueVideo(videoId, 0f) }
                }
            }
        })
    }
}