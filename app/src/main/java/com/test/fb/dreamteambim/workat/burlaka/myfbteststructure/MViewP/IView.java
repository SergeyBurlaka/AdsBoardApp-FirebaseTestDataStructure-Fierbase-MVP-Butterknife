package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Operator on 10.12.2016.
 */

public interface IView {
    String getLotsMessage();
    void cleanMessageField();
    void showToast(String str);
    void setRecyclerViewAdapter(RecyclerView.Adapter rvAdapter);
}
