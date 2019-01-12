package com.santa.xavi.gcd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void createTextView(String text, String color) {
        // Get the widgets reference from XML layout
        LinearLayout rl = (LinearLayout) findViewById(R.id.info2);

        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText(text);

        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor(color));

        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }
    public void deleteTexts(){

    }
    public void run(View v) {
        try{
            deleteTexts();
            TextView _1 = (TextView) findViewById(R.id.textView0);
            TextView _2 = (TextView) findViewById(R.id.textView1);
            int a = Integer.parseInt(_1.getText().toString());
            int b = Integer.parseInt(_2.getText().toString());
            if(a < b){
                int t = a;
                a = b;
                b = t;
            }
            int r0 = a;
            int r1 = b;
            int q, r2;
            String s;
            createTextView("START", "#ff0000");
            while (r1 != 0) {
                q = r0 / r1;
                r2 = r0 - q*r1;
                s = r0 + " = " + q + "*" + r1 + " + " + r2;
                createTextView(s, "#000000");
                r0 = r1;
                r1 = r2;
            }
            s = r0 + " = gcd(" + a + ", " + b +")";
            createTextView("FINISHED","#ff0000");
            createTextView(s,"#000000");
            createTextView(s,"#ffffff");
        }catch(NumberFormatException nfe) {
            createTextView("FAIL","#ff0000");
        }

    }
}