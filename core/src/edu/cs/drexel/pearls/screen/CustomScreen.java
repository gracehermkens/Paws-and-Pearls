package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.cs.drexel.pearls.BobaCafe;
import edu.cs.drexel.pearls.Global;

public class CustomScreen extends BaseScreen {
    SpriteBatch batch;
    Texture customScreen;
    float time = 0;
    Texture skinColor1;
    Texture skinColor2;
    Texture skinColor3;
    Texture skinColor4;
    Texture skinColor5;
    Texture skinColor6;
    Texture baseFemale;
    Texture baseMale;
    Texture selectButton;
    Texture femaleOutline, maleOutline;
    boolean showFemaleOutline = false;
    boolean showMaleOutline = false;


    // coordinates for skin color
    int startX = 5; // Starting X position
    int startY = 20; // Starting Y position
    int offsetX = 10; // Horizontal spacing between skin colors
    int offsetY = 5; // Vertical spacing between skin colors

    public CustomScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        customScreen = new Texture("customScreen.png");

        // skin color option textures
        skinColor1 = new Texture("skinColor1.png");
        skinColor2 = new Texture("skinColor2.png");
        skinColor3 = new Texture("skinColor3.png");
        skinColor4 = new Texture("skinColor4.png");
        skinColor5 = new Texture("skinColor5.png");
        skinColor6 = new Texture("skinColor6.png");

        // female textures
        baseFemale = new Texture("player/female/skin0/Front1.png");

        // male textures
        baseMale = new Texture("player/male/skin0/Front1.png");

        // select button texture
        selectButton = new Texture("customSelect.png");
        updateTextures();
    }
    private void updateTextures() {
        baseFemale = new Texture("player/female/skin"+Global.color+"/Front1.png");
        baseMale = new Texture("player/male/skin"+Global.color+"/Front1.png");
        femaleOutline = new Texture("customOutline"+Global.gender+".png");
        maleOutline = new Texture("customOutline"+Global.gender+".png");
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1);
        batch.begin();
        batch.draw(customScreen, 0, 0);

        // draw female base
        batch.draw(baseFemale, 20, 70, 400, 400);
        if (showFemaleOutline) {
            batch.draw(femaleOutline, -520, -45, 1000, 700);
        }
        // draw male base
        batch.draw(baseMale, 400, 70, 400, 400);
        if (showMaleOutline) {
            batch.draw(maleOutline, 320, -45,1000 , 700);
        }
        // draw skin colors
        batch.draw(skinColor1, startX, startY);
        batch.draw(skinColor2, startX + offsetX, startY);
        batch.draw(skinColor3, startX, startY - offsetY);
        batch.draw(skinColor4, startX + offsetX, startY - offsetY);
        batch.draw(skinColor5, startX, startY - 2 * offsetY);
        batch.draw(skinColor6, startX + offsetX, startY - 2 * offsetY);

        // draw selection button
        batch.draw(selectButton, 30, -10, 750, 700);

        batch.end();
        handleinput();
        updateSkinColor();
        updateGender();
    }
        private void handleinput() {
            if (Gdx.input.justTouched()) {
                int x = Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();

                if (((x >= 365) && (x <= 455)) && ((y >= 80) && (y <= 125))) {
                    System.out.println("x: " + x + " y: " + y);
                    game.setScreen(new CafeScreen(game));
                }
            }
        }
            private void updateSkinColor() {
                if (Gdx.input.justTouched()) {
                    int x = Gdx.input.getX();
                    int y = Gdx.graphics.getHeight() - Gdx.input.getY();

                    // print coordinates for debugging
                    // System.out.println("Clicked at: (" + x + ", " + y + ")");

                    // skin color 1
                    if (((x >= 340) && (x <= 385)) && ((y >= 380) && (y <= 430))) {
                        System.out.println("Skin Color 1 Selected");
                        Global.color = "1";
                    }
                    // skin color 2
                    else if ((x >= 440 ) && (x <= 490) && ((y >= 380) && (y <= 430))) {
                        System.out.println("Skin Color 2 Selected");
                        Global.color = "2";
                    }
                    // skin color 3
                    else if ((x >= 335) && (x <= 380) && ((y >= 265) && (y <= 310))) {
                        System.out.println("Skin Color 3 Selected");
                        Global.color = "3";
                    }
                    // skin color 4
                    else if ((x >= 445) && (x <= 490) && ((y >= 260) && (y <= 305))) {
                        System.out.println("Skin Color 4 Selected");
                        Global.color = "4";
                    }
                    // skin color 5
                    else if ((x >= 340) && (x <= 385) && ((y >= 150) && (y <= 195))) {
                        System.out.println("Skin Color 5 Selected");
                        Global.color = "5";
                    }
                    // skin color 6
                    else if ((x >= 445) && (x <= 490) && ((y >= 150) && (y <= 200))) {
                        System.out.println("Skin Color 6 Selected");
                        Global.color = "6";
                    }
                }
                updateTextures();
            }
        private void updateGender() {
            if (Gdx.input.justTouched()) {
                int x = Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();

                System.out.println("Clicked at: (" + x + ", " + y + ")");
                if (((x >= 130) && (x <= 300)) && ((y >= 100) && (y <= 465))) {
                    Global.gender = "female";
                    showFemaleOutline = true;
                    showMaleOutline = false;
                }
                if (((x >= 525 && (x <= 680)) && ((y >= 110) && (y <= 460)))) {
                    Global.gender = "male";
                    showFemaleOutline = false;
                    showMaleOutline = true;
                }
            }
            updateTextures();
        }

    @Override
    public void dispose() {
        batch.dispose();
        customScreen.dispose();
        skinColor1.dispose();
        skinColor2.dispose();
        skinColor3.dispose();
        skinColor4.dispose();
        skinColor5.dispose();
        skinColor6.dispose();
        baseFemale.dispose();
        selectButton.dispose();
    }
}


