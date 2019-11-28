package com.sample;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		createFromServer();
	}
	
	private static void createFromFile() {
		Excel e = new Excel("C:\\my\\folder\\to\\save", "test17.xlsx");

		String filepath = "C:\\Users\\me\\read\\this.xml";
		e.testwrite(new XmlFile(filepath).read().getElements().toNodeTableRowList());
		System.out.println("Done");
	}
	
	private static void createFromServer() {
		AemComponentUrl url = new AemComponentUrl("global-navigation");
		AemClient client = new AemClient(url, new AemAuth("admin", "admin"));
		
		Excel e = new Excel("C:\\my\\folder\\to\\save", "test20.xlsx");
		e.testwrite(new XmlString(client.getXml(url)).toXmlData().getElements().toNodeTableRowList());
		System.out.println("Done");
	}
}
