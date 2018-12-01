package com.example.david.sinfapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.CommonDataClasses.Customer;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Map<String,Object> parametersMap = new LinkedHashMap<>();
        parametersMap.put("username", "FEUP");
        parametersMap.put("password", "qualquer1");
        parametersMap.put("company", "BELAFLOR");
        parametersMap.put("instance", "DEFAULT");
        parametersMap.put("grant_type", "password");
        parametersMap.put("line", "professional");

     /*   try
        {
            PrimaveraWebAPI.login("FEUP", "qualquer1", "BELAFLOR", "DEFAULT",
                    "password", "professional");
            System.out.println(WebAPI.addCustomer(new Customer(
                    "88890",
                    "DAgsddgsS",
                    "4gdssdg23423",
                    "gdfdf",
                    "42323dfgfdg4",
                    "6",
                    "AS",
                    "gfddfg",
                    "dsfsgfddfgdfgdfs",
                    "GDFDFGD",
                    "",
                    "222431243432",
                    "ES",
                    "EUR",
                    "fdskçlfs",
                    null)));
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }*/

       /* try
        {
            byte[] parametersByteArray = Utils.getBytesOfHTTPParametersToSend(parametersMap);
            PrimaveraWebAPI.login("FEUP", "qualquer1", "BELAFLOR", "DEFAULT",
                    "password", "professional");
            new ListProducts();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }*/


       /* try
        {
            PrimaveraWebAPI.login("FEUP", "qualquer1", "BELAFLOR", "DEFAULT",
                    "password", "professional");
            WebAPI.viewCustomer("1");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }*/

        setContentView(R.layout.create_customer);
        ProgressBar bar = (ProgressBar) findViewById(R.id.creation_progress);
        bar.getProgressDrawable().mutate().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
    }

    public void sendMessage(View view)
    {
        // prototype, change to the checkout view
        Intent intent = new Intent(this, register_order_activity.class);
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