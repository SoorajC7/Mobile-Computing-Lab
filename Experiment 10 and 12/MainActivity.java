package com.example.experiment10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale, radioOther;
    Button showBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);

        showBtn = findViewById(R.id.showBtn);
        resultText = findViewById(R.id.resultText);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    resultText.setText("Please select an option");
                } else {
                    RadioButton selectedRadio = findViewById(selectedId);
                    resultText.setText("Selected: " + selectedRadio.getText());
                }
            }
        });
    }
}
