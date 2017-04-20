package com.neofect.nfspinnerapp;

/**
 * Created by yoojaehong on 2017. 4. 20..
 */

public class DataObject {
	private String text;
	private int color;

	public DataObject(String text, int color) {
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public int getColor() {
		return color;
	}
}
