package com.aaronbruckner.chasm.actors;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by aaronbruckner on 12/13/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Map.class, Texture.class})
public class MapTest {
    private Map map;

    @Mock
    private Batch mockBatch;
    @Mock
    private Texture mockTexture;
    @Mock
    private FileHandle mockFile;

    @Before
    public void beforeEach() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mockTexture.getWidth()).thenReturn(3000);
        when(mockTexture.getHeight()).thenReturn(2000);
        PowerMockito.whenNew(Texture.class).withAnyArguments().thenReturn(mockTexture);

        map = PowerMockito.spy(new Map());
    }

    @After
    public void afterEach(){
    }

    //region Positioning
    @Test
    public void constructorShouldSetBounds(){
        assertTrue("width should be set in constructor", map.getWidth() > 0f);
        assertTrue("height should be set in constructor", map.getHeight() > 0f);
    }
    //endregion Positioning

    //region Display
    @Test
    public void drawShouldRenderTexture(){
        map.draw(mockBatch, 1f);

        verify(mockBatch).draw(any(Texture.class), anyFloat(), anyFloat());
    }
    //endregion Display
}
