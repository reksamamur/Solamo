package com.ta.solamo;

import android.content.Intent;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rvCart;
    TextView tvTotalPriceCart;
    AppCompatButton btnProcessCart;

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rvCart = findViewById(R.id.rv_cart);
        tvTotalPriceCart = findViewById(R.id.tv_tPrice);
        btnProcessCart = findViewById(R.id.btn_processCart);

        adapter = new CartAdapter(this, Temp.cartModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

        for (int i = 0; i < Temp.cartModels.size(); i++) {
            Temp.total_price += Temp.cartModels.get(i).getCart_priceItem();
        }

        int t_price = Temp.total_price;

        Bundle bundle = new Bundle();
        bundle.putInt("total_price_TAG", t_price);

        tvTotalPriceCart.setText(String.valueOf(t_price));

        btnProcessCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, DataUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
