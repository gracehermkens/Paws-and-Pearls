package edu.cs.drexel.pearls.interfaces;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Arrays;

public class MachineInterface extends Actor {
    private Texture texture;
    private Texture cupTexture;
    private Texture thaiTeaTexture, melonTeaTexture, taroTeaTexture;
    private Texture bobaTexture, mangoBobaTexture;
    private Texture strawTexture, lidTexture;

    private final Vector2 resetButtonPosition = new Vector2(500, 450);
    //exit button position written by savannah
    private final Vector2 exitButtonPosition = new Vector2(610, 450);
    private boolean showThaiTea = false;
    private boolean showMelonTea = false;
    private boolean showTaroTea = false;
    private boolean showBoba = false;
    private boolean showMangoBoba = false;


    public boolean showStraw = false;
    private boolean showLid = false;
    private boolean inputsLocked = false;
    private boolean hasTea = false;
    private boolean hasTopping = false;

    public InterfaceItem[] inputs;
    public static Vector2[] inputPositions = {
            new Vector2(370, 280), new Vector2(370, 200)
    };

    public static Vector2 cupPosition = new Vector2(260, 180);

    public InterfaceItem[] inventory;

    public static Vector2[] inventoryPositions = {
            new Vector2(195, 106), new Vector2(282, 106), new Vector2(370, 106), new Vector2(453, 106), new Vector2(540, 104)
    };

    // deprecated
    public InterfaceItem output;

    public MachineInterface() {
        super();
        setVisible(false);
        texture = (new Texture("machineInterfaceRedesign.png"));

        // cup layers
        cupTexture = (new Texture("machineCup.png"));
        strawTexture = (new Texture ("machineCupStraw.png"));
        lidTexture = (new Texture ("machineCupLid.png"));

        // tea layers
        thaiTeaTexture = (new Texture("ThaiTea.png"));
        melonTeaTexture = (new Texture("melonTea.png"));
        taroTeaTexture = (new Texture("taroTea.png"));

        // topping layers
        bobaTexture = (new Texture("boba.png"));
        mangoBobaTexture = (new Texture("mangoBoba.png"));
        setBounds(0, 0, 800, 600);

        this.inventory = new InterfaceItem[]{
                new InterfaceItem("Thai Tea", "thaiTeaBag", inventoryPositions[0]),
                new InterfaceItem("Taro Tea", "taroBag", inventoryPositions[1]),
                new InterfaceItem("Melon Tea", "melonBag", inventoryPositions[2]),
                new InterfaceItem("Boba", "bobaIngredient", inventoryPositions[3]),
                new InterfaceItem("Mango Popping Boba", "mangoIcon", inventoryPositions[4])
        };

        this.inputs = new InterfaceItem[]{
                null, null
        };
        this.output = null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, getX(), getY(), getWidth(), getHeight());
        if (cupTexture != null) {
            // image is scaled by 1/3 (og pixels are 210 by 254)
            batch.draw(this.cupTexture, cupPosition.x, cupPosition.y, 280, 339);

            // tea layers
            if (showThaiTea) {
                batch.draw(this.thaiTeaTexture, cupPosition.x, cupPosition.y, 280, 339);
            }
            if (showTaroTea) {
                batch.draw(this.taroTeaTexture, cupPosition.x, cupPosition.y, 280, 339);
            }
            if (showMelonTea) {
                batch.draw(this.melonTeaTexture, cupPosition.x, cupPosition.y, 280, 339);
            }

            // topping layers
            if (showBoba) {
                batch.draw(this.bobaTexture, cupPosition.x, cupPosition.y, 280, 339);
            }
            if (showMangoBoba) {
                batch.draw(this.mangoBobaTexture, cupPosition.x, cupPosition.y, 280, 339);
            }

            // for final drink
            if (showStraw){
                // straw has to be drawn first
                batch.draw(this.strawTexture, cupPosition.x, cupPosition.y, 280, 339);
            }
            if (showLid){
                batch.draw(this.lidTexture, cupPosition.x, cupPosition.y, 280, 339);
            }

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

    // written by vish : modified by brooke (added first 2 lines)
    public void handleTouchDown(float x, float y) {
        if (coordinatesInResetButton(x,y)) {
            resetInterface();
        }
        //if exit clicked set machine ui visibility to false written by savannah
            if (coordinatesInExitButton(x, y)){
                setVisible(false);
              }
                if (!inputsLocked) {
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

      }

    public void handleDrag(float x, float y) {
        // will only let u drag if input isn't locked
        if (!inputsLocked) {
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
    }


    public void handleLift(float x, float y) {
        int[] landed = listly(x, y);
        hasTea = false;
        hasTopping = false;
        // to check if input already has a tea bag and topping
        for (InterfaceItem input : inputs) {
            if (input != null) {
                if (input.name.equals("Thai Tea") || input.name.equals("Taro Tea") || input.name.equals("Melon Tea")) {
                    hasTea = true;
                } else if (input.name.equals("Boba") || input.name.equals("Mango Popping Boba")) {
                    hasTopping = true;
                }
            }
        }
        // will only let u lift if input isn't locked
        if (!inputsLocked) {
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
                                if (!hasTea && (inventory[i].name.equals("Thai Tea") || inventory[i].name.equals("Taro Tea") || inventory[i].name.equals("Melon Tea"))) {
                                    indigenous = inputs[landed[1]]; // store where we landed
                                    inputs[landed[1]] = inventory[i]; // set where we landed as original
                                    inventory[i] = indigenous; // set original as where we land
                                } else if (!hasTopping && (inventory[i].name.equals("Boba") || inventory[i].name.equals("Mango Popping Boba"))){
                                    indigenous = inputs[landed[1]]; // store where we landed
                                    inputs[landed[1]] = inventory[i]; // set where we landed as original
                                    inventory[i] = indigenous; // set original as where we land
                                }
                                break;
                            case 1: // inv
                                if (!hasTea) {
                                    indigenous = inventory[landed[1]];
                                    inventory[landed[1]] = inventory[i];
                                    inventory[i] = indigenous;
                                    break;
                                }
                        }
                    }
                }
            }
            updateTeaLayers();
            updateToppingLayers();
        }
    }

    // get pos in list of anywhere (if we're anywhere)
    // if not just drop
    // get pos in list of origin of selected item
    // swap the two

    //written by Grace
    private void updateTeaLayers() {
        showThaiTea = false;
        showTaroTea = false;
        showMelonTea = false;
        hasTea = false;
        for (InterfaceItem input : inputs) {
            if (input != null) {
                switch (input.name) {
                    case "Thai Tea":
                        showThaiTea = true;
                        hasTea = true;
                        break;
                    case "Taro Tea":
                        showTaroTea = true;
                        hasTea = true;
                        break;
                    case "Melon Tea":
                        showMelonTea = true;
                        hasTea = true;
                        break;
                }
            }
        }
    }

    // written by Grace
    private void updateToppingLayers() {
        showBoba = false;
        showMangoBoba = false;
        hasTopping = false;
        for (InterfaceItem input : inputs) {
            if (input != null) {
                switch (input.name) {
                    case "Boba":
                        showBoba = true;
                        hasTopping = true;
                        break;
                    case "Mango Popping Boba":
                        showMangoBoba = true;
                        hasTopping = true;
                        break;
                }

            }
        }

        // written by Grace
        // final drink
        if (inputs[0] != null && inputs[1] != null) {
            if (((inputs[0].name.equals("Thai Tea")) || (inputs[0].name.equals("Taro Tea")) || (inputs[0].name.equals("Melon Tea"))) && (inputs[1].name.equals("Boba") || (inputs[1].name.equals("Mango Popping Boba")))) {
                showLid = true;
                showStraw = true;
                inputsLocked = true;
            }
            else if (((inputs[1].name.equals("Thai Tea")) || (inputs[1].name.equals("Taro Tea")) || (inputs[1].name.equals("Melon Tea"))) && (inputs[0].name.equals("Boba") || (inputs[0].name.equals("Mango Popping Boba")))){
                showLid = true;
                showStraw = true;
                inputsLocked = true;
            }
        }
        else {
            showLid = false;
            showStraw = false;
            inputsLocked = false;
        }
    }

    public boolean coordinatesInVector(float x, float y, Vector2 vec) {
        float width = 64;
        float height = 64;
        return (x > vec.x) && (x < (vec.x + width)) && (y > vec.y) && (y < (vec.y + height));
    }


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

    // gets coordinates of reset "button" on interface picture
    // written by brooke
    private boolean coordinatesInResetButton(float x, float y) {
        float buttonWidth = 100;
        float buttonHeight = 50;
        return x >= resetButtonPosition.x && x <= resetButtonPosition.x + buttonWidth &&
                y >= resetButtonPosition.y && y <= resetButtonPosition.y + buttonHeight;
    }
    //create exit button written by savannah
    private boolean coordinatesInExitButton(float x, float y){
        float buttonWidth = 60;
        float buttonHeight = 50;
        return x >= exitButtonPosition.x && x <= exitButtonPosition.x + buttonWidth &&
                y >= exitButtonPosition.y && y <= exitButtonPosition.y + buttonHeight;

    }

    // resetting position of teas and toppings
    // written by brooke
    public void resetInterface() {
        showThaiTea = false;
        showMelonTea = false;
        showTaroTea = false;
        showBoba = false;
        showMangoBoba = false;
        showStraw = false;
        showLid = false;
        inputsLocked = false;
        hasTea = false;
        hasTopping = false;

        for (int i = 0; i < inputs.length; i++) {
            InterfaceItem inputItem = inputs[i];
            if (inputItem != null) {
                for (int j = 0; j < inventory.length; j++) {
                    if (inventory[j] == null) {
                        inventory[j] = inputItem;
                        inputs[i] = null;
                        break;
                    }
                }
            }
        }

        // clearing input slots
        Arrays.fill(inputs, null);
    }

}
