package com.example.pocket;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import Database.DBHelper;

public class login extends AppCompatActivity {

    DBHelper dbHelper;
    EditText email, password;
    Button btnSignUp, btnSignIn;
    private DBHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        mDBHelper = new DBHelper(this);



        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SQLiteDatabase db = mDBHelper.getReadableDatabase();

                //if(validateLogin()==true){
                    Cursor c = db.rawQuery("SELECT * FROM user WHERE email= "
                                    + email.getText().toString().trim() + "'" +
                                    "AND password='"+password.getText().toString().trim()+"'"
                            , null);

                    if (c.moveToFirst()) {

                        String loginEmail = c.getString(1);
                        String loginPassword = c.getString(2);

                        Intent intent = new Intent(login.this, MainActivity.class);
                        intent.putExtra("key_email", loginEmail);

                        startActivity(intent);
                        clearText();

                    } else {

                        showMessage("Oops", "Username and Password does not match");
                        clearText();
                    }
                }
            //}
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(validateLogin()==true){
                addUser();
                startActivity(new Intent(login.this, MainActivity.class)); }


            }

        });
    }
            public void addUser(){

                String uemail = email.getText().toString();
                String upass = password.getText().toString();
                if(dbHelper.addUserInfo(uemail,upass)){
                    Toast t = Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(),"Registration Unsuccessful",Toast.LENGTH_SHORT);
                    t.show();
                }
            }

            public boolean validateLogin(){
                if (email.getText().toString().length() == 0) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return false;
                }
                if (password.getText().toString().length() == 0) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return false;
                }
                else if (password.getText().toString().length() < 8) {
                    password.setError("Password should be at least of 8 characters");
                    password.requestFocus();
                    return false;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                    return false;
                }
                else
                    return true;
            }

    public void showMessage(String title,String message)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        email.setText("");
        password.setText("");
    }

    }






