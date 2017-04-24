package com.neofect.library.ui.nfspinner;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.List;

/**
 * @author rouddyNeo
 *
 * https://github.com/
 */
@SuppressWarnings("unused")
public class NFSpinner extends LinearLayout {

	public interface OnItemSelectedListener {
		void onItemSelected(int index);
	}

    private static final int DEFAULT_ELEVATION = 16;
    private static final String INSTANCE_STATE = "instance_state";
    private static final String SELECTED_INDEX = "selected_index";
    private static final String IS_POPUP_SHOWING = "is_popup_showing";

    private PopupWindow popupWindow;
    private RecyclerView recyclerView;
	private View spinnerView;
    private NFSpinnerBaseAdapter adapter = null;
	private OnItemSelectedListener onItemSelectedListener = null;

	private int marginBetween = 5;
	private int listGravity = Gravity.LEFT;

    public NFSpinner(Context context) {
        super(context);
        init(context, null);
    }

    public NFSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NFSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

	private void init(Context context, AttributeSet attrs) {
		Resources resources = getResources();
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NFSpinner);

		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		setClickable(true);

		createListView();

		typedArray.recycle();
	}

	private void createListView() {
		recyclerView = new RecyclerView(getContext());
		// Set the spinner's id into the listview to make it pretend to be the right parent in
		// onItemClick
		recyclerView.setId(getId());
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		//hide vertical and horizontal scrollbars
		recyclerView.setVerticalScrollBarEnabled(false);
		recyclerView.setHorizontalScrollBarEnabled(false);
		recyclerView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_board_bg));

		popupWindow = new PopupWindow(getContext());
		popupWindow.setContentView(recyclerView);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			popupWindow.setElevation(DEFAULT_ELEVATION);
		}

		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				adapter.onHideDropDown();
				RecyclerView.Adapter adapter = recyclerView.getAdapter();
				if (adapter != null) {
					adapter.notifyDataSetChanged();
				}
			}
		});
	}

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(SELECTED_INDEX, getSelectedIndex());
        if (popupWindow != null) {
            bundle.putBoolean(IS_POPUP_SHOWING, popupWindow.isShowing());
            dismissDropDown();
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedState) {
        if (savedState instanceof Bundle) {
            Bundle bundle = (Bundle) savedState;
            int selectedIndex = bundle.getInt(SELECTED_INDEX);

            if (adapter != null) {
                adapter.notifyItemSelected(selectedIndex);
            }

            if (bundle.getBoolean(IS_POPUP_SHOWING)) {
                if (popupWindow != null) {
                    // Post the show request into the looper to avoid bad token exception
                    post(new Runnable() {
                        @Override
                        public void run() {
                            showDropDown();
                        }
                    });
                }
            }
            savedState = bundle.getParcelable(INSTANCE_STATE);
        }
        super.onRestoreInstanceState(savedState);
    }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthPopupSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
		int heightPopupSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		popupWindow.setWidth(widthPopupSpec);
		popupWindow.setHeight(heightPopupSpec);
		recyclerView.measure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (spinnerView != null) {
			spinnerView.layout(0, 0, r-l, b-t);
		}
	}

	@Override
	public boolean onTouchEvent(@NonNull MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (!popupWindow.isShowing()) {
				showDropDown();
			} else {
				dismissDropDown();
			}
		}
		return super.onTouchEvent(event);
	}

	private void dismissDropDown() {
		popupWindow.dismiss();
		adapter.onHideDropDown();
	}

	private void showDropDown() {
		int left = 0;
		switch (listGravity) {
			case Gravity.LEFT:
				break;

			case Gravity.RIGHT: {
				Log.e("!!!", "recyclerView:"+recyclerView.getMeasuredWidth()+",spinner:"+getMeasuredWidth());
				left = getMeasuredWidth() - recyclerView.getMeasuredWidth();
				break;
			}
		}
		popupWindow.showAsDropDown(this, left, marginBetween);
		adapter.onShowDropDown();
	}

	public int getSelectedItemPosition() {
		if (adapter != null) {
			return adapter.getSelectedIndex();
		}

		return -1;
	}

	public void setListBackground(Drawable drawable) {
		recyclerView.setBackground(drawable);
	}

	public void setListGravity(int gravity) {
		this.listGravity = gravity;
	}

	public void addListItemDecoration(RecyclerView.ItemDecoration decor) {
		recyclerView.addItemDecoration(decor);
	}

	public void setMarginBetween(int marginBetween) {
		this.marginBetween = marginBetween;
	}

	public void attachDataSource(@NonNull List<String> dataset) {
		adapter = new NFSpinnerDataAdapter(dataset);
		setAdapterInternal(adapter);
	}

	public void setAdapter(@NonNull NFSpinnerBaseAdapter adapter) {
		this.adapter = adapter;
		setAdapterInternal(adapter);
	}

	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		onItemSelectedListener = listener;
	}

	private void setAdapterInternal(@NonNull NFSpinnerBaseAdapter adapter) {
		// If the adapter needs to be settled again, ensure to reset the selected index as well
		if (spinnerView != null) {
			removeView(spinnerView);
			spinnerView = null;
		}

		spinnerView = adapter.getSpinnerView(this);
		if (spinnerView != null) {
			LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			addView(spinnerView, 0, params);
		}

		adapter.setSpinner(this);
		adapter.notifyItemClicked(0);
		recyclerView.setAdapter(adapter);
		requestLayout();
	}

	/**
	 * Set the default spinner item using its index
	 *
	 * @param position the item's position
	 */
	void setSelectedIndex(int position) {
		if (adapter != null) {
			if (position >= 0 && position < adapter.getItemCount()) {
				adapter.notifyItemSelected(position);
			} else {
				throw new IllegalArgumentException("Position must be lower than adapter count!");
			}
		}
	}

	int getSelectedIndex() {
		if (adapter != null) {
			return adapter.getSelectedIndex();
		} else {
			return -1;
		}
	}

	void onListItemClicked(int position) {
		if (adapter != null) {
			if (position >= 0 && position < adapter.getItemCount()) {
				adapter.notifyItemSelected(position);
			}
		}

		dismissDropDown();
	}

	void notifyOnItemSelected(int position) {
		if (onItemSelectedListener != null) {
			onItemSelectedListener.onItemSelected(position);
		}
	}
}
