package com.ta.solamo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ta.solamo.R;
import com.ta.solamo.bottomsheetdialog.BottomSheetDialogMenuDetail;
import com.ta.solamo.model.OfferSetGet;


/**
 * A simple {@link Fragment} subclass.
 */
public class MieFragment extends Fragment implements BottomSheetDialogMenuDetail.BottomSheetListenerDetail{

    RecyclerView rvMenu;
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;

    String TAG = getClass().getSimpleName();

    public MieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mie, container, false);

        rvMenu = v.findViewById(R.id.rv_mie);
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        rvMenu.setLayoutManager(layoutManager);

        initAdapter();

        adapter.notifyDataSetChanged();
        rvMenu.setAdapter(adapter);
        return v;
    }

    public void initAdapter(){
        Query expressb = firebaseFirestore.collection("menu");
        Query query = expressb.whereEqualTo("menu_category","mie");
        FirestoreRecyclerOptions<OfferSetGet> respon = new FirestoreRecyclerOptions.Builder<OfferSetGet>()
                .setQuery(query, OfferSetGet.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<OfferSetGet, OfferViewHolder>(respon) {
            @NonNull
            @Override
            public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(getContext())
                        .inflate(R.layout.card_item, viewGroup, false);
                return new OfferViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull OfferViewHolder holder, int position, @NonNull OfferSetGet model) {
                holder.textViewTittle.setText(String.valueOf(model.getMenu_name()));
                holder.textViewPrice.setText("Rp. "+String.valueOf(model.getMenu_price()));
                Glide.with(getContext())
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
                        dialogMenuDetail.show(getChildFragmentManager(), "bottomSheetDialogFragmentDetail");
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
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
