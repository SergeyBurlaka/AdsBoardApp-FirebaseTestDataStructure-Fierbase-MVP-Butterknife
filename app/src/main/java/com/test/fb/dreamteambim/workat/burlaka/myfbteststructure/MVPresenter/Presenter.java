package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter;

import android.support.v7.widget.RecyclerView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP.IView;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.FireBaseHelper;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.IModel;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.Lot;

/**
 * Created by Operator on 10.12.2016.
 */

public class Presenter implements IPresenter {

    //MVP
    IView mainView;
    IModel firebasehelper;

    public Presenter(){
        firebasehelper = new FireBaseHelper(this);
    }

    @Override
    public void setView(IView iView){
            this.mainView = iView;
        }

    @Override
    public void onCreate() {
        firebasehelper.initDataBase();
    }

    @Override
    public void onClickAddLot() {
        firebasehelper.addToBase(mainView.getLotsMessage());
        mainView.cleanMessageField();
    }

    @Override
    public void onClickQuery(){
        firebasehelper.onQuery();
    }


    //manage from list checkbox
    @Override
    public void userliked(Lot lot, String lotKey, Boolean isLiked) {
        // 12.12.2016
        // write selected in firebase
        firebasehelper.setCheckedItem (lot,lotKey,isLiked);
    }

    @Override
    public boolean isULiked(Lot lot) {
        // 12.12.2016
        // read selected from firebase
        return  firebasehelper.isItemChecked(lot);
    }

    @Override
    public void showToast(String check) {
    mainView.showToast(check);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return firebasehelper.getFirebaseAdapter();
    }

}
