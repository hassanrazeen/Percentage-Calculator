package com.example.percentagecalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.percentagecalculator.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        String amountString = Objects.requireNonNull(String.valueOf(activityMainBinding.amountTIEL.getText()));
        String percentString = Objects.requireNonNull(String.valueOf(activityMainBinding.percentageTIEL.getText()));


        activityMainBinding.amountTIEL.addTextChangedListener(watcher);
        activityMainBinding.percentageTIEL.addTextChangedListener(watcher);

        //sets the on click listener for the calculateBtn
        activityMainBinding.calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discount();

            }
        });

    }

    //method to calculate the discount amount
    public void discount() {
        String amountString = Objects.requireNonNull(String.valueOf(activityMainBinding.amountTIEL.getText()));
        String percentString = Objects.requireNonNull(String.valueOf(activityMainBinding.percentageTIEL.getText()));
        int price = Integer.parseInt(amountString);
        int percent = Integer.parseInt(percentString);

        //calculate the amount to be discounted
        int discountAmount = (price * percent) / 100;

        //calculates amount after discount
        int TotalAmount = price - discountAmount;

        //calculates how much is deducted from the actual amount
        int shortAmount = price - TotalAmount;
        String discountedPrice = Integer.toString(TotalAmount);
        String shortPrice = Integer.toString(discountAmount);

        //set the final answer to their respective layout
        activityMainBinding.discountedAmountTIET.setText(discountedPrice);
        activityMainBinding.shortAmountTIEL.setText(shortPrice);
    }


    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String amountString = Objects.requireNonNull(String.valueOf(activityMainBinding.amountTIEL.getText()).trim());
            String percentString = Objects.requireNonNull(String.valueOf(activityMainBinding.percentageTIEL.getText()).trim());
            activityMainBinding.calculateBtn.setEnabled(!amountString.isEmpty() && !percentString.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}