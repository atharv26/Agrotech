package com.example.agrotech;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class cropd extends AppCompatActivity {
   // String m="";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropd);
        Intent intent = getIntent();
       // m = intent.getStringExtra("m");
        t =(TextView)findViewById(R.id.txtv);



        DBAdapter myDb = new DBAdapter(cropd.this);
        Cursor res = myDb.getAllCropDetails();
        if(res.getCount() == 0) {
            // show message
            t.setText("No Crop Details Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("       CROP DETAILS    \n\n");
         while (res.moveToNext()) {
             buffer.append("CTOP_ID\t:\t"+res.getInt(0)+"\n");
             buffer.append("CROP NAME\t:\t"+res.getString(1)+"\n");
             buffer.append("CROP_PRICE\t:\t"+res.getString(2)+"\n\n");

        }
        myDb.close();

        // Show all data
        t.setText(buffer.toString());

    }

}

