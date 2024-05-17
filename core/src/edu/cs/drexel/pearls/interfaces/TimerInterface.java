package edu.cs.drexel.pearls.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

public class TimerInterface extends Actor {
    private Texture button;

    public boolean menuActive;
    public int time = 1500;
    long ticker = 0;

    BitmapFont font;

    // written by vish
    public TimerInterface() {
        menuActive = false; // obviously
        button = new Texture("timerico.png");
        font = new BitmapFont();
        ticker = TimeUtils.nanoTime();
    }

    public void click() {
        time = 1500;
        Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
        bell.play();
        bell.setVolume(1.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.button, 5, 521.5f, 73.5f, 73.5f);
        font.draw(batch, String.format("%02d", (time/60))+":"+String.format("%02d", (time%60)), 740, 560);
        font.draw(batch, "Pomodoro Timer:", 670, 580);
        if (TimeUtils.timeSinceNanos(ticker) > 1000000000) {
            time--;
            if (time < 0) {time = 0;}
            ticker = TimeUtils.nanoTime();
        }
    }
}
