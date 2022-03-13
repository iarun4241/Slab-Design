package com.arun.slabdesign;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class BendingMomentActivity extends AppCompatActivity {

    Double Tag;
    HashMap<String, Double> mappedValues;

    Double r, ratio, ratio1, ratio2, ALPAx1, ALPAx2, ALPAx, ALPAy1, ALPAy2, ALPAy, lx, ly, Wu, Mx, My;
    Double ALPAxp1, ALPAxp2, ALPAxp, Mxp, ALPAxn1, ALPAxn2, ALPAxn, Mxn, ALPAyp, ALPAyn, Myp, Myn;

    TextView textViewResult;
    String Result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bending_moment);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        mappedValues = (HashMap<String, Double>) intent.getExtras().getSerializable("MappedValues");

        Tag = mappedValues.get("Tag");

        textViewResult = findViewById(R.id.result);

        if (Tag == 1) {
            tagOne();
        }

        if (Tag == 2 || Tag == 3 || Tag == 4 || Tag == 5) {
            tagTwoThreeFourFive();
        }


    }

    private void tagTwoThreeFourFive() {
        if (Tag == 2) {
            Double matrix[][] = {
                    {
                            0.032,
                            0.037,
                            0.043,
                            0.047,
                            0.051,
                            0.053,
                            0.060,
                            0.065,
                            0.032
                    },
                    {
                            0.024,
                            0.028,
                            0.032,
                            0.036,
                            0.039,
                            0.041,
                            0.045,
                            0.049,
                            0.024
                    }
            };
            calculate(matrix);
        }
        if (Tag == 3) {
            Double matrix[][] = {
                    {
                            0.037,
                            0.043,
                            0.048,
                            0.051,
                            0.055,
                            0.057,
                            0.064,
                            0.068,
                            0.037
                    },
                    {
                            0.028,
                            0.032,
                            0.036,
                            0.039,
                            0.041,
                            0.044,
                            0.048,
                            0.052,
                            0.028
                    }
            };
            calculate(matrix);
        }
        if (Tag == 4) {
            Double matrix[][] = {
                    {
                            0.037,
                            0.044,
                            0.052,
                            0.057,
                            0.063,
                            0.067,
                            0.077,
                            0.085,
                            0.037
                    },
                    {
                            0.028,
                            0.033,
                            0.039,
                            0.044,
                            0.047,
                            0.051,
                            0.059,
                            0.065,
                            0.028
                    }
            };
            calculate(matrix);
        }

        if (Tag == 5) {
            Double matrix[][] = {
                    {
                            0.047,
                            0.053,
                            0.060,
                            0.065,
                            0.071,
                            0.075,
                            0.084,
                            0.091,
                            0.047
                    },
                    {
                            0.035,
                            0.040,
                            0.045,
                            0.049,
                            0.053,
                            0.056,
                            0.063,
                            0.069,
                            0.035
                    }
            };
            calculate(matrix);
        }

        textViewResult.setText(Result);
    }

    private void calculate(Double[][] matrix) {
        if (r % 10 != 0) {
            ratio1 = (ratio - ((double) (r % 10) / 100));
            Log.d("Ratio1", ratio1.toString());
            ratio2 = ratio1 + 0.1;
            Log.d("Ratio2", ratio2.toString());

            ALPAxp1 = matrix[0][(int) (ratio1 * 10) % 10];
            Log.d("ALPHAx1(+)", ALPAxp1.toString());
            ALPAxp2 = matrix[0][(int) (ratio2 * 10) % 10];
            Log.d("ALPHAx2(+)", ALPAxp2.toString());

            ALPAxn1 = matrix[1][(int) (ratio1 * 10) % 10];
            Log.d("ALPHAx1(-)", ALPAxn1.toString());
            ALPAxn2 = matrix[1][(int) (ratio2 * 10) % 10];
            Log.d("ALPHAx1(-)", ALPAxn2.toString());

            ALPAxp = ALPAxp1 + (((ALPAxp2 - ALPAxp1) / (ratio2 - ratio1)) * (ratio - ratio1));
            Log.d("\nMoment coeffiicient ALPAx(+)", ALPAxp.toString());
            ALPAxn = ALPAxn1 + (((ALPAxn2 - ALPAxn1) / (ratio2 - ratio1)) * (ratio - ratio1));
            Log.d("\nMoment coeffiicient ALPAx(-)", ALPAxn.toString());

            Mxp = ALPAxp * Wu * lx * lx;
            Mxn = ALPAxn * Wu * lx * lx;

            Log.d("Positive Bending moment along Short Span", Mxp.toString());
            Log.d("Negative Bending moment along Short Span", Mxn.toString());

            if (Mxp > Mxn) {
                Mx = Mxp;
                Log.d("Info: ", "Mx(+)>Mx(-)");
            } else {
                Mx = Mxn;
                Log.d("Info: ", "Mx(+)<Mx(-)");
            }
            Log.d("Bending moment along Short span Mx", Mx.toString());

            ALPAyp = matrix[0][8];
            Log.d("ALPHAy(+)", ALPAyp.toString());
            ALPAyn = matrix[1][8];
            Log.d("ALPHAy(-)", ALPAyn.toString());

            Myp = ALPAyp * Wu * lx * lx;
            Log.d("Positive Bending moment along long span My(+)", Myp.toString());

            Myn = ALPAyn * Wu * lx * lx;
            Log.d("Negative Bending moment along long span My(-):", Myn.toString());

            if (Myp > Myn) {
                My = Myp;
                Log.d("Info:", "My(+)>My(-)");
            } else {
                My = Myn;
                Log.d("Info:", "My(+)<My(-)");
            }
            Log.d("Bending moment along long span My", My.toString());
        } else {
            ALPAxp = matrix[0][(int) (ratio * 10) % 10];
            ALPAxn = matrix[1][(int) (ratio * 10) % 10];
            Log.d("ALPHAx(+)", ALPAxp.toString());
            Log.d("ALPHAx(-)", ALPAxn.toString());

            Mxp = ALPAxp * Wu * lx * lx;
            Log.d("Positive Bending moment along Short span Mx(+)", Mxp.toString());

            Mxn = ALPAxn * Wu * lx * lx;
            Log.d("Negative Bending moment along Short span Mx(-)", Mxn.toString());

            if (Mxp > Mxn) {
                Mx = Mxp;
                Log.d("Info", "Mx(+)>Mx(-)");
            } else {
                Mx = Mxn;
                Log.d("Info", "Mx(+)<Mx(-)");
            }
            Log.d("Bending moment along Short span Mx", Mx.toString());

            ALPAyp = matrix[0][8];
            Log.d("ALPHAy(+):%f\n", ALPAyp.toString());
            ALPAyn = matrix[1][8];
            Log.d("ALPHAy(-):%f\n", ALPAyn.toString());

            Myp = ALPAyp * Wu * lx * lx;
            Log.d("Positive Bending moment along long spanMy(+)", Myp.toString());

            Myn = ALPAyn * Wu * lx * lx;
            Log.d("Negative Bending moment along long span My(-):", Myn.toString());

            if (Myp > Myn) {
                My = Myp;
                Log.d("Info", "My(+)>My(-)");
            } else {
                My = Myn;
                Log.d("Info", "My(+)<My(-)");
            }
            Log.d("Bending moment along long span My", My.toString());
        }
    }

    private void tagOne() {
        Double matrix[][] = {
                {
                        0.062,
                        0.074,
                        0.084,
                        0.093,
                        0.099,
                        0.104,
                        0.113,
                        0.118,
                        0.122,
                        0.124
                },
                {
                        0.062,
                        0.061,
                        0.059,
                        0.055,
                        0.051,
                        0.046,
                        0.037,
                        0.029,
                        0.020,
                        0.014
                }
        };
        r = mappedValues.get("r");
        ratio = mappedValues.get("Ratio");
        lx = mappedValues.get("lx");
        ly = mappedValues.get("ly");
        Wu = mappedValues.get("Wu");

        if (r % 10 != 0) {
            ratio1 = (ratio - ((double) (r % 10) / 100.0));
            Log.d("Ratio1", ratio1.toString());
            ratio2 = ratio1 + 0.1;
            Log.d("Ratio2", ratio2.toString());

            Result += "Ratio1 " + ratio1.toString() + "\n";
            Result += "Ratio2" + ratio2.toString() + "\n";

            ALPAx1 = matrix[0][(int) (ratio1 * 10) % 10];
            Log.d("ALPHAx1", String.valueOf(ALPAx1));

            Result += "ALPHAx1 " + ALPAx1.toString() + "\n";

            ALPAx2 = matrix[0][(int) (ratio2 * 10) % 10];
            Log.d("ALPHAx2", String.valueOf(ALPAx2));

            Result += "ALPHAx2" + ALPAx2.toString() + "\n";

            ALPAx = ALPAx1 + (((ALPAx2 - ALPAx1) / (ratio2 - ratio1)) * (ratio - ratio1));
            Log.d("Moment Coeff. ALPAx", ALPAx.toString());

            Result += "Moment Coeff. ALPAx" + ALPAx.toString() + "\n";

            ALPAy1 = matrix[1][(int) (ratio1 * 10) % 10];
            Log.d("ALPHAy1", ALPAy1.toString());

            Result += "ALPHAy1" + ALPAy1.toString() + "\n";

            ALPAy2 = matrix[1][(int) (ratio2 * 10) % 10];
            Log.d("ALPHAy2", ALPAy2.toString());

            Result += "ALPHAy2" + ALPAy2.toString() + "\n";

            ALPAy = ALPAy1 - (((ALPAy1 - ALPAy2) / (ratio2 - ratio1)) * (ratio - ratio1));
            Log.d("ALPHAy", ALPAy.toString());

            Result += "ALPHAy" + ALPAy.toString() + "\n";

            Mx = ALPAx * Wu * lx * lx;
            My = ALPAy * Wu * lx * lx;

            Log.d("Bending moment along Short Span", Mx.toString());
            Log.d("Bending moment along Long Span", My.toString());

            Result += "Bending moment along Short Span" + Mx.toString() + "\n";
            Result += "Bending moment along Long Span" + My.toString() + "\n";

        } else {
            ratio1 = (ratio - ((float) (r % 10) / 100));

            ALPAx = matrix[0][(int) (ratio1 * 10) % 10];
            Log.d("Moment Coeff. ALPAx", ALPAx.toString());

            Result += "Moment Coeff. ALPAx" + ALPAx.toString() + "\n";

            ALPAy = matrix[1][(int) (ratio1 * 10) % 10];
            Log.d("ALPHAy", ALPAy.toString());

            Result += "ALPHAy" + ALPAy.toString() + "\n";

            Mx = ALPAx * Wu * lx * lx;
            My = ALPAy * Wu * lx * lx;

            Log.d("Bending moment along Short Span", Mx.toString());
            Log.d("Bending moment along Long Span", My.toString());

            Result += "Bending moment along Short Span" + Mx.toString() + "\n";
            Result += "Bending moment along Long Span" + My.toString() + "\n";
        }
    }
}