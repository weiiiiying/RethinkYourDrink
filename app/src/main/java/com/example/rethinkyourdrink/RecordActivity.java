package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Button BtnSubmit = findViewById(R.id.BtnSubmit);
        Context context = getApplicationContext();

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ETCurrentDay = findViewById(R.id.ETDay);
                EditText ETAmount = findViewById(R.id.ETAmount);
                EditText ETName = findViewById(R.id.ETName);

                RadioButton RBPlainWater = findViewById(R.id.RBPlain);
                RadioButton RBNonSweetened = findViewById(R.id.RBNonSweetened);
                RadioButton RBSweetened = findViewById(R.id.RBSweetened);

                String category = "";

                if(RBPlainWater.isChecked()){
                    category = "Plain Water";
                }
                if(RBNonSweetened.isChecked()){
                    category = "Other Beverages (Non-Sweetened)";
                }
                if(RBSweetened.isChecked()){
                    category = "Other Beverages (Sweetened)";
                }

                String filename = "user_file";
                String currentDay = ETCurrentDay.getText().toString();
                String amount = ETAmount.getText().toString();
                String name = ETName.getText().toString();


                String fileContent = currentDay + "," + category + "," + amount + "\n";

                try(FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND)){
                    fos.write(fileContent.getBytes());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}