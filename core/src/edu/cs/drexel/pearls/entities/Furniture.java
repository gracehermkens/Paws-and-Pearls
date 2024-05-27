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
    private final Texture greenTableCloth;
    private final Texture greenTableClothBack;
    private final Texture orangeTableCloth;
    private final Texture orangeTableClothBack;
    private final Texture flowerVase1;
    private final Texture flowerVase2;
    private final boolean placeLC1;
    private final boolean placeLC2;
    private final boolean placeLeftDiningTable;
    private final boolean placeRC1;
    private final boolean placeRC2;
    private final boolean placeRightDiningTable;
    private final boolean placeGreenTableCloth;
    private final boolean placeGreenTableClothBack;
    private final boolean placeOrangeTableCloth;
    private final boolean placeOrangeTableClothBack;
    private final boolean placeFlowerVase1;
    private final boolean placeFlowerVase2;

    public Furniture(boolean placeLC1, boolean placeLC2, boolean placeLeftDiningTable,
                     boolean placeRC1, boolean placeRC2, boolean placeRightDiningTable,
                     boolean placeGreenTableCloth, boolean placeGreenTableClothBack,
                     boolean placeOrangeTableCloth, boolean placeOrangeTableClothBack,
                     boolean placeFlowerVase1, boolean placeFlowerVase2) {
        super();
        leftChair1 = new Texture("leftChair1.PNG");
        leftChair2 = new Texture("leftChair2.PNG");
        leftDiningTable = new Texture("leftDiningTable.PNG");

        rightChair1 = new Texture("rightChair1.PNG");
        rightChair2 = new Texture("rightChair2.PNG");
        rightDiningTable = new Texture("rightDiningTable.PNG");

        greenTableCloth = new Texture("greenTableCloth.PNG");
        greenTableClothBack = new Texture("greenTableClothBack.PNG");
        orangeTableCloth = new Texture("orangeTableCloth.PNG");
        orangeTableClothBack = new Texture("orangeTableClothBack.PNG");

        flowerVase1 = new Texture("flowerVase1.PNG");
        flowerVase2 = new Texture("flowerVase2.PNG");

        this.placeLC1 = placeLC1;
        this.placeLC2 = placeLC2;
        this.placeLeftDiningTable = placeLeftDiningTable;
        this.placeRC1 = placeRC1;
        this.placeRC2 = placeRC2;

        this.placeRightDiningTable = placeRightDiningTable;
        this.placeGreenTableCloth = placeGreenTableCloth;
        this.placeGreenTableClothBack = placeGreenTableClothBack;
        this.placeOrangeTableCloth = placeOrangeTableCloth;
        this.placeOrangeTableClothBack = placeOrangeTableClothBack;

        this.placeFlowerVase1 = placeFlowerVase1;
        this.placeFlowerVase2 = placeFlowerVase2;

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
        if (placeGreenTableClothBack) {
            batch.draw(greenTableClothBack, 17, -28);
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
        if (placeOrangeTableClothBack) {
            batch.draw(orangeTableClothBack, 53, 0);
        }
        if (placeRightDiningTable) {
            batch.draw(rightDiningTable, 53, 0);
        }
        if (placeGreenTableCloth) {
            batch.draw(greenTableCloth, 17, -28);
        }
        if (placeOrangeTableCloth) {
            batch.draw(orangeTableCloth, 53, 0);
        }
        if (placeFlowerVase1) {
            batch.draw(flowerVase1, 12, -28);
        }
        if (placeFlowerVase2) {
            batch.draw(flowerVase2, 58, 0);
        }
    }
}
