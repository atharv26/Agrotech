package com.example.agrotech;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Choose_User extends AppCompatActivity {
   // String m="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__user);

        ImageButton b1 = (ImageButton) findViewById(R.id.b1);
        ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        ImageButton b4 = (ImageButton) findViewById(R.id.b4);



// perform click event on button's
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Home Button", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Choose_User.this, Login.class);
               // i.putExtra("m", m);
              //  startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Crop Info Button", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Choose_User.this,  cropd.class);
               // i.putExtra("m", m);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Customer Module", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Choose_User.this, CLogin.class);
              //  i.putExtra("m", m);

                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Farmer Module", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Choose_User.this, Login.class);
              //  i.putExtra("m", m);
                startActivity(i);
            }
        });

    }}




















