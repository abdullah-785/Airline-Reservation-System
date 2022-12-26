package com.example.airlinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class book_flight extends AppCompatActivity {

    TextView roundTrip;
    TextView oneWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

        roundTrip = findViewById(R.id.textView5);
        oneWay = findViewById(R.id.textView6);
        Button searchFlight = findViewById(R.id.searchFlight);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout, new RoundTrip());
        fragmentTransaction.commit();

        roundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout, new RoundTrip());
                fragmentTransaction.commit();
            }
        });


        oneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout, new one_way());
                fragmentTransaction.commit();
            }
        });


        searchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(book_flight.this, flights.class);
                startActivity(intent);
            }
        });



    }
}