package com.ta.solamo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ta.solamo.R;
import com.ta.solamo.model.CartModel;
import com.ta.solamo.temp.Temp;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {

    private Context context;
    private ArrayList<CartModel> arrayList;
    private String TAG = getClass().getSimpleName();

    public CartAdapter(Context context, ArrayList<CartModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CartAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_item_order, viewGroup, false);
        return new CartAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapterViewHolder holder, int i) {
        final CartModel current = arrayList.get(i);
        holder.textViewTittle.setText(String.valueOf(current.getCart_nameItem()));
        holder.textViewPrice.setText("Rp. "+String.valueOf(current.getCart_priceItem()));
        Glide.with(context)
                .load(current.getCart_image())
                .into(holder.imageViewThumb);
        holder.imageButtonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(current);
                Log.d(TAG, "onClick cart: " + Temp.cartModels.size());
                for (int i = 0; i < Temp.cartModels.size(); i++) {
                    Log.d(TAG, "onClick cart: " + Temp.cartModels.get(i).getCart_id());
                    Log.d(TAG, "onClick cart: " + Temp.cartModels.get(i).getCart_nameItem());
                    Log.d(TAG, "onClick cart: " + Temp.cartModels.get(i).getCart_priceItem());
                    Log.d(TAG, "onClick cart: " + Temp.cartModels.get(i).getCart_quantity());
                    Log.d(TAG, "onClick cart: " + Temp.cartModels.get(i).getCart_image());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CartAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewThumb;
        TextView textViewTittle, textViewPrice, textViewQTY;
        ImageButton imageButtonDel;

        CartAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumb = itemView.findViewById(R.id.img_thumbCart);
            textViewTittle = itemView.findViewById(R.id.tv_itemNameCart);
            textViewPrice = itemView.findViewById(R.id.tv_itemPriceCart);
            imageButtonDel = itemView.findViewById(R.id.btn_Delete);
        }
    }
}
