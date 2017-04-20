package com.neofect.library.ui.nfspinner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yoojaehong on 2017. 4. 19..
 */

public class NFSpinnerDataViewHolder extends NFSpinnerViewHolder {

	private TextView textView;
	private ImageView checkView;

	public NFSpinnerDataViewHolder(View itemView, NFSpinnerBaseAdapter adapter) {
		super(itemView, adapter);

		textView = (TextView) itemView.findViewById(R.id.spinner_item_textview);
		checkView = (ImageView) itemView.findViewById(R.id.spinner_item_check);
	}

	public void setText(String text) {
		if (textView != null) {
			textView.setText(text);
		}
	}

	public void setSelected(boolean selected) {
		checkView.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
	}
}
