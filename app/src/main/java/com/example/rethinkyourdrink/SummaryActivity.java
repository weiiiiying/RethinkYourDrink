package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SummaryActivity extends AppCompatActivity {

    TextView TVTotalAmount ;
    TextView TVTotalPlainWater ;
    TextView TVTotalSweetened ;
    TextView TVTotalNonSweetened ;

    TextView TVDay1 ;
    TextView TVDay2 ;
    TextView TVDay3 ;

    TextView TVCategoryD1 ;
    TextView TVCategoryD2 ;
    TextView TVCategoryD3 ;

    TextView TVAmountD1 ;
    TextView TVAmountD2 ;
    TextView TVAmountD3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

         TVTotalAmount = findViewById(R.id.TVTotalAmount);
         TVTotalPlainWater = findViewById(R.id.TVTotalPlainWater);
         TVTotalSweetened = findViewById(R.id.TVTotalSweetened);
         TVTotalNonSweetened = findViewById(R.id.TVTotalNonSweetened);

         TVDay1 = findViewById(R.id.TVDay1);
         TVDay2 = findViewById(R.id.TVDay2);
         TVDay3 = findViewById(R.id.TVDay3);

         TVCategoryD1 = findViewById(R.id.TVCategoryD1);
         TVCategoryD2 = findViewById(R.id.TVCategoryD2);
         TVCategoryD3 = findViewById(R.id.TVCategoryD3);

         TVAmountD1 = findViewById(R.id.TVAmountD1);
         TVAmountD2 = findViewById(R.id.TVAmountD2);
         TVAmountD3 = findViewById(R.id.TVAmountD3);

        Context context = getApplicationContext();
        File userFile = new File(context.getFilesDir(), "user_file");

        if(userFile.exists()){
            retrieveData();
        }


    }

    private void retrieveData() {
        String fileContent = "";


        FileInputStream fis = null;

        try {
            fis = getApplicationContext().openFileInput("user_file");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(reader)){

            int totalAmount = 0;
            int totalPlain = 0;
            int totalNonSweetened = 0;
            int totalSweetened = 0;

                String line1 = bufferedReader.readLine();
                if(line1 != null){

                    String [] componentLine1 = line1.split(",");
                    TVDay1.setText("CurrentDay: " + componentLine1[0]);
                    TVCategoryD1.setText("Category: " + componentLine1[1]);
                    TVAmountD1.setText("Amount: " + componentLine1[2]);
                    totalAmount += Integer.parseInt(componentLine1[2]);
                    if(componentLine1[1].equals("Plain Water")){
                        totalPlain += Integer.parseInt(componentLine1[2]);
                    }
                    if(componentLine1[1].equals("Other Beverages (Non-Sweetened)")){
                        totalNonSweetened += Integer.parseInt(componentLine1[2]);
                    }
                    if(componentLine1[1].equals("Other Beverages (Sweetened)")){
                        totalSweetened += Integer.parseInt(componentLine1[2]);
                    }


                }

            String line2 = bufferedReader.readLine();
            if(line2 != null){

                String [] componentLine2 = line2.split(",");
                TVDay2.setText("CurrentDay: " + componentLine2[0]);
                TVCategoryD2.setText("Category: " + componentLine2[1]);
                TVAmountD2.setText("Amount: " + componentLine2[2]);
                totalAmount += Integer.parseInt(componentLine2[2]);
                if(componentLine2[1].equals("Plain Water")){
                    totalPlain += Integer.parseInt(componentLine2[2]);
                }
                if(componentLine2[1].equals("Other Beverages (Non-Sweetened)")){
                    totalNonSweetened += Integer.parseInt(componentLine2[2]);
                }
                if(componentLine2[1].equals("Other Beverages (Sweetened)")){
                    totalSweetened += Integer.parseInt(componentLine2[2]);
                }

            }

            String line3 = bufferedReader.readLine();
            if(line3 != null){

                String [] componentLine3 = line3.split(",");
                TVDay3.setText("CurrentDay: " + componentLine3[0]);
                TVCategoryD3.setText("Category: " + componentLine3[1]);
                TVAmountD3.setText("Amount: " + componentLine3[2]);
                totalAmount += Integer.parseInt(componentLine3[2]);
                if(componentLine3[1].equals("Plain Water")){
                    totalPlain += Integer.parseInt(componentLine3[2]);
                }
                if(componentLine3[1].equals("Other Beverages (Non-Sweetened)")){
                    totalNonSweetened += Integer.parseInt(componentLine3[2]);
                }
                if(componentLine3[1].equals("Other Beverages (Sweetened)")){
                    totalSweetened += Integer.parseInt(componentLine3[2]);
                }

            }

            TVTotalAmount.setText("Total Amount of Water for 3 days: " + totalAmount);
            TVTotalPlainWater.setText("Plain Water: " + totalPlain);
            TVTotalNonSweetened.setText("Non-sweetened: " +totalNonSweetened);
            TVTotalSweetened.setText("Sweetened: " + totalSweetened);


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
}