package com.sample;

public class AemComponentUrl {
	
	private final String URL = "http://localhost:4502/apps/path/to/your/components/";
	String componentName;
	
	public AemComponentUrl(String componentName) {
		this.componentName = componentName;
	}
	
	public String getFullUrl() {
		String s = URL + componentName + ".xml";
		return s;
	}
}
