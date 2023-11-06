package com.example.trainticketbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.busticketbooking.R;

public class Ticket extends AppCompatActivity {
    TextView VarTrainID,VarSrc,VarDest,VarTimings,VarSeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        VarTrainID = findViewById(R.id.idTrainID);
        VarSrc = findViewById(R.id.idTrainSource);
        VarDest = findViewById(R.id.idTrainDestination);
        VarTimings = findViewById(R.id.idTrainTimings);
        VarSeat = findViewById(R.id.idTrainSeat);
        Intent intent=getIntent();
        String trainID=intent.getStringExtra("TrainID");
        String src=intent.getStringExtra("Source");
        String dest=intent.getStringExtra("Destination");
        String Timings=intent.getStringExtra("Timings");
        String Seats=intent.getStringExtra("Seats");

        VarTrainID.setText(trainID);
        VarSrc.setText("From: "+src);
        VarDest.setText("To: "+dest);
        VarTimings.setText("Timings: "+Timings);
        VarSeat.setText("Seat: "+Seats);

        //Creating PDF of activity
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),View. MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        PrintHelper photoPrinter = new PrintHelper(Ticket.this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("print", bitmap);

    }
}