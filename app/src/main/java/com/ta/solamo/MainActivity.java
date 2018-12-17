package com.ta.solamo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomSheetDialogMenu.BottomSheetListener{

    ImageButton btnMore;
    TextView tvGreetings;
    ImageView imgGreetings;
    AppCompatButton btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGreetings = findViewById(R.id.tv_greeting);
        imgGreetings = findViewById(R.id.img_greeting);
        initGreetings();

        btnMore = findViewById(R.id.btn_menu);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogMenu bottomSheetDialogFragment = new BottomSheetDialogMenu();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "bottomSheetDialogFragment");
            }
        });

        btnOrder = findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGreetings();
    }

    public void initGreetings(){
        Calendar calendar = Calendar.getInstance();
        int TOD = calendar.get(Calendar.HOUR_OF_DAY);

        if (TOD >= 0 && TOD < 12){
            tvGreetings.setText("Good Morning");
            imgGreetings.setImageResource(R.drawable.tea);
        }else if (TOD >= 12 && TOD < 16){
            tvGreetings.setText("Good Afternoon");
            imgGreetings.setImageResource(R.drawable.orange_juice);
        }else  if (TOD >= 16 && TOD < 21){
            tvGreetings.setText("Good Evening");
            imgGreetings.setImageResource(R.drawable.pie);
        }else  if (TOD >= 21 && TOD < 24){
            tvGreetings.setText("Good Night");
            imgGreetings.setImageResource(R.drawable.croissant);
        }
    }

    @Override
    public void onBottomSheetClicked() {

    }
}
