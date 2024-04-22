package edu.cs.drexel.pearls.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

public class NPC extends Actor {
    private Texture texture;
    private Texture speechBubble;
    public Texture dotDotDot;
    public Texture spHeart;
    private Vector2 position;
    private float speed = 60f;
    public boolean atCounter;
    public boolean heart;


    public NPC(Vector2 startPosition) {
        super();
        texture = (new Texture("catCustomer.PNG"));
        speechBubble = (new Texture("speechBubble.PNG"));
        dotDotDot = (new Texture("speechBubbleDotDotDot.PNG"));
        spHeart = (new Texture("speechBubbleHeart.PNG"));
        setPosition(startPosition.x, startPosition.y);
        position = new Vector2();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY());
        float xOffset = 15;
        float yOffset = 120;
        if (atCounter) {
            batch.draw(speechBubble, getX() + xOffset, getY() + yOffset);
            if (dotDotDot != null) {
                batch.draw(dotDotDot, getX() + xOffset, getY() + yOffset);
            }
        } if (heart) {
            batch.draw(speechBubble, getX() + xOffset, getY() + yOffset);
            if (spHeart != null) {

                batch.draw(spHeart , getX() + xOffset, getY() + yOffset);
            }
        }
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
        if (distance <=1 && !atCounter) {
            atCounter = true;
        }
        else if (distance >1 && atCounter) {
            atCounter = false;
        }
    }

    public void setPosition(Vector2 targetPosition) {
        this.position.set(targetPosition);
        this.position.x += 220;
        this.position.y += 50;
    }

}
