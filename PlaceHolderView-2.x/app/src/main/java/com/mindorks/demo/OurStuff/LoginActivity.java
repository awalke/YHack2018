package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mindorks.demo.ActivityTinder;
import com.mindorks.demo.OurStuff.Models.Individual;
import com.mindorks.demo.OurStuff.Models.Shelter;
import com.mindorks.demo.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference individualsRef = database.child("individuals");
    DatabaseReference sheltersRef = database.child("shelters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);

        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
    }

    @Override
    public void onClick(View view) {
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean foundUser = false;
                boolean foundShelter = false;

                if (!email.equals("") && !password.equals("")) {
                    Iterable<DataSnapshot> snapshots = dataSnapshot.getChildren();

                    for (DataSnapshot snap : snapshots) {
                        String category = snap.getKey().toString();

                        if (category.equals("individuals")) {
                            Iterable<DataSnapshot> snapshots2 = snap.getChildren();
                            for (DataSnapshot s: snapshots2) {
                                Individual individual = s.getValue(Individual.class);
                                String dbEmail = individual.getEmail();
                                String dbPassword = individual.getPassword();

                                if (dbEmail.equals(email) && dbPassword.equals(password)) {
                                    foundUser = true;
                                    break;
                                }
                            }
                        } else if (category.equals("shelters")) {
                            Iterable<DataSnapshot> snapshots2 = snap.getChildren();
                            for (DataSnapshot s: snapshots2) {
                                Shelter shelter = s.getValue(Shelter.class);
                                Shelter.instance = shelter;
                                String dbEmail = shelter.getEmail();
                                String dbPassword = shelter.getPassword();

                                if (dbEmail.equals(email) && dbPassword.equals(password)) {
                                    foundShelter = true;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please Enter Your Email and Password", Toast.LENGTH_SHORT).show();
                }

                if (foundUser) {
                    Intent intent = new Intent(LoginActivity.this, ActivityTinder.class);
                    startActivity(intent);
                } else if(foundShelter) {
                    Intent intent = new Intent(LoginActivity.this, ManageShelter.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
