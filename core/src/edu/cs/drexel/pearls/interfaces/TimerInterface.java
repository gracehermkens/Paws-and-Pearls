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
    Texture digit_w;
    Texture digit_x;
    Texture digit_y;
    Texture digit_z;
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
        digit_w = new Texture("timer2.png");
        digit_x = new Texture("timer5.png");
        digit_y = new Texture("timer0.png");
        digit_z = new Texture("timer0.png");
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
        digit_w = new Texture("timer"+k[0]+".png");
        digit_x = new Texture("timer"+k[1]+".png");
        digit_y = new Texture("timer"+k[2]+".png");
        digit_z = new Texture("timer"+k[3]+".png");
        if (TimeUtils.timeSinceNanos(ticker) > 1000000000) {
            if (timerActive) {time--;}
            if (time < 0) {time = 0;}
            ticker = TimeUtils.nanoTime();
        }

        if (menuActive) {
            batch.draw(this.menu, 0, 0, 800, 600);
            float z_in_x_left = 170;
            float z_in_x_right = 220;
            float z_w = 100;
            float z_y = 250;
            batch.draw(this.digit_w, z_in_x_left + (z_w*0), z_y, 100, 160);
            batch.draw(this.digit_x, z_in_x_left  + (z_w*1), z_y, 100, 160);
            batch.draw(this.digit_y, z_in_x_right + 5 + (z_w*2), z_y, 100, 160);
            batch.draw(this.digit_z, z_in_x_right + 5 + (z_w*3), z_y, 100, 160);
        }
    }
}
