package info.vivime.bobacafe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import info.vivime.bobacafe.screen.TitleScreen;

public class BobaCafe extends Game {
	@Override
	public void create() {
		setScreen(new TitleScreen(this));
	}
}
