package com.proxiad.plovdev.adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proxiad.plovdev.LectureDetailsActivity;
import com.proxiad.plovdev.R;
import com.proxiad.plovdev.beans.LectureBean;

public class LectureAdapter extends ArrayAdapter<LectureBean> {

	private final Context context;
	private final List<LectureBean> itemsArrayList;

	public LectureAdapter(Context context, List<LectureBean> itemsArrayList) {

		super(context, R.layout.row_lecture, itemsArrayList);

		this.context = context;
		this.itemsArrayList = itemsArrayList;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.row_lecture, parent, false);

		// 3. Get the image view and the two text views from the rowView
		ImageView imageSpeakerPortraitView = (ImageView) rowView.findViewById(R.id.imageSpeakerPortrait);
		TextView timePeriodView = (TextView) rowView.findViewById(R.id.timePeriod);
		TextView descriptionView = (TextView) rowView.findViewById(R.id.description);

		// 4. Set the text for textView and the image for the imageView
		imageSpeakerPortraitView.setImageResource(itemsArrayList.get(position).getSpeaker().getPortraitId());
		timePeriodView.setText(itemsArrayList.get(position).getName());
		descriptionView.setText(itemsArrayList.get(position).getDescription());

		OnTextClickedListener listener = new OnTextClickedListener(position);
		timePeriodView.setOnClickListener(listener);
		descriptionView.setOnClickListener(listener);
		
		// 5. return rowView
		return rowView;
	}

	private class OnTextClickedListener implements OnClickListener {
		private int position;

		private OnTextClickedListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View view) {
			Intent intent = new Intent(context, LectureDetailsActivity.class);
		    intent.putExtra("position", position);
		    context.startActivity(intent);
		}
	}
}