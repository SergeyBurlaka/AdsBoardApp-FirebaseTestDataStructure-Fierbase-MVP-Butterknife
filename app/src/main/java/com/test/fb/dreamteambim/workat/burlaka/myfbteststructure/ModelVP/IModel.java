package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Operator on 12.12.2016.
 */

public interface IModel {
    void initDataBase();

    void onQuery();

    void addToBase(String message);

    void setCheckedItem(Lot lot, String lotKey,Boolean isLiked);

    boolean isItemChecked(Lot lot);

    RecyclerView.Adapter getFirebaseAdapter();

}
