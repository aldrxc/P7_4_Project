package io.github.some_example_name.tests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

// IMPORT YOUR ABSTRACT BASE CLASS
import io.github.some_example_name.engine.entity.GameEntity;

public class TestEntity extends GameEntity implements Disposable {
    private Texture texture;
    private TextureRegion region;
    private Vector2 position;
    private float width = 50;
    private float height = 50;

    public TestEntity(float x, float y) {
        position = new Vector2(x, y);

        // Create a 1x1 Red Pixel programmatically (so no assets needed)
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        texture = new Texture(pixmap);
        region = new TextureRegion(texture);

        pixmap.dispose();
    }

    // --- Implementing the Abstract Methods from GameEntity ---

    @Override
    public TextureRegion getTexture() {
        return region;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void dispose() {
        if (texture != null)
            texture.dispose();
    }
}