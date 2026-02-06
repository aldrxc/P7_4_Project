package io.github.some_example_name.engine.collision;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    Rectangle getBounds();
    void onCollision(Collidable other);
}
