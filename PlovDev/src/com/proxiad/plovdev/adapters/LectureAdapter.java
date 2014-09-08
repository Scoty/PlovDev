package com.proxiad.plovdev.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder viewHolder;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.row_lecture, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imageSpeakerPortraitView = (ImageView) rowView.findViewById(R.id.imageSpeakerPortrait);
			viewHolder.timePeriodView = (TextView) rowView.findViewById(R.id.timePeriod);
			viewHolder.descriptionView = (TextView) rowView.findViewById(R.id.description);
			rowView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) rowView.getTag();
		}

		LectureBean lecture = itemsArrayList.get(position);
		viewHolder.imageSpeakerPortraitView.setImageResource(lecture.getSpeaker().getPortraitId());
		viewHolder.timePeriodView.setText(lecture.getTimePeriodName());
		viewHolder.descriptionView.setText(lecture.getDescription());

		return rowView;
	}

	static class ViewHolder {
		ImageView imageSpeakerPortraitView;
		TextView timePeriodView;
		TextView descriptionView;
	}
}