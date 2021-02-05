package com.id_nan.gameEngine.engine;

import com.id_nan.gameEngine.UIObjects.TestObject;
import com.id_nan.gameEngine.UIObjects.UIObject;
import com.id_nan.gameEngine.engine.events.MainEventHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;

public class GameInstance {
	// the config file
	private final Document configDocument;

	// storage for essential components
	// delta and frame timing
	public final TimeManager timing;
	// scene loader (loads scenes based on specified .xml file)
	public final SceneLoader sceneLoader;
	// default values such as font to use, such as a font
	public final DefaultValues defaultValues;
	// event system
	public final MainEventHandler eventHandler;
	// stores if the game is still running
	public boolean running;

	//holds all renderable Objects
	public final ArrayList<UIObject> uiObjects;

	public GameInstance() {
		// load config document
		configDocument = new ResourceLoader().getXMLFile("/engineConfig/config.xml");
		// load essential components
		timing = new TimeManager(this);
		uiObjects = new ArrayList<>();
		sceneLoader = new SceneLoader(this);
		defaultValues = new DefaultValues(this);
		eventHandler = new MainEventHandler();

		// load scene
		sceneLoader.loadScene(loadFromConfig("game/startScene"));

		// set running
		running = true;

		// create main thread
		new MainThread(this).start();
	}

	// load any string from config
	public String loadFromConfig(String key) {
		// divide the key into an array of nodes
		String[] tagList = key.split("/");
		// get base node
		Node node = configDocument.getDocumentElement();
		// get last node of path
		for (String s : tagList) {
			node = ((Element) node).getElementsByTagName(s).item(0);
		}
		// return text content of last node
		return node.getTextContent();
	}

	// update every object
	public void tick() {
		for (UIObject object : uiObjects) {
			if (object != null) {
				object.tick();
			}
		}
	}

	// adds any object to the UI
	public void addUIObject(UIObject object) {
		uiObjects.add(object);
	}

	// remove any object by index in uiObjects
	public void removeObject(int index) {
		uiObjects.set(index, null);
	}

	// remove any object in uiObjects
	public void removeObject(UIObject object) {
		removeObject(uiObjects.indexOf(object));
	}
}
