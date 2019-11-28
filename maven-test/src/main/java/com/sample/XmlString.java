package com.sample;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlString {
	private final String value;
	
	public XmlString(String s) {
		this.value = s;
	}
	
	public Document toXmlDocument() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(this.value)));
			doc.normalize();
			return doc;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("this wouldn't happen", e);
		} catch (SAXException | IOException e) {
			throw new RuntimeException("cannot convert string to document class", e);
		}
	}
	
	public XmlData toXmlData() {
		return new XmlData(this.toXmlDocument());
	}
}
