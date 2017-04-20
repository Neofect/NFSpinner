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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		leftSpinner = (NFSpinner) findViewById(R.id.left_spinner);
		rightSpinner = (NFSpinner) findViewById(R.id.right_spinner);

		List<String> dataList = new ArrayList<>();
		dataList.add("1");
		dataList.add("2");
		dataList.add("3");
		dataList.add("4");
		dataList.add("5");
		leftSpinner.attachDataSource(dataList);
		leftSpinner.setListGravity(Gravity.RIGHT);
		leftSpinner.setOnItemSelectedListener(new NFSpinner.OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				Toast.makeText(MainActivity.this, "Item Selected:"+String.valueOf(index), Toast.LENGTH_LONG).show();
			}
		});

		List<DataObject> dataObjects = new ArrayList<>();
		dataObjects.add(new DataObject("1", Color.RED));
		dataObjects.add(new DataObject("2", Color.BLUE));
		dataObjects.add(new DataObject("3", Color.GREEN));
		dataObjects.add(new DataObject("4", Color.YELLOW));
		dataObjects.add(new DataObject("5", Color.CYAN));
		SpinnerAdapter adapter = new SpinnerAdapter(dataObjects);
		rightSpinner.setAdapter(adapter);

		rightSpinner.setListBackground(ContextCompat.getDrawable(this, R.drawable.rounded_board_bg));
		rightSpinner.addListItemDecoration(new NFSpinnerSeperateDecoration());
	}
}
