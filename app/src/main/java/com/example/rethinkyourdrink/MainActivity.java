package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecord = findViewById(R.id.BtnRecord);
        Button btnSummary = findViewById(R.id.BtnSummary);
        TextView title = findViewById(R.id.TVTitle);
        TextView title2 = findViewById(R.id.TVTitle2);
        TextView TVPlainWater = findViewById(R.id.TVPlainWater);
        TextView TVNonSweetened = findViewById(R.id.TVNonSweetened);
        TextView TVSweetened = findViewById(R.id.TVSweetened);
        Context context = getApplicationContext();
        File userFile = new File(context.getFilesDir(), "user_file");
        if(userFile.exists()){
            FileInputStream fis = null;
            try {
                fis = context.openFileInput("user_file");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                String line1 = bufferedReader.readLine();
                if(line1 != null){

                    String [] componentLine1 = line1.split(",");
                    if(componentLine1[1].equals("Plain Water")){
                        TVPlainWater.setText("Plain Water \n " + componentLine1[2] + "ml");
                    }
                    if(componentLine1[1].equals("Other Beverages (Non-Sweetened)")){
                        TVNonSweetened.setText("Other Beverages (Non-Sweetened) \n " + componentLine1[2] + "ml");
                    }
                    if(componentLine1[1].equals("Other Beverages (Sweetened)")){
                        TVSweetened.setText("Other Beverages (Sweetened) \n " + componentLine1[2] + "ml");
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try{
                    if(fis != null){
                        fis.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }



        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(intent);
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                startActivity(intent);
            }
        });

    }
}