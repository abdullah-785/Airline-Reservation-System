package com.example.airlinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent =  getIntent();
        int price = intent.getIntExtra("price",0);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://airlinereservationsystem-3c586-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();

        EditText name = findViewById(R.id.editTextTextPersonName);
        EditText accountNumber = findViewById(R.id.accountNumber);
        EditText expireDate = findViewById(R.id.expireDate);
        EditText cvv = findViewById(R.id.cvv);
        Button procceed = findViewById(R.id.procceedBtn);

//        TextView amount = findViewById(R.id.price);
//        TextView fare_amount = findViewById(R.id.fareamont);
//        String fareAmount = fare_amount.getText().toString();
//
//        amount.setText("$"+price);


        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String an = accountNumber.getText().toString();
                String ex = expireDate.getText().toString();
                String c = cvv.getText().toString();

                if(!n.isEmpty() && !an.isEmpty() && !ex.isEmpty() && !c.isEmpty()){

                    myRef.child("cardDetials").child(an).child("name").setValue(n);
                    myRef.child("cardDetials").child(an).child("accountNumber").setValue(an);
                    myRef.child("cardDetials").child(an).child("expireDate").setValue(ex);
                    myRef.child("cardDetials").child(an).child("cvv").setValue(c);

                    Toast.makeText(checkout.this, "Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(checkout.this, confirmation.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(checkout.this, "Please provide all information", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}