package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Constants.ForMyTags.MY_TAG;

/**
 * Created by Operator on 10.12.2016.
 */

public class MyChildEventListener implements ChildEventListener {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(MY_TAG, "onChildAdded:" + dataSnapshot.getKey());
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d(MY_TAG, "onChildChanged:" + dataSnapshot.getKey());
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d(MY_TAG, "onChildRemoved:" + dataSnapshot.getKey());
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d(MY_TAG, "onChildMoved:" + dataSnapshot.getKey());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.w(MY_TAG, "postComments:onCancelled", databaseError.toException());
    }
}
