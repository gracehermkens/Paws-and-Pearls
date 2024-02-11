package info.vivime.bobacafe.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import info.vivime.bobacafe.BobaCafe;

public class TitleScreen extends BaseScreen {

    SpriteBatch batch;
    Texture titleImage;
    float time = 0;

    public TitleScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();

        titleImage = new Texture("titlescreen2.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1);
        batch.begin();
        batch.draw(titleImage, 0, 0);
        batch.end();

        time += delta;
        if (time > 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
                game.setScreen(new TestScreen(game));
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        titleImage.dispose();
    }
}
