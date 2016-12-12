package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter;

import android.support.v7.widget.RecyclerView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP.IView;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.Lot;

/**
 * Created by Operator on 10.12.2016.
 */
public interface IPresenter {
    void setView(IView iView);
    void onCreate();
    void onClickAddLot();
    void onClickQuery();
    void userliked(Lot lot, String lotKey, Boolean isLiked);
    void showToast(String check);
    void setAdapterTolist(RecyclerView.Adapter rvAdapter);
}
