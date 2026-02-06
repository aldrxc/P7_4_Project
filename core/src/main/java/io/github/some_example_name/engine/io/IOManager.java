package io.github.some_example_name.engine.io;
// package main.java.io.github.some_example_name.engine.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

public class IOManager implements Disposable {

    // 1. Singleton Instance
    private static IOManager instance;

    // 2. Composition: The Manager "owns" these sub-systems
    private AudioOutput audio;
    private DynamicInput dynamicInput;
    private OutputManager outputManager;

    // 3. Private Constructor (Singleton Pattern)
    private IOManager() {
        audio = new AudioOutput();
        dynamicInput = new DynamicInput();
        outputManager = new OutputManager();
    }

    // 4. Global Access Point
    public static IOManager getInstance() {
        if (instance == null) {
            instance = new IOManager();
        }
        return instance;
    }

    /**
     * Call this in your GameMaster.create()
     */
    public void init() {
        // Initialize the renderer
        outputManager.initialize();

        // Tell LibGDX to send all keyboard/mouse events to our DynamicInput class
        Gdx.input.setInputProcessor(dynamicInput);
    }

    // --- Getters for the Sub-Managers ---

    public AudioOutput getAudio() {
        return audio;
    }

    public DynamicInput getDynamicInput() {
        return dynamicInput;
    }

    public OutputManager getOutputManager() {
        return outputManager;
    }

    @Override
    public void dispose() {
        if (audio != null)
            audio.dispose();
        if (outputManager != null)
            outputManager.dispose();

        // Reset the input processor to avoid memory leaks or crashes
        Gdx.input.setInputProcessor(null);
    }
}