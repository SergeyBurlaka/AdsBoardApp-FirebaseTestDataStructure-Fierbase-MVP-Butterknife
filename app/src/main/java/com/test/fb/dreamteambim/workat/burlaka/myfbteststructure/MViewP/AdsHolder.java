package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MVPresenter.IPresenter;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP.Lot;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

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

    @BindView(R.id.price_itemProductList)
    TextView priceProduct;

    @BindView(R.id.checkbox_itemProductList)
    CheckBox checkbox;


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

    public void setLikedUser (final Lot lot, final IPresenter presenter){
        //presenter.showToast(lot.getLotId());

        //checkbox.setTag(lot.getLotId());

        //checkbox.setChecked(presenter.isULiked(lot));


         checkbox.setChecked(false);


        //on user like-click
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox cb = (CheckBox) v;
               // String lotId = (String) cb.getTag();

                /*presenter.showToast(
                        "Product key "+lot.getLotId()+
                                "\n" +"is check" +cb.isChecked());*/

                //***************Lot lot, String lotKey, Boolean isLiked
             presenter.userliked(lot,lot.getLotId(),cb.isChecked());

            }
        });
    }


    public void setIsLiked() {

    }
}
