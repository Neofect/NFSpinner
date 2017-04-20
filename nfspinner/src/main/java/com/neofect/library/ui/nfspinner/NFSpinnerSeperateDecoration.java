package com.neofect.library.ui.nfspinner;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yoojaehong on 2017. 4. 20..
 */

public class NFSpinnerSeperateDecoration extends RecyclerView.ItemDecoration {

	private int topMargin = 5;
	private int bottomMargin = 5;

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		if (parent.getChildAdapterPosition(view) == 0) {
			outRect.top = topMargin;
		} else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount()-1) {
			outRect.bottom = bottomMargin;
		}

		outRect.left = 1;
		outRect.right = 1;
	}
}
