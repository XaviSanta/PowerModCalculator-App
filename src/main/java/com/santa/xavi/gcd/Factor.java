package com.santa.xavi.gcd;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Arrays;

public class Factor extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factor);
    }
    public void runFactor(View v){
        try{
            EditText b = findViewById(R.id.base);
            BigInteger b1 = new BigInteger(b.getText().toString());


            createTextView("Factors of " + b1 + " : ", "#fd6600");
            StringBuilder sb = new StringBuilder("");
            int[] a = new int[31]; // 2^31
            int i = 0, j;
            int num = b1.intValue();
            while (num % 2 == 0) {
                a[i++] = 2;
                num /= 2;
            }
            j = 3;
            while (j <= Math.sqrt(num) + 1) {
                if (num % j == 0) {
                    a[i++] = j;
                    num /= j;
                } else {
                    j += 2;
                }
            }
            if (num > 1) {
                a[i++] = num;
            }
            int[] bf = Arrays.copyOf(a, i);
            for (int factor: bf ) {
                sb.append(" [ " + factor + " ] ");
            }
            createTextView(sb.toString(), "#0078ff");
        }catch (Exception e){
            createTextView("Fail", "#ff0000");
        }
    }

    public void clearFactor(View v) {
        setContentView(R.layout.activity_factor);
    }
    public void createTextView(String text, String color) {
        // Get the widgets reference from XML layout
        LinearLayout rl = (LinearLayout) findViewById(R.id.info2);

        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText(text);
        tv.setTextSize(18);
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor(color));

        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }
}
