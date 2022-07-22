package com.mygdx.game.screens.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.elements.player.ICommand;
import com.mygdx.game.screens.game.view.IViewCommand;

public class Control implements InputProcessor, IControl {
    private ICommand pCase;
    private ICommand pTars;
    protected IViewCommand view;
    private float timeOut = 0;
    
    public void conectCase(ICommand pCase) {
    	this.pCase = pCase;
    }
    
    public void conectTars(ICommand pTars) {
    	this.pTars = pTars;
    }
    
    public void conectView(IViewCommand view) {
    	this.view = view;
    }
    
    public boolean keyDown(int keycode) {
        if(timeOut != 0) return false;
        if(keycode == Input.Keys.LEFT)
            pCase.moveLeft();
        else if(keycode == Input.Keys.RIGHT)
        	pCase.moveRight();
        else if(keycode == Input.Keys.UP)
        	pCase.moveUp();
        else if(keycode == Input.Keys.DOWN)
        	pCase.moveDown();
        
        else if(keycode == Input.Keys.A)
        	pTars.moveLeft();
        else if(keycode == Input.Keys.D)
        	pTars.moveRight();
        else if(keycode == Input.Keys.W)
        	pTars.moveUp();
        else if(keycode == Input.Keys.S)
        	pTars.moveDown();
        
        //else if(keycode == Input.Keys.NUMPAD_0)
        else if(keycode == Input.Keys.PAGE_DOWN || keycode == Input.Keys.NUMPAD_ENTER)
        	pCase.commandAction();
        else if(keycode == Input.Keys.E)
        	pTars.commandAction();

        else if(keycode == Input.Keys.INSERT || keycode == Input.Keys.NUMPAD_1)
            pCase.useHability(0);
        else if(keycode == Input.Keys.HOME || keycode == Input.Keys.NUMPAD_2)
            pCase.useHability(1);
        else if(keycode == Input.Keys.PAGE_UP || keycode == Input.Keys.NUMPAD_3)
            pCase.useHability(2);
        else if(keycode == Input.Keys.NUM_1)
            pTars.useHability(0);
        else if(keycode == Input.Keys.NUM_2)
            pTars.useHability(1);
        else if(keycode == Input.Keys.NUM_3)
            pTars.useHability(2);
        
        else if(keycode == Input.Keys.END || keycode == Input.Keys.NUMPAD_0)
            pCase.dropCrystal();
        else if(keycode == Input.Keys.Q)
            pTars.dropCrystal();
        else if(keycode == Input.Keys.SPACE)
        	view.showCommands(true);
        	
        return false;
    }

    public boolean keyUp(int keycode) {
    	if(keycode == Input.Keys.PAGE_DOWN || keycode == Input.Keys.NUMPAD_ENTER)
        	pCase.commandDeaction();
        else if(keycode == Input.Keys.E)
        	pTars.commandDeaction();
        else if(keycode == Input.Keys.SPACE)
        	view.showCommands(false);
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void setTimeOut(float t) {
        timeOut = t;
    }

    @Override
    public void update(float t) {
        if(timeOut == 0) return;
        timeOut -= t;
        if(timeOut < 0)
            timeOut = 0;
    }

}