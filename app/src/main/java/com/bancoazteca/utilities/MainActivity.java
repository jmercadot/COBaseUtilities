package com.bancoazteca.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bancoazteca.cobaseutilities.baseutils.COBaseActivity;

public class MainActivity extends COBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress(true);
    }
}