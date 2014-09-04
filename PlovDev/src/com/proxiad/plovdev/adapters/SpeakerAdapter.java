package com.proxiad.plovdev.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.SpeakerBean;
import com.proxiad.plovdev.utils.DataParser;

public class SpeakerAdapter extends ArrayAdapter<SpeakerBean> {

	private final Context context;
	private final List<SpeakerBean> itemsArrayList;

	public SpeakerAdapter(Context context, List<SpeakerBean> itemsArrayList) {

		super(context, R.layout.row_partner, itemsArrayList);

		this.context = context;
		this.itemsArrayList = itemsArrayList;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.row_speaker, parent, false);

		// 3. Get the image view from the rowView
		ImageView speakerImageView = (ImageView) rowView.findViewById(R.id.imageSpeakerPortrait);
		TextView nameSpeakerView = (TextView) rowView.findViewById(R.id.nameSpeaker);
		TextView bioSpeakerView = (TextView) rowView.findViewById(R.id.bioSpeaker);

		// 4. Set the image and the click listener for textView
		speakerImageView.setImageResource(itemsArrayList.get(position).getPortraitId());
		nameSpeakerView.setText(itemsArrayList.get(position).getName());
		bioSpeakerView.setText(itemsArrayList.get(position).getBio());

		// 5. return rowView
		return rowView;
	}

}
