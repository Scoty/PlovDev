package com.proxiad.plovdev.adapters;

import java.util.List;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.LectureBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class LectureAdapter extends ArrayAdapter<LectureBean> {
 
        private final Context context;
        private final List<LectureBean> itemsArrayList;
 
        public LectureAdapter(Context context, List<LectureBean> itemsArrayList) {
 
            super(context, R.layout.row_lecture, itemsArrayList);
 
            this.context = context;
            this.itemsArrayList = itemsArrayList;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.row_lecture, parent, false);
 
            // 3. Get the two text view from the rowView
            TextView timePeriodView = (TextView) rowView.findViewById(R.id.timePeriod);
            TextView descriptionView = (TextView) rowView.findViewById(R.id.description);
 
            // 4. Set the text for textView 
            timePeriodView.setText(itemsArrayList.get(position).getName());
            descriptionView.setText(itemsArrayList.get(position).getDescription());
 
            // 5. return rowView
            return rowView;
        }
}