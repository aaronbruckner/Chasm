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
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

/**
 * Created by aaronbruckner on 12/13/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SquadMember.class, Texture.class})
public class SquadMemberTest {
    private SquadMember squadMember;

    @Mock
    private Batch mockBatch;
    @Mock
    private Texture mockTexture;
    @Mock
    private FileHandle mockFile;

    @Before
    public void beforeEach() throws Exception {
        MockitoAnnotations.initMocks(this);
        // Mock texture and file system calls.
        Gdx.files = mock(Files.class);
        when(Gdx.files.internal("actors/squadMembers/squadMember.png")).thenReturn(mockFile);
        PowerMockito.whenNew(Texture.class).withArguments(mockFile).thenReturn(mockTexture);

        squadMember = PowerMockito.spy(new SquadMember());
    }

    @After
    public void afterEach(){
    }

    //region Positioning
    @Test
    public void constructorShouldSetBounds(){
        assertTrue("xPos should be set in constructor", squadMember.getX() > 0f);
        assertTrue("yPos should be set in constructor", squadMember.getY() > 0f);
        assertTrue("width should be set in constructor", squadMember.getWidth() > 0f);
        assertTrue("height should be set in constructor", squadMember.getHeight() > 0f);
    }
    //endregion Positioning

    //region Display
    @Test
    public void drawShouldRenderTextureAtCurrentXposYpos(){
        squadMember.setPosition(2f, 3f);
        squadMember.setSize(5f, 6f);

        squadMember.draw(mockBatch, 1f);

        verify(mockBatch).draw(mockTexture, 2f, 3f, 5f, 6f);
    }
    //endregion Display


}
