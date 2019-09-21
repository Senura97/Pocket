package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button intent;
    private Button intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = findViewById(R.id.intent);

        intent1 = findViewById(R.id.intent1);

        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,account.class));
            }
        });

        intent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                startActivity(new Intent(MainActivity.this,viewaccounts.class));
            }
        });
    }
}
