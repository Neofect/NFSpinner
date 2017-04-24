package com.neofect.library.ui.nfspinner;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yoojaehong on 2017. 4. 20..
 */

public class NFSpinnerSeperateDecoration extends RecyclerView.ItemDecoration {

	private final int topMargin;
	private final int bottomMargin;
	private final int seperatorMargin;

	public NFSpinnerSeperateDecoration(int topMargin, int bottomMargin, int seperatorMargin) {
		this.topMargin = topMargin;
		this.bottomMargin = bottomMargin;
		this.seperatorMargin = seperatorMargin;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		if (parent.getChildAdapterPosition(view) == 0) {
			outRect.top = topMargin;
		} else {
			outRect.top = seperatorMargin;
		}

		if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount()-1) {
			outRect.bottom = bottomMargin;
		}

		outRect.left = 1;
		outRect.right = 1;
	}
}
