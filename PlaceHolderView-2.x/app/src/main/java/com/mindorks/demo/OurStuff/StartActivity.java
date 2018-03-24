package com.mindorks.demo.OurStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mindorks.demo.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        signUpButton = (Button) findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.loginButton:

                break;
            case R.id.signupButton:
                Intent intent = new Intent(StartActivity.this, SignUpActivity.class);
                StartActivity.this.startActivity(intent);
                break;
        }
    }
}
