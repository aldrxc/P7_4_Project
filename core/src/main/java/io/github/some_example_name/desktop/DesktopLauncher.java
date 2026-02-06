package io.github.some_example_name.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// IMPORT THE TESTER CLASS
import io.github.some_example_name.tests.EngineTester;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("Abstract Engine Test");
        config.setWindowedMode(800, 600);
        config.setForegroundFPS(60);

        // Run the tester
        new Lwjgl3Application(new EngineTester(), config);
    }
}