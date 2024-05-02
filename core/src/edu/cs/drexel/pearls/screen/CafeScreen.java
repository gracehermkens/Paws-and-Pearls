package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import edu.cs.drexel.pearls.BobaCafe;
import edu.cs.drexel.pearls.entities.*;
import edu.cs.drexel.pearls.interfaces.InterfaceItem;
import edu.cs.drexel.pearls.interfaces.MachineInterface;

public class CafeScreen extends BaseScreen {
    SpriteBatch batch;
    Counter counter;
    Texture image;
    Stage stage;
    Player player;
    NPC npc;
    MachineInterface machineInterface;
    Furniture furniture;


    public CafeScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        stage = new Stage();

        image = new Texture("cafeBackground.PNG");


        counter = new Counter();
        machineInterface = new MachineInterface();
        Machine machine = new Machine(355, 490);
        player = new Player(new Vector2(320, 380));
        npc = new NPC(new Vector2(740, 80));
        furniture = new Furniture(true, true, true,
                true, true, true);



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
        npc.jingle();

        // Draw things
        stage.addActor(counter);
        stage.addActor(machine);
        stage.addActor(player);
        stage.addActor(npc);
        stage.addActor(furniture);


        // Draw Interfaces Last :)
        stage.addActor(machineInterface);

        Gdx.input.setInputProcessor(stage);

        // Play music when the game loads
        playMusic();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        batch.begin();
        batch.draw(image, 0, 0);

        batch.end();

        input();

        counter.setBobaStatus(machineInterface.showStraw);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void input() {
        float speed = 5f;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX();
            int y = 600 - Gdx.input.getY();

            // clicking npc
            // code written by vish
            if (x > npc.getX() && npc.getX() + 160  > x) {
                if (y > npc.getY() && npc.getY() + 160 > y) {
                    if (!machineInterface.isVisible()) {
                        if (npc.atCounter) {
                            npc.heart = true;
                        }

                        // by vish
                        // reset machine
                        machineInterface.resetInterface();

                        Vector2 newPosition = new Vector2(750, 80);
                        npc.setPosition(newPosition);
                        Timer.schedule(new Timer.Task(){
                            @Override
                            public void run() {
                                npc.setPosition(new Vector2(0,  100));
                                npc.heart = false;
                                npc.jingle();
                            }
                        },
                                (int) Math.floor(
                                        Math.random() * ((
                                            (12 - 8) - (npc.getX()/100)) + 1)
                                        + 8));
                    }
                }
            }

        }
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

    }

    // written by Brooke
    public void playMusic() {
        Music cafeMusic = Gdx.audio.newMusic(Gdx.files.local("thejazzpiano.ogg"));
        cafeMusic.play();
        cafeMusic.setVolume(0.5f);
        cafeMusic.setLooping(true);
    }


    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
    }

}
