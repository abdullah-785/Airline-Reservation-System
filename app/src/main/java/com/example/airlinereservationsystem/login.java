package com.example.airlinereservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    Sqlite sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sq = new Sqlite(login.this);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.loginBtn);

        TextView createBtn = findViewById(R.id.textView);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://airlinereservationsystem-3c586-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();

                if(!e.isEmpty() && !p.isEmpty()){

                    myRef.child("users").child(e).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            String email = task.getResult().child("email").getValue().toString();
                            String pass = task.getResult().child("password").getValue().toString();

                            if(e.equals(email) && p.equals(pass)){
                                Intent intent = new Intent(login.this, book_flight.class);
                                startActivity(intent);
                                Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            }else if(!e.equals(email) || !p.equals(pass) ){
                                Toast.makeText(login.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }

                        }
                    });


                }else if(e.isEmpty()){
                    Toast.makeText(login.this, "Please provide email address", Toast.LENGTH_LONG).show();
                }else if(p.isEmpty()){
                    Toast.makeText(login.this, "Please enter password", Toast.LENGTH_LONG).show();
                }

            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}