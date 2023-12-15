package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar myToolbar;
    EditText input1, input3;
    RadioButton keep, wear;
    Button btnSubmit, btnReset;
    TextView output1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The Toolbar defined in the layout has the id "my_toolbar".
        myToolbar = (Toolbar) (findViewById(R.id.my_toolbar));
        setSupportActionBar(myToolbar);

        input1 = findViewById(R.id.input1);
        wear = findViewById(R.id.wear);
        keep = findViewById(R.id.keep);
        input3 = findViewById(R.id.input3);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        output1 = findViewById(R.id.output1);

        btnSubmit.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_share) { //if user click share button
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - https://github.com/BalqisFarhana/ict602");
            startActivity(Intent.createChooser(shareIntent,null));

            return true;

        } else if (item.getItemId() == R.id.item_about){ //when user click about button
            Intent aboutIntent = new Intent ( this, AboutUs.class);
            startActivity(aboutIntent);
        }

        return false;
    }

    @Override
    public void onClick(View k) { //if user click button submit to calculate
        if (k.getId() == R.id.btnSubmit) {
            calculateZakat();
        }
        else if (k.getId() == R.id.btnReset){ //if user enter reset  button
            resetFields();
        }
    }

    private void resetFields() {
        //when user enter reset button, this will happen
        input1.setText("");
        input3.setText("");
        output1.setText("");

        RadioButton wear = findViewById(R.id.wear);
        RadioButton keep = findViewById(R.id.keep);

        wear.setChecked(false);
        keep.setChecked(false);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;


    }

    private void calculateZakat() {
        try {
            double weight = Double.parseDouble(input1.getText().toString());
            double goldValue = Double.parseDouble(input3.getText().toString());

            if (weight < 0) {
                Toast.makeText(this, "Please enter a valid weight of gold.", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalValue = weight * goldValue;
            double calculateZakatPayable = 0.0;

            if (keep.isChecked()) {
                if (weight < 85) {
                    Toast.makeText(this, "You have no eligible Zakat.", Toast.LENGTH_SHORT).show();
                    return;
                }
                calculateZakatPayable = (weight - 85) * goldValue;
            } else if (wear.isChecked()) {
                if (weight < 200) {
                    Toast.makeText(this, "You have no eligible Zakat.", Toast.LENGTH_SHORT).show();
                    return;
                }
                calculateZakatPayable = (weight - 200) * goldValue;
            } else {
                // Display a message to prompt the user to choose one option.
                Toast.makeText(this, "Please choose either 'Keep' or 'Wear'.", Toast.LENGTH_SHORT).show();
                return;
            }

            double zakat = 0.025 * calculateZakatPayable;

            String result;
            if (zakat <= 0) {
                result = "You have no Zakat.";
            } else
            {
                result = String.format("Total Value of Gold: %.2f\nZakat Payable: RM%.2f\nTotal Zakat: %.2f", totalValue, calculateZakatPayable, zakat);
            }

            output1.setText(result);

        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            Toast.makeText(this, "Please enter valid values for weight and gold value.", Toast.LENGTH_SHORT).show();
        }
    }
}
