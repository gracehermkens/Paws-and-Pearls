package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.cs.drexel.pearls.BobaCafe;
import edu.cs.drexel.pearls.entities.Counter;
import edu.cs.drexel.pearls.entities.NPC;

public class CafeScreen extends BaseScreen {
    SpriteBatch batch;
    Texture image;
    Stage stage;

    public CafeScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        stage = new Stage();

        image = new Texture("cafeBackground.PNG");
        NPC npc = new NPC(new Vector2(740, 80));
        stage.addActor(npc);
        Counter counter = new Counter();
        stage.addActor(counter);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
    }

}
