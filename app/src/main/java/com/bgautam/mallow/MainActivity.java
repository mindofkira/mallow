package com.bgautam.mallow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bgautam.mallow.App.Application;
import com.bgautam.mallow.network.NetworkUtils;
import com.bgautam.mallow.pojo.EmployeeEntity;
import com.bgautam.mallow.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        if(NetworkUtils.isConnected()) {
            fetchData();
        } else {
            showNoConnectionDialog(this);
        }
    }

    private void showNoConnectionDialog(final Context ctx1) {
        final Context ctx = ctx1;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setMessage("This app needs internet net access. Please Turn on your internet access");
        builder.setTitle("Internet Access Needed");
        builder.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                ctx.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });

        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                loadData();
                return;
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            public void onCancel(DialogInterface dialog) {
                return;
            }
        });

        builder.show();
    }


    private void fetchData() {

        RequestQueue queue = Volley.newRequestQueue(Application.getContext());
        final ProgressDialog Asycdialog = new ProgressDialog(this);
        Asycdialog.setMessage("Loading...");
        Asycdialog.setTitle("Please Wait");
        Asycdialog.show();

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Constants.EMPLOYEE_ENDPOINT_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson gson = new Gson();
                            EmployeeEntity[] employeeDetails = gson.fromJson(response.toString(), EmployeeEntity[].class);
                            Log.i("TAG", "Success from API - Response JSON : " + employeeDetails.toString());

                            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                            EmployeesListAdaptor mAdapter = new EmployeesListAdaptor(employeeDetails[0].getEmployee(), Application.getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            Asycdialog.dismiss();
                            recyclerView.setAdapter(mAdapter);
                            
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Asycdialog.dismiss();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        queue.add(getRequest);

    }

}
