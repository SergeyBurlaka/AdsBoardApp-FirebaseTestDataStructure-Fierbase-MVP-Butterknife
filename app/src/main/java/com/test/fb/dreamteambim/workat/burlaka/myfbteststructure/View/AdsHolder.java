package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Presenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.Model.Lot;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Operator on 11.12.2016.
 */

public class AdsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title_itemProductList)
    TextView titleField;

    @BindView(R.id.email_itemProductList)
    TextView emailField;

    @BindView(R.id.checkbox_itemProductList)
    CheckBox checkbox;

    // TODO_+:
    // 13.12.2016 find category spinner
    @BindView(R.id.category_itemProductList)
    TextView category;
    //todo --------

    public AdsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setTitle(String name) {
        titleField.setText(name);
    }

    public void setEmail(String text) {
        emailField.setText(text);
    }

    public void setLikedUser (final Lot lot, final IPresenter presenter, final Set<String> likedLots){
        // TODO_+: 12.12.2016
        //check box set checked if key from lot exist
        checkbox.setChecked( likedLots.contains(lot.getLotId()));

        //on user like-click
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                //***************Lot lot, String lotKey, Boolean isLiked
             presenter.userliked(lot,lot.getLotId(),cb.isChecked());
            }
        });
    }

    public void setIsLiked() {
    }

    public void setCategory(String category){
        // TODO_+: 13.12.2016
        // create set category
        this.category.setText(category);
        //todo ------>
    }
}
