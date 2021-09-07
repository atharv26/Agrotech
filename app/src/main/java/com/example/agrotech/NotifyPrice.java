package com.example.agrotech;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Collections;

public class NotifyPrice extends AppCompatActivity implements View.OnClickListener {
    String m="";
    EditText ed,ed1,ed2;
    Button btn1, btn2;
    Spinner crop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_price);
        Intent intent = getIntent();
        m = intent.getStringExtra("m");
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void onClick(View v) {
        String s="",s1="",s2="";
        int price=0;
        if (v == btn2) {
            ed1.setText("");
            ed2.setText("");
            Toast.makeText(getApplicationContext(), "Reset the form successfully", Toast.LENGTH_SHORT).show();
        } else if (v == btn1) {
             s1=ed1.getText().toString();
            s2=ed2.getText().toString();

            if (TextUtils.isEmpty(s1)) {
                ed1.setError("Please enter title!");
            }
            else if (TextUtils.isEmpty(s2)) {
                ed2.setError("Please enter mobile no!");
            }
            else {
                DBAdapter myDb = new DBAdapter(NotifyPrice.this);
                Cursor res = myDb.getAllCropPriceDetails();
                StringBuffer buffer = new StringBuffer();
                if(res.getCount() == 0) {
                    // show message
                    buffer.append("No Crop Details Found");
                    return;
                }


                buffer.append("       LAST CROP PRICE DETAILS    \n\n");
                while (res.moveToNext()) {
                    buffer.append("CTOP ID\t:\t"+res.getInt(0)+"\n");
                    buffer.append("FARMER MOBILE NO\t:\t"+res.getString(1)+"\n");
                    buffer.append("CROP NAME\t:\t"+res.getString(2)+"\n");
                    buffer.append("CROP PRICE\t:\t"+res.getInt(3)+"\n");
                    buffer.append("LAST UPDATED DATE\t:\t"+res.getString(4)+"\n\n");
                }
                myDb.close();
                //CropBean b = new CropBean();
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle(s1).setContentText(buffer.toString()+"\nIn case of any query call us in (M)"+m).
                        setContentTitle(s1).setSmallIcon(R.drawable.farmer1).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
                if (s2 != null) {


                    sendSmsMsgFnc(s2, "WELCOME TO AGROTECH APP\nDear Customer \nThanks for showing interest in our APP.\nPlease go thru the prices set for today.\n"+buffer.toString()+"\nFor more update you can contact us in (M) :"+m);
                }

                Toast.makeText(getApplicationContext(), "Price changes successfully for today,send notification", Toast.LENGTH_SHORT).show();



            }
        }

    }
    void sendSmsMsgFnc(String mblNumVar, String smsMsgVar)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            try
            {
                SmsManager smsMgrVar = SmsManager.getDefault();
                smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent successfully to Customer",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ErrVar)
            {
                Toast.makeText(getApplicationContext(),ErrVar.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }

    }
}