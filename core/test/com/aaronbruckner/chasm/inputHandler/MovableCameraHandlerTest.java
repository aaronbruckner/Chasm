package com.aaronbruckner.chasm.inputHandler;

import com.aaronbruckner.chasm.inputHandlers.MovableCameraHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.*;
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
    private OrthographicCamera mockCamera;
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

    //region Panning
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
    //endregion Panning

    //region Zooming
    @Test
    public void shouldZoomInWhenScrolledUp(){
        float originalZoom = 1f;
        mockCamera.zoom = originalZoom;

        movableCameraHandler.scrolled(-1);

        assertTrue("Zoom should have decreased when scrolled up", mockCamera.zoom < originalZoom);
    }

    @Test
    public void shouldZoomOutWhenScrolledDown(){
        float originalZoom = 1f;
        mockCamera.zoom = originalZoom;

        movableCameraHandler.scrolled(1);

        assertTrue("Zoom should have increased when scrolled up", mockCamera.zoom > originalZoom);
    }

    @Test
    public void zoomShouldNeverBeLessThanZero(){
        mockCamera.zoom = 0.01f;

        int i = 0;
        while(i++ < 100){
            movableCameraHandler.scrolled(-1);
        }

        assertTrue("Zoom should be greater than zero", mockCamera.zoom > 0);
    }
    //endregion Zooming
}
