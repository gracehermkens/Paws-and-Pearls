package edu.cs.drexel.pearls.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NPC extends Actor {
    private Texture texture;
    private Vector2 position;
    private float speed = 60f;


    public NPC(Vector2 startPosition) {
        super();
        texture = (new Texture("catCustomer.PNG"));
        setPosition(startPosition.x, startPosition.y);
        position = new Vector2();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY());
    }

    public void act(float delta) {
        super.act(delta);

        float distance = position.dst(getX(), getY());

        if (distance > 1) {
            float step = speed * delta;
            setPosition(getX() + step * Math.signum(position.x - getX()),
                    getY() + step * Math.signum(position.y - getY()));
        } else {
            setPosition(position.x, position.y);
        }
    }

    public void setPosition(Vector2 targetPosition) {
        this.position.set(targetPosition);
        this.position.x += 220;
        this.position.y += 50;
    }

}
