package com.aaronbruckner.chasm.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.aaronbruckner.chasm.actors.SquadMember;

/**
 * Represents one of the many game flows. This flow allows a player to move their {@link SquadMember}'s
 * through a level, engaging enemies and hostile environments while trying to find the exit.
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public class ClearLevelStage extends Stage {

    public ClearLevelStage(){
        super();
        addActor(new SquadMember());
    }

    @Override
    public void act(float delta){
        super.act(delta);

    }
}
