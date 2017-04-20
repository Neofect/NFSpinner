package com.neofect.library.ui.nfspinner;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yoojaehong on 2017. 2. 21..
 */

public class NFSpinnerViewHolder extends RecyclerView.ViewHolder {

	protected final NFSpinnerBaseAdapter adapter;

	private int position = -1;

	public NFSpinnerViewHolder(View itemView, NFSpinnerBaseAdapter adapter) {
		super(itemView);

		this.adapter = adapter;

		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onItemSelected();
			}
		});
	}

	public void setIndex(int position) {
		this.position = position;
	}

	private void onItemSelected() {
		adapter.onItemSelected(position);
	}
}
