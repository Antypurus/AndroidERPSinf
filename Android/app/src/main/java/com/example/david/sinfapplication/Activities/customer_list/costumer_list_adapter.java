package com.example.david.sinfapplication.Activities.customer_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.Main_Menu.main_menu_activity;
import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static android.Manifest.permission.CALL_PHONE;


public class costumer_list_adapter extends RecyclerView.Adapter<costumer_list_adapter.costumer_list_holder>
{

    ArrayList<CustomerOfSalesman> customers;
    Boolean isPerformingCheckout = false;
    ArrayList<CartProduct> cartProducts;
    Boolean isSale;

    public costumer_list_adapter(ArrayList<CustomerOfSalesman> customers)
    {
        this.customers = customers;
        isPerformingCheckout = false;

    }

    public costumer_list_adapter(ArrayList<CustomerOfSalesman> customers, ArrayList<CartProduct> cartProducts, Boolean isSale)
    {
        this.customers = customers;
        this.isPerformingCheckout = true;
        this.cartProducts = cartProducts;
        this.isSale = isSale;
    }

    public static class costumer_list_holder extends RecyclerView.ViewHolder
    {
        public ConstraintLayout costumer = null;
        public TextView costumer_name = null;
        public ConstraintLayout costumer_layout = null;
        public Button costumer_call_button = null;

        public costumer_list_holder(ConstraintLayout costumer)
        {
            super(costumer);

            this.costumer = costumer;
            this.costumer_name = (TextView) this.costumer.findViewById(R.id.costumer_name);
            this.costumer_layout = (ConstraintLayout) this.costumer.findViewById(R.id.costumer_layout);
            this.costumer_call_button = (Button) this.costumer.findViewById(R.id.costumer_call_button);
        }
    }

    @Override
    public costumer_list_holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.costumer_list_item, parent, false);

        costumer_list_adapter.costumer_list_holder adapter = new costumer_list_adapter.costumer_list_holder(c_layout);


        return adapter;
    }

    @Override
    public void onBindViewHolder(costumer_list_holder holder, int position)
    {
        holder.costumer_name.setText(this.customers.get(position).getName());

        holder.costumer_layout.setOnClickListener(view -> goToCustomer(holder.costumer, customers.get(position).getId()));
        holder.costumer_call_button.setOnClickListener(view -> callClient(holder.costumer_call_button, customers.get(position).getPhoneNumber()));
    }

    @Override
    public int getItemCount()
    {
        return this.customers.size();
    }

    public void goToCustomer(View view, String client)
    {
        if (!isPerformingCheckout)
        {
            Intent intent = new Intent(view.getContext(), view_customer_activity.class);
            intent.putExtra("customerId", client);
            view.getContext().startActivity(intent);
        } else
        {
            //submit document
            submitDocument(view, client, cartProducts, isSale);
        }
    }

    public void callClient(View view, String client_number)
    {
        Intent call_intent = new Intent(Intent.ACTION_CALL);
        call_intent.setData(Uri.parse("tel:" + client_number));
        if (ActivityCompat.checkSelfPermission(view.getContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{CALL_PHONE}, 1);

            if (ActivityCompat.checkSelfPermission(view.getContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                view.getContext().startActivity(call_intent);
            }
        } else
        {
            view.getContext().startActivity(call_intent);
        }
    }


    private void submitDocument(View view, String customerId, ArrayList<CartProduct> cartProductArrayList,
                                Boolean isSale)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        String docType = isSale ? "ECL" : "ORC";
        Document document = new Document(docType, "A");
        document.setLines(getDocumentLinesFromCartProductArrayList(cartProductArrayList));
        try
        {
            boolean success = WebAPI.createDocument(document, customerId);

            if (success)
            {
                showMessageBoxAndForwardToMainMenu(view, builder, "Success", "Order created Successfully");

                //clear cart
                CommonStorage.cartProducts.clear();
            }
            else
                showMessageBoxAndForwardToRegisterOrder(view, builder, "Error", "The request to primavera has failed. Please try again later.");

        } catch (TimeoutException e)
        {
            showMessageBoxAndForwardToRegisterOrder(view, builder, "Error", "The request to primavera server timed out. Could not complete your request. Please try again later.");

        } catch (Exception e)
        {
            showMessageBoxAndForwardToRegisterOrder(view, builder, "Error", "Internal error occurred. Could not complete your request. Please try again later.");
        }
    }

    private void showMessageBoxAndForwardToRegisterOrder(View view, AlertDialog.Builder builder, String title, String message)
    {
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(view.getContext(), register_order_activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                view.getContext().startActivity(intent);
            }
        });
        builder.show();
    }

    private void showMessageBoxAndForwardToMainMenu(View view, AlertDialog.Builder builder, String title, String message)
    {
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(view.getContext(), main_menu_activity.class);
                view.getContext().startActivity(intent);
            }
        });
        builder.show();
    }

    private ArrayList<DocumentLine> getDocumentLinesFromCartProductArrayList(ArrayList<CartProduct> cartProductArrayList)
    {
        ArrayList<DocumentLine> lines = new ArrayList<>();
        for (CartProduct cartProduct : cartProductArrayList)
        {
            DocumentLine line = new DocumentLine(cartProduct.getId(), cartProduct.getQuantity());
            lines.add(line);
        }

        return lines;
    }

}
