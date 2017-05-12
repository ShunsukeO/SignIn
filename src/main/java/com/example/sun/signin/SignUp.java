package com.example.sun.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText et1, et2, et3, et4, et5;
    Button bt1, bt2;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.birthday);
        et3 = (EditText) findViewById(R.id.phone);
        et4 = (EditText) findViewById(R.id.email);
        et5 = (EditText) findViewById(R.id.password);
        bt1 = (Button) findViewById(R.id.ok);
        bt2 = (Button) findViewById(R.id.back);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

        helper = new DatabaseHelper(SignUp.this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(SignUp.this, MainActivity.class);
        switch (v.getId()) {
            case R.id.ok: {
                String name = et1.getText().toString();
                String birthday = et2.getText().toString();
                String phone = et3.getText().toString();
                String email = et4.getText().toString();
                String password = et5.getText().toString();

                if (name.contentEquals("") && birthday.contentEquals("") && phone.contentEquals("") && email.contentEquals("") && password.contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Fill the Form", Toast.LENGTH_SHORT).show();
                } else {
                    int s = helper.insert(name, birthday, phone, email, password);
                    if (s == 1) {
                        Toast.makeText(getApplicationContext(), "Resistered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Resistered. Try Again", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(i);
                }

            }
            break;
            case R.id.back: {
                startActivity(i);
            }
            break;
        }
    }
}
