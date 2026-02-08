package io.github.some_example_name.engine.scene;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Scene coordinator.
 * Handles registration, activation, lifecycle dispatch, and cleanup.
 */

public final class SceneManager {

    private static SceneManager instance;

    private final Map<String, EngineScreen> scenes;
    private final Set<EngineScreen> initialisedScenes;

    private EngineScreen active;
    private String activeName;

    private SceneManager() {
        this.scenes = new ConcurrentHashMap<>();
        this.initialisedScenes = Collections.newSetFromMap(new IdentityHashMap<>());
    }

    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public synchronized void load(String name, EngineScreen scene) {
        if (name == null | name.isBlank()) {
            throw new IllegalArgumentException("Scene name cannot be null or blank.");
        }

        if (scene == null) {
            throw new IllegalArgumentException("Scene cannot be null.");
        }
        scenes.put(name, scene);
    }
    
    public synchronized void unload(String name) {
        if (name == null || name.isBlank()) {
            return;
        }

        EngineScreen removed = scenes.remove(name);
        if (removed == null) {
            return;
        }

        initialisedScenes.remove(removed);

        if (removed == active) {
            active = null;
            activeName = null;
        }

        removed.dispose();
    }
}
