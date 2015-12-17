package com.aaronbruckner.chasm.inputHandlers;

import com.badlogic.gdx.InputProcessor;

/**
 * <p>
 * Simple extension for an input handler expected to poll input every render cycle and/or respond to
 * input events. Any object implementing input polling should be invoked every render cycle to allow
 * it to respond to current input.
 * </p>
 *
 * <p>
 * This is a functionless class intended to be directly extended.
 * </p>
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public class InputPollingHandler implements InputProcessor{
    /**
     * Input polling function. Handler should poll {@link com.badlogic.gdx.Gdx#input} to do work based
     * on current input. {@link InputPollingHandler#pollInput()} should be called every render loop.
     */
    public void pollInput(){
    }

    //region InputProcessor Interface
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    //endregion InputProcessor Interface
}