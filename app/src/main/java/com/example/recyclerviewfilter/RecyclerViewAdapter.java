package com.example.recyclerviewfilter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.example.recyclerviewfilter.RecyclerViewAdapter.MyViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 규열 on 2018-02-13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {

    Context context;
    ArrayList<list> unFilteredlist;
    ArrayList<list> filteredList;

    public RecyclerViewAdapter(Context context, ArrayList<list> list) {
        super();
        this.context = context;
        this.unFilteredlist = list;
        this.filteredList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(filteredList.get(position).name);
        holder.textView1.setText(filteredList.get(position).friend);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class MyViewHolder extends ViewHolder {

        TextView textView;
        TextView textView1;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
            textView1 = itemView.findViewById(R.id.textview1);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filteredList = unFilteredlist;
                } else {
                    ArrayList<list> filteringList = new ArrayList<list>();

                    for (int i = 0; i < unFilteredlist.size(); i++) {
                        String name = unFilteredlist.get(i).name;
                        String friend = unFilteredlist.get(i).friend;

                        list data = new list();
                        data.name = name;
                        data.friend = friend;

                        if (name.toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(data);
                        }
                    }

                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<list>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
