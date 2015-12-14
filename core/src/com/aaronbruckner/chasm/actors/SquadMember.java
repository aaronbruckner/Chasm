package com.aaronbruckner.chasm.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Represents an actor that's controlled by the player. The player can be controlling many
 * {@link SquadMember}'s at a time
 * Created by aaronbruckner on 12/13/2015.
 */
public class SquadMember extends Actor{
    private Texture texture;

    public SquadMember(){
        super();
        setBounds(1, 1, 50, 50);
        //TODO: Dispose Texture
        texture = new Texture(Gdx.files.internal("actors/squadMembers/squadMember.png"));
    }


    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, getX(), getY());
    }

}
