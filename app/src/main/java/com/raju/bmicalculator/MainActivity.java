package com.raju.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private TextView tvBMI, tvCategory;
    private CardView resultCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        tvBMI = findViewById(R.id.tv_bmi);
        tvCategory = findViewById(R.id.tv_category);
        resultCard = findViewById(R.id.result_card);
    }

    // calculate bmi
    public void calculateBMI(View view) {
        if (!isEmpty(etHeight) && !isEmpty(etWeight)) {
            double heightInCm = Double.parseDouble(etHeight.getText().toString().trim());
            double heightInM = heightInCm / 100;
            double weight = Double.parseDouble(etWeight.getText().toString().trim());
            double bmi = weight / (heightInM * heightInM);
            DecimalFormat decimalFormat = new DecimalFormat("#.0");

            // hide keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

            showCategory(decimalFormat.format(bmi));
            etHeight.setText(" ");
            etWeight.setText(" ");
            etHeight.requestFocus();
        }
    }

    // show bmi category
    private void showCategory(String bmiValue) {
        double bmi = Double.parseDouble(bmiValue);
        tvBMI.setText(bmi + "");
        if (bmi < 18.5) {
            tvCategory.setText("Underweight");
            setTextColor(R.color.blue_grey_700);
            Toast.makeText(this, "Underweight", Toast.LENGTH_LONG).show();
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            tvCategory.setText("Normal Weight");
            setTextColor(R.color.colorSuccess);
            Toast.makeText(this, "Normal Weight", Toast.LENGTH_LONG).show();
        } else if (bmi > 25 && bmi < 29.9) {
            tvCategory.setText("Overweight");
            setTextColor(R.color.colorWarning);
            Toast.makeText(this, "Overweight", Toast.LENGTH_LONG).show();
        } else if (bmi >= 30) {
            tvCategory.setText("Obesity");
            setTextColor(R.color.colorDanger);
            Toast.makeText(this, "Obesity", Toast.LENGTH_LONG).show();
        }

        resultCard.setVisibility(View.VISIBLE);
    }

    // set text colors based on category
    private void setTextColor(int color) {
        tvBMI.setTextColor(getResources().getColor(color));
        tvCategory.setTextColor(getResources().getColor(color));
    }

    // validate input fields
    private boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError("Required");
            return true;
        } else {
            return false;
        }
    }
}
