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

import java.math.*;

public class PowerMod extends AppCompatActivity {

    private Boolean showSteps = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_mod);
    }
    public void runPower(View v){
        try{
            EditText b = findViewById(R.id.base);
            EditText e = findViewById(R.id.exponent);
            EditText m = findViewById(R.id.modulus);
            BigInteger b1 = new BigInteger(b.getText().toString());
            BigInteger b2 = new BigInteger(e.getText().toString());
            BigInteger b3 = new BigInteger(m.getText().toString());
            if(showSteps){
                modExp(b1, b2, b3);
            }else{
                modExpNoSteps(b1, b2, b3);
            }
        }catch (Exception e){
            createTextView("Fail", "#ff0000");
        }
    }

    public void showSteps(View v) {
        this.showSteps = !this.showSteps;
    }

    // SHOW STEPS == TRUE
    public void modExp(BigInteger a, BigInteger b, BigInteger c){
        int door = 1;
        String str;
        str = a + " ^ " + b + " mod " + c;
        createTextView(str,"#fd6600");

        while (door == 1) {
            // Base Case
            try {
                if (Math.pow(a.doubleValue(), b.doubleValue()) < c.doubleValue()) {
                    double mySol = Math.pow(a.doubleValue(), b.doubleValue());
                    str = "Solution: " +  Math.round(mySol);
                    createTextView(str,"#2259e3");
                    createTextView(" ","#ffffff");
                    break;
                }
                if (b.longValue() == 1){
                    exponentOne(a,c);
                    break;
                }
            } catch (Exception e) {
                createTextView("Fail","#ff0000");
            }

            // FERMAT
            if (a.gcd(c).intValue() == 1 && b.longValue() > c.longValue()) {
                str = "Little Fermat Theorem";
                createTextView(str,"#2259e3");
                b = new BigInteger(Long.toString(fermat(a,b,c)));
                if (b.longValue() == 1){
                    exponentOne(a,c);
                    door = 0;
                }
            }
            // BY HAND
            else {
                str = "Binary Phase";
                createTextView(str,"#2259e3");
                byHand(a, b, c);
                createTextView(" ","#ffffff");
                door = 0;
            }
        }
    }
    public long fermat(BigInteger a, BigInteger b, BigInteger c) {
        long d      = (c.longValue() - 1);
        long newExp = b.longValue() / d;
        long rem    = b.longValue() % d;
        String str;
        str ="1)  " + b + " = " + newExp + " * " + d + " + " + rem;
        createTextView(str,"#000000");
        str ="2)  " +  a + " ^ (" + newExp + " * " + d + ") * "
                   + a + " ^ " + rem + " mod " + c;
        createTextView(str,"#000000");
        str ="3)  " + a + " ^ " + rem + " mod " + c;
        createTextView(str,"#000000");
        return rem;
    }
    public void byHand(BigInteger a, BigInteger b, BigInteger c) {

        String str ="1) Pass the exponent to binary";
        createTextView(str,"#2259e3");

        String myBin = Long.toBinaryString(b.longValue());
        str = b.longValue() + " = " + myBin + " in binary";
        createTextView(str,"#000000");

        long bin = Long.parseLong(myBin, 10);
        int length = (int) (Math.log10(bin) + 1);

        str ="2) Compute " + a + " ^ " + b +" using binary. Following that: \n" +
                "2.1) From left to right, write "+ a + " for the first `1` \n" +
                "2.2) Next number = `1` --> add `)² *"+ a + "`\n" +
                "2.3) Next number = `0` --> add `)²`" ;
        createTextView(str,"#2259e3");

        str = printConcatenation(length, a.toString(), a, bin);
        createTextView(str,"#000000");
        // ---

        // print doing calculus
        long myVal = a.longValue();
        int digit;
        for (int l = 1; l < length; l++) {
            digit = Integer.parseInt(Long.toString(bin).substring(l, l + 1));
            if (digit == 1) {
                if(myVal*myVal > c.longValue()){
                    myVal = myVal*myVal;
                    String myStr = myVal + " *" + a.longValue();
                    str = printConcatenation(length - l, myStr, a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                    createTextView(str,"#000000");

                    if(myVal != myVal % c.longValue()){
                        myVal = myVal % c.longValue();
                        myStr = myVal + " *" + a.longValue();
                        str = printConcatenation(length - l, myStr, a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                        createTextView(str,"#000000");
                    }

                    myVal = myVal * a.longValue();
                    str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                    createTextView(str,"#000000");

                    if(myVal >= c.longValue()){
                        myVal = myVal % c.longValue();
                        str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                        createTextView(str,"#000000");
                    }
                }else{
                    if(myVal*myVal*a.longValue() > c.longValue()){
                        myVal = (myVal * myVal);
                        str = myVal + " *" + a.longValue();
                        str = printConcatenation(length - l, str, a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                        createTextView(str,"#000000");

                        myVal = myVal * a.longValue();
                        str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                        createTextView(str,"#000000");
                        if(myVal >= c.longValue()){
                            myVal = myVal % c.longValue();
                            str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                            createTextView(str,"#000000");
                        }
                    }else{
                        myVal = (myVal * myVal * a.longValue()) % c.longValue();
                        str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                        createTextView(str,"#000000");
                    }
                }

            } else {
                if(myVal*myVal > c.longValue()){
                    myVal = myVal*myVal;
                    str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                    createTextView(str,"#000000");

                    myVal = myVal % c.longValue();
                    str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                    createTextView(str,"#000000");

                }else{
                    myVal = (myVal * myVal) % c.longValue();
                    str = printConcatenation(length - l, Long.toString(myVal), a, Integer.parseInt(Long.toString(bin).substring(l, length)));
                    createTextView(str,"#000000");
                }
            }
            if (l+1 == length) createTextView("Solution: " + Long.toString(myVal),"#2259e3");
        }
    }
    public void exponentOne(BigInteger a, BigInteger c) {

        String str  = "Solution: " + a.mod(c) + "\n";
        createTextView(str,"#2259e3");
    }
    public static String printConcatenation(int realLength, String str, BigInteger a, long bin) {
        int myLength = 0;
        if (bin != 0) {
            myLength = (int) (Math.log10(bin) + 1);
        }

        if (myLength != realLength) {
            int nZ = realLength - myLength;
            if (bin == 0) {
                nZ--;
            }
            for (int i = 0; i < nZ; i++) {

                str = "(" + str + ")²";
            }
            if (Integer.parseInt(Long.toString(bin).substring(0, 1)) == 1) {
                str = str + " *" + a;
            }
        }

        int digit;
        for (int l = 1; l < myLength; l++) {
            digit = Integer.parseInt(Long.toString(bin).substring(l, l + 1));
            if (digit == 1) {
                str = "(" + str + ")²" + " *" + a;
            } else {
                str = "(" + str + ")²";
            }
        }

        return str;
    }

    // SHOW STEPS == FALSE
    public void modExpNoSteps(BigInteger a, BigInteger b, BigInteger c) {
        String str = a + " ^ " + b + " mod " + c;
        createTextView(str,"#fd6600");

        str  = "Solution: " + a.modPow(b,c) + "\n";
        createTextView(str,"#2259e3");
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
    public void clearPower(View v) {
        setContentView(R.layout.activity_power_mod);
    }
}
