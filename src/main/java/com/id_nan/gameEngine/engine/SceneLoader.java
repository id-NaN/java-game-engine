package com.id_nan.gameEngine.engine;

import com.id_nan.gameEngine.UIObjects.UIObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SceneLoader {
	private final GameInstance game;

	// saves GameInstance pointer
	public SceneLoader(GameInstance game) {
		this.game = game;
	}

	// load any scene with the specified name/path from resources/scenes
	public void loadScene(String sceneName) {
		// clear the old data from the scene
		game.uiObjects.clear();
		// load scene file
		Document sceneFile = new ResourceLoader().getXMLFile(String.format("/scenes/%s", sceneName));
		// get main node of file
		Node mainNode = sceneFile.getDocumentElement();
		// get every element in main node
		NodeList childNodes = mainNode.getChildNodes();
		// create a corresponding UIObject for every child element
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			// only if node is an actual node(no text nodes inbetween the element nodes)
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// get the classpath of the UIObject
				String classPath = String.format("com.id_nan.gameEngine.UIObjects.%s", node.getNodeName());
				try {
					// get class of UIObject
					Class<?> objectClass = Class.forName(classPath);
					// check if object can be loaded from scene
					if (!objectClass.isAnnotationPresent(SceneLoadable.class)) {
						System.out.printf("UIObject \"%s\" not loadable from scene%n", classPath);
						// skip over object if not
						continue;
					}
					// get constructor with arguments (GameInstance, String)
					Constructor<?> constructor = objectClass.getConstructor(GameInstance.class, String.class);
					// create object from constructor and add it to uiObject list in GameInstance
					game.uiObjects.add((UIObject)constructor.newInstance(game, node.getTextContent()));
				} catch (ClassNotFoundException e) {
					System.out.printf("unable to find UIObject \"%s\"%n", classPath);
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.printf("unable to call constructor of \"%s\"%n", classPath);
					e.printStackTrace();
				} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
