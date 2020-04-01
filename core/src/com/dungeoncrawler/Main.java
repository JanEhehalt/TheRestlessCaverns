package com.dungeoncrawler;

import com.badlogic.gdx.ApplicationAdapter;
import com.dungeoncrawler.view.View;
import com.dungeoncrawler.control.Controller;

public class Main extends ApplicationAdapter {
    
    View v;
    Controller c;
    
    public Main(){
        v = new View();
        c = new Controller();
    }
    
}
