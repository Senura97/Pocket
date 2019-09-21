package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBHelper;

public class viewaccounts extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewaccounts);

        DBHelper db = new DBHelper(this);
        ArrayList<HashMap<String,String>> accList = db.GetAccounts();
        ListView lv = (ListView) findViewById(R.id.acc_list);
        ListAdapter adapter = new SimpleAdapter(viewaccounts.this,accList,R.layout.list_row,new String[]{"name","amount"},new int[]{R.id.amount,R.id.title});
        lv.setAdapter(adapter);
        Button addact = (Button)findViewById(R.id.addact);
    }

}