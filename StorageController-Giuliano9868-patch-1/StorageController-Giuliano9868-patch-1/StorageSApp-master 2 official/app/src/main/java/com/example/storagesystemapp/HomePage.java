package com.example.storagesystemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    private Button logout;
    private Button compartment1;
    private Button compartment2;
    private Button compartment3;
    private Button compartment4;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logout = (Button)findViewById(R.id.SignOut);

        compartment4 = (Button)findViewById(R.id.comp4);
        compartment4.setOnClickListener(this);

        compartment3 = (Button)findViewById(R.id.comp3);
        compartment3.setOnClickListener(this);

        compartment2 = (Button)findViewById(R.id.comp2);
        compartment2.setOnClickListener(this);

        compartment1 = (Button)findViewById(R.id.comp1);
        compartment1.setOnClickListener(this);


        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               // FirebaseAuth.getInstance().signOut();
                //startActivity(new Intent(HomePage.this, MainActivity.class));

                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();

                finish();



            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comp4:
                startActivity(new Intent(this,Compartment4.class));
                break;
            case R.id.comp3:
                startActivity(new Intent(this,Compartment3.class));
                break;
            case R.id.comp2:
                startActivity(new Intent(this,Compartment2.class));
                break;
            case R.id.comp1:
                startActivity(new Intent(this,Compartment1.class));
                break;


        }
    }

}