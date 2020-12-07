package com.mumbojumbo.annagrammer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.mumbojumbo.annagrammer.viewmodels.AnagramsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Observer <List<String>>{

    FirebaseSyncService syncService;
    List<String> anagrams;
    AnagramsViewModel anagramsViewModel;
    EditText editText;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syncService = FirebaseSyncService.getInstance();
        syncService.getAnagrams();
        setupViewModel();
        anagramsViewModel.getAnagrams().observe(this,this);
    }

    private void setupViewModel() {
        anagramsViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(AnagramsViewModel.class);
    }

    @Override
    public void onChanged(List<String> anagramsFromFireStore) {
        if(anagramsFromFireStore != null ){
            if(anagrams == null) anagrams = new ArrayList<>();
            anagrams.clear();
            anagrams.addAll(anagramsFromFireStore);
        }
    }
}