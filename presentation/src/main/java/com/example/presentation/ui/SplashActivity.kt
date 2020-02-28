package com.example.presentation.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val MIN_OFFSET = 0.5f
        private const val MAX_OFFSET = 1f
        private const val MILLIS = 2000
        private const val REPEAT_COUNT = 0
    }

    private lateinit var scaleXAnimator: ObjectAnimator
    private lateinit var scaleYAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        setContentView(R.layout.activity_splash)
        val imageView = findViewById<ImageView>(R.id.nasa_logo)
        scaleXAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_X, MIN_OFFSET, MAX_OFFSET)
        scaleYAnimator = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, MIN_OFFSET, MAX_OFFSET)
        configure(scaleXAnimator).start()
        configure(scaleYAnimator).start()
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, MILLIS.toLong())
    }

    override fun onStop() {
        super.onStop()
        scaleXAnimator.cancel()
        scaleYAnimator.cancel()
    }

    /**
     * Конфигурируется анимация
     *
     * @return сконфигурированный Animator
     */
    private fun configure(animator: ObjectAnimator): Animator {
        animator.duration = MILLIS.toLong()
        animator.repeatCount = REPEAT_COUNT
        return animator
    }

    /**
     * В методе сначала убирается заголовок, потом делается полноэкранный режим
     */
    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}