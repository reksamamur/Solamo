package com.ta.solamo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ta.solamo.adapter.CartAdapter;
import com.ta.solamo.temp.Temp;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rvCart;
    TextView tvTotalPriceCart;
    AppCompatButton btnProcessCart;
    ImageButton btnBack;

    LinearLayout imgEmpty;
    LinearLayout linearLayoutProcced;
    Button btnBSMT;
    AppBarLayout appBarLayout;

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

        imgEmpty = findViewById(R.id.linearEmpty);
        linearLayoutProcced = findViewById(R.id.linearbottom);
        btnBSMT = findViewById(R.id.btn_buySMT);
        appBarLayout = findViewById(R.id.appBarTopCart);

        adapter = new CartAdapter(this, Temp.cartModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter.notifyDataSetChanged();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

        btnProcessCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(OrderActivity.this, "size == + " + i, Toast.LENGTH_LONG).show();
                if (Temp.cartModels.size() != 0) {
                    Intent intent = new Intent(getApplicationContext(), DataUserActivity.class);
                    startActivity(intent);
                } else {
                    showEmptyDialog();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // hitung total price item
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initEmpty();
                                getTotalPrice();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public int getTotalPrice() {
        int totalCost = 0;
        for (int i = 0; i < Temp.cartModels.size(); i++) {
            totalCost += Temp.cartModels.get(i).getCart_priceItem() * Temp.cartModels.get(i).getCart_quantity();
            Temp.total_price = totalCost;
            tvTotalPriceCart.setText("Rp. " + String.valueOf(Temp.total_price));
        }

        return totalCost;
    }

    public void initEmpty(){
        if (Temp.cartModels.size() == 0){
            rvCart.setVisibility(View.GONE);
            linearLayoutProcced.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.GONE);
            imgEmpty.setVisibility(View.VISIBLE);
            btnBSMT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }else{
            rvCart.setVisibility(View.VISIBLE);
            linearLayoutProcced.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.VISIBLE);
            imgEmpty.setVisibility(View.GONE);
        }
    }

    private void showEmptyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setTitle("Empty");
        builder.setMessage("You can't");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
