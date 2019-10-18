package com.sumit.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button increase_count, button_black, button_red, button_blue, button_green;
    Button reset_count;
    TextView show_count;
    private int value;
    private int colour;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        increase_count = findViewById(R.id.increase_count);
        reset_count = findViewById(R.id.reset_count);
        button_black = findViewById(R.id.button_black);
        button_red = findViewById(R.id.button_red);
        button_blue = findViewById(R.id.button_blue);
        button_green = findViewById(R.id.button_green);
        show_count = findViewById(R.id.show_count);


        colour = ContextCompat.getColor(this, R.color.default_background);

        increase_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = Integer.parseInt(show_count.getText().toString());
                value++;
                show_count.setText(String.valueOf(value));
            }
        });

        reset_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = 0;
                show_count.setText(String.valueOf(value));
                colour = ContextCompat.getColor(getApplicationContext(), R.color.default_background);
                show_count.setTextColor(colour);
            }
        });

        button_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int colorResourceName = getResources().getIdentifier("black", "color", getApplicationContext().getPackageName());
                int colorRes = ContextCompat.getColor(getApplicationContext(), colorResourceName);
                show_count.setTextColor(colorRes);
                colour = colorRes;
            }
        });

        button_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_count.setTextColor(getResources().getColor(R.color.red));
                colour = getResources().getColor(R.color.red);
            }
        });

        button_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_count.setTextColor(getResources().getColor(R.color.blue));
                colour = getResources().getColor(R.color.blue);
            }
        });

        button_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_count.setTextColor(getResources().getColor(R.color.green));
                colour = getResources().getColor(R.color.green);
            }
        });

        try {
            value = mPreferences.getInt("show_count", 0);
            colour = mPreferences.getInt("colour", 0);
            if (value != 0) {
                show_count.setText(String.valueOf(value));
                show_count.setTextColor(colour);
            }
        }
        catch (Exception e)
        {}
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("show_count", value);
        preferencesEditor.putInt("colour", colour);
        preferencesEditor.apply();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
