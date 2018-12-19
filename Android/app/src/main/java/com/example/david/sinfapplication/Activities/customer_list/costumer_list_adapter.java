package com.example.david.sinfapplication.Activities.customer_list;

import android.Manifest;
import android.app.Activity;
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

import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.MainActivity;
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
            submitDocument(client, cartProducts, isSale);

            //clear cart
            CommonStorage.cartProducts.clear();

            //TODO ver para onde redirecionar
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


    private void submitDocument(String customerId, ArrayList<CartProduct> cartProductArrayList,
                                Boolean isSale)
    {
        String docType = isSale ? "ECL" : "ORC";
        Document document = new Document(docType, "A");
        document.setLines(getDocumentLinesFromCartProductArrayList(cartProductArrayList));
        try
        {
            WebAPI.createDocument(document, customerId);
        } catch (TimeoutException e)
        {
            //TODO ver como fazer para mostrar o erro
            //((TextView)findViewById(R.id.error_pane)).setText("Network error!");
            return;
        } catch (Exception e)
        {
            //TODO ver como fazer para mostrar o erro
            //((TextView)findViewById(R.id.error_pane)).setText("Server error!");
            return;
        }
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
