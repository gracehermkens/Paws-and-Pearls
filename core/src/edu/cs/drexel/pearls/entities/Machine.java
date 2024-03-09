package edu.cs.drexel.pearls.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Machine extends Actor {

    private Texture texture;

    public Machine(float x, float y) {
        super();
        texture = (new Texture("teaMachine.png"));
        setBounds(x, y, 80, 80);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY(), getWidth(), getHeight());
    }
}
