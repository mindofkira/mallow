package com.bgautam.mallow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gautam on 07/01/17.
 */
public class EmployeesListAdaptor extends RecyclerView.Adapter<EmployeesListAdaptor.MyViewHolder> {

    private List<Employee> employeeDetails;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView firstName, lastName, designation, city;

        public MyViewHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.firstName);
            lastName = (TextView) view.findViewById(R.id.lastName);
            designation = (TextView) view.findViewById(R.id.designation);
            city = (TextView) view.findViewById(R.id.city);
        }

    }

    public EmployeesListAdaptor(List<Employee> kings) {
        this.employeeDetails = kings;
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
    }

    @Override
    public int getItemCount() {
        return employeeDetails.size();
    }
}
