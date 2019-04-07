package com.ta.solamo.bottomsheetdialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ta.solamo.MainActivity;
import com.ta.solamo.MenuActivity;
import com.ta.solamo.OrderActivity;
import com.ta.solamo.R;
import com.ta.solamo.model.CartModel;
import com.ta.solamo.temp.Temp;

import java.util.ArrayList;

public class BottomSheetDialogMenuDetail extends BottomSheetDialogFragment {

    String TAG = getClass().getSimpleName();
    int quantity = 0;

    TextView textViewQty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet_menu, container, false);

        ImageView imageViewThumb = v.findViewById(R.id.img_thumbDetail);
        TextView textViewTittle = v.findViewById(R.id.tv_tittleDetail);
        TextView textViewPrice = v.findViewById(R.id.tv_priceDetail);
        textViewQty = v.findViewById(R.id.tv_QtyDetail);
        Button btnRemove = v.findViewById(R.id.btn_removeQtyDetail);
        final Button btnAdd = v.findViewById(R.id.btn_addQtyDetail);
        ImageButton imageButtonDismiss = v.findViewById(R.id.btn_closeDetail);

        AppCompatButton appCompatButtonBuy = v.findViewById(R.id.btn_buyDetail);

        Bundle bundle = getArguments();
        final String tittle = bundle.getString("posisiTittle");
        final String image = bundle.getString("posisiImage");
        final int price = bundle.getInt("posisiPrice");

        textViewTittle.setText(String.valueOf(tittle));
        textViewPrice.setText("Rp. " + String.valueOf(price));
        //imageViewThumb.setImageDrawable();

        Glide.with(getContext())
                .load(image)
                .into(imageViewThumb);

        imageButtonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                textViewQty.setText(String.valueOf(quantity));
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity > 0)
                {
                    quantity--;
                    textViewQty.setText(String.valueOf(quantity));
                }


            }
        });

        for(int i=0;i<Temp.cartModels.size();i++)
        {
            if(tittle.equals(Temp.cartModels.get(i).getCart_nameItem()))
            {
                if(Temp.cartModels.get(i).getCart_quantity() > 0)
                {
                    quantity = Temp.cartModels.get(i).getCart_quantity();
                    textViewQty.setText(String.valueOf(Temp.cartModels.get(i).getCart_quantity()));
                }
            }
        }

        appCompatButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean sama = false;

                CartModel models = new CartModel();

                models.setCart_id(++Temp.count_id);
                models.setCart_nameItem(tittle);
                models.setCart_priceItem(price);
                models.setCart_quantity(quantity);
                models.setCart_image(image);

                for(int i=0;i<Temp.cartModels.size();i++)
                {
                    if(models.getCart_nameItem().equals(Temp.cartModels.get(i).getCart_nameItem()))
                    {
                        sama = true;
                        Temp.cartModels.get(i).setCart_quantity(quantity);
                        if(quantity == 0)
                        {
                            Temp.cartModels.remove(i);
                        }
                    }
                }

                if(!sama)
                {
                    if(quantity == 0)
                    {
                        Toast.makeText(getActivity(), "Minimum Quantity 1", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Temp.cartModels.add(new CartModel(++Temp.count_id, tittle, price, quantity, image));
                        Snackbar snackbar = Snackbar
                                .make(getActivity().findViewById(android.R.id.content), "Added to Cart", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        dismiss();
                    }
                }
                else
                {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), "Cart updated", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    dismiss();
                }

            }
        });

        return v;
    }

    public interface BottomSheetListenerDetail {
        void onBottomSheetClickedDetail();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            BottomSheetListenerDetail bottomSheetListener = (BottomSheetListenerDetail) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Implemet BottomSheetListener interface");
        }
    }
}
