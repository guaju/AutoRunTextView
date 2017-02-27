package com.guaju.autoruntextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guaju.autoruntextview.view.AutoRunTextView;

public class MainActivity extends AppCompatActivity {
    AutoRunTextView artv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artv= (AutoRunTextView) findViewById(R.id.artv);
        artv.setAutoRunNum(100);
    }
}
