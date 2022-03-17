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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

public class Compartment1 extends AppCompatActivity {

    private ImageView compImag;


    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();

    private DatabaseReference childreference=reference.child("Compartment Photo");
    private DatabaseReference childofchildreference = childreference.child("Compartment1");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartment1);

        compImag=findViewById(R.id.img1);


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Compartment1.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Compartment1));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i ==1){
                    startActivity (new Intent(Compartment1.this, HomePage.class));
                }else
                if(i ==2){
                    startActivity (new Intent(Compartment1.this, Compartment2.class));
                } else
                if(i ==3){
                    startActivity (new Intent(Compartment1.this, Compartment3.class));
                } else
                if(i ==4){
                    startActivity (new Intent(Compartment1.this, Compartment4.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

}
