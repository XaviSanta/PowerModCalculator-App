package com.santa.xavi.gcd;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Inverse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverse);
    }
    public void run(View v) {
        try{
            TextView _1 = (TextView) findViewById(R.id.textView0);
            TextView _2 = (TextView) findViewById(R.id.textView1);
            long inv = Integer.parseInt(_1.getText().toString());
            long mod = Integer.parseInt(_2.getText().toString());
            long [] ext = extendedEuclidean(inv,mod);
            if (ext[1] < 0){
                ext[1] = mod + ext[1];
            }
            String s = inv + "⁻¹ mod " + mod + " = " + ext[1];
            createTextView(s,"#000000");
        }catch(NumberFormatException nfe) {
            createTextView("Fail","#ff0000");
        }
    }

    public void clearInverse(View v) {
        setContentView(R.layout.activity_inverse);
    }

    public long[] extendedEuclidean(long p, long q) {
        if (q == 0) {
            return new long[]{p, 1, 0};
        }

        long[] vals = extendedEuclidean(q, p % q);
        long d = vals[0];
        long a = vals[2];
        long b = vals[1] - (p / q) * vals[2];
        return new long[]{d, a, b};
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
