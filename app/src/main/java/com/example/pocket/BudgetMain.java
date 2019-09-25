package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.BudgetDbHelper;

public class BudgetMain extends AppCompatActivity {

    private ListView listView;

    private ArrayList<Budget> budgetList;

    BudgetDbHelper budgetDbHelper;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.budgetDbHelper.close();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_main);

        this.budgetDbHelper = new BudgetDbHelper(this);
        EditText theFilter = (EditText)findViewById(R.id.searchFilter);

        FloatingActionButton floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addBudgetIntent = new Intent(BudgetMain.this,Add_Budget.class);
                addBudgetIntent.putExtra(Add_Budget.BUDGET_ACTIVITY_MODE,AddBudgetMode.ADD.toString());

                startActivity(addBudgetIntent);



            }
        });

        listView = (ListView)findViewById(R.id.budget_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent viewBudgetIntent = new Intent(BudgetMain.this,Add_Budget.class);
                viewBudgetIntent.putExtra(Add_Budget.BUDGET_ACTIVITY_MODE,AddBudgetMode.DISPLAY.toString());
                int budgetId = budgetList.get(i).id;
                viewBudgetIntent.putExtra(Add_Budget.DISPLAY_BUDGET_ID,budgetId);
                startActivity(viewBudgetIntent);
            }
        });


        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (BudgetMain.this).arrayAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }
    @Override
    protected void onResume(){
        super.onResume();

        budgetList = this.budgetDbHelper.getAllBudgets();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,budgetList);
        final EditText theFilter = (EditText) findViewById(R.id.searchFilter);
        listView.setAdapter(arrayAdapter);

    }



}
