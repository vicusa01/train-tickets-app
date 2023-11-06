package com.example.trainticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.busticketbooking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Edit_Train extends AppCompatActivity {
    public EditText trainNumEdit,srcEdit,destEdit,timingsEdit,priceEdit;
    private DatabaseReference databaseReference;
    public String TrainID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_train);
        trainNumEdit =findViewById(R.id.TrainNumber);
        srcEdit=findViewById(R.id.source);
        destEdit=findViewById(R.id.dest);
        timingsEdit=findViewById(R.id.timings);
        priceEdit=findViewById(R.id.price);
        Button deleteButton = findViewById(R.id.deletebtn);
        Button updateButton = findViewById(R.id.updatebtn);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        TrainRVModel trainRVModel = getIntent().getParcelableExtra("Train");
        if(trainRVModel !=null){
            trainNumEdit.setText(trainRVModel.getTrainNum());
            srcEdit.setText(trainRVModel.getSrc());
            destEdit.setText(trainRVModel.getDest());
            timingsEdit.setText(trainRVModel.getTimings());
            priceEdit.setText(trainRVModel.getPrice());
            TrainID= trainRVModel.getTrainID();
        }
        databaseReference= firebaseDatabase.getReference("Train").child(TrainID);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trainNumVar= trainNumEdit.getText().toString();
                String srcVar=srcEdit.getText().toString();
                String destVar=destEdit.getText().toString();
                String timingsVar=timingsEdit.getText().toString();
                String priceVar=priceEdit.getText().toString();

                Map<String,Object> map=new HashMap<>();
                map.put("trainNum", trainNumVar);
                map.put("src",srcVar);
                map.put("dest",destVar);
                map.put("timings",timingsVar);
                map.put("price",priceVar);
                map.put("trainID",TrainID);

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(Edit_Train.this, "Train updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Edit_Train.this,MainActivity2.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Edit_Train.this, "Update Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTrain();
            }
        });
    }
    private void deleteTrain(){
        databaseReference.removeValue();
        Toast.makeText(this, "Train Deleted!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Edit_Train.this,MainActivity2.class));
    }
}