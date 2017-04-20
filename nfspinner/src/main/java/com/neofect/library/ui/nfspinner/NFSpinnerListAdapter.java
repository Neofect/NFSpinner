package com.neofect.library.ui.nfspinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yoojaehong on 2017. 2. 21..
 */

public abstract class NFSpinnerListAdapter<D, VH extends NFSpinnerViewHolder> extends NFSpinnerBaseAdapter<VH> {

	protected List<D> dataList = null;

	public NFSpinnerListAdapter() {
		dataList = new ArrayList<>();
	}

	public NFSpinnerListAdapter(List<D> items) {
		setDataList(items);
	}

	public void setDataList(List<D> dataList) {
		this.dataList = dataList;
	}

	@Override
	public int getItemCount() {
		if (dataList != null) {
			return dataList.size();
		}

		return 0;
	}

	public int size() {
		return dataList.size();
	}

	public boolean isEmpty() {
		return dataList.isEmpty();
	}

	public boolean contains(D var1) {
		return dataList.contains(var1);
	}

	public boolean add(D var1) {
		return dataList.add(var1);
	}

	public boolean remove(D var1) {
		return dataList.remove(var1);
	}

	public boolean containsAll(Collection<?> var1) {
		return dataList.containsAll(var1);
	}

	public boolean addAll(Collection<? extends D> var1) {
		return dataList.addAll(var1);
	}

	public boolean addAll(int var1, Collection<? extends D> var2) {
		return dataList.addAll(var2);
	}

	public boolean removeAll(Collection<?> var1) {
		return dataList.removeAll(var1);
	}

	public boolean retainAll(Collection<?> var1) {
		return dataList.retainAll(var1);
	}

	public void clear() {
		dataList.clear();
	}

}
