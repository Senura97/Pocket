package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Database.DBHelper;

public class account extends AppCompatActivity {

    Button add;
    EditText name, bank, no, amount;
    Spinner type;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        dbHelper = new DBHelper(this);
        add = (Button)findViewById(R.id.addaccount);
        name = (EditText)findViewById(R.id.editText6);
        bank = (EditText)findViewById(R.id.editText11);
        no = (EditText)findViewById(R.id.editText10);
        amount = (EditText)findViewById(R.id.editText9);
        type = (Spinner)findViewById(R.id.spinner2);

        //add.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {

                //String aname = name.getText().toString().trim();

                // long status =  dbHelper.addInfo(aname.......);

/*                if(status == 1){
                    Toast.makeText(account.this, "OK", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),//));

                }

                else{

                    name.setText(" ");
                }*/

            }
            public void onClick(View v){

                switch (v.getId()){
                    case R.id.addaccount:
                        addUser();
                        break;
                }
            }

            public void addUser(){

            String aname = name.getText().toString();
            String abank = bank.getText().toString();
            String ano = no.getText().toString();
            String amt = amount.getText().toString();
            String atype = type.getSelectedItem().toString();
            if(!aname.equals("") && !abank.equals("") && !(ano==null) && !(amt==null) && !atype.equals("")){
                if(dbHelper.addInfo(aname,abank,ano,amt,atype)){
                    Toast t = Toast.makeText(getApplicationContext(),"Successfully inserted new account",Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(),"Cannot insert account",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
            else{
                Toast t = Toast.makeText(getApplicationContext(),"Empty Fields",Toast.LENGTH_SHORT);
                t.show();
            }

            }

    }


