package com.example.agrotech;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Select_Option_Customer extends AppCompatActivity {
    // String m="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__option__customer);

        Button b1 = (Button) findViewById(R.id.btn1);
        Button b2 = (Button) findViewById(R.id.btn2);
        Button b4 = (Button) findViewById(R.id.btn4);



// perform click event on button's
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Show Crop Details", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option_Customer.this, CropInfo.class);
                // i.putExtra("m", m);
                 startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Show Crop Details", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option_Customer.this,  Show_Farmer.class);
                // i.putExtra("m", m);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Back to Main", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option_Customer.this, Choose_User.class);
                //  i.putExtra("m", m);
                startActivity(i);
            }
        });

    }}




















