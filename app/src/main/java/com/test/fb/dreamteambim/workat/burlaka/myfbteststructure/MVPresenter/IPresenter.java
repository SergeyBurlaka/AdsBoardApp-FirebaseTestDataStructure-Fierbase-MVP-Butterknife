package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter;

import android.support.v7.widget.RecyclerView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP.IView;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.Lot;

/**
 * Created by Operator on 10.12.2016.
 */

public interface IPresenter {

    void setView(IView iView);

    //main activity does not know about firebase things;
    //main activity is only speaking with presenter
    // because life circle or user click some view
    void onCreate();

    void onClickAddLot();

    void onClickQuery();

    void userliked(Lot lot, String lotKey, Boolean isLiked);

    boolean isULiked(Lot lot);

    void showToast(String check);

    //FirebaseRecyclerAdapter<Lot,AdsHolder> getAdapter();

    RecyclerView.Adapter getAdapter();

}
