package com.proxiad.plovdev.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.PartnerBean;

public class PartnerAdapter extends ArrayAdapter<PartnerBean> {
	
	private final Context context;
    private final List<PartnerBean> itemsArrayList;

    public PartnerAdapter(Context context, List<PartnerBean> itemsArrayList) {

        super(context, R.layout.row_partner, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 1. Create inflater 
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row_partner, parent, false);

        // 3. Get the image view from the rowView
        ImageView partnerImageView = (ImageView) rowView.findViewById(R.id.partnerImageView);
   
        // 4. Set the image and the click listener for textView 
        partnerImageView.setImageResource(itemsArrayList.get(position).getPortraitId());
        partnerImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemsArrayList.get(position).getUrlLink()));
                context.startActivity(intent);
            }
        });
        
        // 5. return rowView
        return rowView;
    }

}
