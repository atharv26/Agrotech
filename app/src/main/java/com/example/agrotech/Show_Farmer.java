package com.example.agrotech;

import android.content.Intent;
import android.database.Cursor;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrotech.Choose_User;

public class Show_Farmer extends AppCompatActivity {
    // String m="";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__farmer);


        Intent intent = getIntent();
        // m = intent.getStringExtra("m");
        t =(TextView)findViewById(R.id.txtv);



        DBAdapter myDb = new DBAdapter(Show_Farmer.this);
        Cursor res = myDb.getAllFarmerDetails();
        if(res.getCount() == 0) {
            // show message
            t.setText("No Farmer Details Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("       FARMER DETAILS    \n\n");
        while (res.moveToNext()) {
            buffer.append("ID\t:\t"+res.getInt(0)+"\n");
            buffer.append("NAME\t:\t"+res.getString(1)+" "+res.getString(2)+"\n");
            buffer.append("MOBILE\t:\t"+res.getString(3)+"\n");
            buffer.append("ADDRESS\t:\t"+res.getString(5)+"\n\n");


        }
        myDb.close();

        // Show all data
        t.setText(buffer.toString());

    }

}

