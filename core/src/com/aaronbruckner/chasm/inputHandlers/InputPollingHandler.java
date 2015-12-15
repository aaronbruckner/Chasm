package com.aaronbruckner.chasm.inputHandlers;

/**
 * Simple interface for an input handler expected to poll input every render cycle. Any object
 * implementing this interface should be invoked every render to give it the change to respond
 * to current input.
 *
 * Created by aaronbruckner on 12/13/2015.
 */
public interface InputPollingHandler {
    /**
     * Input polling function. Handler should poll {@link com.badlogic.gdx.Gdx#input} to do work based
     * on current input. {@link InputPollingHandler#pollInput()} should be called every render loop.
     */
    public void pollInput();
}