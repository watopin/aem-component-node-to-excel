package com.sample;

import java.util.Map;

public class NodeTableRow {
	Map<Integer, String> stringValues;
	int namePosition;
	int primaryTypePosition;
	
	public NodeTableRow(Map<Integer, String> values, int namePos, int PrimaryPos) {
		// TODO Auto-generated constructor stub
		this.stringValues = values;
		this.namePosition = namePos;
		this.primaryTypePosition = PrimaryPos;
	}
	
	public int getLastCellNo() {
		return this.primaryTypePosition + 25;
	}
	
	public int getResourceTypePosition() {
		return this.primaryTypePosition + 7;
	}
}
