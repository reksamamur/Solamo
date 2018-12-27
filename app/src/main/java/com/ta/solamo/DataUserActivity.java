package com.ta.solamo;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class DataUserActivity extends AppCompatActivity {

    EditText editTextName, editTextTable;
    FloatingActionButton btnFinish;
    ImageButton btnBack;

    String TAG = getClass().getSimpleName();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = firestore.collection("orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        editTextName = findViewById(R.id.ed_personName);
        editTextTable = findViewById(R.id.ed_tableNumber);
        btnFinish = findViewById(R.id.btn_finish);
        btnBack = findViewById(R.id.btn_backUser);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });
    }

    public void addOrder() {
        String userName = editTextName.getText().toString().trim();
        String userTable = editTextTable.getText().toString().trim();

        UserModel userModel = new UserModel(Temp.cartModels, ++Temp.count_userID, userName, userTable, Temp.total_price);

        collectionReference.add(userModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onSuccess: sukses");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailur: fail :" + e);
                    }
                });
    }
}
