package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.cs.drexel.pearls.BobaCafe;
import edu.cs.drexel.pearls.entities.Counter;
import edu.cs.drexel.pearls.entities.Machine;
import edu.cs.drexel.pearls.entities.NPC;
import edu.cs.drexel.pearls.entities.Player;
import edu.cs.drexel.pearls.interfaces.InterfaceItem;
import edu.cs.drexel.pearls.interfaces.MachineInterface;

public class CafeScreen extends BaseScreen {
    SpriteBatch batch;
    Texture image;
    Stage stage;
    Player player;

    NPC npc;
    MachineInterface machineInterface;


    public CafeScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        stage = new Stage();

        image = new Texture("cafeBackground.PNG");

        Counter counter = new Counter();
        machineInterface = new MachineInterface();
        Machine machine = new Machine(355, 490);
        player = new Player(new Vector2(320, 380));
        npc = new NPC(new Vector2(740, 80));

        machine.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                machineInterface.setVisible(true);
                return true;
            }
        });


        // fill machine in (temporary debug code)


        machineInterface.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                machineInterface.handleTouchDown(x, y);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                // check if clicking something
                // move it! along (if click not in new box move it back)
                // otherwise (swap items)
                machineInterface.handleDrag(x, y);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                machineInterface.handleLift(x, y);
            }
        });


        Vector2 counterPosition = new Vector2(counter.getX(), counter.getY());
        counterPosition.y += 100;
        npc.setPosition(counterPosition);

        // Draw things
        stage.addActor(counter);
        stage.addActor(machine);
        stage.addActor(player);
        stage.addActor(npc);


        // Draw Interfaces Last :)
        stage.addActor(machineInterface);



        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.end();

        input();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void input() {
        float speed = 5f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(speed, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            machineInterface.setVisible(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            // temporary until order interaction is implemented
            // press e for the npc to leave
            Vector2 newPosition = new Vector2(750, 80);
            npc.setPosition(newPosition);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
    }

}
