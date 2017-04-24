package com.neofect.library.ui.nfspinner;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yoojaehong on 2017. 4. 18..
 */

public class NFSpinnerDataAdapter extends NFSpinnerListAdapter<String, NFSpinnerDataViewHolder> {

	private static final int MAX_LEVEL = 10000;

	private TextView spinnerTextView = null;
	private Drawable drawable;

	public NFSpinnerDataAdapter(List<String> items) {
		super(items);
	}

	@Override
	public View getSpinnerView(ViewGroup parent) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_data_spinner_view, null);
		spinnerTextView = (TextView) view.findViewById(R.id.spinner_textview);

		drawable = ContextCompat.getDrawable(spinnerTextView.getContext(), R.drawable.spinner_data_view_arrow);
		spinnerTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

		changeData(getSelectedIndex());

		return view;
	}

	@Override
	public NFSpinnerDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_data_item_view, null);
		NFSpinnerDataViewHolder mainViewHoler = new NFSpinnerDataViewHolder(view, this);
		return mainViewHoler;
	}

	@Override
	protected void onShowDropDownImpl() {
		animateArrow(true);
	}

	@Override
	protected void onHideDropDownImpl() {
		animateArrow(false);
	}

	@Override
	protected void onBindViewHolderImpl(NFSpinnerDataViewHolder holder, int position) {
		String data = getData(position);
		holder.setText(data);
		holder.setSelected(position == getSelectedIndex());
	}

	@Override
	protected void onItemSelectedImpl(int position) {
		changeData(position);
	}

	// View
	private void changeData(int position) {
		String data = getData(position);
		if (data != null) {
			spinnerTextView.setText(data);
		} else {
			spinnerTextView.setText("");
		}
	}

	private void animateArrow(boolean shouldRotateUp) {
		int start = shouldRotateUp ? 0 : MAX_LEVEL;
		int end = shouldRotateUp ? MAX_LEVEL : 0;
		ObjectAnimator animator = ObjectAnimator.ofInt(drawable, "level", start, end);
		animator.setInterpolator(new LinearOutSlowInInterpolator());
		animator.start();
	}
}
