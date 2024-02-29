package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.cs.drexel.pearls.BobaCafe;
import edu.cs.drexel.pearls.entities.Counter;
import edu.cs.drexel.pearls.entities.NPC;
import edu.cs.drexel.pearls.entities.Player;

public class CafeScreen extends BaseScreen {
    SpriteBatch batch;
    Texture image;
    Stage stage;

    public CafeScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        stage = new Stage();

        image = new Texture("cafeBackground.PNG");
        Counter counter = new Counter();
        stage.addActor(counter);
        Gdx.input.setInputProcessor(stage);

        Player player = new Player(new Vector2(320, 380));
        stage.addActor(player);

        NPC npc = new NPC(new Vector2(740, 80));
        stage.addActor(npc);

        Vector2 counterPosition = new Vector2(counter.getX(), counter.getY());
        counterPosition.y += 100;
        npc.setPosition(counterPosition);

        // temporary until order interaction is implemented
        // press e for the npc to leave
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int key) {
                if (key == Input.Keys.E) {
                    Vector2 newPosition = new Vector2(750, 80);
                    npc.setPosition(newPosition);
                    return true;
                }
                return false;
            }
        });

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
