package com.example.pocket;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBHelper;

public class viewaccounts extends AppCompatActivity {


    SimpleAdapter adapter;
    Intent intent;
    Button addact;
    ImageView viewActBk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewaccounts);

        DBHelper db = new DBHelper(this);
        ArrayList<HashMap<String,String>> accList = db.GetAccounts();
        ListView lv = (ListView) findViewById(R.id.acc_list);
        EditText theFilter = (EditText)findViewById(R.id.searchFilter);

        adapter = new SimpleAdapter(viewaccounts.this,accList,R.layout.list_row,new String[]{"name","amount"},new int[]{R.id.amount,R.id.title});
        lv.setAdapter(adapter);

        Button addact = (Button)findViewById(R.id.addact);
        ImageView viewActBk = (ImageView) findViewById(R.id.viewActBk);

        viewActBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewaccounts.this,account.class));
            }
        });


        addact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewaccounts.this,account.class));
            }
        });

        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (viewaccounts.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



}