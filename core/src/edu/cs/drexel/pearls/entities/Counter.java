package edu.cs.drexel.pearls.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
public class Counter extends Actor{
    private Texture texture;
    private Texture finalDrinkTexture;
    private boolean showFinalDrink;

    public Counter() {
        super();
        texture = (new Texture("kitchenFurniture.PNG"));
        showFinalDrink = false;
        setPosition(0,0);
    }

    public void setFinalDrinkTexture(Texture texture){
        this.finalDrinkTexture = texture;
    }
    public void setBobaStatus(boolean b) {
        this.showFinalDrink = b;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY());
        if (showFinalDrink && finalDrinkTexture != null) {
            batch.draw(this.finalDrinkTexture, 340, 330,70 ,65 );
        }
    }

}
