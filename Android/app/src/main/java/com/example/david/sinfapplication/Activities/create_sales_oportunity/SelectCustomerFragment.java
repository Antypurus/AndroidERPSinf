package com.example.david.sinfapplication.Activities.create_sales_oportunity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SelectCustomerFragment extends DialogFragment {

    private CharSequence[] names = {};
    private ArrayList<CustomerOfSalesman>customers;

    public interface CustomerSelectionInterface
    {
        public void onDialogClick(DialogFragment dialog,String customerId);
    }

    public SelectCustomerFragment()
    {

        ArrayList<CustomerOfSalesman> customers = null;
        try {
            customers = WebAPI.listCustomersOfASalesman(CommonStorage.vender_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        if(customers!=null) {
            ArrayList<String> names = new ArrayList<>();
            for(CustomerOfSalesman customer:customers)
            {
                names.add(customer.getName());
            }
            this.customers = customers;
            this.names = names.toArray(new CharSequence[names.size()]);
        }
    }

    CustomerSelectionInterface selectionInterface;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            selectionInterface = (CustomerSelectionInterface)context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(" must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Customer");
        builder.setItems(names, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CustomerOfSalesman customer = customers.get(which);
                selectionInterface.onDialogClick(SelectCustomerFragment.this,customer.getId());
            }
        });

        return builder.create();
    }

}
