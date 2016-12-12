package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter.MyChildEventListener;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.View.AdsHolder;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Constants.ForFireBase.FB_CHILD_LOTS;
import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Constants.ForFireBase.FB_CHILD_USER_LOTS;
import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Constants.ForMyTags.MY_TAG;

/**
 * Created by Operator on 12.12.2016.
 */

public class FireBaseHelper implements IModel {

    //Firebase using
    MyChildEventListener childEventListener = new MyChildEventListener();
    
    //Firebase
    DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser fbUser;
    
    //include MVP
    IPresenter mainPresenter;

    public FireBaseHelper(IPresenter presenter){
        this.mainPresenter = presenter;
    }

    @Override
    public void initDataBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        fbUser = mFirebaseAuth.getCurrentUser();

        onStartGetDataToList();
    }

    private void onStartGetDataToList() {
            // TODO_+: 12.12.2016
            // create user liked query
            final Query lotsIdQuery = mDatabase.child(FB_CHILD_USER_LOTS ).orderByChild("/users/"+fbUser.getUid()).equalTo(true);//.equalTo("anatoliy8827@gmail.com");

            lotsIdQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                Set<String> likedLots = new HashSet<>();

                @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren())//get node with Product_ID
                {
                    likedLots.add(singleSnapshot.getKey());
                }
                    onCreateFireBaseAdapter(likedLots);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(MY_TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void onCreateFireBaseAdapter( Set<String> likedLots){
        RecyclerView.Adapter recyclerAdapter;
        recyclerAdapter = new MyFireBaseAdsAdapter(
                        Lot.class,
                        R.layout.rvitem_product,
                        AdsHolder.class,getDataBaseRef(),
                        likedLots,mainPresenter
                );
        mainPresenter.setAdapterTolist(recyclerAdapter);
    }

    @Override
    public void addToBase(String message ) {
        // Create new post at /user-posts/$userid/$postid
        // and at /posts/$postid simultaneously
        String lotId = mDatabase.child(FB_CHILD_LOTS).push().getKey();

        //create new lot
        //******************* String owner, String text, String uid, String lotId
        Lot lot = new Lot( fbUser.getEmail(), message,fbUser.getUid(), lotId );

        //turn to map
        Map<String, Object> lotValues = lot.toMapInfo();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/"+FB_CHILD_LOTS+"/" + lotId, lotValues);
        //childUpdates.put("/"+FB_CHILD_USER_LOTS+"/" + lotId +"/"+"message", mainView.getLotsMessage());
        //childUpdates.put("/"+FB_CHILD_USER_LOTS+"/" + lotId + "/"+"users"+"/"+ fbUser.getUid(), mainView.getTrueIfcheck());

        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public void setCheckedItem(Lot lot, String lotKey,Boolean isLiked) {
        mDatabase.child(FB_CHILD_LOTS).push().getKey();
        Map<String, Object> childUpdatesUliked = new HashMap<>();

        childUpdatesUliked.put("/"+FB_CHILD_USER_LOTS+"/" + lotKey +"/"+"message",lot.getText() );
        childUpdatesUliked.put("/"+FB_CHILD_USER_LOTS+"/" + lotKey + "/"+"users"+"/"+ fbUser.getUid(), isLiked);

        mDatabase.updateChildren(childUpdatesUliked);
    }

    @Override
    public void onQuery() {
        on_qd_test();
    }

    public Query getDataBaseRef() {
        return mDatabase.child(FB_CHILD_LOTS);
    }

    private void on_qd_test() {
        Query lotsIdQuery = mDatabase.child(FB_CHILD_USER_LOTS ).orderByChild("/users/"+fbUser.getUid()).equalTo(true);//.equalTo("anatoliy8827@gmail.com");
        lotsIdQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren())//get node ProductID
                {
                    DataSnapshot vMessageLots =  singleSnapshot.child("message");
                    Log.d(MY_TAG,  " message is " +  vMessageLots.getValue());
                   mainPresenter.showToast("" + vMessageLots.getValue()+"\n"+fbUser.getEmail());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(MY_TAG, "onCancelled", databaseError.toException());
            }
        });
    }
}
