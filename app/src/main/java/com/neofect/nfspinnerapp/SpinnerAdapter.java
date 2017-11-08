package com.neofect.nfspinnerapp;

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
	private ImageView arrowView;

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
		arrowView = (ImageView) view.findViewById(R.id.layout_spinner_arrow_view);

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
		spinner.setSelected(true);
	}

	@Override
	protected void onHideDropDownImpl() {
		spinner.setSelected(false);
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
}
