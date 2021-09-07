package com.example.agrotech;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class Change_Price extends AppCompatActivity implements View.OnClickListener {
    String m="";
    EditText ed,ed1,ed2;
    Button btn1, btn2;
    Spinner crop;

    String crop_name_string, soil_type_string;
    private String[] crop_name = new String[]{"Onion","Potato","Wheat", "Maize", "Millet", "Sorghum", "Barley", "Tomato", "Potato", "Vegetables", "Alfalfa", "Dates", "Citrus", "Grapes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__price);
        Intent intent = getIntent();
        m = intent.getStringExtra("m");
        ed = findViewById(R.id.ed);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);

        crop=(Spinner)findViewById(R.id.crop);
        ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,crop_name);
        crop.setAdapter(a);
        crop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {@Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long arg3) {


            crop_name_string=(String) crop.getSelectedItem();

        }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_role=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,crop_name);
        adapter_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crop.setAdapter(adapter_role);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void onClick(View v) {
        String s="",s1="",s2="";
        int price=0;
        if (v == btn2) {
            ed.setText("");
            ed1.setText("");
            ed2.setText("");
            Toast.makeText(getApplicationContext(), "Reset the form successfully", Toast.LENGTH_SHORT).show();
        } else if (v == btn1) {
             s=ed.getText().toString();
            s1=ed1.getText().toString();
            s2=ed2.getText().toString();
             price = Integer.parseInt(s);

            if (TextUtils.isEmpty(s)) {
                ed.setError("Please enter price!");
            }
            else  if (TextUtils.isEmpty(s1)) {
                ed1.setError("Please enter customer name !");
            }
            else if (TextUtils.isEmpty(s2)) {
                ed2.setError("Please enter mobile no!");
            }
            else {
                //CropBean b = new CropBean();
                DBAdapter dbAdapter = new DBAdapter(Change_Price.this);
                dbAdapter.updatePrice(crop_name_string,price);
                dbAdapter.addPrice(m,crop_name_string,price);
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle(s2).setContentText("Crop Name :"+crop_name_string+" Crop Price :"+price+"\nIn case of any query call us in (M)"+m).
                        setContentTitle(s1).setSmallIcon(R.drawable.farmer1).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
                if (s2 != null) {


                    sendSmsMsgFnc(s2, "WELCOME TO AGROTECH APP\nDear Customer "+s1+ ".\nThanks for showing interest in our APP.\nPlease go thru the prices set for today.\nCrop Name :"+crop_name_string+"\n Crop Price :"+price+"\nFor more update you can contact us in (M) : 8830745564");
                }

                Toast.makeText(getApplicationContext(), "Price changes successfully for today,Notification/SMS sent", Toast.LENGTH_SHORT).show();
                dbAdapter.close();
                //Intent intent =new Intent(Change_Price.this,FarmerOutput.class);
                //intent.putExtra("crop_name_string", crop_name_string);
              //  intent.putExtra("area", area);
              //  intent.putExtra("m", m);
              //  startActivity(intent);

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