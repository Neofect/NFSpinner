package com.neofect.library.ui.nfspinner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yoojaehong on 2017. 2. 21..
 */

public abstract class NFSpinnerBaseAdapter<VH extends NFSpinnerViewHolder> extends RecyclerView.Adapter<VH> {

	protected int selectedIndex;
	protected NFSpinner spinner;

	public NFSpinnerBaseAdapter() {
	}

	void onItemSelected(int position) {
		notifyItemClicked(position);
	}

	protected abstract void onItemSelectedImpl(int position);

	protected abstract void onBindViewHolderImpl(VH holder, int position);

	@Override
	public final void onBindViewHolder(VH holder, int position) {
		holder.setIndex(position);
		onBindViewHolderImpl(holder, position);
	}

	public abstract View getSpinnerView(ViewGroup parent);

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int index) {
		spinner.setSelectedIndex(index);
	}

	void notifyItemSelected(int index) {
		selectedIndex = index;
		onItemSelectedImpl(index);
		spinner.notifyOnItemSelected(index);
	}

	void setSpinner(NFSpinner spinner) {
		this.spinner = spinner;
	}

	void notifyItemClicked(int position) {
		spinner.onListItemClicked(position);
	}

	void onShowDropDown() {
		onShowDropDownImpl();
	}

	void onHideDropDown() {
		onHideDropDownImpl();
	}

	protected abstract void onShowDropDownImpl();
	protected abstract void onHideDropDownImpl();
}
