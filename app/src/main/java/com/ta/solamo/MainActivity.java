package com.ta.solamo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ta.solamo.bottomsheetdialog.BottomSheetDialogMenu;
import com.ta.solamo.bottomsheetdialog.BottomSheetDialogMenuDetail;
import com.ta.solamo.fragment.OfferSetGet;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomSheetDialogMenu.BottomSheetListener, BottomSheetDialogMenuDetail.BottomSheetListenerDetail {

    ImageButton btnMore;
    TextView tvGreetings;
    ImageView imgGreetings;
    AppCompatButton btnOrder;
    RecyclerView rvOffer;

    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGreetings = findViewById(R.id.tv_greeting);
        imgGreetings = findViewById(R.id.img_greeting);
        initGreetings();
        rvOffer = findViewById(R.id.rv_offer);

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

        firebaseFirestore = FirebaseFirestore.getInstance();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvOffer.setLayoutManager(layoutManager);

        initAdapter();

        adapter.notifyDataSetChanged();
        rvOffer.setAdapter(adapter);
    }

    public void initAdapter(){
        Query query = firebaseFirestore.collection("menu");
        FirestoreRecyclerOptions<OfferSetGet> respon = new FirestoreRecyclerOptions.Builder<OfferSetGet>()
                .setQuery(query, OfferSetGet.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<OfferSetGet, OfferViewHolder>(respon){
            @NonNull
            @Override
            public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.card_item, viewGroup, false);
                return new OfferViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(OfferViewHolder holder, final int position, OfferSetGet model) {
                holder.textViewTittle.setText(String.valueOf(model.getMenu_name()));
                holder.textViewPrice.setText("Rp. "+String.valueOf(model.getMenu_price()));
                Glide.with(MainActivity.this)
                        .load(model.getMenu_image())
                        .into(holder.imageView);
                final OfferSetGet current = (OfferSetGet) adapter.getItem(position);

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("posisiTittle", current.getMenu_name());
                        bundle.putInt("posisiPrice", current.getMenu_price());
                        bundle.putString("posisiImage", current.getMenu_image());
                        BottomSheetDialogMenuDetail dialogMenuDetail = new BottomSheetDialogMenuDetail();
                        dialogMenuDetail.setArguments(bundle);
                        dialogMenuDetail.show(getSupportFragmentManager(), "bottomSheetDialogFragmentDetail");
                    }
                });
            }
        };
    }

    @Override
    public void onBottomSheetClickedDetail() {

    }

    public class OfferViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTittle, textViewPrice;
        MaterialCardView cardView;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_thumb);
            textViewTittle = itemView.findViewById(R.id.tv_itemNameCard);
            textViewPrice = itemView.findViewById(R.id.tv_itemPriceCard);
            cardView = itemView.findViewById(R.id.mcard_item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGreetings();
    }

    public void initGreetings() {
        Calendar calendar = Calendar.getInstance();
        int TOD = calendar.get(Calendar.HOUR_OF_DAY);

        if (TOD >= 0 && TOD < 12) {
            tvGreetings.setText("Good Morning");
            imgGreetings.setImageResource(R.drawable.tea);
        } else if (TOD >= 12 && TOD < 16) {
            tvGreetings.setText("Good Afternoon");
            imgGreetings.setImageResource(R.drawable.orange_juice);
        } else if (TOD >= 16 && TOD < 21) {
            tvGreetings.setText("Good Evening");
            imgGreetings.setImageResource(R.drawable.pie);
        } else if (TOD >= 21 && TOD < 24) {
            tvGreetings.setText("Good Night");
            imgGreetings.setImageResource(R.drawable.croissant);
        }
    }

    @Override
    public void onBottomSheetClicked() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
