package com.bgautam.mallow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bgautam.mallow.App.Application;
import com.bgautam.mallow.network.CustomVolleyRequestQueue;
import com.bgautam.mallow.pojo.Employee;

import java.util.List;

/**
 * Created by gautam on 07/01/17.
 */
public class EmployeesListAdaptor extends RecyclerView.Adapter<EmployeesListAdaptor.MyViewHolder> {

    private List<Employee> employeeDetails;
    private ImageLoader mImageLoader;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView firstName, lastName, designation, city;
        public NetworkImageView networkImageView;

        public MyViewHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.firstName);
            lastName = (TextView) view.findViewById(R.id.lastName);
            designation = (TextView) view.findViewById(R.id.designation);
            city = (TextView) view.findViewById(R.id.city);
            networkImageView = (NetworkImageView) view.findViewById(R.id.networkImageView);
            this.itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Employee data =  employeeDetails.get(this.getLayoutPosition());
            Intent intent = new Intent(Application.getContext(), DetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Employee",data);
            Application.getContext().startActivity(intent);
        }
    }

    public EmployeesListAdaptor(List<Employee> kings, Context context) {
        this.employeeDetails = kings;
        mImageLoader = CustomVolleyRequestQueue.getInstance(context)
                .getImageLoader();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employees_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Employee employee = employeeDetails.get(position);
        holder.firstName.setText(employee.getFirstName());
        holder.lastName.setText(employee.getLastName());
        holder.designation.setText(employee.getDesignation());
        holder.city.setText(employee.getCity());

        // Instantiate the RequestQueue.

        //Image URL - This can point to any image file supported by Android
        mImageLoader.get(employee.getImageURL(), ImageLoader.getImageListener(holder.networkImageView,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        holder.networkImageView.setImageUrl(employee.getImageURL(), mImageLoader);

    }

    @Override
    public int getItemCount() {
        return employeeDetails.size();
    }
}
