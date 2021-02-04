package com.id_nan.gameEngine.engine;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ResourceLoader {
	public File getFile(String name) {
		try {
			return Paths.get(this.getClass().getResource(name).toURI()).toFile();
		} catch (Exception e) {
			System.out.println(String.format("resource \"%s\" could not be loaded", name));
			return null;
		}
	}

	public Document getXMLFile(String name) {
		File file = getFile(name);
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
