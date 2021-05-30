// This is the Desktop Launcher of the game and is also where the start screen is generated

package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygame.gdx.drop.Drop;





public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fruit Game";
	    config.width = 800;
	    config.height = 480;
		
		
		new LwjglApplication(new Drop(), config);
		
	}
}

