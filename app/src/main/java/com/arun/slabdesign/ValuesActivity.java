package com.arun.slabdesign;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ValuesActivity extends AppCompatActivity {

    String Ly, Lx, WallWidth, LiveLoad, FloorThickness, Fck;
    EditText editTextLy, editTextLx, editTextWallWidth, editTextLiveLoad, editTextFloorThickness, editTextFck;
    int flag = 0;
    Double r, Ratio, parsedLy, parsedLx, parsedWallWidth, parsedLiveLoad, parsedFloorThickness, parsedFck;

    Double d, D;

    Double cex, ccx, lx, cey, ccy, ly;

    Double Wd, Wl, Wf, t, W, Wu;

    HashMap<String, Double> mappedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button buttonNextTwo = findViewById(R.id.buttonNextTwo);
        mappedValues = new HashMap<String, Double>();

        editTextLy = findViewById(R.id.editTextLy);
        editTextLx = findViewById(R.id.editTextLx);
        editTextWallWidth = findViewById(R.id.editTextWallWidth);
        editTextLiveLoad = findViewById(R.id.editTextLiveLoad);
        editTextFloorThickness = findViewById(R.id.editTextFloorFinishThickness);
        editTextFck = findViewById(R.id.editTextFck);

        Intent intent = getIntent();
        String Tag = intent.getExtras().getString("Tag");

        mappedValues.put("Tag", Double.parseDouble(Tag));

        editTextLx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextLx.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextLx.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        editTextLy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextLy.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextLy.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        editTextWallWidth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextWallWidth.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextWallWidth.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        editTextLiveLoad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextLiveLoad.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextLiveLoad.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        editTextFloorThickness.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextFloorThickness.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextFloorThickness.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        editTextFck.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editTextFck.setBackgroundResource(R.drawable.edittext_focused);
                }
                else{
                    editTextFck.setBackgroundResource(R.drawable.edittext);
                }
            }
        });

        buttonNextTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Ly = editTextLy.getText().toString();
                Lx = editTextLx.getText().toString();
                WallWidth = editTextWallWidth.getText().toString();
                LiveLoad = editTextLiveLoad.getText().toString();
                FloorThickness = editTextFloorThickness.getText().toString();
                Fck = editTextFck.getText().toString();

                if (Ly.isEmpty()) {
                    editTextLy.setBackgroundResource(R.drawable.edittext_error);
                    editTextLy.setError("Ly value missing!");
                    flag = 1;
                }

                if (Lx.isEmpty()) {
                    editTextLx.setBackgroundResource(R.drawable.edittext_error);
                    editTextLx.setError("Lx value missing!");
                    flag = 1;
                }
                if (WallWidth.isEmpty()) {
                    editTextWallWidth.setBackgroundResource(R.drawable.edittext_error);
                    editTextWallWidth.setError("Width of the wall value missing!");
                    flag = 1;
                }
                if (LiveLoad.isEmpty()) {
                    editTextLiveLoad.setBackgroundResource(R.drawable.edittext_error);
                    editTextLiveLoad.setError("Live load value value missing!");
                    flag = 1;
                }
                if (FloorThickness.isEmpty()) {
                    editTextFloorThickness.setBackgroundResource(R.drawable.edittext_error);
                    editTextFloorThickness.setError("Floor Thickness value missing!");
                    flag = 1;
                }
                if (Fck.isEmpty()) {
                    editTextFck.setBackgroundResource(R.drawable.edittext_error);
                    editTextFck.setError("Fck value missing!");
                    flag = 1;
                }

                if (flag == 0) {
                    parsedLy = Double.parseDouble(Ly);
                    parsedLx = Double.parseDouble(Lx);
                    parsedWallWidth = Double.parseDouble(WallWidth);
                    parsedLiveLoad = Double.parseDouble(LiveLoad);
                    parsedFloorThickness = Double.parseDouble(FloorThickness);
                    parsedFck = Double.parseDouble(Fck);

                    Wl = parsedLiveLoad;
                    t = parsedFloorThickness;

                    Ratio = parsedLy / parsedLx;
                    r = Ratio * 100;

                    mappedValues.put("Ratio",Ratio);
                    mappedValues.put("r",r);
                }

                if (flag == 0 && Ratio < 2) {

                    calculateDepthOfSlab();
                    Log.d("Depth of Slab: ", d.toString());
                    Log.d("Overall Depth of Slab: ", d.toString());

                    calculateEffectiveSpanForShortSpan();
                    Log.d("Effective Span for short span: ", lx.toString());

                    calculateEffectiveSpanForLongSpan();
                    Log.d("Effective Span for long span: ", ly.toString());

                    calculateLoad();
                    Log.d("Dead Load: ", Wd.toString());
                    Log.d("Live Load: ", Wl.toString());
                    Log.d("Floor Finish: ", Wf.toString());
                    Log.d("Total Load: ", W.toString());
                    Log.d("Ultimate Load: ", Wu.toString());


                    Intent intent1 = new Intent(ValuesActivity.this, BendingMomentActivity.class);
                    intent1.putExtra("MappedValues", mappedValues);
                    startActivity(intent1);
                    finish();
                }

            }
        });
    }

    private void calculateLoad() {
        Wd = (D / 1000) * 1 * 25;
        Wf = (t / 1000) * 1 * 24;
        W = Wd + Wl + Wf;
        Wu = W * 1.5;

        mappedValues.put("Wd", Wd);
        mappedValues.put("Wf", Wf);
        mappedValues.put("Wl", Wl);
        mappedValues.put("W", W);
        mappedValues.put("Wu", Wu);
    }

    private void calculateEffectiveSpanForLongSpan() {
        cey = parsedLy + (d / 1000);
        ccy = parsedLy + ((parsedWallWidth / 2) / 1000) * 2;
        if (cey < ccy)
            ly = cey;
        else
            ly = ccy;

        mappedValues.put("ly", ly);
    }

    private void calculateEffectiveSpanForShortSpan() {
        cex = parsedLx + (d / 1000);
        ccx = parsedLx + ((parsedWallWidth / 2) / 1000) * 2;
        if (cex < ccx)
            lx = cex;
        else
            lx = ccx;

        mappedValues.put("lx", lx);
    }

    private void calculateDepthOfSlab() {
        d = parsedLx * 1000 / 20;
        d = d - 60;
        D = d + 30;

        mappedValues.put("d", d);
        mappedValues.put("D", D);
    }
}