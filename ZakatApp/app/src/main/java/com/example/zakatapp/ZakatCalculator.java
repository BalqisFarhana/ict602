package com.example.zakatapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ZakatCalculator extends AppCompatActivity {

    private EditText input1, input3;
    private CheckBox radioKeep, radioWear;
    private TextView output1;
    private TextView output2;
    private TextView output3;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_calculator);

        input1 = findViewById(R.id.input1);
        radioKeep = findViewById(R.id.radioKeep);
        radioWear = findViewById(R.id.radioWear);
        input3 = findViewById(R.id.input3);

        output1 = findViewById(R.id.tvOutput1);
        output2 = findViewById(R.id.tvOutput2);
        output3 = findViewById(R.id.tvOutput3);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        Button calculateButton = findViewById(R.id.btnCalculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });
    }

    private void calculateZakat() {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double goldValue = Double.parseDouble(goldValueEditText.getText().toString());

            double totalValue = weight * goldValue;

            double zakatPayable = 0.0;
            if (keepCheckBox.isChecked()) {
                zakatPayable = weight - 85 * goldValue;
            } else if (wearCheckBox.isChecked()) {
                zakatPayable = weight - 200 * goldValue;
            } else {
                // Display a message to prompt the user to choose one option.
                Toast.makeText(this, "Please choose either 'Keep' or 'Wear'.", Toast.LENGTH_SHORT).show();
                return;
            }

            double zakat = 0.025 * zakatPayable;

            // Display the results
            totalValueTextView.setText("Total Value of Gold: " + totalValue);
            zakatPayableTextView.setText("Zakat Payable: " + zakatPayable);
            totalZakatTextView.setText("Total Zakat: " + zakat);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            Toast.makeText(this, "Please enter valid values for weight and gold value.", Toast.LENGTH_SHORT).show();
        }
    }
}