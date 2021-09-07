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
import android.telephony.SmsManager;
import androidx.appcompat.app.AppCompatActivity;

public class Select_Option extends AppCompatActivity {
    String m="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__option);
        Intent intent = getIntent();
        m = intent.getStringExtra("m");
        Button b1 = (Button) findViewById(R.id.btn1);
        Button b2 = (Button) findViewById(R.id.btn2);
        Button b3 = (Button) findViewById(R.id.btn3);
        Button b4 = (Button) findViewById(R.id.btn4);



// perform click event on button's
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Show Customer Details", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option.this, Show_Customer.class);
               //i.putExtra("m", m);
                 startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Change Crop Price", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option.this,  Change_Price.class);
                i.putExtra("m", m);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Notify/Send Message", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option.this,  NotifyPrice.class);
                i.putExtra("m", m);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Back to Main", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Select_Option.this,Choose_User.class);
                //  i.putExtra("m", m);
                startActivity(i);
            }
        });

    }

}




















