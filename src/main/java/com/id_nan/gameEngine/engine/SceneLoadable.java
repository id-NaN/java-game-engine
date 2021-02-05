package com.id_nan.gameEngine.engine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// marks any UIElement that can be loaded from a config file
// requires a constructor with parameters (GameInstance, String)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SceneLoadable {
}
