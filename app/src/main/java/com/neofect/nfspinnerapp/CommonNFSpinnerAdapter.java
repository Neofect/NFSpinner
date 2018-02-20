package com.neofect.nfspinnerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neofect.library.ui.nfspinner.NFSpinnerBaseAdapter;
import com.neofect.library.ui.nfspinner.NFSpinnerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neo on 2018/02/19.
 */

public class CommonNFSpinnerAdapter extends NFSpinnerBaseAdapter<CommonNFSpinnerAdapter.ViewHolder> {

	private List<ItemData> items;
	private TextView spinnerTextView;

	public CommonNFSpinnerAdapter(List<ItemData> items) {
		this.items = items;
	}

	@Override
	protected void onItemSelectedImpl(int position) {
		if (position >= 0 && position < items.size()) {
			int resourceId = items.get(position).getTitleResourceId();
			spinnerTextView.setText(resourceId);
		}
	}

	@Override
	protected void onBindViewHolderImpl(ViewHolder holder, int position) {
		if (position >= 0 && position < items.size()) {
			int resourceId = items.get(position).getTitleResourceId();
			holder.nameView.setText(resourceId);
		}
		boolean selected = (getSelectedIndex() == position);
		holder.nameView.setSelected(selected);
		holder.selectedIconView.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public View getSpinnerView(ViewGroup parent) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_common_spinner, parent, false);
		spinnerTextView = (TextView) view.findViewById(R.id.spinner_title_view);
		return view;
	}

	@Override
	protected void onShowDropDownImpl() {
		spinner.setSelected(true);
	}

	@Override
	protected void onHideDropDownImpl() {
		spinner.setSelected(false);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner_drop_down_item, parent, false);
		return new ViewHolder(view, this);
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public static final class ItemData {
		private int titleResourceId;

		public ItemData(int titleResourceId) {
			this.titleResourceId = titleResourceId;
		}

		public int getTitleResourceId() {
			return titleResourceId;
		}
	}

	static class ViewHolder extends NFSpinnerViewHolder {
		@BindView(R.id.spinner_drop_down_item_title_view)
		TextView nameView;
		@BindView(R.id.spinner_drop_down_item_selected_icon_view)
		ImageView selectedIconView;

		public ViewHolder(View itemView, NFSpinnerBaseAdapter adapter) {
			super(itemView, adapter);
			ButterKnife.bind(this, itemView);
		}
	}

}
