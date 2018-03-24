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
import com.mindorks.demo.OurStuff.Models.Individual;
import com.mindorks.demo.OurStuff.Models.OrganizationType;
import com.mindorks.demo.OurStuff.Models.Shelter;
import com.mindorks.demo.R;

public class ShelterCreateProfile extends AppCompatActivity implements View.OnClickListener {

    private Button createButton;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_shelter_create_profile);

        createButton = findViewById(R.id.createShelterButton);
        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        RadioGroup organization = findViewById(R.id.organization);
        boolean enabled = true;

        if (name.getText().equals(null) || name.getText().length() < 1) {
            Toast.makeText(ShelterCreateProfile.this, "Please Enter Organization Name", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (email.getText().equals(null) || email.getText().length() < 1) {
            Toast.makeText(ShelterCreateProfile.this, "Please Enter Organization Email", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (password.getText().equals(null) || password.getText().length() < 1) {
            Toast.makeText(ShelterCreateProfile.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (phone.getText().equals(null) || phone.getText().length() < 1) {
            Toast.makeText(ShelterCreateProfile.this, "Please Enter Organization Phone", Toast.LENGTH_SHORT).show();
            enabled = false;
        }
        else if (address.getText().equals(null) || address.getText().length() < 1) {
            Toast.makeText(ShelterCreateProfile.this, "Please Enter Organization Address", Toast.LENGTH_SHORT).show();
            enabled = false;
        }

        OrganizationType type = OrganizationType.SHELTER;
        int organizationId = organization.getCheckedRadioButtonId();
        switch (organizationId) {
            case R.id.shelter:
                type = OrganizationType.SHELTER;
                break;
            case R.id.fosterGroup:
                type = OrganizationType.FOSTERGROUP;
                break;
            default:
                Toast.makeText(ShelterCreateProfile.this, "Please Select an Organization Type", Toast.LENGTH_SHORT).show();
                enabled = false;
                break;
        }

        if (enabled) {

            Shelter shelter = new Shelter(name.getText().toString(), email.getText().toString(),
                    password.getText().toString(), phone.getText().toString(), address.getText().toString(), type );

            int hashcode = shelter.hashCode();
            shelter.setId(hashcode);
            database.child("shelters").child("user-" + hashcode).setValue(shelter);

            Intent intent = new Intent(ShelterCreateProfile.this, ManageShelter.class);
            startActivity(intent);
        }

    }
}
