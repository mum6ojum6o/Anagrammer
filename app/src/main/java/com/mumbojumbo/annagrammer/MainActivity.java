package com.mumbojumbo.annagrammer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FirebaseSyncService syncService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syncService = FirebaseSyncService.getInstance();
        syncService.getAnagrams();
    }
}