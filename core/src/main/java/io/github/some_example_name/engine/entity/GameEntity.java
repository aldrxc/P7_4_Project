package io.github.some_example_name.engine.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class GameEntity {
    // Abstract methods allow the engine to draw ANY object (Player, Enemy, Wall)
    // without knowing exactly what it is.
    public abstract TextureRegion getTexture();

    public abstract Vector2 getPosition();

    public abstract float getWidth();

    public abstract float getHeight();
}