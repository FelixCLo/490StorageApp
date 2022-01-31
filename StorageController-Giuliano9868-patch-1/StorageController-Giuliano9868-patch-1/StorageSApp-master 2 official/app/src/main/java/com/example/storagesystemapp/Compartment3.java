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

public class Compartment3 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img3;
    private Button btnHo;
    private Button btnCom1, btnCom2, btnCom4;


    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();

    private DatabaseReference childreference=reference.child("Compartment Photo");
    private DatabaseReference childofchildreference = childreference.child("Compartment3");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartment3);


        img3=findViewById(R.id.img3);

        btnHo = (Button)findViewById(R.id.btnHo);
        btnHo.setOnClickListener(this);

        btnCom2 = (Button)findViewById(R.id.btnCom2);
        btnCom2.setOnClickListener(this);

        btnCom1 = (Button)findViewById(R.id.btnCom1);
        btnCom1.setOnClickListener(this);

        btnCom4 = (Button)findViewById(R.id.btnCom4);
        btnCom4.setOnClickListener(this);

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
                img3.setImageBitmap(bitmap);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHo:
                startActivity(new Intent(this, HomePage.class));
                break;
            case R.id.btnCom2:
                startActivity(new Intent(this, Compartment2.class));
                break;
            case R.id.btnCom1:
                startActivity(new Intent(this, Compartment1.class));
                break;
            case R.id.btnCom4:
                startActivity(new Intent(this, Compartment4.class));
                break;


        }

    }
}