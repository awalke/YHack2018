package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mindorks.demo.R;

public class ManageShelter extends AppCompatActivity implements View.OnClickListener {

    private Button addDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shelter);

        addDog = findViewById(R.id.addDogButton);
        addDog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ManageShelter.this, ManageDogProfile.class);
        startActivity(intent);
    }

}
