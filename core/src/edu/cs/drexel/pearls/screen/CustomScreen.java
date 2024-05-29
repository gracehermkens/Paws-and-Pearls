package edu.cs.drexel.pearls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.cs.drexel.pearls.BobaCafe;
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
    // coordinates for skin color
    int startX = 5; // Starting X position
    int startY = 20; // Starting Y position
    int offsetX = 10; // Horizontal spacing between skin colors
    int offsetY = 5; // Vertical spacing between skin colors

    public CustomScreen(final BobaCafe game) {
        super(game);
        batch = new SpriteBatch();
        customScreen = new Texture("customScreen.png");
        skinColor1 = new Texture("skinColor1.png");
        skinColor2 = new Texture("skinColor2.png");
        skinColor3 = new Texture("skinColor3.png");
        skinColor4 = new Texture("skinColor4.png");
        skinColor5 = new Texture("skinColor5.png");
        skinColor6 = new Texture("skinColor6.png");
        baseFemale = new Texture("baseCharacter1.png");
        baseMale = new Texture("baseCharacter2.png");
        selectButton = new Texture("customSelect.png");

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1);
        batch.begin();
        batch.draw(customScreen, 0, 0);

        // draw female base
        batch.draw(baseFemale, 20, 70, 400, 400);

        // draw male base
        batch.draw(baseMale, 400, 70, 400, 400);

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
                    System.out.println("Clicked at: (" + x + ", " + y + ")");

                    // skin color 1
                    if (((x >= 340) && (x <= 385)) && ((y >= 380) && (y <= 430))) {
                        System.out.println("Skin Color 1 Selected");
                    }
                    // skin color 2
                    else if ((x >= 440 ) && (x <= 490) && ((y >= 380) && (y <= 430))) {
                        System.out.println("Skin Color 2 Selected");
                    }
                    // skin color 3
                    else if ((x >= 335) && (x <= 380) && ((y >= 265) && (y <= 310))) {
                        System.out.println("Skin Color 3 Selected");
                    }
                    // skin color 4
                    else if ((x >= 445) && (x <= 490) && ((y >= 260) && (y <= 305))) {
                        System.out.println("Skin Color 4 Selected");
                    }
                    // skin color 5
                    else if ((x >= 340) && (x <= 385) && ((y >= 150) && (y <= 195))) {
                        System.out.println("Skin Color 5 Selected");
                    }
                    // skin color 6
                    else if ((x >= 445) && (x <= 490) && ((y >= 150) && (y <= 200))) {
                        System.out.println("Skin Color 6 Selected");
                    }
                }
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


