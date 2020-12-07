package com.mumbojumbo.annagrammer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirebaseSyncService implements OnCompleteListener<DocumentSnapshot> {
    private static final String TAG = "FirebaseSyncService" ;
    private static FirebaseSyncService sInstance;
    private final FirebaseFirestore firebaseFirestore;
    private CollectionReference anagramsStore;
    private final DocumentReference docReference;
    private final MutableLiveData<List<String>> anagrams;

    public static FirebaseSyncService getInstance(){
        if(sInstance == null){
            sInstance = new FirebaseSyncService();
        }
        return sInstance;
    }

    private FirebaseSyncService() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        docReference = firebaseFirestore.collection("anagrams").document("anagrams");
        docReference.get().addOnCompleteListener(this);
        anagrams = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getAnagrams(){
        return anagrams;
    }

    @Override
    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        if(task.isSuccessful()){
            DocumentSnapshot documentSnapshot = task.getResult();
            if(documentSnapshot.exists()){
                Log.d(TAG,"DocumentSnapshot data: "+documentSnapshot.getData());
                List<String>anagrams = new ArrayList<>();
                for(String anagram: documentSnapshot.getData().keySet()){
                    anagrams.add(anagram);
                }
                this.anagrams.setValue(anagrams);
            }else{
                Log.d(TAG,"No such document ");
            }
        }else{
            Log.d(TAG,"Failed:", task.getException());
        }
    }
}
