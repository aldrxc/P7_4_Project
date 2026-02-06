package io.github.some_example_name.engine.io;
// package main.java.io.github.some_example_name.engine.io;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;

public class DynamicInput implements InputProcessor {

    // Maps KeyCodes (Integers) to Boolean states
    private Map<Integer, Boolean> keyState;
    private Map<Integer, Boolean> keyJustPressed;

    private String currentInput = "";
    private Vector2 mousePosition;

    public DynamicInput() {
        keyState = new HashMap<>();
        keyJustPressed = new HashMap<>();
        mousePosition = new Vector2();
    }

    // --- LOGIC QUERIES (Used by your Game Loop) ---

    public boolean isKeyPressed(int keycode) {
        return keyState.getOrDefault(keycode, false);
    }

    public boolean isKeyJustPressed(int keycode) {
        boolean pressed = keyJustPressed.getOrDefault(keycode, false);
        if (pressed) {
            keyJustPressed.put(keycode, false); // Consume the event so it only fires once
        }
        return pressed;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }

    // --- LIBGDX CALLBACKS (Hardware Events) ---

    @Override
    public boolean keyDown(int keycode) {
        keyState.put(keycode, true);
        keyJustPressed.put(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyState.put(keycode, false);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mousePosition.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mousePosition.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        currentInput += character;
        return true;
    }

    @Override
    public boolean touchCancelled(int sX, int sY, int p, int b) {
        return false;
    }

    @Override
    public boolean touchDragged(int sX, int sY, int p) {
        mousePosition.set(sX, sY);
        return true;
    }

    @Override
    public boolean scrolled(float aX, float aY) {
        return false;
    }
}