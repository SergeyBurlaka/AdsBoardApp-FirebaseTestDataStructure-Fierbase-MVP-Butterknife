package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.View.AdsHolder;

import java.util.Set;

/**
 * Created by Operator on 12.12.2016.
 */

public class MyFireBaseAdsAdapter extends FirebaseRecyclerAdapter <Lot,AdsHolder>{

    Set<String> likedLots;
    IPresenter mainPresenter;

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public MyFireBaseAdsAdapter(
            Class modelClass,
            int modelLayout,
            Class viewHolderClass,
            Query ref
    ){
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    public MyFireBaseAdsAdapter(
            Class modelClass,
            int modelLayout,
            Class viewHolderClass,
            Query ref,
            Set<String> likedLots,
            IPresenter mainPresenter
    ){
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.likedLots = likedLots;
        this.mainPresenter = mainPresenter;
    }

    @Override
    protected void populateViewHolder(AdsHolder productItemViewHolder, Lot lot, int position) {
        productItemViewHolder.setTitle(lot.getText());
        productItemViewHolder.setEmail(lot.getOwner());
        productItemViewHolder.setCategory(lot.getCategory());
        //checkBox liked products
        productItemViewHolder.setLikedUser(lot, mainPresenter, likedLots);
    }
}
