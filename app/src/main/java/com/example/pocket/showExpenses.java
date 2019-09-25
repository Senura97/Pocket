package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBHelper;

public class showExpenses extends AppCompatActivity {

    SimpleAdapter adapter;
    Intent intent;
    Button addact;
    ImageView viewActBk;
    ImageButton updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses);

        updatebtn = (ImageButton) findViewById(R.id.btnUpdateExp);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(showExpenses.this, editExpenses.class));
            }
        });


        DBHelper db = new DBHelper(this);
        ArrayList<HashMap<String,String>> expList = db.GetExpenses();
        ListView listv = (ListView) findViewById(R.id.expenselist);
        EditText theExFilter = (EditText)findViewById(R.id.searchvalue);

        adapter = new SimpleAdapter(showExpenses.this,expList,R.layout.list_row_expenses,new String[]{"type","date","amount"},new int[]{R.id.showtype,R.id.showdate,R.id.showamount});
        listv.setAdapter(adapter);

        /*Button addact = (Button)findViewById(R.id.addact);
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
        });*/

        theExFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (showExpenses.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}

