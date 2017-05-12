package com.example.sun.signin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AllAccount extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button bt;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_account);

        tv = (TextView)findViewById(R.id.info);
        bt = (Button)findViewById(R.id.back);
        bt.setOnClickListener(this);
        helper = new DatabaseHelper(AllAccount.this);

        Cursor c = helper.showall();
        c.moveToFirst();
        do{
            String name = c.getString(c.getColumnIndex("Name"));
            String birthday = c.getString(c.getColumnIndex("Birthday"));
            String phone = c.getString(c.getColumnIndex("Phone"));
            String email = c.getString(c.getColumnIndex("Email"));
            String password = c.getString(c.getColumnIndex("Password"));
            tv.append("\n\nName: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone + "\nEmail: " + email + "\nPassword: " + password);
        }while(c.moveToNext());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(AllAccount.this, MainActivity.class);
        startActivity(i);
    }
}
