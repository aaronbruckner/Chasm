package com.aaronbruckner.chasm;

import com.aaronbruckner.chasm.stages.ClearLevelStage;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {
	private ClearLevelStage clearLevelStage;

	@Override
	public void create () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        clearLevelStage = new ClearLevelStage();
        Gdx.input.setInputProcessor(clearLevelStage);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        clearLevelStage.act(Gdx.graphics.getDeltaTime());
        clearLevelStage.draw();
	}

    @Override
    public void dispose(){
        super.dispose();
        clearLevelStage.dispose();
    }
}
