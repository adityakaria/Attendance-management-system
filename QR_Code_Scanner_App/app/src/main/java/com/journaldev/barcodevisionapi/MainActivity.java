package com.journaldev.barcodevisionapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnScanBarcode;
    TextView Date_Prompt, textView2;
    EditText mLab_Date, mLab_Duration;
    String finalString;
    String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        Date_Prompt = findViewById((R.id.Date_Prompt));
        textView2 = findViewById((R.id.textView2));

        btnScanBarcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mLab_Date = findViewById(R.id.Lab_Date);
        mLab_Duration = findViewById(R.id.Lab_Duration);

        try {

//            startActivity(i);
//                                    File file = new File(dir, "student_data.txt");
            File file = new File("/storage/sdcard0/Download", mLab_Date.getText().toString() + ".csv");
            Toast.makeText(getApplicationContext(), mLab_Date.getText().toString() + ".csv", Toast.LENGTH_SHORT).show();

            FileOutputStream stream = new FileOutputStream(file, true);

            try {

                finalString = mLab_Date.getText().toString() + '\n' + mLab_Duration.getText().toString() + " " + "hrs" + '\n';
                Toast.makeText(getApplicationContext(), finalString, Toast.LENGTH_SHORT).show();
                stream.write(finalString.getBytes());
                Toast.makeText(getApplicationContext(), "-----writing------", Toast.LENGTH_SHORT).show();
            } finally {
                stream.close();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "FileNotFoundException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        switch (v.getId()) {
            case R.id.btnScanBarcode:

                Intent i = new Intent(MainActivity.this, ScannedBarcodeActivity.class);

                Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
                i.putExtra("fileName", (mLab_Date.getText().toString() + ".csv"));
                startActivity(i);
//                startActivity(new Intent(MainActivity.this, ScannedBarcodeActivity.class));
                break;
        }

    }
}
