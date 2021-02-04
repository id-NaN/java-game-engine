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
	private final Document configDocument;

	public final TimeManager timing;
	public final SceneLoader sceneLoader;
	public final DefaultValues defaultValues;
	public final MainEventHandler eventHandler;
	public boolean running;

	public final ArrayList<UIObject> uiObjects;

	public GameInstance() {
		configDocument = new ResourceLoader().getXMLFile("/engineConfig/config.xml");
		timing = new TimeManager(this);
		uiObjects = new ArrayList<UIObject>();
		sceneLoader = new SceneLoader(this);
		defaultValues = new DefaultValues(this);
		eventHandler = new MainEventHandler(this);

		sceneLoader.loadScene(loadFromConfig("game/startScene"));

		running = true;

		new MainThread(this).start();
	}

	public String loadFromConfig(String key) {
		String[] tagList = key.split("/");
		Node node = configDocument.getDocumentElement();
		for (String s : tagList) {
			node = ((Element) node).getElementsByTagName(s).item(0);
		}
		return node.getTextContent();
	}

	public void tick() {
		for (UIObject object : uiObjects) {
			if (object != null) {
				object.tick();
			}
		}
	}

	public void addUIObject(UIObject object) {
		uiObjects.add(object);
	}

	public void removeObject(int index) {
		uiObjects.set(index, null);
	}

	public void removeObject(UIObject object) {
		removeObject(uiObjects.indexOf(object));
	}
}
