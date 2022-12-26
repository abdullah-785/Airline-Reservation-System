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

public class MainActivity extends AppCompatActivity {
    Sqlite sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sq = new Sqlite(MainActivity.this);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText c_password = findViewById(R.id.c_password);
        TextView createAccount = findViewById(R.id.createAccount);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://airlinereservationsystem-3c586-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();


        Button signup = findViewById(R.id.signUpBtn);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = email.getText().toString();
                String p = password.getText().toString();
                String c_p = c_password.getText().toString();

                if(!e.isEmpty() && !p.isEmpty() && !c_p.isEmpty() && p.equals(c_p)){

                    myRef.child("users").child(e).child("email").setValue(e);
                    myRef.child("users").child(e).child("password").setValue(p);

                    Toast.makeText(MainActivity.this, "Sign up successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);


                }else if(e.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please provide email address", Toast.LENGTH_LONG).show();
                }else if(p.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }else if(c_p.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please confirm password", Toast.LENGTH_LONG).show();
                }else if(!p.equals(c_p)){
                    Toast.makeText(MainActivity.this, "Password and confirm password is differnt", Toast.LENGTH_LONG).show();
                }



            }
        });



        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

    }
}