package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class buttonpage extends AppCompatActivity {

    private CardView expensesListCardView;
    private CardView addExpCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonpage);

        addExpCardView = findViewById(R.id.addExpCardView);
        expensesListCardView = findViewById(R.id.expensesListCardView);

        addExpCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buttonpage.this, Expenses.class));
            }
        });

        expensesListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buttonpage.this, showExpenses.class));
            }
        });
    }
}
