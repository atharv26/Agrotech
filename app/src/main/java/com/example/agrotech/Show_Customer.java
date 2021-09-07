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

public class Show_Customer extends AppCompatActivity {
    // String m="";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__customer);
        Intent intent = getIntent();
        t =(TextView)findViewById(R.id.txtv);



        DBAdapter myDb = new DBAdapter(Show_Customer.this);
        Cursor res = myDb.getAllCustomerDetails();
        if(res.getCount() == 0) {
            // show message

            t.setText("No customer Details Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("       CUSTOMER DETAILS    \n\n");
        while (res.moveToNext()) {
            buffer.append("ID\t:\t"+res.getInt(0)+"\n");
            buffer.append("NAME\t:\t"+res.getString(1)+"\n");
            buffer.append("MOBILE\t:\t"+res.getString(2)+"\n");
            buffer.append("EMAIL\t:\t"+res.getString(3)+"\n");
            buffer.append("ADDRESS\t:\t"+res.getString(4)+"\n");


        }
        myDb.close();

        // Show all data
        t.setText(buffer.toString());

    }

}

