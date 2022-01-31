package com.example.storagesystemapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;


public class Compartment2 extends AppCompatActivity implements View.OnClickListener {
   // private TextView Compartment2;
    private ImageView img;
    private Button btnHome;
    private Button btnComp1, btnc3, btnc4;
            ;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();

    private DatabaseReference childreference=reference.child("Compartment Photo");
    private DatabaseReference childofchildreference = childreference.child("Compartment2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartment2);

       // Compartment2=findViewById(R.id.text1);
        img=findViewById(R.id.img);

        btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);

        btnComp1 = (Button)findViewById(R.id.btnComp1);
        btnComp1.setOnClickListener(this);

        btnc3 = (Button)findViewById(R.id.btnc3);
        btnc3.setOnClickListener(this);

        btnc4 = (Button)findViewById(R.id.btnc4);
        btnc4.setOnClickListener(this);


    }
    @Override
    protected void onStart(){
        super.onStart();
        childofchildreference.addValueEventListener(new ValueEventListener(){

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                    String message = dataSnapshot.getValue(String.class);


                    String base64Image = message.split(",")[1];
                    String result = "";

                    try {
                        result = URLDecoder.decode(base64Image, "UTF8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    byte[] imageBytes = Base64.getDecoder().decode(result);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    img.setImageBitmap(bitmap);

                    //Compartment2.setText(message);
                    // Picasso.get().load(imageBytes.toString()).into(img);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnHome:
                startActivity(new Intent(this, HomePage.class));
                break;
            case R.id.btnComp1:
                startActivity(new Intent(this, Compartment1.class));
                break;
            case R.id.btnc3:
                startActivity(new Intent(this, Compartment3.class));
                break;
            case R.id.btnc4:
                startActivity(new Intent(this, Compartment4.class));
                break;

        }
    }
}