package com.aaronbruckner.chasm.inputHandler;

import com.aaronbruckner.chasm.inputHandlers.MovableCameraHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.AdditionalMatchers.*;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link MovableCameraHandler}
 *
 * Created by aaronbruckner on 12/14/2015.
 */
public class MovableCameraHandlerTest {
    private MovableCameraHandler movableCameraHandler;

    @Mock
    private Viewport mockViewport;
    @Mock
    private Input mockInput;
    @Mock
    private Camera mockCamera;
    @Mock
    private Graphics mockGraphics;

    @Before
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        Gdx.input = mockInput;
        Gdx.graphics = mockGraphics;
        when(mockViewport.getCamera()).thenReturn(mockCamera);
        when(mockGraphics.getDeltaTime()).thenReturn(0.5f);

        movableCameraHandler = new MovableCameraHandler(mockViewport);
    }

    //region Keyboard
    @Test
    public void shouldPanRightOnInput(){
        when(mockInput.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);

        movableCameraHandler.pollInput();

        verify(mockCamera).translate(gt(0f), eq(0f), eq(0f));
    }

    @Test
    public void shouldPanLeftOnInput(){
        when(mockInput.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);

        movableCameraHandler.pollInput();

        verify(mockCamera).translate(lt(0f), eq(0f), eq(0f));
    }

    @Test
    public void shouldPanUpOnInput(){
        when(mockInput.isKeyPressed(Input.Keys.UP)).thenReturn(true);

        movableCameraHandler.pollInput();

        verify(mockCamera).translate(eq(0f), gt(0f), eq(0f));
    }

    @Test
    public void shouldPanDownOnInput(){
        when(mockInput.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);

        movableCameraHandler.pollInput();

        verify(mockCamera).translate(eq(0f), lt(0f), eq(0f));
    }
    //endregion Keyboard
}
