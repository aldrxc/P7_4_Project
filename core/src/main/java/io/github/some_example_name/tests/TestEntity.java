package io.github.some_example_name.tests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import io.github.some_example_name.engine.entity.GameEntity;

public class TestEntity extends GameEntity implements Disposable {
    private Texture texture;
    private TextureRegion region;
    private Vector2 position;

    public TestEntity(float x, float y) {
        position = new Vector2(x, y);

        // CHANGE 1: We create a 50x50 pixel image directly (instead of 1x1)
        // This ensures the red square is visible even if scaling fails.
        Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        texture = new Texture(pixmap);
        region = new TextureRegion(texture);
        pixmap.dispose();
    }

    @Override
    public TextureRegion getTexture() {
        return region;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    // CHANGE 2: Simplified width/height to just return the size of our texture
    @Override
    public float getWidth() {
        return 50;
    }

    @Override
    public float getHeight() {
        return 50;
    }

    @Override
    public void dispose() {
        if (texture != null)
            texture.dispose();
    }
}