package com.mumbojumbo.annagrammer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mumbojumbo.annagrammer.viewmodels.AnagramsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Observer <List<String>>, View.OnClickListener{

    FirebaseSyncService syncService;
    List<String> words;
    AnagramsViewModel anagramsViewModel;
    EditText searchWordEditText;
    RecyclerView recyclerView;
    Button searchButton;
    TextView searchTitletextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syncService = FirebaseSyncService.getInstance();
        syncService.getAnagrams();
        searchButton = (Button) findViewById(R.id.searchButton);
        searchWordEditText = (EditText)findViewById(R.id.searchWord);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        searchTitletextView = (TextView)findViewById(R.id.textView);
        searchButton.setOnClickListener(this);

        setupViewModel();
        anagramsViewModel.getWords().observe(this,this);

    }

    private void setupViewModel() {
        anagramsViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(AnagramsViewModel.class);
    }

    @Override
    public void onChanged(List<String> anagramsFromFireStore) {
        if(anagramsFromFireStore != null ){
            if(words == null) words = new ArrayList<>();
            words.clear();
            words.addAll(anagramsFromFireStore);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchButton:
                validateAndInitiateAnagramSearch();
                break;
        }
    }

    private void validateAndInitiateAnagramSearch() {
        if(searchWordEditText.getText() != null && searchWordEditText.getText().toString().length() >0){
            anagramsViewModel.getAnagrams(searchWordEditText.getText().toString(), words).observe(this, new Observer<List<String>>() {
                @Override
                public void onChanged(List<String> strings) {
                    //push to recyclerview.
                    searchTitletextView.append(" "+strings.size()+" anagrams found!");
                }
            });
        }
    }
}