package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter.MyChildEventListener;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP.AdsHolder;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

import java.util.HashMap;
import java.util.Map;

import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.utils.Constants.ForFireBase.FB_CHILD_LOTS;
import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.utils.Constants.ForFireBase.FB_CHILD_USER_LOTS;
import static com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.utils.Constants.ForMyTags.MY_TAG;

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
    
    //MVP
    IPresenter mainPresenter;


    public FireBaseHelper(IPresenter presenter){
        this.mainPresenter = presenter;

    }

    @Override
    public void initDataBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        fbUser = mFirebaseAuth.getCurrentUser();
    }

    @Override
    public RecyclerView.Adapter getFirebaseAdapter() {

       RecyclerView.Adapter recyclerAdapter;

               recyclerAdapter = new FirebaseRecyclerAdapter<Lot,AdsHolder>(Lot.class, R.layout.rvitem_product, AdsHolder.class,getDataBaseRef()) {

            //onCheckBox click
            //selected product items
            private Map<String, Boolean> selectedItems = new HashMap<>();

            //each new changes in base immediately populates in recycler list
            @Override
            public void populateViewHolder( AdsHolder productItemViewHolder, Lot lot, int position) {

                productItemViewHolder.setTitle(lot.getText());
                productItemViewHolder.setEmail(lot.getOwner());
                productItemViewHolder.setIsLiked();

                //checkBox
                productItemViewHolder.setLikedUser(lot, mainPresenter);
            }};
        return recyclerAdapter;
    }

    @Override
    public void addToBase(String message ) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
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

        //VIEW in activity (MVP)
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
    public boolean isItemChecked(Lot lot) {
        // TODO: 12.12.2016

        /*Query query = mDatabase.child(FB_CHILD_USER_LOTS ).child(lot.getLotId())
                .orderByChild("/users/"+fbUser.getUid()).endAt(true);;
        if (query==null) return false;
        else return true;*/
        return false;
    }

    @Override
    public void onQuery() {
        on_qd_test_2();
    }


    public Query getDataBaseRef() {
        return mDatabase.child(FB_CHILD_LOTS);
    }
    
    private void on_qd_test_1() {

        Query phoneQuery = mDatabase.child(FB_CHILD_USER_LOTS ).orderByChild(fbUser.getUid()).equalTo(true);//.equalTo("anatoliy8827@gmail.com");
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Log.d(MY_TAG, "\n"+"singleSnapshot key = " + singleSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(MY_TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void on_qd_test_2() {

        Query lotsIdQuery = mDatabase.child(FB_CHILD_USER_LOTS ).orderByChild("/users/"+fbUser.getUid()).equalTo(true);//.equalTo("anatoliy8827@gmail.com");
        lotsIdQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //mainView.showToast("hi");
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
