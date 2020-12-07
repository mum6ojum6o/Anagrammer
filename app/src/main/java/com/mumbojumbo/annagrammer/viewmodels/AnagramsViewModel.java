package com.mumbojumbo.annagrammer.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mumbojumbo.annagrammer.FirebaseSyncService;

import java.util.List;

public class AnagramsViewModel extends ViewModel {
    LiveData<List<String>> anagrams;
    FirebaseSyncService firebaseSyncService;

    public AnagramsViewModel() {
        firebaseSyncService = FirebaseSyncService.getInstance();
        anagrams = firebaseSyncService.getAnagrams();
    }

    public LiveData<List<String>> getAnagrams(){
        return anagrams;
    }
}
