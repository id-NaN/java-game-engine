package com.id_nan.gameEngine.engine.events;

import com.id_nan.gameEngine.engine.GameInstance;

import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;

public class MainEventHandler implements WindowListener, WindowFocusListener, WindowStateListener, MouseWheelListener, MouseMotionListener, MouseListener, KeyListener {
	private final GameInstance game;

	// create human readable event id keys
	public static final int EVENT_MOUSE_CLICK = 0;
	public static final int EVENT_MOUSE_PRESS = 1;
	public static final int EVENT_MOUSE_RELEASE = 2;
	public static final int EVENT_MOUSE_ENTER = 3;
	public static final int EVENT_MOUSE_EXIT = 4;
	public static final int EVENT_MOUSE_DRAG = 5;
	public static final int EVENT_MOUSE_MOVE = 6;
	public static final int EVENT_MOUSE_WHEEL_MOVE = 7;

	public static final int EVENT_WINDOW_GAIN_FOCUS = 8;
	public static final int EVENT_WINDOW_LOOSE_FOCUS = 9;
	public static final int EVENT_WINDOW_OPEN = 10;
	public static final int EVENT_WINDOW_ICONIFY = 11;
	public static final int EVENT_WINDOW_DEICONIFY = 12;
	public static final int EVENT_WINDOW_ACTIVATED = 13;
	public static final int EVENT_WINDOW_DEACTIVATED = 14;
	public static final int EVENT_WINDOW_CHANGED = 15;

	public static final int EVENT_KEY_TYPE = 16;
	public static final int EVENT_KEY_PRESS = 17;
	public static final int EVENT_KEY_RELEASE = 18;

	public static final int EVENT_GAME_CLOSED = 19;

	private int currentID = 19;

	private final LinkedList<EventListener> registeredListeners;

	// create event listener register
	public MainEventHandler(GameInstance game) {
		this.game = game;
		registeredListeners = new LinkedList<>();
	}

	// add listener
	public void registerListener(EventListener listener) {
		registeredListeners.add(listener);
	}

	// calls every event listener with event specified by id
	public void handleEvent(int eventID, Object event) {
		for (EventListener listener : registeredListeners) {
			if (listener != null) {
				listener.onEvent(eventID, event);
			}
		}
	}

	// returns unused custom event-id for use in custom events
	public int newEvent() {
		currentID++;
		return currentID;
	}

	// handle window closing
	@Override
	public void windowClosing(WindowEvent e) {
		handleEvent(EVENT_GAME_CLOSED, null);
		game.running = false;
	}

	// respond to all standard AWT events
	@Override
	public void mouseClicked(MouseEvent e) {
		handleEvent(EVENT_MOUSE_CLICK, e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		handleEvent(EVENT_MOUSE_PRESS, e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		handleEvent(EVENT_MOUSE_RELEASE, e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		handleEvent(EVENT_MOUSE_ENTER, e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		handleEvent(EVENT_MOUSE_EXIT, e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		handleEvent(EVENT_MOUSE_DRAG, e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		handleEvent(EVENT_MOUSE_MOVE, e);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		handleEvent(EVENT_MOUSE_WHEEL_MOVE, e);
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		handleEvent(EVENT_WINDOW_GAIN_FOCUS, e);
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		handleEvent(EVENT_WINDOW_LOOSE_FOCUS, e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		handleEvent(EVENT_WINDOW_OPEN, e);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {
		handleEvent(EVENT_WINDOW_ICONIFY, e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		handleEvent(EVENT_WINDOW_DEICONIFY, e);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		handleEvent(EVENT_WINDOW_ACTIVATED, e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		handleEvent(EVENT_WINDOW_DEACTIVATED, e);
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		handleEvent(EVENT_WINDOW_CHANGED, e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		handleEvent(EVENT_KEY_TYPE, e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		handleEvent(EVENT_KEY_PRESS, e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		handleEvent(EVENT_KEY_RELEASE, e);
	}
}
