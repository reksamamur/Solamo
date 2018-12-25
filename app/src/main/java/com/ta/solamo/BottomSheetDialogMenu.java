package com.ta.solamo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class BottomSheetDialogMenu extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet_main, container, false);

        AppCompatButton btnOpenMenu = v.findViewById(R.id.btn_sheetMenu);
        AppCompatButton btnOpenOrder = v.findViewById(R.id.btn_sheetOrder);
        ImageButton btnDismiss = v.findViewById(R.id.btn_close);

        btnOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getActivity(), MenuActivity.class);
                startActivity(menuIntent);
                dismiss();
            }
        });

        btnOpenOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getActivity(), OrderActivity.class);
                startActivity(menuIntent);
                dismiss();
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListener{
        void onBottomSheetClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            BottomSheetListener bottomSheetListener = (BottomSheetListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Implemet BottomSheetListener interface");
        }

    }
}
