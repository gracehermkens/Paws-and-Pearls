package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Screen;
import edu.cs.drexel.pearls.BobaCafe;

public class BaseScreen implements Screen {
    BobaCafe game;

    public BaseScreen(BobaCafe game) {
        this.game = game;
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}
