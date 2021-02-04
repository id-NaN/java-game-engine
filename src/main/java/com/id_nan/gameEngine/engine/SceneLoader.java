package com.id_nan.gameEngine.engine;

import com.id_nan.gameEngine.UIObjects.UIObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SceneLoader {
	private GameInstance game;

	public SceneLoader(GameInstance game) {
		this.game = game;
	}

	public void loadScene(String sceneName) {
		game.uiObjects.clear();
		Document sceneFile = new ResourceLoader().getXMLFile(String.format("/scenes/%s", sceneName));
		Node mainNode = sceneFile.getDocumentElement();
		NodeList childNodes = mainNode.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String classPath = String.format("com.id_nan.gameEngine.UIObjects.%s", node.getNodeName());
				try {
					Class<?> objectClass = Class.forName(classPath);
					if (!objectClass.isAnnotationPresent(SceneLoadable.class)) {
						System.out.println(String.format("UIObject \"%s\" not loadable from scene", classPath));
						continue;
					}
					Constructor<?> constructor = objectClass.getConstructor(GameInstance.class, String.class);
					game.uiObjects.add((UIObject)constructor.newInstance(game, node.getTextContent()));
				} catch (ClassNotFoundException e) {
					System.out.println(String.format("unable to find UIObject \"%s\"", classPath));
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println(String.format("unable to call constructor of \"%s\"", classPath));
					e.printStackTrace();
				} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
