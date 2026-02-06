package io.github.some_example_name.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// IMPORT YOUR ENGINE HUB
import io.github.some_example_name.engine.io.IOManager;

public class EngineTester extends ApplicationAdapter {

    private IOManager io;
    private TestEntity testObject;

    @Override
    public void create() {
        // 1. Initialize the Engine
        io = IOManager.getInstance();
        io.init();

        // 2. Create the Test Object
        testObject = new TestEntity(100, 100);

        System.out.println("Engine Online. Press ARROWS to move. Click for mouse coords.");
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        // --- TEST INPUT ---
        // We use our DynamicInput manager, not Gdx.input directly
        if (io.getDynamicInput().isKeyPressed(Input.Keys.LEFT)) {
            testObject.getPosition().x -= 200 * dt;
        }
        if (io.getDynamicInput().isKeyPressed(Input.Keys.RIGHT)) {
            testObject.getPosition().x += 200 * dt;
        }
        if (io.getDynamicInput().isKeyPressed(Input.Keys.UP)) {
            testObject.getPosition().y += 200 * dt;
        }
        if (io.getDynamicInput().isKeyPressed(Input.Keys.DOWN)) {
            testObject.getPosition().y -= 200 * dt;
        }

        // Test Mouse Input
        if (Gdx.input.justTouched()) {
            System.out.println("Click at: " + io.getDynamicInput().getMousePosition());
            // Test Audio (Only works if you actually have a file named 'beep.mp3' in
            // assets)
            // io.getAudio().playSound("beep.mp3");
        }

        // --- TEST OUTPUT ---
        io.getOutputManager().beginFrame();
        io.getOutputManager().drawEntity(testObject);
        io.getOutputManager().endFrame();
    }

    @Override
    public void resize(int width, int height) {
        io.getOutputManager().resize(width, height);
    }

    @Override
    public void dispose() {
        testObject.dispose();
        io.dispose();
    }
}