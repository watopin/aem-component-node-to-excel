package com.sample;

import org.w3c.dom.Element;

public class ElementWrapper {
	static final String PRIMARY_TYPE = "jcr:primaryType";
	static final String RESOURCE_TYPE = "sling:resourceType";
	static final String RESOURCE_SUPERTYPE = "sling:resourceSuperType";
	
	private final int indentLevel;
	private final Element value;
	
	
	public ElementWrapper(Element e, int i) {
		// TODO Auto-generated constructor stub
		this.value = e;
		this.indentLevel = i;
	}
	
	public String getName() {
		return value.getNodeName();
	}
	
	public int getIndentLevel() {
		return this.indentLevel;
	}
	
	public String getPrimaryType() {
		String primaryType = this.value.getAttribute(PRIMARY_TYPE);
		if(primaryType.isEmpty()) {
			return "-";
		}
		return primaryType;
	}
	
	public String getResourceType() {
		String resourceType = this.value.getAttribute(RESOURCE_TYPE);
		if (resourceType.isEmpty()) {
			String superType = this.value.getAttribute(RESOURCE_SUPERTYPE);
			if(superType.isEmpty()) {
				return "-";
			}
			return superType;
		}
		return resourceType;
	}
}
