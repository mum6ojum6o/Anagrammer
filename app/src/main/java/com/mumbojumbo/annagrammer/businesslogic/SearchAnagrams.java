package com.mumbojumbo.annagrammer.businesslogic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAnagrams {
    MutableLiveData<List<String>> matchedAnagrams;

    public SearchAnagrams() {
        this.matchedAnagrams = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getAnagrams(String searchWord, List<String> searchSpace){
        List<String> anagrams = new ArrayList<>();

        for(String candidateAnagram:searchSpace){
            if(isAnagram(searchWord,candidateAnagram)){
                anagrams.add(candidateAnagram);
            }
        }
        matchedAnagrams.postValue(anagrams);
        return matchedAnagrams;
    }

    public boolean isAnagram(String searchWord, String candidate){
        if(candidate.length() != searchWord.length()) return false;
        Map<Character,Integer> map = new HashMap<>();
        for(char c:candidate.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int charFound = 0;
        char[] arr = searchWord.toCharArray();
        int i=0;
        for(;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                //map.clear();
                break;
            }else{
                int charCount = map.get(arr[i]);
                if(charCount>0){
                    map.put(arr[i],charCount-1);
                    charFound++;
                }else{
                    break;
                }
            }
        }
        return searchWord.length()==arr.length && i == arr.length;
    }
}
