package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model;

/**
 * Created by Operator on 12.12.2016.
 */

public interface IModel {
    void initDataBase();
    void onQuery();
    void addToBase(String message, String category);
    void setCheckedItem(Lot lot, String lotKey,Boolean isLiked);
}
