package com.example.agrotech;

import android.Manifest;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2;
    EditText tv1, tv2;
    String user_name = "";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        tv1 = (EditText) findViewById(R.id.tv1);
        tv2 = (EditText) findViewById(R.id.tv2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    public void onClick(View v) {

        if (v == btn2) {
            Toast.makeText(getApplicationContext(), "Customer Registration Module", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Login.this, Registration.class);
            startActivity(intent);

        }
        if (v == btn1) {
            user_name = tv1.getText().toString();
            String pass_word = tv2.getText().toString();
            Toast.makeText(getApplicationContext(), "Customer Module selected", Toast.LENGTH_SHORT).show();


            if (TextUtils.isEmpty(user_name)) {
                tv1.setError("enter user name");
            } else if (TextUtils.isEmpty(pass_word)) {
                tv2.setError("enter password");
            }
            DBAdapter dbAdapter = new DBAdapter(Login.this);
            boolean farmerbean = dbAdapter.checkUser(user_name, pass_word);

            if (farmerbean != false) {


                Toast.makeText(getApplicationContext(), "Farmer Login successful", Toast.LENGTH_SHORT).show();
                    if (user_name != null) {


                    //sendSmsMsgFnc(user_name, "Login Alert: You are successfully logged into Smart Irrigation System.");
                }
                Intent intent = new Intent(Login.this, Select_Option.class);

                intent.putExtra("m", user_name);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Customer Login failed", Toast.LENGTH_SHORT).show();
            }
            dbAdapter.close();
        }

    }
    //8097119524



}