package com.example.trainticketbooking;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busticketbooking.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements TrainRVAdapter.TrainClickInterface {
    public ProgressBar loadingPB;
    public DatabaseReference databaseReference;
    public ArrayList<TrainRVModel> TrainRVModelArrayList;
    public RelativeLayout bottomSheetRL;
    public TrainRVAdapter trainRVAdapter;
    public FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView trainRV = findViewById(R.id.idRVTrains);
        loadingPB = findViewById(R.id.idPBLoading);
        FloatingActionButton addFAB = findViewById(R.id.idAddFAB);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference("Train");
        TrainRVModelArrayList = new ArrayList<>();
        trainRVAdapter = new TrainRVAdapter(TrainRVModelArrayList, this, this);
        trainRV.setLayoutManager(new LinearLayoutManager(this));
        trainRV.setAdapter(trainRVAdapter);
        addFAB.setOnClickListener(view -> startActivity(new Intent(MainActivity2.this, Add_Train.class)));
        getAllTrains();
    }

    private void getAllTrains() {
        TrainRVModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                TrainRVModelArrayList.add(snapshot.getValue(TrainRVModel.class));
                trainRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                trainRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                trainRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                trainRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        @Override
        public void onTrainClick ( int position){
            displayBottomSheet(TrainRVModelArrayList.get(position));
        }

        private void displayBottomSheet (TrainRVModel trainRVModel){
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, bottomSheetRL);
            bottomSheetDialog.setContentView(layout);
            bottomSheetDialog.setCancelable(false);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.show();

            TextView trainNumTV = layout.findViewById(R.id.idTrainNum);
            TextView SrcTV = layout.findViewById(R.id.idSrc);
            TextView DestTV = layout.findViewById(R.id.idDest);
            TextView TimingsTV = layout.findViewById(R.id.idTimings);
            Button editBtn = layout.findViewById(R.id.idBtnEditTrain);

            trainNumTV.setText("Number Plate: "+trainRVModel.getTrainNum());
            SrcTV.setText("From: "+trainRVModel.getSrc());
            DestTV.setText("To: "+trainRVModel.getDest());
            TimingsTV.setText("Time: "+trainRVModel.getTimings());

            editBtn.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity2.this, Edit_Train.class);
                i.putExtra("Train", trainRVModel);
                startActivity(i);
            });
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            int id = item.getItemId();
            if (id == R.id.idLogOut) {
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                this.finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

