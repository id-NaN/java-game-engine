package com.id_nan.gameEngine.engine.events;

public interface EventListener {
	// called on every event, event filtering is done by the implementation
	void onEvent(int eventID, Object event);
}
