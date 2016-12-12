package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter;

import android.support.v7.widget.RecyclerView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.View.IView;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Lot;

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

    String getCategory();
}
