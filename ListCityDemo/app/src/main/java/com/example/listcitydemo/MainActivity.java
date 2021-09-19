package com.example.listcitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Sydney", "Paris", "Toronto", "Beijing", "New Delhi", "Austin"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        editText.setVisibility(View.GONE); // Hides the text input field by default

        Button confirmButton= (Button) findViewById(R.id.button9);
        confirmButton.setVisibility(View.GONE); // Hides the Confirm button by default

        Button removeCityButton= (Button) findViewById(R.id.button10);
        Button addCityButton= (Button) findViewById(R.id.button11);

        // Show the text input and confirmButton when "Add city" is clicked
        addCityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the city name from the text field and add it to the list
                EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
                String message = editText.getText().toString();
                dataList.add(message);
                cityList.setAdapter(cityAdapter);

                // Hide and clear the text field and hide the confirm button
                editText.setVisibility(View.GONE);
                editText.getText().clear();
                confirmButton.setVisibility(View.GONE);


                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });

        // Remove the selected city if the user clicks on "Delete City"
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCity = cityList.getItemAtPosition(i).toString();

                removeCityButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dataList.remove(selectedCity);
                        cityList.setAdapter(cityAdapter);
                    }
                });
            }
        });

    }
}