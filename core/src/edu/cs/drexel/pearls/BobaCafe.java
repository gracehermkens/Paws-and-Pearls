package edu.cs.drexel.pearls;

import com.badlogic.gdx.Game;
import edu.cs.drexel.pearls.screen.TitleScreen;

public class BobaCafe extends Game {
	@Override
	public void create() {
		setScreen(new TitleScreen(this));
	}
}
