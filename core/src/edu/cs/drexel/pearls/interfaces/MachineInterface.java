package edu.cs.drexel.pearls.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MachineInterface extends Actor {
    private Texture texture;
    private Texture cupTexture;

    public InterfaceItem[] inputs;
    public static Vector2[] inputPositions = {
            new Vector2(204, 360), new Vector2(204, 278)
    };

    public static Vector2 cupPosition = new Vector2(260, 180);

    public InterfaceItem[] inventory;
    public static Vector2[] inventoryPositions = {
            new Vector2(195, 106), new Vector2(282, 106), new Vector2(370, 106), new Vector2(453, 106), new Vector2(540, 104)
    };

    public InterfaceItem output;
    public static Vector2 outputPosition = new Vector2(498, 320);




    public MachineInterface() {
        super();
        setVisible(false);
        texture = (new Texture("machineInterfaceRedesign.png"));
        cupTexture = (new Texture("machineCup.png"));
        setBounds(0, 0, 800, 600);

        this.inventory = new InterfaceItem[] {
                new InterfaceItem("Thai Tea", "thaiTeaIngredient", inventoryPositions[0]),
                new InterfaceItem("Taro Tea", "taroBag", inventoryPositions[1]),
                new InterfaceItem("Melon Tea", "melonBag", inventoryPositions[2]),
                new InterfaceItem("Boba", "bobaIngredient", inventoryPositions[3]),
                new InterfaceItem("Mango Popping Boba", "mangoIcon", inventoryPositions[4])
        };

        this.inputs = new InterfaceItem[] {
                null, null
        };
        this.output = null;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY(), getWidth(), getHeight());
        if (cupTexture != null) {
            // image is scaled by 1/3 (og pixels are 210 by 254)
            batch.draw(this.cupTexture, cupPosition.x, cupPosition.y, 280, 339 );
        }


        // Force origin & Draw
        for (int i = 0; i < inputPositions.length; i++) {
            if (inputs[i] != null) {
                inputs[i].origin = inputPositions[i];
                drawWithLogic(batch, inputs[i]);
            }
        }
        for (int i = 0; i < inventoryPositions.length; i++) {
            if (inventory[i] != null) {
                inventory[i].origin = inventoryPositions[i];
                drawWithLogic(batch, inventory[i]);
            }
        }

        // draw interface items
        if (output != null) {
            drawWithLogic(batch, output);
        }
    }

    public void drawWithLogic(Batch batch, InterfaceItem item) {
        if (!item.selected) {
            item.diffX = 0;
            item.diffY = 0;
            if (item.x != item.origin.x) item.x = item.origin.x;
            if (item.y != item.origin.y) item.y = item.origin.y;
        }
        batch.draw(item.texture, item.x, item.y, 64, 64);
    }

    public void handleTouchDown(float x, float y) {
        for (int i = 0; i < inputPositions.length; i++) {
            if (coordinatesInVector(x, y, inputPositions[i])) {
                if (inputs[i] != null) {
                    inputs[i].select(x, y);
                }
            }
        }
        for (int i = 0; i < inventoryPositions.length; i++) {
            if (coordinatesInVector(x, y, inventoryPositions[i])) {
                if (inventory[i] != null) {
                    inventory[i].select(x, y);
                }
            }
        }
    }

    public void handleDrag(float x, float y) {
        for (int i = 0; i < inputPositions.length; i++) {
            if (inputs[i] != null) {
                inputs[i].dragIfSelected(x, y);
            }
        }
        for (int i = 0; i < inventoryPositions.length; i++) {
            if (inventory[i] != null) {
                inventory[i].dragIfSelected(x, y);
            }
        }
    }

    public void handleLift(float x, float y) {
        int[] landed = listly(x, y);
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != null) {
                if (inputs[i].selected) {
                    // inputs, i @
                    InterfaceItem indigenous;
                    inputs[i].deselect();
                    switch (landed[0]) {
                        case 0: // inputs
                            indigenous = inputs[landed[1]]; // store where we landed
                            inputs[landed[1]] = inputs[i]; // set where we landed as original
                            inputs[i] = indigenous; // set original as where we landed
                            break;
                        case 1: // inv
                            indigenous = inventory[landed[1]]; // store where we landed
                            inventory[landed[1]] = inputs[i]; //
                            inputs[i] = indigenous;
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                if (inventory[i].selected) {
                    // inventory, i @
                    InterfaceItem indigenous;
                    inventory[i].deselect();
                    switch (landed[0]) {
                        case 0: // inputs
                            indigenous = inputs[landed[1]]; // store where we landed
                            inputs[landed[1]] = inventory[i]; // set where we landed as original
                            inventory[i] = indigenous; // set original as where we land
                            break;
                        case 1: // inv
                            indigenous = inventory[landed[1]];
                            inventory[landed[1]] = inventory[i];
                            inventory[i] = indigenous;
                            break;
                    }
                }
            }
        }

        // get pos in list of anywhere (if we're anywhere)
        // if not just drop
        // get pos in list of origin of selected item
        // swap the two

        // boba logic
        if (inputs[0] != null && inputs[1] != null) {
            output = new InterfaceItem("Boba", "placeholder_c", outputPosition);
        } else {
            output = null;
        }
    }


    public boolean coordinatesInVector(float x, float y, Vector2 vec) {
        float width = 64;
        float height = 64;
        return (x > vec.x) && (x < (vec.x + width)) && (y > vec.y) && (y < (vec.y + height));
    }

    // im so tired dude
    public int[] listly(float x, float y) {
        for (int i = 0; i < inputPositions.length; i++) {
            if (coordinatesInVector(x, y, inputPositions[i])) {
                return new int[]{0, i};
            }
        }

        for (int i = 0; i < inventoryPositions.length; i++) {
            if (coordinatesInVector(x, y, inventoryPositions[i])) {
                return new int[]{1, i};
            }
        }
        return new int[]{-1, -1};
    }
}
