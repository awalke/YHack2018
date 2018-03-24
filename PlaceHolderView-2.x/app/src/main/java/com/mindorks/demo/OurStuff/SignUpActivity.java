package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mindorks.demo.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button shelterButton;
    private Button individualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        shelterButton = (Button) findViewById(R.id.individualButton);
        shelterButton.setOnClickListener(this);

        individualButton = (Button) findViewById(R.id.shelterButton);
        individualButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.individualButton:
                //do individual sign up
                Intent intent = new Intent(SignUpActivity.this, IndividualCreateProfile.class);
                startActivity(intent);
                break;
            case R.id.shelterButton:
                //do shelter sign up
                break;
        }
    }
}
