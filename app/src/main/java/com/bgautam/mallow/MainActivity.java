package com.bgautam.mallow;

import android.os.Bundle;
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
import com.google.gson.Gson;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();

    }

    private void fetchData() {

        RequestQueue queue = Volley.newRequestQueue(Application.getContext());

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, Constants.EMPLOYEE_ENDPOINT_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson gson = new Gson();
                            EmployeeEntity[] employeeDetails = gson.fromJson(response.toString(), EmployeeEntity[].class);
                            Log.i("TAG", "Success from API - Response JSON : " + employeeDetails.toString());

                            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                            EmployeesListAdaptor mAdapter = new EmployeesListAdaptor(employeeDetails[0].getEmployee());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);


                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        queue.add(getRequest);

    }

}
