package com.example.airlinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class flightsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_details);

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String toShortForm = intent.getStringExtra("toShortForm");
        String fromShortForm = intent.getStringExtra("fromShortForm");
        String departTime = intent.getStringExtra("departTime");
        String flightNumber = intent.getStringExtra("flightNumber");
        String timeRequired = intent.getStringExtra("timeRequired");
        String Price = intent.getStringExtra("Price");

        TextView to_1 = findViewById(R.id.sydney);
        TextView from_1 = findViewById(R.id.distination);
        TextView toShortForm_1 = findViewById(R.id.syd);
        TextView fromShortForm_1 = findViewById(R.id.startFormlondon);
        TextView departTime_1 = findViewById(R.id.departTime);
        TextView flightNumber_1 = findViewById(R.id.flightNumber);
//        TextView timeRequired_1 = findViewById(R.id.sydney);
        TextView Price_1 = findViewById(R.id.price);

        to_1.setText(to);
        from_1.setText(from);
        toShortForm_1.setText(toShortForm);
        fromShortForm_1.setText(fromShortForm);
        departTime_1.setText(departTime);
        flightNumber_1.setText(flightNumber);
        Price_1.setText(Price);

        Button checkout = findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(flightsDetails.this, checkout.class);
                intent.putExtra("price", Price);
                startActivity(intent);
            }
        });
    }
}