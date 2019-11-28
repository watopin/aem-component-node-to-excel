package com.sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFile {
	private final String filePath;
	
	public XmlFile(String file) {
		this.filePath = file;
	}
	
	public XmlData read() {
		DocumentBuilder builder = provideDocBuilder();
		File file = Paths.get(this.filePath).toFile();
		if (file.exists()) {
			try {
				Document doc = builder.parse(file);
				doc.normalize();
				return new XmlData(doc);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
				return new XmlData();
			}
		}
		return new XmlData();
	}
	
	private DocumentBuilder provideDocBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			builder = null;
		}
		return builder;
	}
} 
