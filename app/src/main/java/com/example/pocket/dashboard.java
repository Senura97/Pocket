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

        incomeCardView = (CardView) findViewById(R.id.incomeCardView);
        expensesCardView = (CardView) findViewById(R.id.expensesCardView);
        accountsCardView = (CardView) findViewById(R.id.accountsCardView);
        budgetCardView = (CardView) findViewById(R.id.budgetCardView);

        /*incomeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, .class));
            }
        });

        incomeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, .class));
            }
        });

        incomeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, .class));
            }
        });*/

        expensesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, buttonpage.class));
            }
        });


    }

}
