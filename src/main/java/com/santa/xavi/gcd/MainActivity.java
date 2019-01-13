package com.santa.xavi.gcd;

import android.content.Intent;
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

    public void euclideanGcd(View view) {
        Intent intent = new Intent(this, Euclidean.class);
        startActivity(intent);
    }

    public void inverseMod(View view) {
        Intent intent = new Intent(this, Inverse.class);
        startActivity(intent);
    }

    public void powerMod(View view) {
        Intent intent = new Intent(this, PowerMod.class);
        startActivity(intent);
    }
}