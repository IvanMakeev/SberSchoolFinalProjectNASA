package com.example.presentation.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.presentation.R;

public class SplashActivity extends AppCompatActivity {

    private static final float MIN_OFFSET = 0.5f;
    private static final float MAX_OFFSET = 1f;
    private static final int MILLIS = 2000;
    private static final int REPEAT_COUNT = 1;
    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private ObjectAnimator scaleXAnimator;
    private ObjectAnimator scaleYAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.nasa_logo);
        scaleXAnimator = ObjectAnimator.ofFloat(imageView, SCALE_X, MIN_OFFSET, MAX_OFFSET);
        scaleYAnimator = ObjectAnimator.ofFloat(imageView, SCALE_Y, MIN_OFFSET, MAX_OFFSET);

        configure(scaleXAnimator).start();
        configure(scaleYAnimator).start();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, MILLIS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        scaleXAnimator.cancel();
        scaleYAnimator.cancel();
    }

    /**
     * Конфигурируем анимация
     *
     * @return сконфигурированный аниматор
     */
    private Animator configure(ObjectAnimator animator) {
        animator.setDuration(MILLIS);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(REPEAT_COUNT);
        return animator;

    }

    /**
     * В методе сначала убирается заголовок, потом делается полноэкранный режим
     */
    private void makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
