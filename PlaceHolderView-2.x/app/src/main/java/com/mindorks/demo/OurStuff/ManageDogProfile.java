package com.mindorks.demo.OurStuff;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mindorks.demo.OurStuff.Models.ActivityLevel;
import com.mindorks.demo.OurStuff.Models.DogProfile;
import com.mindorks.demo.OurStuff.Models.Shelter;
import com.mindorks.demo.R;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ManageDogProfile extends AppCompatActivity implements View.OnClickListener{



    private DatabaseReference databaseReference;

    private TextView editButton;
    private TextView nameText;
    private CheckBox houseBox;
    private CheckBox dogBox;
    private CheckBox catBox;
    private CheckBox kidBox;
    private RadioGroup activityGroup;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_dog_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

//        image1 = findViewById(R.id.image1);
//        image1.setOnClickListener(this);

        editButton = findViewById(R.id.edit);
        editButton.setOnClickListener(this);

        nameText = findViewById(R.id.dogName);
        houseBox = findViewById(R.id.houseBox);
        dogBox = findViewById(R.id.dogBox);
        catBox = findViewById(R.id.catBox);
        kidBox = findViewById(R.id.kidBox);
        activityGroup = findViewById(R.id.activityGroup);


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        int id = getIntent().getIntExtra("dogId", -1);
        if (id != -1) {
            DatabaseReference ref = databaseReference.child("shelters").child("user-"+Shelter.instance.getId()).child("dogs").child("dog-"+id);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                     DogProfile dog = dataSnapshot.getValue(DogProfile.class);
                     nameText.setText(dog.getName());
                     houseBox.setChecked(dog.isHousetrained());
                     dogBox.setChecked(dog.isDogFriendly());
                     catBox.setChecked(dog.isCatFriendly());
                     kidBox.setChecked(dog.isKidFriendly());
                     List<String> photos = dog.getPhoto();
                     for (int i = 0; i < photos.size(); i++) {
                         String s = photos.get(i);
                         StorageReference islandRef = mStorageRef.child("images/" + s);

                         File localFile = null;
                         try {
                             localFile = File.createTempFile("images", "jpg");
                         } catch (IOException e) {
                             e.printStackTrace();
                         }

                         final File finalLocalFile = localFile;
                         final int finalI = i;
                         islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                             @Override
                             public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                 // Local temp file has been
                                 Uri uri = Uri.fromFile(finalLocalFile);
                                 switch(finalI) {
                                     case 0:
                                         imageView1.setImageURI(uri);
                                         break;
                                     case 1:
                                         imageView2.setImageURI(uri);
                                         break;
                                     case 2:
                                         imageView3.setImageURI(uri);
                                         break;
                                     case 3:
                                         imageView4.setImageURI(uri);
                                         break;
                                 }
                                 System.out.println("pass");
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception exception) {
                                 // Handle any errors
                                 System.out.println("fail");
                             }
                         });
                     }

                     if (dog.getHowActive() == ActivityLevel.LOW) {
                         activityGroup.check(R.id.high);
                     } else if (dog.getHowActive() == ActivityLevel.MEDIUM) {
                         activityGroup.check(R.id.medium);
                     } else {
                         activityGroup.check(R.id.low);
                     }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit:
                Intent intent = new Intent(ManageDogProfile.this, EditDogProfile.class);
                startActivity(intent);
                break;


        }
    }


}
