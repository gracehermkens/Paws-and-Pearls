package edu.cs.drexel.pearls.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TimerInterface extends Actor {
    private Texture button;

    public boolean menuActive;

    // written by vish
    public TimerInterface() {
        menuActive = false; // obviously
        button = new Texture("timerico.png");
    }

    public void click() {
        System.out.println("debug func");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.button, 5, 521.5f, 73.5f, 73.5f);

    }
}
