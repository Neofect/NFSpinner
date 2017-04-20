package com.neofect.nfspinnerapp;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neofect.library.ui.nfspinner.NFSpinnerBaseAdapter;
import com.neofect.library.ui.nfspinner.NFSpinnerViewHolder;

/**
 * Created by yoojaehong on 2017. 4. 20..
 */

public class SpinnerViewholder extends NFSpinnerViewHolder {

	private View iconView;
	private TextView textView;

	public SpinnerViewholder(View itemView, NFSpinnerBaseAdapter adapter) {
		super(itemView, adapter);

		iconView = itemView.findViewById(R.id.layout_spinner_icon_view);
		textView = (TextView) itemView.findViewById(R.id.layout_spinner_text_view);
		ImageView arrowView = (ImageView) itemView.findViewById(R.id.layout_spinner_arrow_view);

		iconView.setBackgroundColor(Color.RED);
		arrowView.setVisibility(View.GONE);
	}

	public void setIconColor(int color) {
		iconView.setBackgroundColor(color);
	}

	public void setText(String text) {
		textView.setText(text);
	}
}
