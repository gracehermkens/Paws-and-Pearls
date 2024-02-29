package edu.cs.drexel.pearls.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
public class Counter extends Actor{
    private Texture texture;

    public Counter() {
        super();
        texture = (new Texture("kitchenFurniture.PNG"));
        setPosition(0,0);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY());
    }

}
