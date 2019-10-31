package com.example.nasa.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nasa.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root_view, MainFragment.newInstance())
                .commit();
    }
}
