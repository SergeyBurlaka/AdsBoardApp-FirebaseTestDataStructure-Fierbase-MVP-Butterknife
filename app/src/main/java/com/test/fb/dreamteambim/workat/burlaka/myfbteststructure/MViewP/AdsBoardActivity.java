package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sdsmdg.tastytoast.TastyToast;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter.Presenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsBoardActivity extends AppCompatActivity implements IView {

    //DI using
    @BindView(R.id.post_lot_ActivityAdsBoard)
    EditText mMessage;

    @BindView(R.id.lotsListView)
    RecyclerView messagesView;

    //MVP using
    private IPresenter mainPresenter;

    //private FirebaseRecyclerAdapter<Lot, AdsHolder> mAdapter_rv;
    RecyclerView.Adapter mAdapter_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_board);

        //include DI
        ButterKnife.bind(this);

        /**
         *    include MVP
         *    Main activity does not know about firebase things.
         *    Main activity is only speaking with presenter,
         *    because life circle or user click some view.
         */
        mainPresenter = new Presenter();
        mainPresenter.setView(this);
        mainPresenter.onCreate();

        //include UI
        initLotsList ();
    }


    /**
     *  Method for MVP usage.
     *  This override method for managing by presenter, and
     *  creating list of product ads
     */
    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter rvAdapter) {

        //init recycler view list
        messagesView.setHasFixedSize(true);
        messagesView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter getting from firebase helper via presenter
        this.mAdapter_rv = rvAdapter;
        messagesView.setAdapter(mAdapter_rv);
    }

    public void onCLickAddLot(View view){
        mainPresenter.onClickAddLot();
    }

    public void onCLickQuery(View view) {
        mainPresenter.onClickQuery();
    }

    private void initLotsList(){
    }

    public void onSignOut (View view){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        startActivity(new Intent(AdsBoardActivity.this, SignInActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter_rv = null;
    }

    /**
     * Method for MVP usage
     *  This override methods for managing by presenter
     */
    @Override
    public String getLotsMessage() {
        return mMessage.getText().toString();
    }

    @Override
    public void cleanMessageField() {
        mMessage.setText("");
    }

    @Override
    public void showToast(String str) {
        TastyToast.makeText(this, str, TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }
}