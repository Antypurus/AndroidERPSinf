package com.example.david.sinfapplication.Activities.view_customer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.edit_customer.edit_customer_activity;
import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.R;

public class view_customer_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customer);
    }

    public void goto_edit_customer(View view)
    {
        Intent intent = new Intent(this, edit_customer_activity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}
