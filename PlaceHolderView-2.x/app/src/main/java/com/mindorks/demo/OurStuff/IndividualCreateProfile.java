package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.demo.ActivityTinder;
import com.mindorks.demo.OurStuff.Models.HousingType;
import com.mindorks.demo.OurStuff.Models.Individual;
import com.mindorks.demo.R;

public class IndividualCreateProfile extends AppCompatActivity implements View.OnClickListener {

    private Button createButton;
    private DatabaseReference database;

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
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email2);
        EditText password = findViewById(R.id.password2);
        EditText address = findViewById(R.id.address);
        RadioGroup housing = findViewById(R.id.housing);
        RadioGroup kid_group = findViewById(R.id.kid_group);
        RadioGroup pet_group = findViewById(R.id.pet_group);
        boolean enabled = true;

        if (name.getText().equals(null) || name.getText().length() < 1) {
            Toast.makeText(IndividualCreateProfile.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (email.getText().equals(null) || email.getText().length() < 1) {
            Toast.makeText(IndividualCreateProfile.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (address.getText().equals(null) || address.getText().length() < 1) {
            Toast.makeText(IndividualCreateProfile.this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
            enabled = false;
        } else if (password.getText().equals(null) || password.getText().length() < 1) {
            Toast.makeText(IndividualCreateProfile.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
            enabled = false;
        }

        HousingType type = HousingType.RENT;
        int housingId = housing.getCheckedRadioButtonId();
        switch (housingId) {
            case R.id.rent:
                type = HousingType.RENT;
                break;
            case R.id.own:
                type = HousingType.OWN;
                break;
            default:
                Toast.makeText(IndividualCreateProfile.this, "Please Select a Housing Type", Toast.LENGTH_SHORT).show();
                enabled = false;
                break;
        }

        int kidId = kid_group.getCheckedRadioButtonId();
        switch (kidId) {
            case R.id.one_kid:
                break;
            case R.id.two_kid:
                break;
            case R.id.three_kid:
                break;
            default:
                Toast.makeText(IndividualCreateProfile.this, "Please Select How Many Children", Toast.LENGTH_SHORT).show();
                enabled = false;
                break;
        }

        int petId = pet_group.getCheckedRadioButtonId();
        switch (petId) {
            case R.id.one_pet:
                break;
            case R.id.two_pet:
                break;
            case R.id.three_pet:
                break;
            default:
                Toast.makeText(IndividualCreateProfile.this, "Please Select How Many Pets", Toast.LENGTH_SHORT).show();
                enabled = false;
                break;
        }

        if (enabled) {
            RadioButton kidButton = findViewById(kidId);
            RadioButton petButton = findViewById(petId);

            Individual individual = new Individual(name.getText().toString(), email.getText().toString(),
                                                    address.getText().toString(), type, Integer.parseInt(kidButton.getText().toString()),
                                                    Integer.parseInt(petButton.getText().toString()), password.getText().toString());

            database.child("individuals").child("user-" + individual.hashCode()).setValue(individual);

            Intent intent = new Intent(IndividualCreateProfile.this, ActivityTinder.class);
            startActivity(intent);
        }
    }
}
