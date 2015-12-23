package com.aaronbruckner.chasm.inputHandlers;

import com.aaronbruckner.chasm.actors.SquadMember;
import com.aaronbruckner.chasm.stages.EnvironmentDataProvider;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;


/**
 * <p>
 * Handles toggle selection between a group of {@link com.aaronbruckner.chasm.actors.SquadMember}s.
 * Squad members must be actively selected to be given commands. When a member is selected, the
 * stage will be notified of the selected member.
 * </p>
 *
 * Created by aaronbruckner on 12/15/2015.
 */
public class SquadActionHandler extends InputPollingHandler{
    private Group squadGroup;
    private Stage stage;
    private SquadMember selectedSquadMember;
    private Listener listener;
    private Vector2 tempVector;

    public interface Listener {
        public void onSquadMemberSelected(SquadMember selectedSquadMember);
    }

    public SquadActionHandler(Group squadGroup, Listener listener){
        this.squadGroup = squadGroup;
        this.listener = listener;
        stage = squadGroup.getStage();

        //region Temporary variables to avoid garbage collection
        tempVector = new Vector2();
    }

    /**
     * Listens to a touch up event (initiated by either a left mouse click or a release of a
     * finger on a touch screen).
     *
     * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            squadGroup.screenToLocalCoordinates(tempVector.set(screenX, screenY));
            Actor actor = squadGroup.hit(tempVector.x, tempVector.y, true);

            if(actor != null){
                selectedSquadMember = (SquadMember)actor;
                listener.onSquadMemberSelected(selectedSquadMember);
                return false;
            }
            if(selectedSquadMember != null){
                MoveToAction action = new MoveToAction();
                action.setActor(selectedSquadMember);
                action.setPosition(tempVector.x, tempVector.y);
                action.setDuration(3f);
                stage.addAction(action);
            }
        }
        return false;
    }
}
