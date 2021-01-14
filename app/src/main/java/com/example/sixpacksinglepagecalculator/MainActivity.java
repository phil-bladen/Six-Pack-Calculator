package com.example.sixpacksinglepagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText totalCurrentWeightField;
    private EditText bodyFatField;
    private EditText weightLossRateField;

    private Button maleButton;
    private Button femaleButton;
    private Button calculateButton;

    private Double totalCurrentWeight;
    private Double bodyFatPercentage;
    private Double leanBodyMass;
    private Double goalLeanBodyMass;
    private Double rateOfWeightLoss;
    private Double goalTotalWeight;
    private Double weeksToSixPack;

    private boolean calculationReady;
    private boolean sexSelected;

    private String sex;

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCurrentWeightField = (EditText)findViewById(R.id.userTotalWeightEntered);
        bodyFatField = (EditText)findViewById(R.id.bodyFatEntered);
        weightLossRateField = (EditText)findViewById(R.id.weightLossRateEntry);
        maleButton = (Button)findViewById(R.id.maleButton);
        femaleButton = (Button)findViewById(R.id.femaleButton);
        resultText = (TextView)findViewById(R.id.resultText);
        calculateButton = (Button)findViewById(R.id.calculateButton);

        calculationReady = false;
        sexSelected = false;

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexSelected = true;
                sex = "male";
                maleButton.setBackgroundColor(getResources().getColor(R.color.teal_200));
                femaleButton.setBackgroundColor(getResources().getColor(R.color.purple_500));

            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexSelected = true;
                sex = "female";
                femaleButton.setBackgroundColor(getResources().getColor(R.color.teal_200));
                maleButton.setBackgroundColor(getResources().getColor(R.color.purple_500));

            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (calculationReady() ) {
                    totalCurrentWeight = Double.parseDouble(totalCurrentWeightField.getText().toString());
                    bodyFatPercentage = Double.parseDouble(bodyFatField.getText().toString());
                    leanBodyMass = totalCurrentWeight - totalCurrentWeight * (bodyFatPercentage / 100.00);
                    goalLeanBodyMass = leanBodyMass * 0.97;
                    rateOfWeightLoss = Double.parseDouble(weightLossRateField.getText().toString());

                    if (sex.equals("male")) {
                        goalTotalWeight = goalLeanBodyMass / (1 - 0.12);
                        weeksToSixPack = (totalCurrentWeight - goalTotalWeight) / rateOfWeightLoss;
                        resultText.setTextColor(getResources().getColor(R.color.black));
                        resultText.setText("Your six pack is " + weeksToSixPack + " weeks away");
                    }
                    else if (sex.equals("female")) {
                        goalTotalWeight = goalLeanBodyMass / (1 - 0.17);
                        weeksToSixPack = (totalCurrentWeight - goalTotalWeight) / rateOfWeightLoss;
                        resultText.setTextColor(getResources().getColor(R.color.black));
                        resultText.setText("Your six pack is " + weeksToSixPack + " weeks away");
                    }
                }
                else {
                    resultText.setText("Make sure to fill in all fields before calculating");
                    resultText.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }

    private boolean calculationReady() {
        try {
            totalCurrentWeight = Double.parseDouble(totalCurrentWeightField.getText().toString());
            bodyFatPercentage = Double.parseDouble(bodyFatField.getText().toString());
            rateOfWeightLoss = Double.parseDouble(weightLossRateField.getText().toString());
        }
        catch (Exception e) {
            return false;
        }
        return sexSelected;
    }
}