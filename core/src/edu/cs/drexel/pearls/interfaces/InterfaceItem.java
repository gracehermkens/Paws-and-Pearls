package edu.cs.drexel.pearls.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

// helper class
public class InterfaceItem {
    public String name;
    public Texture texture;
    public float x;
    public float y;
    public float diffX;
    public float diffY;

    public Vector2 origin;
    public boolean selected = false;

    public InterfaceItem(String name, String id, Vector2 position) {
        this.name = name;
        this.texture = new Texture(id+".png");;
        this.x = position.x;
        this.y = position.y;
        this.origin = position;
    }

    public void select(float x, float y) {
        this.diffX = x - this.x;
        this.diffY = y - this.y;
        this.selected = true;
    }

    public void deselect() {
        this.selected = false;
    }

    public void dragIfSelected(float x, float y) {
        if (this.selected) {
            this.x = x - diffX;
            this.y = y - diffY;
        }
    }



}
