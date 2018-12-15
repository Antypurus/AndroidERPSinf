package com.example.david.sinfapplication.Activities.edit_customer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.R;

public class edit_customer_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer);
        ProgressBar bar = (ProgressBar) findViewById(R.id.creation_progress);
        bar.getProgressDrawable().mutate().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
    }

    public void sendMessage(View view)
    {
        // prototype, change to the checkout view
        Intent intent = new Intent(this, register_order_activity.class);
        startActivity(intent);
    }

}
