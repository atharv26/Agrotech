package com.example.agrotech;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class CRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2, ed3, ed4, ed5,ed6;
    Button btn1,btn2;

    String a="";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cregistration);
        ed1 = findViewById(R.id.input_name);
        ed2 = findViewById(R.id.input_email);
        ed3 = findViewById(R.id.input_mobile);
        ed4 = findViewById(R.id.input_address);
        ed5 = findViewById(R.id.input_password);
        ed6 = findViewById(R.id.input_reEnterPassword);

        btn1 = (Button) findViewById(R.id.b1);
        btn2 = (Button) findViewById(R.id.b2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v==btn2)
        {
            Intent intent = new Intent(CRegistration.this, CLogin.class);

           // intent.putExtra("m", a);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Login Selected", Toast.LENGTH_SHORT).show();
        }
        else if (v==btn1)
        {
            a = ed1.getText().toString();
            String b = ed2.getText().toString();
            String c = ed3.getText().toString();
            String d = ed4.getText().toString();
            String e = ed5.getText().toString();
            String f = ed6.getText().toString();


            if (TextUtils.isEmpty(a)) {
                ed1.setError("please enter customerfirstname");
            }
              else if (e.equals(f)==false) {
                    ed6.setError("password and confirm password does not match");
                }
            else if (TextUtils.isEmpty(b)) {
                ed2.setError("please enter mobile no");
            }

            else if (TextUtils.isEmpty(c)) {
                ed3.setError("enter email");
            }
            else if (TextUtils.isEmpty(d)) {
                ed4.setError("please enter address");
            }
            else if (TextUtils.isEmpty(e)) {
                ed5.setError("enter password");
            }
            else if (TextUtils.isEmpty(f)) {
                ed6.setError("enter confirm password");
            }
            else {
                DBAdapter dbAdapter = new DBAdapter(CRegistration.this);
               CustomerBean  b1 = new CustomerBean();
                b1.setcustomer_firstname(a);
                b1.setcustomer_mobilenumber(c);
                b1.setcustomer_email(b);
                b1.setcustomer_address(d);
                b1.setcustomer_password(e);

                dbAdapter = new DBAdapter(CRegistration.this);
                dbAdapter.addCustomer(b1);

                Toast.makeText(getApplicationContext(), "New Customer added successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(CRegistration.this,CLogin.class);
                intent.putExtra("m", a);
                startActivity(intent);

            }
        }

    }

}

