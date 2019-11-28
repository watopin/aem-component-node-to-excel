package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class ElementWrapperList {
	private final List<ElementWrapper> value;
	
	public ElementWrapperList(List<ElementWrapper> elements) {
		this.value = elements;
	}
	
	public OptionalInt maxIndent() {
		return value.stream().mapToInt(e -> e.getIndentLevel()).max();
	}
	
	public int length() {
		return value.size();
	}
	
	public List<Map<Integer, String>> test() {
		int max = this.maxIndent().getAsInt();
		int c = 0;
		List<Map<Integer, String>> rows = new ArrayList<>();
		for (ElementWrapper e : value) {
			Map<Integer, String> row = new HashMap<>();
			row.put(1, String.valueOf(c++)); //No
			int nameposition = e.getIndentLevel() + 4;
			row.put(nameposition, e.getName()); //Name
			int typeposition = max + 9;
			row.put(typeposition, e.getPrimaryType()); //primary type
			row.put(typeposition + 1, e.getResourceType()); //resource type
			rows.add(row);
		}
		return rows;
	}
	
	public List<NodeTableRow> toNodeTableRowList() {
		int max = this.maxIndent().getAsInt();
		int c = 0;
		List<NodeTableRow> rows = new ArrayList<>();
		for (ElementWrapper e : value) {
			Map<Integer, String> rowVals = new HashMap<>();
			rowVals.put(1, String.valueOf(c++)); //No
			int nameposition = e.getIndentLevel() + 4;
			rowVals.put(nameposition, e.getName()); //Name
			int typeposition = max + 9;
			rowVals.put(typeposition, e.getPrimaryType()); //primary type
			rowVals.put(typeposition + 7, e.getResourceType()); //resource type
			rows.add(new NodeTableRow(rowVals, nameposition, typeposition));
		}
		return rows;
	}
}
