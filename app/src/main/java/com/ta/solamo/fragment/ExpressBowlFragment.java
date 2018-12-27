package com.ta.solamo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ta.solamo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpressBowlFragment extends Fragment {


    public ExpressBowlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_express_bowl, container, false);
    }

}
