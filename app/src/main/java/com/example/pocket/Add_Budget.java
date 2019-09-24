package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Database.BudgetDbHelper;

public class Add_Budget extends AppCompatActivity  implements View.OnClickListener {

    public final static String BUDGET_ACTIVITY_MODE = "BUDGET_ACTIVITY_MODE";
    public final static String DISPLAY_BUDGET_ID = "DISPLAY_NOTE_ID";


    private Budget budgeToDisplay = null;



    BudgetDbHelper budgetDbHelper;
    EditText budgetDate;
    EditText budgetCategory;
    EditText budgetAmmount;
    AddBudgetMode mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__budget);


        this.budgetDbHelper = new BudgetDbHelper(this);


        Button button = findViewById(R.id.addBudget);
        button.setOnClickListener(this);



        Button button1 = findViewById(R.id.dlt);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetDbHelper.deleteBudget(budgeToDisplay.id);
                finish();
            }
        });

        budgetDate = findViewById(R.id.datePick);
        budgetCategory = findViewById(R.id.categoryPick);
        budgetAmmount = findViewById(R.id.ammountPick);


        Intent recievedIntent = getIntent();
        this.mode = AddBudgetMode.valueOf(recievedIntent.getExtras().getString(BUDGET_ACTIVITY_MODE));

        if(this.mode == AddBudgetMode.DISPLAY){

            int currentBudgetId = recievedIntent.getExtras().getInt(DISPLAY_BUDGET_ID);
            Log.i("OTE_EDITOR",String.valueOf(currentBudgetId));
            this.budgeToDisplay = budgetDbHelper.getBudget(currentBudgetId);
            budgetDate.setText(budgeToDisplay.date);
            budgetCategory.setText(budgeToDisplay.category);
            budgetAmmount.setText(budgeToDisplay.ammount);
        }

    }

    private Budget getBudget(){
        Budget b = new Budget();
        b.date = this.budgetDate.getText().toString();
        b.category = this.budgetCategory.getText().toString();
        b.ammount = this.budgetAmmount.getText().toString();


        return b;
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();

        this.budgetDbHelper.close();
    }


    @Override
    public void onClick(View view) {

        Budget newBudget = this.getBudget();
        boolean result;

        if (budgeToDisplay != null){
            result = this.budgetDbHelper.updateBudget(newBudget.date,newBudget.category,newBudget.ammount,budgeToDisplay.id);
        }
        else {
            result = this.budgetDbHelper.insertData(newBudget.date,newBudget.category,newBudget.ammount);
        }

        if (result != false){
            finish();
        }

    }
}
