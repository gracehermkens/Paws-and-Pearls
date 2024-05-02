package edu.cs.drexel.pearls.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Furniture extends Actor {

    private final Texture leftChair1;
    private final Texture leftChair2;
    private final Texture leftDiningTable;
    private final Texture rightChair1;
    private final Texture rightChair2;
    private final Texture rightDiningTable;
    private final boolean placeLC1;
    private final boolean placeLC2;
    private final boolean placeLeftDiningTable;
    private final boolean placeRC1;
    private final boolean placeRC2;
    private final boolean placeRightDiningTable;
    public Furniture(boolean placeLC1, boolean placeLC2, boolean placeLeftDiningTable,
                     boolean placeRC1, boolean placeRC2, boolean placeRightDiningTable) {
        super();
        leftChair1 = new Texture("leftChair1.PNG");
        leftChair2 = new Texture("leftChair2.PNG");
        leftDiningTable = new Texture("leftDiningTable.PNG");

        rightChair1 = new Texture("rightChair1.PNG");
        rightChair2 = new Texture("rightChair2.PNG");
        rightDiningTable = new Texture("rightDiningTable.PNG");

        this.placeLC1 = placeLC1;
        this.placeLC2 = placeLC2;
        this.placeLeftDiningTable = placeLeftDiningTable;
        this.placeRC1 = placeRC1;
        this.placeRC2 = placeRC2;
        this.placeRightDiningTable = placeRightDiningTable;

        setPosition(0,-38);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(leftChair1, getX(), getY());
        if (placeLC1) {
            batch.draw(leftChair1, 0, -38);
        }
        if (placeLC2) {
            batch.draw(leftChair2, 2, 4);
        }
        if (placeLeftDiningTable) {
            batch.draw(leftDiningTable, 17, -28);
        }
        if (placeRC1) {
            batch.draw(rightChair1, 83, 45);
        }
        if (placeRC2) {
            batch.draw(rightChair2, 65, 3);
        }
        if (placeRightDiningTable) {
            batch.draw(rightDiningTable, 53, 0);
        }
    }
}
