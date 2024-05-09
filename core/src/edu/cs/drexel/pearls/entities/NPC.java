package edu.cs.drexel.pearls.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import java.util.Random;

public class NPC extends Actor {
    private Texture texture;
    private Texture speechBubble;
    private Texture currentOrder;
    public Texture dotDotDot;
    public Texture spHeart;
    public Texture melonBOrder;
    public Texture thaiBOrder;
    public Texture taroBOrder;
    public Texture melonMOrder;
    public Texture thaiMOrder;
    public Texture taroMOrder;
    private Vector2 position;
    private float speed = 60f;
    public boolean atCounter;
    public boolean heart;
    private int tick = 0;
    private int direction = 2; // front, right, back, left;

    public NPC(Vector2 startPosition) {
        super();
        texture = (new Texture("catCustomer.PNG"));
        speechBubble = (new Texture("speechBubble.PNG"));
        dotDotDot = (new Texture("speechBubbleDotDotDot.PNG"));
        spHeart = (new Texture("speechBubbleHeart.PNG"));

        melonBOrder = (new Texture("melonBOrder.PNG"));
        thaiBOrder = (new Texture("thaiBOrder.PNG"));
        taroBOrder = (new Texture("taroBOrder.PNG"));
        melonMOrder = (new Texture("melonMOrder.PNG"));
        thaiMOrder = (new Texture("thaiMOrder.PNG"));
        taroMOrder = (new Texture("taroMOrder.PNG"));

        setPosition(startPosition.x, startPosition.y);
        position = new Vector2();
    }

    // by vish
    private String getDirectionID() {
        float distance = position.dst(getX(), getY());
        if (distance > 0.3f) {
            float dy = (position.y - getY());
            float dx = position.x - getX();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx < 0) direction = 3;
                else direction = 1;
            } else {
                if (dy < 0) direction = 2;
                else direction = 0;
            }
        } else {
            tick = 0;
        }
        switch (direction) {
            case 1:
                return "Right";
            case 2:
                return "Back";
            case 3:
                return "Left";
            default:
                return "Front";
        }
    }

    // modified by brooke
    // modified by vish
    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.texture = new Texture("bunny"+getDirectionID()+((tick/10)+1)+".PNG");
        batch.draw(this.texture, getX(), getY());
        float xOffset = 15;
        float yOffset = 120;
        if (atCounter) {
            if (currentOrder != null) {
                batch.draw(speechBubble, getX() + xOffset, getY() + yOffset);
                batch.draw(currentOrder, getX() + xOffset, getY() + yOffset);
            }
        } else if (heart) {
            batch.draw(speechBubble, getX() + xOffset, getY() + yOffset);
            batch.draw(spHeart, getX() + xOffset, getY() + yOffset);
            }
        else {
            batch.draw(speechBubble, getX() + xOffset, getY() + yOffset);
            batch.draw(dotDotDot, getX() + xOffset, getY() + yOffset);
        }
        tick = (tick + 1) % 40;
    }



    public void jingle() {
        // written by vish
        Music bell = Gdx.audio.newMusic(Gdx.files.local("bell.ogg"));
        bell.play();
        bell.setVolume(1.0f);
    }

    // written by brooke
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
            currentOrder = getRandomOrderImage();
        }
        else if (distance > 1 && atCounter) {
            atCounter = false;
        }
    }

    // written by brooke
    public void setPosition(Vector2 targetPosition) {
        this.position.set(targetPosition);
        this.position.x += 220;
        this.position.y += 50;
        if (atCounter) {
            currentOrder = getRandomOrderImage();
        }
    }

    // written by brooke
    public Texture getRandomOrderImage() {
        Texture[] orderChoice = {melonBOrder, thaiBOrder, taroBOrder, melonMOrder, thaiMOrder, taroMOrder};
        Random random = new Random();
        int randomIndex = random.nextInt(orderChoice.length);
        return orderChoice[randomIndex];
    }


}
