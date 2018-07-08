package com.example.prate.mytodo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    ArrayList<Expense> item;
    LayoutInflater inflator;
    public Adapter(@NonNull Context context, ArrayList<Expense> item) {
        super(context,0, item);
        this.item=item;
        inflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return item.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



     View output=inflator.inflate(R.layout.adapter_view,parent,false);

           TextView h=output.findViewById(R.id.tittle);
           TextView i=output.findViewById(R.id.discription);




        Expense expense=item.get(position);

        h.setText(expense.getTittle());
        h.setTextColor(Color.parseColor("white"));

        i.setText(expense.getDiscription());
        i.setTextColor(Color.parseColor("white"));
        return output;
    }
}
