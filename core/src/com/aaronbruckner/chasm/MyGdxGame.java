package com.aaronbruckner.chasm;

import com.aaronbruckner.chasm.stages.ClearLevelStage;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
	private ClearLevelStage clearLevelStage;

	@Override
	public void create () {
        clearLevelStage = new ClearLevelStage(new FitViewport(2000,2000));
	}

    public void resize(int width, int height){
        clearLevelStage.getViewport().update(width, height, true);
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        clearLevelStage.act();
        clearLevelStage.draw();
	}

    @Override
    public void dispose(){
        super.dispose();
        clearLevelStage.dispose();
    }
}
