package com.aaronbruckner.chasm.inputHandler;

import com.aaronbruckner.chasm.actors.SquadMember;
import com.aaronbruckner.chasm.inputHandlers.SquadMovementHandler;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SquadMovementHandler}
 *
 * Created by aaronbruckner on 12/15/2015.
 */
public class SquadMovementHandlerTest {
    private SquadMovementHandler squadMovementHandler;
    private ArgumentCaptor<Action> actionCaptor;

    @Mock
    private Group mockGroup;
    @Mock
    private SquadMovementHandler.Listener mockListener;
    @Mock
    private SquadMember mockSquadMember;
    @Mock
    private Stage mockStage;
    @Mock
    private MoveToAction mockMoveToAction;

    @Before
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        when(mockGroup.getStage()).thenReturn(mockStage);

        // Divides screen coordinates by two to find "local" coordinates. Forces code to do correct translation.
        when(mockGroup.screenToLocalCoordinates(any(Vector2.class))).then(new Answer<Vector2>() {
            @Override
            public Vector2 answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Vector2 screenVector = (Vector2) args[0];
                screenVector.x /= 2;
                screenVector.y /= 2;
                return screenVector;
            }
        });

        actionCaptor = ArgumentCaptor.forClass(Action.class);
        squadMovementHandler = new SquadMovementHandler(mockGroup, mockListener);
    }

    //region Squad Selection
    @Test
    public void shouldNotNotifyListenerIfNoMemberSelected(){
        when(mockGroup.hit(50, 100, true)).thenReturn(null);

        squadMovementHandler.touchUp(100, 200, 0, Input.Buttons.LEFT);

        verify(mockListener, never()).onSquadMemberSelected(any(SquadMember.class));
    }

    @Test
    public void shouldOnlyRespondToLeftClick(){
        when(mockGroup.hit(50, 100, true)).thenReturn(mockSquadMember);

        squadMovementHandler.touchUp(100, 200, 0, Input.Buttons.RIGHT);

        verify(mockListener, never()).onSquadMemberSelected(any(SquadMember.class));
    }

    @Test
    public void shouldNotifyListenerWhenSquadMemberSelected(){
        when(mockGroup.hit(50, 100, true)).thenReturn(mockSquadMember);

        squadMovementHandler.touchUp(100, 200, 0, Input.Buttons.LEFT);

        verify(mockListener).onSquadMemberSelected(mockSquadMember);
    }
    //endregion Squad Selection

    //region Movement Actions
    @Test
    public void noMovementActionIfSquadMemberIsUnselected(){
        when(mockGroup.hit(50, 100, true)).thenReturn(mockSquadMember);

        squadMovementHandler.touchUp(400, 400, 0, Input.Buttons.LEFT);

        verify(mockStage, never()).addAction(any(Action.class));
    }

    @Test
    public void issueMovementActionToSelectedSquadMemberOnClick(){
        when(mockGroup.hit(50, 100, true)).thenReturn(mockSquadMember);

        //Select Squad Member
        squadMovementHandler.touchUp(100, 200, 0, Input.Buttons.LEFT);
        //Issue Movement command
        squadMovementHandler.touchUp(300, 400, 0, Input.Buttons.LEFT);

        verify(mockStage).addAction(actionCaptor.capture());
        MoveToAction action = (MoveToAction)actionCaptor.getValue();
        assertEquals("Selected squad member should have been moved", mockSquadMember, action.getActor());
        assertEquals("Movement to x position should match translated screen x coordinate", 150f, action.getX());
        assertEquals("Movement to y position should match translated screen y coordinate", 200f, action.getY());
    }
    //endregion Movement Actions
}
