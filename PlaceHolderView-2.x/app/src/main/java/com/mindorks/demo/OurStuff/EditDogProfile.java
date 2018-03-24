package com.mindorks.demo.OurStuff;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mindorks.demo.OurStuff.Models.ActivityLevel;
import com.mindorks.demo.OurStuff.Models.DogProfile;
import com.mindorks.demo.OurStuff.Models.Shelter;
import com.mindorks.demo.OurStuff.Models.ShelterInfo;
import com.mindorks.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditDogProfile extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    Button saveButton;
    EditText nameText;
    CheckBox houseBox;
    CheckBox dogBox;
    CheckBox catBox;
    CheckBox kidBox;
    RadioGroup activityGroup;
    DogProfile dog;

    String imageUri;

    private ImageView image1;
    private static final int RESULT_IMAGE_1 = 1;
    private static final int RESULT_IMAGE_2 = 1;
    private static final int RESULT_IMAGE_3 = 1;
    private static final int RESULT_IMAGE_4 = 1;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        nameText = findViewById(R.id.dogName);
        houseBox = findViewById(R.id.houseBox);
        dogBox = findViewById(R.id.dogBox);
        catBox = findViewById(R.id.catBox);
        kidBox = findViewById(R.id.kidBox);
        activityGroup = findViewById(R.id.activityGroup);

        image1 = findViewById(R.id.imageView1);
        image1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.saveButton:
                onSaveClick();
                break;

        }


    }
    public void onSaveClick() {
        String name = nameText.getText().toString();
        boolean houseTrained = houseBox.isChecked();
        boolean dogFriendly = dogBox.isChecked();
        boolean catFriendly = catBox.isChecked();
        boolean kidFriendly = kidBox.isChecked();
        ActivityLevel activityLevel = ActivityLevel.LOW;

        int activityID = activityGroup.getId();
        switch (activityID) {
            case R.id.low:
                activityLevel = ActivityLevel.HIGH;
                break;
            case R.id.medium:
                activityLevel = ActivityLevel.MEDIUM;
                break;
            case R.id.high:
                activityLevel = ActivityLevel.LOW;
                break;
        }

        List<String> list = new ArrayList<>();
        list.add("test");

        dog = new DogProfile(name, "", -1, kidFriendly, dogFriendly, catFriendly, activityLevel, houseTrained, "", list);
        dog.setId(dog.hashCode());
        databaseReference.child("shelters").child("user-" + Shelter.instance.getId()).child("dogs").child("dog-" + dog.hashCode()).setValue(dog);

        Intent intent = new Intent(EditDogProfile.this, ManageDogProfile.class);
        intent.putExtra("dogId", dog.hashCode());
        startActivity(intent);
    }
}
