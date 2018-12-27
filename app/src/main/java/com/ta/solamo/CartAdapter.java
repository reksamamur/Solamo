package com.ta.solamo;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
        holder.textViewPrice.setText(String.valueOf(current.getCart_priceItem()));
        Glide.with(context)
                .load(current.getCart_image())
                .into(holder.imageViewThumb);

        holder.textViewQTY.setText(String.valueOf(current.getCart_quantity()));

        holder.imageButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        holder.imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CartAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewThumb;
        TextView textViewTittle, textViewPrice, textViewQTY;
        ImageButton imageButtonMin, imageButtonPlus;

        public CartAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumb = itemView.findViewById(R.id.img_thumbCart);
            textViewTittle = itemView.findViewById(R.id.tv_itemNameCart);
            textViewPrice = itemView.findViewById(R.id.tv_itemPriceCart);
            textViewQTY = itemView.findViewById(R.id.tv_quantityCart);
            imageButtonMin = itemView.findViewById(R.id.btn_minCart);
            imageButtonPlus = itemView.findViewById(R.id.btn_plusCart);
        }
    }
}
