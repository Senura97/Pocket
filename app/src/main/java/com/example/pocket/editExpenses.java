package com.example.pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class editExpenses extends AppCompatActivity implements View.OnClickListener {

    BottomSheetDialog bottomSheetDialog;
    TextView categorychange;
    ImageButton categorySelector;
    LinearLayout auto, charity, childcare, clothing, eatingout, education, entertainment, groceries, healthFitness, medical, rent, tax, pets, transport, travel, utilities, household, insurance, cash, creditcard;
    public static EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expenses);

        dateText = (EditText)findViewById(R.id.dateText);
        categorychange = findViewById(R.id.categorychange);
        categorySelector = findViewById(R.id.categorySelector);

        createBottomSheetDialog();
    }


    public void btnpickdate(View view) {

        DialogFragment fragment = new DatePicker();
        fragment.show(getSupportFragmentManager(),"Date Picker");

    }

    public  static  void populateSetDateText(int year, int month, int day){
        dateText.setText(month+ "/" +day+ "/" +year);
    }

    private  void createBottomSheetDialog(){
        if(bottomSheetDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.category_bottom_sheet,null);

            auto = view.findViewById(R.id.auto);
            charity = view.findViewById(R.id.charity);
            childcare = view.findViewById(R.id.childcare);
            clothing = view.findViewById(R.id.clothing);
            eatingout  = view.findViewById(R.id.eatingout);
            education = view.findViewById(R.id.education);
            entertainment = view.findViewById(R.id.entertainment);
            groceries = view.findViewById(R.id.groceries);
            healthFitness = view.findViewById(R.id.healthFitness);
            medical = view.findViewById(R.id.medical);
            rent = view.findViewById(R.id.rent);
            tax = view.findViewById(R.id.tax);
            pets = view.findViewById(R.id.pets);
            transport = view.findViewById(R.id.transport);
            travel  = view.findViewById(R.id.travel);
            utilities = view.findViewById(R.id.utilities);
            household = view.findViewById(R.id.household);
            insurance = view.findViewById(R.id.insurance);
            cash = view.findViewById(R.id.cash);
            creditcard = view.findViewById(R.id.creditcard);



            auto.setOnClickListener(this);
            charity.setOnClickListener(this);
            childcare.setOnClickListener(this);
            clothing.setOnClickListener(this);
            eatingout.setOnClickListener(this);
            education.setOnClickListener(this);
            entertainment.setOnClickListener(this);
            groceries.setOnClickListener(this);
            healthFitness.setOnClickListener(this);
            medical.setOnClickListener(this);
            rent.setOnClickListener(this);
            tax.setOnClickListener(this);
            pets.setOnClickListener(this);
            transport.setOnClickListener(this);
            travel.setOnClickListener(this);
            utilities.setOnClickListener(this);
            household.setOnClickListener(this);
            insurance.setOnClickListener(this);
            cash.setOnClickListener(this);
            creditcard.setOnClickListener(this);


            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto:
                categorychange.setText("Auto");
                categorySelector.setImageResource(R.drawable.auto);
                bottomSheetDialog.dismiss();
                break;

            case R.id.charity:
                categorychange.setText("Charity");
                categorySelector.setImageResource(R.drawable.charity);
                bottomSheetDialog.dismiss();
                break;

            case R.id.childcare:
                categorychange.setText("Childcare");
                categorySelector.setImageResource(R.drawable.childcare);
                bottomSheetDialog.dismiss();
                break;

            case R.id.clothing:
                categorychange.setText("Clothing");
                categorySelector.setImageResource(R.drawable.clothing);
                bottomSheetDialog.dismiss();
                break;

            case R.id.eatingout:
                categorychange.setText("Eating Out");
                categorySelector.setImageResource(R.drawable.eatingout);
                bottomSheetDialog.dismiss();
                break;

            case R.id.education:
                categorychange.setText("Education");
                categorySelector.setImageResource(R.drawable.education);
                bottomSheetDialog.dismiss();
                break;

            case R.id.entertainment:
                categorychange.setText("Entertainment");
                categorySelector.setImageResource(R.drawable.entertainment);
                bottomSheetDialog.dismiss();
                break;

            case R.id.groceries:
                categorychange.setText("Groceries");
                categorySelector.setImageResource(R.drawable.groceries);
                bottomSheetDialog.dismiss();
                break;

            case R.id.healthFitness:
                categorychange.setText("Health & Fitness");
                categorySelector.setImageResource(R.drawable.health);
                bottomSheetDialog.dismiss();
                break;

            case R.id.medical:
                categorychange.setText("Medical");
                categorySelector.setImageResource(R.drawable.medical);
                bottomSheetDialog.dismiss();
                break;

            case R.id.rent:
                categorychange.setText("Rent");
                categorySelector.setImageResource(R.drawable.rent);
                bottomSheetDialog.dismiss();
                break;

            case R.id.tax:
                categorychange.setText("Taxes");
                categorySelector.setImageResource(R.drawable.tax);
                bottomSheetDialog.dismiss();
                break;

            case R.id.pets:
                categorychange.setText("Pets");
                categorySelector.setImageResource(R.drawable.pets);
                bottomSheetDialog.dismiss();
                break;

            case R.id.transport:
                categorychange.setText("Transport");
                categorySelector.setImageResource(R.drawable.transport);
                bottomSheetDialog.dismiss();
                break;

            case R.id.travel:
                categorychange.setText("Travel");
                categorySelector.setImageResource(R.drawable.travel);
                bottomSheetDialog.dismiss();
                break;

            case R.id.utilities:
                categorychange.setText("Utilities");
                categorySelector.setImageResource(R.drawable.utilities);
                bottomSheetDialog.dismiss();
                break;

            case R.id.household:
                categorychange.setText("Household");
                categorySelector.setImageResource(R.drawable.household);
                bottomSheetDialog.dismiss();
                break;

            case R.id.insurance:
                categorychange.setText("Insurance");
                categorySelector.setImageResource(R.drawable.insurance);
                bottomSheetDialog.dismiss();
                break;

            case R.id.cash:
                categorychange.setText("Cash Payments");
                categorySelector.setImageResource(R.drawable.cash);
                bottomSheetDialog.dismiss();
                break;

            case R.id.creditcard:
                categorychange.setText("Card Payments");
                categorySelector.setImageResource(R.drawable.creditcard);
                bottomSheetDialog.dismiss();
                break;

            default:
                categorychange.setText("Categories");
                categorySelector.setImageResource(R.drawable.ic_stars_black_24dp);
                bottomSheetDialog.dismiss();
                break;


        }

    }

    public void showDialog(View view) {

        bottomSheetDialog.show();
    }


}
