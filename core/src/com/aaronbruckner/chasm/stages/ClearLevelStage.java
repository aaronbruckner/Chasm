package com.aaronbruckner.chasm.stages;

import com.aaronbruckner.chasm.actors.Enemy;
import com.aaronbruckner.chasm.actors.Map;
import com.aaronbruckner.chasm.inputHandlers.MovableCameraHandler;
import com.aaronbruckner.chasm.inputHandlers.SquadActionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.aaronbruckner.chasm.actors.SquadMember;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents one of the many game flows. This flow allows a player to move their {@link SquadMember}'s
 * through a level, engaging enemies and hostile environments while trying to find the exit.
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public class ClearLevelStage extends Stage implements EnvironmentDataProvider, SquadActionHandler.Listener {
    private Group squadGroup;
    private Group enemyGroup;
    private SquadMember selectedSquadMember;

    //region Input Handlers
    private MovableCameraHandler movableCameraHandler;
    private SquadActionHandler squadActionHandler;
    //endregion Input Handlers

    //region  Temporary variables for reuse to avoid garbage collection
    private Vector2 tempVector;
    //endregion

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
        tempVector = new Vector2();
        squadGroup = new Group();
        squadGroup.addActor(new SquadMember());
        enemyGroup = new Group();
        enemyGroup.addActor(new Enemy());
        addActor(new Map());
        addActor(squadGroup);
        addActor(enemyGroup);

        movableCameraHandler = new MovableCameraHandler(getViewport());
        squadActionHandler = new SquadActionHandler(squadGroup, this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(movableCameraHandler);
        inputMultiplexer.addProcessor(squadActionHandler);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        return false;
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

    @Override
    public void onSquadMemberSelected(SquadMember selectedSquadMember) {
        this.selectedSquadMember = selectedSquadMember;
    }

    //region EnvironmentDataProvider Interface
    @Override
    public Group getEnemyGroup() {
        return enemyGroup;
    }
    //endregion EnvironmentDataProvider Interface
}
