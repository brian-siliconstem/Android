package com.example.b.class8;

/**
 * Created by b on 2/17/2016.
 */
import com.wok.food.*;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 1/27/2016.
 */
public class FoodListAdapter extends ArrayAdapter<Food> {
    public interface BtnClickListener {
        public abstract void onDeleteClick(int position);
        public abstract void onEditClick(int position);
    }

    public BtnClickListener clickListener;

    public FoodListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public FoodListAdapter(Context context, int resource, List<Food> items) {
        super(context, resource, items);
    }

    public FoodListAdapter(Context context, int resource, List<Food> items,BtnClickListener listener ) {
        super(context, resource, items);
        clickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.adapter_food_list_row, null);
        }

        Food f = this.getItem(position);

        if (f != null) {
            TextView ftv1 = (TextView) v.findViewById(R.id.foodListAdapterFoodName);
            TextView ftv2 = (TextView) v.findViewById(R.id.foodListAdapterFoodCategory);


            if (ftv1 != null) {
                ftv1.setText(f.foodName);
            }


            if (ftv2 != null) {
                ftv2.setText(f.category.name());
            }
        }

        ImageButton deleteB = (ImageButton) v.findViewById(R.id.foodListAdapterButtonDelete);
        deleteB.setTag(position); //For passing the list item index
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickListener != null)
                    clickListener.onDeleteClick((Integer) v.getTag());
            }
        });

        ImageButton editB = (ImageButton) v.findViewById(R.id.foodListAdapterButtonEdit);
        editB.setTag(position); //For passing the list item index
        editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null)
                    clickListener.onEditClick((Integer) v.getTag());
            }
        });
        if (position % 2 == 1) {
            v.setBackgroundColor(Color.rgb(230,230,240));
        } else {
            v.setBackgroundColor(Color.rgb(240,240,255));
        }
        return v;
    }

}