package com.example.sun.signin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1, et2;
    Button bt;
    TextView title, tv;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.password);
        bt = (Button) findViewById(R.id.signin);
        title = (TextView) findViewById(R.id.allacount);
        tv = (TextView) findViewById(R.id.resister);
        bt.setOnClickListener(this);
        title.setOnClickListener(this);
        tv.setOnClickListener(this);

        helper = new DatabaseHelper(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin: {
                String name = et1.getText().toString();
                String password = et2.getText().toString();

                if (name.contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                } else {

                    Cursor cAll = helper.showall();
                    cAll.moveToFirst();
                    int flag = 0;
//                    Toast.makeText(getApplicationContext(), "okok", Toast.LENGTH_SHORT).show();
                    do {
                        String cName = cAll.getString(cAll.getColumnIndex("Name"));
                        if (cName.contentEquals(name)) {
                            flag++;
                        }
                    } while (cAll.moveToNext());
                    if (flag != 0) {
                        Cursor c = helper.show(name);
                        c.moveToFirst();
                        String cPassword = c.getString(c.getColumnIndex("Password"));

                        if (cPassword.contentEquals(password)) {
                            Intent i = new Intent(MainActivity.this, Result.class);
                            i.putExtra("NAME", name);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "The Name was NOT Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case R.id.resister: {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
            break;
            case R.id.allacount: {
                Intent i = new Intent(MainActivity.this, AllAccount.class);
                startActivity(i);
            }
        }
    }
}
