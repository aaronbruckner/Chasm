package com.aaronbruckner.chasm.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by aaronbruckner on 12/16/2015.
 */
public class Enemy extends Actor {
    private Texture texture;
    public Enemy(){
        super();
        setBounds(1500, 900, 100, 100);
        //TODO: Dispose Texture
        texture = new Texture(Gdx.files.internal("actors/enemies/badGuy.png"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, getX(), getY());
    }
}
