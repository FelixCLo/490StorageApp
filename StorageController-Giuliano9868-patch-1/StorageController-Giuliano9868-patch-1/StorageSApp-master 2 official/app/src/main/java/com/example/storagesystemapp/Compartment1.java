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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

public class Compartment1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView compImag;
    private Button btnHom;
    private Button btnComp2, btnComp3, btnComp4;


    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();

    private DatabaseReference childreference=reference.child("Compartment Photo");
    private DatabaseReference childofchildreference = childreference.child("Compartment1");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartment1);


        compImag=findViewById(R.id.img1);

        btnHom = (Button)findViewById(R.id.btnHom);
        btnHom.setOnClickListener(this);

        btnComp2 = (Button)findViewById(R.id.btnComp2);
        btnComp2.setOnClickListener(this);

        btnComp3 = (Button)findViewById(R.id.btnComp3);
        btnComp3.setOnClickListener(this);

        btnComp4 = (Button)findViewById(R.id.btnComp4);
        btnComp4.setOnClickListener(this);
    }

    @Override
    protected void onStart() {

        super.onStart();

        childofchildreference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {

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
                compImag.setImageBitmap(bitmap);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHom:
                startActivity(new Intent(this, HomePage.class));
                break;
            case R.id.btnComp2:
                startActivity(new Intent(this, Compartment2.class));
                break;
            case R.id.btnComp3:
                startActivity(new Intent(this, Compartment3.class));
                break;
            case R.id.btnComp4:
                startActivity(new Intent(this, Compartment4.class));
                break;


        }
    }
}
