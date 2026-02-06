// package io.github.some_example_name;

// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.utils.ScreenUtils;

// /** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
// public class Main extends ApplicationAdapter {
//     private SpriteBatch batch;
//     private Texture image;

//     @Override
//     public void create() {
//         batch = new SpriteBatch();
//         image = new Texture("libgdx.png");
//     }

//     @Override
//     public void render() {
//         ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//         batch.begin();
//         batch.draw(image, 140, 210);
//         batch.end();
//     }

//     @Override
//     public void dispose() {
//         batch.dispose();
//         image.dispose();
//     }
// }

package io.github.some_example_name;

import com.badlogic.gdx.Game;

import io.github.some_example_name.engine.io.IOManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

// public class GameMaster extends Game {
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        IOManager.getInstance().init();
        System.out.println("GameMaster (Root) Initialized!");
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
        super.render();
    }

    @Override
    public void dispose() {
        IOManager.getInstance().dispose();
        batch.dispose();
        image.dispose();
    }
}