package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mindorks.demo.OurStuff.Models.DogProfile;
import com.mindorks.demo.OurStuff.Models.Shelter;
import com.mindorks.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ManageShelter extends AppCompatActivity implements View.OnClickListener {

    private Button addDog;
    private DatabaseReference databaseReference;
    private List<DogProfile> dogs;
    private TextView nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shelter);

        Shelter shelter = Shelter.instance;

        nameText = findViewById(R.id.shelterName);
        nameText.setText(shelter.getName());

        dogs = new ArrayList<>();

        addDog = findViewById(R.id.addDogButton);
        addDog.setOnClickListener(this);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("shelters").child("user-"+shelter.getId()).child("dogs");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshots = dataSnapshot.getChildren();
                int count = 0;
                for(DataSnapshot snap: snapshots) {
                    DogProfile dog = snap.getValue(DogProfile.class);

                    TextView text = new TextView(getApplicationContext());
                    text.setText(dog.getName());
                    text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                    text.setGravity(Gravity.CENTER);
                    text.setId(count++);
                    setTextListener(text);
                    dogs.add(dog);


                    LinearLayout layout = findViewById(R.id.dogList);
                    layout.addView(text);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void setTextListener(TextView textView) {
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addDogButton:
                Intent intent = new Intent(ManageShelter.this, ManageDogProfile.class);
                startActivity(intent);
                break;
            case 0:
                int id = dogs.get(0).getId();

                Intent intent1 = new Intent(ManageShelter.this, ManageDogProfile.class);
                intent1.putExtra("dogId", id);
                startActivity(intent1);
                break;
            case 1:
                int id2 = dogs.get(1).getId();

                Intent intent2 = new Intent(ManageShelter.this, ManageDogProfile.class);
                intent2.putExtra("dogId", id2);
                startActivity(intent2);
                break;
            case 2:
                int id3 = dogs.get(2).getId();

                Intent intent3 = new Intent(ManageShelter.this, ManageDogProfile.class);
                intent3.putExtra("dogId", id3);
                startActivity(intent3);
                break;
        }
    }

}
