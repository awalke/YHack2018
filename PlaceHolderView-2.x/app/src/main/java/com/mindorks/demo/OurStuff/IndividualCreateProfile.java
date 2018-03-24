package com.mindorks.demo.OurStuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.demo.R;

public class IndividualCreateProfile extends AppCompatActivity implements View.OnClickListener {

    private Button createButton;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_individual_create_profile);

        createButton = findViewById(R.id.createIndividualButton);
        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        
    }
}
