package com.aaronbruckner.chasm.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by aaronbruckner on 12/13/2015.
 */
public class Map extends Actor {
    //TODO: dispose
    private Texture texture;

    public Map(){
        super();
        texture = new Texture("maps/shittyMap.png");
        setBounds(0, 0, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, getX(), getY());
    }
}
