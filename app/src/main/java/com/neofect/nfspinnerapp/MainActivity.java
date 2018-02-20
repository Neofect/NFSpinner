package com.neofect.nfspinnerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.neofect.library.ui.nfspinner.NFSpinner;
import com.neofect.library.ui.nfspinner.NFSpinnerSeperateDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private NFSpinner leftSpinner;
	private NFSpinner rightSpinner;
	private SpinnerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Left spinner
		{
			leftSpinner = (NFSpinner) findViewById(R.id.left_spinner);
			List<String> dataList = new ArrayList<>();
			dataList.add("1");
			dataList.add("22");
			dataList.add("333");
			dataList.add("4444");
			dataList.add("55555");
			dataList.add("666666");
			dataList.add("7777777");
			dataList.add("XXXXXXXXXXXXXXXXXXXXXXXX");
			leftSpinner.attachDataSource(dataList);
			leftSpinner.setListGravity(Gravity.RIGHT);
			leftSpinner.setOnItemSelectedListener(new NFSpinner.OnItemSelectedListener() {
				@Override
				public void onItemSelected(int index) {
					Toast.makeText(MainActivity.this, "Item Selected:"+String.valueOf(index), Toast.LENGTH_LONG).show();
				}
			});
		}

		// Right spinner
		{
			rightSpinner = (NFSpinner) findViewById(R.id.right_spinner);
			List<DataObject> dataObjects = new ArrayList<>();
			dataObjects.add(new DataObject("1", Color.RED));
			dataObjects.add(new DataObject("2", Color.BLUE));
			dataObjects.add(new DataObject("3", Color.GREEN));
			dataObjects.add(new DataObject("4", Color.YELLOW));
			dataObjects.add(new DataObject("5", Color.CYAN));
			adapter = new SpinnerAdapter(dataObjects);
			adapter.notifyDataSetChanged();
			rightSpinner.setAdapter(adapter);

			rightSpinner.setListBackground(ContextCompat.getDrawable(this, R.drawable.rounded_board_bg));
			rightSpinner.addListItemDecoration(new NFSpinnerSeperateDecoration(5, 5, 0));
		}

		// Second row spinner 1
		{
			NFSpinner spinner = (NFSpinner) findViewById(R.id.second_row_spinner_1);
			spinner.setListGravity(Gravity.RIGHT);

			List<CommonNFSpinnerAdapter.ItemData> itemDatas = new ArrayList<>();
			itemDatas.add(new CommonNFSpinnerAdapter.ItemData(R.string.app_name));
			itemDatas.add(new CommonNFSpinnerAdapter.ItemData(R.string.middle_length_text));
			CommonNFSpinnerAdapter adapter = new CommonNFSpinnerAdapter(itemDatas);
			adapter.notifyDataSetChanged();
			spinner.setAdapter(adapter);

			spinner.setListBackground(ContextCompat.getDrawable(this, R.drawable.rounded_board_bg));
			spinner.addListItemDecoration(new NFSpinnerSeperateDecoration(5, 5, 0));
		}
	}
}
