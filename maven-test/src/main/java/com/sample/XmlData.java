package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XmlData {
	private final Document document;

	public XmlData(Document document) {
		this.document = document;
	}

	public XmlData() {
		this.document = null;
	}

	public boolean noData() {
		return this.document == null;
	}

	public ElementWrapperList getElements() {
		Element root = this.document.getDocumentElement();
		System.out.println("root node name is " + root.getNodeName());
		List<ElementWrapper> list = new ArrayList<>();
		test(root, 0, list);
		return new ElementWrapperList(list);
	}

	private void test(Node n, int indentLevel, List<ElementWrapper> list) {
		if (n instanceof Text) {
			String val = n.getNodeValue().trim();
			if (val.equals("")) {
				return;
			}
		}

		list.add(new ElementWrapper((Element)n, indentLevel));
		indentLevel++;

		if (n.hasChildNodes()) {
			NodeList nl = n.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				test(nl.item(i), indentLevel, list);
			}
		}
	}
}
