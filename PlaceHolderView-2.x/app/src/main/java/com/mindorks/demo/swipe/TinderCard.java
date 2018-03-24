package com.mindorks.demo.swipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
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
import com.mindorks.demo.ActivityTinder;
import com.mindorks.demo.OurStuff.ManageDogProfile;
import com.mindorks.demo.OurStuff.Models.DogProfile;
import com.mindorks.demo.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeHead;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by janisharali on 19/08/16.
 */
@NonReusable
@Layout(R.layout.tinder_card_view)
public class TinderCard {

    @View(R.id.profileImageView)
    ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    TextView locationNameTxt;

    @SwipeView
    android.view.View view;

    List<DogProfile> dogs;
    DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    int dogNum;
    Context context;
    int dogId;

    public TinderCard(int dogNum, Context context) {
        this.dogNum = dogNum;
        this.context = context;
    }

    @Resolve
    public void onResolve() {
        nameAgeTxt.setText("Name " + this.hashCode());
    }

    @SwipeOut
    public void onSwipedOut() {
        Log.d("DEBUG", "onSwipedOut");
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeIn
    public void onSwipeIn() {
        Log.d("DEBUG", "onSwipedIn");
        ActivityTinder.onSwipeRight();
    }

    @SwipeInState
    public void onSwipeInState() {
        Log.d("DEBUG", "onSwipeInState");
    }

    @SwipeOutState
    public void onSwipeOutState() {
        Log.d("DEBUG", "onSwipeOutState");
    }

    @SwipeHead
    public void onSwipeHead() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("shelters");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> shelterList = dataSnapshot.getChildren();
                for (DataSnapshot shelter: shelterList) {
                    final String shelterName = shelter.child("name").getValue(String.class);
                    Iterable<DataSnapshot> dogList = shelter.child("dogs").getChildren();
                    int count = 0;
                    for(DataSnapshot dog: dogList) {
                        if (count == dogNum) {
                            DogProfile dogProfile = dog.getValue(DogProfile.class);
                            String photo;
                            if (count == 2) {
                                photo = dogProfile.getPhoto().get(1);
                            } else {
                                photo = dogProfile.getPhoto().get(0);
                            }
                            final String name = dogProfile.getName();
                            dogId = dogProfile.getId();
                            mStorageRef = FirebaseStorage.getInstance().getReference();

                            String path = "images/" + photo;
                            StorageReference islandRef = mStorageRef.child(path);

                            File localFile = null;
                            try {
                                localFile = File.createTempFile("images", "jpg");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            final File finalLocalFile = localFile;
                            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been
                                    profileImageView.setImageURI(Uri.fromFile(finalLocalFile));
                                    nameAgeTxt.setText(name);
                                    locationNameTxt.setText(shelterName);
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

                        count++;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Log.d("DEBUG", "onSwipeHead");
    }

    @Click(R.id.profileImageView)
    public void onProfileImageViewClick() {
        Log.d("DEBUG", "onProfileImageViewClick");
        Intent intent = new Intent(context, ManageDogProfile.class);
        intent.putExtra("dogId", dogId);
        context.startActivity(intent);
    }

    @LongClick(R.id.profileImageView)
    public void onProfileImageViewLongClick() {
        Log.d("DEBUG", "onProfileImageViewLongClick");
    }
}
