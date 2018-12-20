package com.example.david.sinfapplication.Activities.product_catalog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.example.david.sinfapplication.Activities.Main_Menu.main_menu_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class product_catalog_activity extends Activity {

    private RecyclerView m_product_list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_catalog);
        m_product_list = (RecyclerView) findViewById(R.id.products);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        m_product_list.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        m_product_list.setLayoutManager(mLayoutManager);


        products = new ArrayList<>();
        try {
            products = WebAPI.getProductsList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new product_list_adapter(products);
        m_product_list.setAdapter(mAdapter);

        Set<String>familias = new HashSet<>();
        HashMap<String,HashSet<String>> subfamilies = new HashMap<>();
        for(Product product:products)
        {
            familias.add(product.getFamily());

            if(subfamilies.containsKey(product.getFamily()))
            {
                subfamilies.get(product.getFamily()).add(product.getSubfamily());
            }else
            {
                HashSet<String> subfamily = new HashSet<>();
                subfamily.add(product.getSubfamily());
                subfamilies.put(product.getFamily(),subfamily);
            }
        }

        NavigationView navigationView = this.findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        MenuItem clear = menu.add("Clear Filter");
        clear.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                m_product_list.setAdapter(mAdapter);
                ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
                return true;
            }
        });

        for(String str:familias)
        {
            SubMenu sub = menu.addSubMenu(str);

            HashSet<String> subfams = subfamilies.get(str);
            for(String string:subfams)
            {
                MenuItem item = sub.add(str+"-"+string);
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        ArrayList<Product> correct_products = new ArrayList<>();
                        for(Product product:products)
                        {
                            Log.d("Primavera API","Clicked");
                            String prod_str = product.getFamily()+"-"+product.getSubfamily();
                            if(prod_str.equals(item.getTitle()))
                            {
                                correct_products.add(product);
                            }

                            RecyclerView.Adapter newAdapter = new product_list_adapter(correct_products);
                            m_product_list.setAdapter(newAdapter);
                        }
                        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawers();
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, main_menu_activity.class);
        startActivity(intent);
    }

}
