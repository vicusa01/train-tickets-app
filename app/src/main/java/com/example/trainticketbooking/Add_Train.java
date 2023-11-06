package com.example.trainticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Train extends AppCompatActivity {
    private EditText trainNum, src, dest, timings, price;
    private Button button;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String TrainID;
    private String availableSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);
        trainNum = findViewById(R.id.TrainNumber);
        src = findViewById(R.id.source);
        dest = findViewById(R.id.dest);
        timings = findViewById(R.id.timings);
        price = findViewById(R.id.price);
        button = findViewById(R.id.btn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Train");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trainNumVar = trainNum.getText().toString();
                String srcVar = src.getText().toString();
                String destVar = dest.getText().toString();
                String timingsVar = timings.getText().toString();
                String priceVar = price.getText().toString();
                TrainID = trainNumVar;
                availableSeats = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24";
                TrainRVModel trainRVModel = new TrainRVModel(trainNumVar, srcVar, destVar, timingsVar, priceVar, TrainID, availableSeats);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        databaseReference.child(TrainID).setValue(trainRVModel);
                        Toast.makeText(Add_Train.this, "Train added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Train.this, MainActivity2.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Add_Train.this, "Error is: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
