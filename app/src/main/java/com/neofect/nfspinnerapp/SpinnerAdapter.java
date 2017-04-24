package com.neofect.nfspinnerapp;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neofect.library.ui.nfspinner.NFSpinnerListAdapter;

import java.util.List;

/**
 * Created by yoojaehong on 2017. 4. 20..
 */

public class SpinnerAdapter extends NFSpinnerListAdapter<DataObject, SpinnerViewholder> {

	private static final int MAX_LEVEL = 10000;

	private View spinnerIconView;
	private TextView spinnerTextView;
	private Drawable arrowDrawable;

	public SpinnerAdapter(List<DataObject> items) {
		super(items);
	}

	@Override
	protected void onBindViewHolderImpl(SpinnerViewholder holder, int position) {
		DataObject dataObject = getData(position);
		holder.setIconColor(dataObject.getColor());
		holder.setText(dataObject.getText());
	}

	@Override
	public View getSpinnerView(ViewGroup parent) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner, null);
		spinnerIconView = view.findViewById(R.id.layout_spinner_icon_view);
		spinnerTextView = (TextView) view.findViewById(R.id.layout_spinner_text_view);
		ImageView arrowView = (ImageView) view.findViewById(R.id.layout_spinner_arrow_view);

		arrowDrawable = ContextCompat.getDrawable(spinnerTextView.getContext(), com.neofect.library.ui.nfspinner.R.drawable.spinner_data_view_arrow);
		arrowView.setImageDrawable(arrowDrawable);

		changeData(getSelectedIndex());

		return view;
	}

	@Override
	public SpinnerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner, null);
		SpinnerViewholder mainViewHoler = new SpinnerViewholder(view, this);
		return mainViewHoler;
	}

	@Override
	protected void onItemSelectedImpl(int position) {
		changeData(position);
	}

	@Override
	protected void onShowDropDownImpl() {
		animateArrow(true);
	}

	@Override
	protected void onHideDropDownImpl() {
		animateArrow(false);
	}

	private void changeData(int index) {
		DataObject data = getData(index);
		if (data != null) {
			spinnerIconView.setVisibility(View.VISIBLE);
			spinnerIconView.setBackgroundColor(data.getColor());
			spinnerTextView.setText(data.getText());
		} else {
			spinnerIconView.setVisibility(View.INVISIBLE);
			spinnerTextView.setText("");
		}
	}

	private void animateArrow(boolean shouldRotateUp) {
		int start = shouldRotateUp ? 0 : MAX_LEVEL;
		int end = shouldRotateUp ? MAX_LEVEL : 0;
		ObjectAnimator animator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
		animator.setInterpolator(new LinearOutSlowInInterpolator());
		animator.start();
	}
}
