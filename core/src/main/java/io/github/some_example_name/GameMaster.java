package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// IMPORTS
import io.github.some_example_name.engine.io.IOManager;
import io.github.some_example_name.tests.TestEntity;

public class GameMaster extends Game {

    // We add a test object here so we can see if rendering works
    private TestEntity testObject;

    @Override
    public void create() {
        // 1. Initialize the Engine Hub
        IOManager.getInstance().init();
        System.out.println("IOManager Online. Systems Check Initiated...");

        // 2. Create a dummy object to test the OutputManager
        testObject = new TestEntity(200, 200);

        System.out.println("------------------------------------------------");
        System.out.println(" VISUAL TEST: You should see a RED SQUARE.");
        System.out.println(" INPUT TEST:  Use ARROW KEYS to move.");
        System.out.println(" MOUSE TEST:  Click anywhere to see coordinates.");
        System.out.println("------------------------------------------------");
    }

    @Override
    public void render() {
        // --- LOGIC PHASE (Testing InputManager) ---

        // We use OUR DynamicInput, not Gdx.input directly!
        if (IOManager.getInstance().getDynamicInput().isKeyPressed(Input.Keys.LEFT)) {
            testObject.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (IOManager.getInstance().getDynamicInput().isKeyPressed(Input.Keys.RIGHT)) {
            testObject.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
        }
        if (IOManager.getInstance().getDynamicInput().isKeyPressed(Input.Keys.UP)) {
            testObject.getPosition().y += 200 * Gdx.graphics.getDeltaTime();
        }
        if (IOManager.getInstance().getDynamicInput().isKeyPressed(Input.Keys.DOWN)) {
            testObject.getPosition().y -= 200 * Gdx.graphics.getDeltaTime();
        }

        // Test Mouse Input (Hardware Check)
        if (Gdx.input.justTouched()) {
            System.out.println("[MOUSE] Click detected at: " +
                    IOManager.getInstance().getDynamicInput().getMousePosition());
        }

        // --- RENDER PHASE (Testing OutputManager) ---

        // 1. Clear Screen & Prep Camera
        IOManager.getInstance().getOutputManager().beginFrame();

        // 2. Draw our test entity
        IOManager.getInstance().getOutputManager().drawEntity(testObject);

        // 3. Finalize
        IOManager.getInstance().getOutputManager().endFrame();
    }

    @Override
    public void resize(int width, int height) {
        // Test Resize Logic
        IOManager.getInstance().getOutputManager().resize(width, height);
    }

    @Override
    public void dispose() {
        // Cleanup
        testObject.dispose();
        IOManager.getInstance().dispose();
        System.out.println("GameMaster Disposed.");
    }
}