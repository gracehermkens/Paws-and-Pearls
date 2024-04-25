package edu.cs.drexel.pearls.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    private Texture texture;


    public Player(Vector2 startPosition) {
        super();
        texture = (new Texture("baseCharacter1.PNG"));
        setPosition(startPosition.x, startPosition.y);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY());
    }

    public void move(float x, float y) {
        float newX = getX() + x;
        float newY = getY() + y;
        // collision code by vish!

        if (!( // bound to table
                newX > 70 && newX < 565 &&
                newY > 235 && newY < 400 &&
                !(
                    newX > 205 && newX < 430 &&
                    newY > 365 && newY < 400
                )
        ) && ( // bound to room
            newX >= -35 && newX <= 675 &&
            newY >= 0 && newY <= 405
        )) {
            // good to go
            setPosition(newX, newY);
        }
    }
}


