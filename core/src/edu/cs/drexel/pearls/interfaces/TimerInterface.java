package edu.cs.drexel.pearls.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

import java.lang.reflect.Array;

public class TimerInterface extends Actor {
    Texture timer;
    Texture digit_a;
    Texture digit_b;
    Texture digit_c;
    Texture digit_d;
    Texture menu;

    public boolean menuActive;
    public boolean timerActive = false;
    public int time = 1500;
    long ticker = 0;


    // written by vish
    public TimerInterface() {
        menuActive = false; // obviously
        timer = new Texture("baseClock.png");
        menu = new Texture("timerInterface.PNG");
        digit_a = new Texture("clock2.png");
        digit_b = new Texture("clock5.png");
        digit_c = new Texture("clock0.png");
        digit_d = new Texture("clock0.png");
        ticker = TimeUtils.nanoTime();
    }

    public void click() {
        if (!menuActive) {
            menuActive = true;
            Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
            bell.setVolume(0.5f);
            bell.play();
        }
    }
    public void close() {
        if (menuActive) {
            System.out.println("band");
            Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
            bell.setVolume(0.2f);
            bell.play();
            menuActive = false;
        }
    }
    public void reset() {
        if (menuActive) {
            System.out.println("reset");
            Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
            bell.setVolume(0.2f);
            bell.play();
            time = 1500;
        }
    }
    public void toggle() {
        if (menuActive) {
            Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
            bell.setVolume(0.2f);
            bell.play();
            timerActive = !timerActive;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.timer, 520, 320, 84, 70);
        float in_x = 534;
        float w = 10;
        batch.draw(this.digit_a, in_x + (w*0), 341, 20, 23.33f);
        batch.draw(this.digit_b, in_x  + (w*1), 341, 20, 23.33f);
        batch.draw(this.digit_c, in_x + 5 + (w*2), 341, 20, 23.33f);
        batch.draw(this.digit_d, in_x + 5 + (w*3), 341, 20, 23.33f);


        char[] k = (String.format("%02d", (time / 60)) + String.format("%02d", (time % 60))).toCharArray();
        digit_a = new Texture("clock"+k[0]+".png");
        digit_b = new Texture("clock"+k[1]+".png");
        digit_c = new Texture("clock"+k[2]+".png");
        digit_d = new Texture("clock"+k[3]+".png");
        if (TimeUtils.timeSinceNanos(ticker) > 1000000000) {
            if (timerActive) {time--;}
            if (time < 0) {time = 0;}
            ticker = TimeUtils.nanoTime();
        }

        if (menuActive) {
            batch.draw(this.menu, 0, 0, 800, 600);
        }
    }
}
