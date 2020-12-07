package com.mumbojumbo.annagrammer.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mumbojumbo.annagrammer.FirebaseSyncService;
import com.mumbojumbo.annagrammer.businesslogic.SearchAnagrams;

import java.util.List;

public class AnagramsViewModel extends ViewModel {
    LiveData<List<String>> words; //contains list of words in the database.
    LiveData<List<String>> anagrams; //contains list of words in the database.

    FirebaseSyncService firebaseSyncService;

    public AnagramsViewModel() {
        firebaseSyncService = FirebaseSyncService.getInstance();
        words = firebaseSyncService.getAnagrams();


    }

    public LiveData<List<String>> getWords(){
        return words;
    }

    public LiveData<List<String>> getAnagrams(String searchWord, List<String> searchSpace){
        anagrams = new SearchAnagrams().getAnagrams(searchWord,searchSpace);
        return anagrams;
    }
}
