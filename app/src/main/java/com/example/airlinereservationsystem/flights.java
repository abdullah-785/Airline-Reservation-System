package com.example.airlinereservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class flights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        TextView from_1 = findViewById(R.id.textView5);
        TextView to_1 = findViewById(R.id.textView6);
        TextView toShortForm_1 = findViewById(R.id.textView8);
        TextView fromShortForm_1 = findViewById(R.id.textView7);
        TextView departTime_1 = findViewById(R.id.editTextDate);
        TextView flightNumber_1 = findViewById(R.id.departure);
        TextView timeRequired_1 = findViewById(R.id.timeRequired);
        TextView Price_1 = findViewById(R.id.textView14);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://airlinereservationsystem-3c586-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();

        myRef.child("Tickets").child("EKOOK").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                String to = task.getResult().child("to").getValue().toString();
                String from = task.getResult().child("from").getValue().toString();
                String toShortForm = task.getResult().child("toShortForm").getValue().toString();
                String fromShortForm = task.getResult().child("fromShortForm").getValue().toString();
                String departTime = task.getResult().child("departTime").getValue().toString();
                String flightNumber = task.getResult().child("flightNumber").getValue().toString();
                String timeRequired = task.getResult().child("timeRequired").getValue().toString();
                String Price = task.getResult().child("price").getValue().toString();

                int price = Integer.parseInt(Price);

                from_1.setText(from);
                to_1.setText(to);
                toShortForm_1.setText(toShortForm);
                fromShortForm_1.setText(fromShortForm);
                departTime_1.setText(departTime);
                flightNumber_1.setText(flightNumber);
                timeRequired_1.setText(timeRequired);
                Price_1.setText("$"+price);

            }
        });

        TextView viewDetials = findViewById(R.id.textView16);

        viewDetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(flights.this, flightsDetails.class);

                intent.putExtra("from", from_1.getText().toString());
                intent.putExtra("to", to_1.getText().toString());
                intent.putExtra("toShortForm", toShortForm_1.getText().toString());
                intent.putExtra("fromShortForm", fromShortForm_1.getText().toString());
                intent.putExtra("departTime", departTime_1.getText().toString());
                intent.putExtra("flightNumber", flightNumber_1.getText().toString());
                intent.putExtra("timeRequired", timeRequired_1.getText().toString());
                intent.putExtra("Price", Price_1.getText().toString());
                startActivity(intent);
            }
        });



    }
}