package com.dungeoncrawler.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dungeoncrawler.control.Controller;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1280;
        config.height = 720;
        config.title = "The Restless Caverns - Der beste Dungeon Crawler ALLER ZEITEN";
        config.resizable = true;

        //config.addIcon("logo.png", Files.FileType.Internale);
        new LwjglApplication(new Controller(), config);
    }
}