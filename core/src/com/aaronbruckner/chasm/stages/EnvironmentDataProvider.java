package com.aaronbruckner.chasm.stages;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * <p>
 * Interface defining data to be shared between components that represents an environment. This
 * provides a way to get any about what's currently loaded in the LibGdx stage.
 * </p>
 * Created by aaronbruckner on 12/16/2015.
 */
public interface EnvironmentDataProvider {
    /**
     * <p>
     * Gets all active enemies within the environment.
     * </p>
     * @return a Group of all current enemies alive within the environment.
     */
    public Group getEnemyGroup();
}
