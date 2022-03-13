package com.arun.slabdesign;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton checkedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        RadioGroup radioGroupSlabType = findViewById(R.id.radioGroupSlabType);
        Button buttonNextOne = findViewById(R.id.buttonNextOne);


        buttonNextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupSlabType.getCheckedRadioButtonId();
                checkedRadioButton = findViewById(selectedId);
                String tag = checkedRadioButton.getTag().toString();

                Intent intent = new Intent(MainActivity.this, ValuesActivity.class);
                intent.putExtra("Tag",tag);
                startActivity(intent);
                finish();
            }
        });
    }
}