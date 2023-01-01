package com.example.a6306021621138_hw2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtInput;
    TextView txtDecimal, txtBinary, txtOctal, txtHex;

    private void setup() {
        edtInput = (EditText) findViewById(R.id.edt_number);

        txtDecimal = (TextView) findViewById(R.id.txt_decimalResult);
        txtBinary = (TextView) findViewById(R.id.txt_binaryResult);
        txtOctal = (TextView) findViewById(R.id.txt_octalResult);
        txtHex = (TextView) findViewById(R.id.txt_hexResult);
    }


    public boolean validate() {

        if (edtInput.getText().toString().equals("")) {
            edtInput.setError("Number is required!");
            Toast.makeText(this, "Number is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


    private String processModDivide(int num, int base) {
        String strNum = "";
        String[] listBase16 = {"A", "B", "C", "D", "E", "F"};

        while (num > 0) {
            if (base != 16) {
                strNum += Integer.toString(num % base);
            } else {
                strNum += ((num % base) < 10 ? Integer.toString(num % base) : listBase16[(num % base) - 10]);
            }
            num /= base;
        }

        for (int i = strNum.length() - 1; i >= 0; i--) {
            strNum += strNum.charAt(i);
        }
        strNum = strNum.substring((strNum.length() / 2));

        return strNum;
    }


    private String convertDecToBinary(int num) {
        return this.processModDivide(num, 2);
    }

    private String convertDecToOctal(int num) {
        return this.processModDivide(num, 8);
    }

    private String convertDecToHexa(int num) {
        return this.processModDivide(num, 16);
    }


    public void onClickBtnChange(View view) {
        if (!this.validate()) return;

        int number = Integer.parseInt(edtInput.getText().toString());

        txtDecimal.setText(edtInput.getText().toString());
        txtBinary.setText(this.convertDecToBinary(number));
        txtOctal.setText(this.convertDecToOctal(number));
        txtHex.setText(this.convertDecToHexa(number));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setup();
    }

}