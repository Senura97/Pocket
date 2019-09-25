package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    private CardView incomeCardView;
    private CardView expensesCardView;
    private CardView accountsCardView;
    private CardView budgetCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        incomeCardView = findViewById(R.id.incomeCardView);
        expensesCardView = (CardView) findViewById(R.id.expensesCardView);
        accountsCardView = findViewById(R.id.accountsCardView);
        budgetCardView = findViewById(R.id.budgetCardView);

        expensesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpenses();
            }
        });

        /*expensesCardView.setOnClickListener();{
            startActivity(new Intent(dashboard.this,Expenses.class));

        });*/

    }


    private void openExpenses() {

        Intent intent = new Intent(this, Expenses.class);
        startActivity(intent);
    }
}
