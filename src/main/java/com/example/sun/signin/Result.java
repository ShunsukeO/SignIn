package com.example.sun.signin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv1 = (TextView) findViewById(R.id.logout);
        tv2 = (TextView) findViewById(R.id.success);
        tv3 = (TextView) findViewById(R.id.info);
        tv1.setOnClickListener(this);
        helper = new DatabaseHelper(Result.this);

        Intent i = getIntent();
        String name = i.getExtras().getString("NAME");
        Cursor c = helper.show(name);
        c.moveToFirst();

        String birthday = c.getString(c.getColumnIndex("Birthday"));
        String phone = c.getString(c.getColumnIndex("Phone"));
        String email = c.getString(c.getColumnIndex("Email"));
        String password = c.getString(c.getColumnIndex("Password"));

        tv2.setText("Hi, " + name + "\nSuccessed Sign in!");
        tv3.setText("Name: " + name + "\nBirthday: " + birthday + "\nPhone: " + phone + "\nEmail: " + email + "\nPassword: " + password);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Result.this, MainActivity.class);
        startActivity(i);
    }
}
