package com.aaronbruckner.chasm.inputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Input handler that makes a viewport movable with natural input used to scroll, pan, and zoom
 * a map.
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public class MovableCameraHandler implements InputPollingHandler {
    private Viewport viewport;

    /**
     * Base constructor. Takes a viewport to manage movement input for.
     * @param viewport The viewport to manage. Will translate the camera associated with this
     *                 viewport to provide map navigation.
     */
    public MovableCameraHandler(Viewport viewport){
        super();
        this.viewport = viewport;
    }

    /**
     * Pans the camera left, right, up, or down depending on keyboard input.
     */
    @Override
    public void pollInput(){
        float cameraPanDistance = Gdx.graphics.getDeltaTime() * 1000;
        Camera camera = viewport.getCamera();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.translate(cameraPanDistance, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.translate(-cameraPanDistance, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.translate(0, cameraPanDistance, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.translate(0, -cameraPanDistance, 0);
        }

    }
}
