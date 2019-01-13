package com.santa.xavi.gcd;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;

public class Euclidean extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euclidean);
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
        tv.setTextSize(15);
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor(color));

        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }

    public void clearEuclidean(View v) {
        setContentView(R.layout.activity_euclidean);
    }

    public void run(View v) {
        try{
            TextView _1 = (TextView) findViewById(R.id.textView0);
            TextView _2 = (TextView) findViewById(R.id.textView1);
            long a = Integer.parseInt(_1.getText().toString());
            long b = Integer.parseInt(_2.getText().toString());
            if(a < b){
                long t = a;
                a = b;
                b = t;
            }
            long r0 = a;
            long r1 = b;
            long q, r2;
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

    public void runExtEucli(View v) {
        try{
            TextView _1 = (TextView) findViewById(R.id.textView0);
            TextView _2 = (TextView) findViewById(R.id.textView1);
            long a = Integer.parseInt(_1.getText().toString());
            long b = Integer.parseInt(_2.getText().toString());
            long [] ext = extendedEuclidean(a,b);
            String s = "BEZOUT IDENTITY";
            createTextView(s,"#FF0000");
            s = ext[1] + "(" + a + ") + " + ext[2] + "(" + b + ") = " + ext[0];
            createTextView(s,"#000000");
        }catch(NumberFormatException nfe) {
            createTextView("FAIL","#ff0000");
        }
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
}
