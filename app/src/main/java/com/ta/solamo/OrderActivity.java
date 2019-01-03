package com.ta.solamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ta.solamo.adapter.CartAdapter;
import com.ta.solamo.temp.Temp;

public class OrderActivity extends AppCompatActivity{

    RecyclerView rvCart;
    TextView tvTotalPriceCart;
    AppCompatButton btnProcessCart;
    ImageButton btnBack;

    CartAdapter adapter;

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rvCart = findViewById(R.id.rv_cart);
        tvTotalPriceCart = findViewById(R.id.tv_tPrice);
        btnProcessCart = findViewById(R.id.btn_processCart);
        btnBack = findViewById(R.id.btn_back);

        adapter = new CartAdapter(this, Temp.cartModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter.notifyDataSetChanged();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

        // hitung total price item
        /*for (int i = 0; i < Temp.cartModels.size(); i++) {
            Temp.total_price += Temp.cartModels.get(i).getCart_priceItem();
        }
        tvTotalPriceCart.setText(String.valueOf(Temp.total_price));*/
        for (int i = 0; i < Temp.cartModels.size(); i++) {
            Temp.total_price += Temp.cartModels.get(i).getCart_priceItem();
            Log.d(TAG, "onCreate: Price"+Temp.total_price);
            tvTotalPriceCart.setText("Rp. "+String.valueOf(Temp.total_price));
        }

        btnProcessCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(OrderActivity.this, "size == + " + i, Toast.LENGTH_LONG).show();
                if (Temp.cartModels.size() != 0){
                    Log.d(TAG, "onClick: != " + Temp.cartModels.size());
                }else{
                    Log.d(TAG, "onClick: nothing ==");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
