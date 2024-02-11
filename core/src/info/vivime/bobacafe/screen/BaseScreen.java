package info.vivime.bobacafe.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import info.vivime.bobacafe.BobaCafe;

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
