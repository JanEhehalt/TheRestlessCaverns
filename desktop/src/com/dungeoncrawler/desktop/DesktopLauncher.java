package com.dungeoncrawler.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dungeoncrawler.control.Controller;

public class DesktopLauncher {
        public static void main (String[] arg) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.width = 1600;
                config.height = 900;
                config.title = "The Restless Caverns";
                //weconfig.addIcon("logo.png", Files.FileType.Internale);
                
                new LwjglApplication(new Controller(), config);
        }
}
