package info.vivime.bobacafe.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import info.vivime.bobacafe.BobaCafe;

public class TestScreen extends BaseScreen {
    SpriteBatch batch;
    Texture image;

    public TestScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();

        image = new Texture("technical_difficulties.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
