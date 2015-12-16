package com.aaronbruckner.chasm.stages;

import com.aaronbruckner.chasm.actors.Map;
import com.aaronbruckner.chasm.inputHandlers.MovableCameraHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.aaronbruckner.chasm.actors.SquadMember;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents one of the many game flows. This flow allows a player to move their {@link SquadMember}'s
 * through a level, engaging enemies and hostile environments while trying to find the exit.
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public class ClearLevelStage extends Stage {
    private MovableCameraHandler movableCameraHandler;

    public ClearLevelStage(){
        super();
        init();
    }

    public ClearLevelStage(Viewport viewport){
        super(viewport);
        init();
    }

    public ClearLevelStage(Viewport viewport, Batch batch){
        super(viewport, batch);
        init();
    }

    private void init(){
        addActor(new Map());
        addActor(new SquadMember());
        movableCameraHandler = new MovableCameraHandler(getViewport());

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(movableCameraHandler);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        movableCameraHandler.pollInput();
    }

    @Override
    public void draw(){
        super.draw();
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
