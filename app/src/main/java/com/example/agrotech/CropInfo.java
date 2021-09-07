package com.example.agrotech;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CropInfo extends AppCompatActivity {
    // String m="";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_info);
        Intent intent = getIntent();
        // m = intent.getStringExtra("m");
        t =(TextView)findViewById(R.id.txtv);



        DBAdapter myDb = new DBAdapter(CropInfo.this);
        Cursor res = myDb.getAllCropPriceDetails();
        if(res.getCount() == 0) {
            // show message
            t.setText("No Crop Details Found");
            return;
        }
//  String price="CREATE TABLE cropprice( cropid INTEGER PRIMARY KEY AUTOINCREMENT,farmer_mobile TEXT,crop_name Text not null,crop_price integer not null, pdate DATE DEFAULT (datetime('now','localtime')))";
//
        StringBuffer buffer = new StringBuffer();
        buffer.append("       LAST CROP PRICE DETAILS    \n\n");
        while (res.moveToNext()) {
            buffer.append("CTOP ID\t:\t"+res.getInt(0)+"\n");
            buffer.append("FARMER MOBILE NO\t:\t"+res.getString(1)+"\n");
            buffer.append("CROP NAME\t:\t"+res.getString(2)+"\n");
            buffer.append("CROP PRICE\t:\t"+res.getInt(3)+"\n");
            buffer.append("LAST UPDATED DATE\t:\t"+res.getString(4)+"\n\n");
        }
        myDb.close();

        // Show all data
        t.setText(buffer.toString());

    }

}

