package com.mygdx.game.elements.player;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SoundManager;
import com.mygdx.game.app.IGameControl;
import com.mygdx.game.elements.Crystal;
import com.mygdx.game.elements.element.Element;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.habilities.IHability;
import com.mygdx.game.habilities.IVisualEffect;
import com.mygdx.game.habilities.IVisualHability;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.screens.game.ILantern;
import com.mygdx.game.screens.game.space.ISpaceCommand;

public class Player extends Element implements IPlayer{
	private ISpaceCommand space;
	private char variation;
	private ILantern lantern;
	private boolean dead = false;

	private double totalTime = 300000;
	private double timeRemaining = totalTime;
	
	private Array<IVisualEffect> effects = new Array<IVisualEffect>();
	private IHability[] habilities = new IHability[3];
	
	private boolean phantom = false;
	private Array<Crystal> crystals = new Array<Crystal>();
	private Array<Character> inventory = new Array<Character>();
	
    private IGameControl game;
    private IUpdate bh;
	
	public Player(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
	}
	
	public void connect(ISpaceCommand space) {
		this.space = space;
	}
	
    public void connect(ILantern lantern) {
        this.lantern = lantern;
    }
    
    public void connect(IHability h, int i) {
        this.habilities[i] = h;
    }
    
    public void connect(IGameControl game) {
        this.game = game;
    }
    
    public void connect(IUpdate bh) {
        this.bh = bh;
    }
	
    // From ICommand
	@Override
	public void moveLeft() {
        move(-1,0);
	}

	@Override
	public void moveRight() {
        move(+1,0);
	}

	@Override
	public void moveUp() {
        move(0,+1);
	}

	@Override
	public void moveDown() {
        move(0,-1);
	}
	
	@Override
    public void moveTo(int x, int y) {
        move(x - this.x, y - this.y);
    }
	
	private void move(int dx, int dy) {
	    if(dead) return;
	    int xi = x, yi = y;
	    
	    x += dx;
        y += dy;
        try {
            space.move(this, xi, yi, x, y, phantom);
            checkEffects();
            if (bh != null)
                bh.update();
        } catch (ObstructedCell e) {
    	    SoundManager.playWallHit();
            x = xi;
            y = yi;
        }
	}

    @Override
    public void commandAction() {
        if(dead) return;
        space.action(x, y, this);
    }

    @Override
    public void commandDeaction() {
        if(dead) return;
        space.deaction(x, y, this);
    }

    @Override
    public void useHability(int i) {
        if(dead) return;
        habilities[i].use();
    }
	
	// From IVisual
	@Override
	public char type() {
		return 'P';
	}

	@Override
	public char variation() {
		return variation;
	}

	@Override
	public char state() {
		return phantom ? 'P' : 'N';
	}
	
	// From ITime
    @Override
    public void update(float t) {
        updateEffects(t);
        updateHabilities(t);
    }
    
    // From IVisualPlayer
    @Override
    public float timeRemaining() {
        return (float) (timeRemaining/totalTime);
    }
	
	// From IPlayerEffect
    @Override
    public void addEffect(IVisualEffect effect) {
        effects.add(effect);
        effect.applyEffect();
    }

    @Override
    public void changeRadius(int change) {
        lantern.changeRadius(change);
    }
    
    @Override
    public IPosition getIPosition() {
        return this;
    }
    
    private void checkEffects() {
        for(IVisualEffect effect : effects)
            if(effect.isOver()) {
                effect.removeEffect();
                effects.removeValue(effect, true);
            }
    }
    
    private void updateEffects(float t) {
        for(IVisualEffect effect : effects)
            effect.update(t);
        checkEffects();
    }
    
    private void updateHabilities(float t) {
        for(IHability hability : habilities) {
            if(hability == null) break;
            hability.update(t);
        }
    }

    @Override
    public void leave() {
        space.remove(this);
    }

    @Override
    public void enter() {
        space.insert(this);
    }

    @Override
    public void setPhantom(boolean phantom) {
        this.phantom = phantom;
		cell.update();
    }

    public void addCrystal(Crystal c) {
        crystals.add(c);
        inventory.add(c.variation());
        updateHabilities();
        SoundManager.playCrystalGetting();
    }
    
    public void dropCrystal() {
        if(crystals.size == 0) return;
        Crystal removed = crystals.removeIndex(crystals.size - 1);
        removed.setX(x);
        removed.setY(y);
        if(removed.canEnter()) {
            removed.enter();
            updateHabilities();
            inventory.removeIndex(inventory.size -1);
        }
        else {
            crystals.add(removed);
        }
    }

    private void updateHabilities() {
        for(IHability hability : habilities)
            hability.update();
    }

    @Override
    public boolean hasCrystal(char variation) {
        for(Crystal crystal : crystals)
            if(crystal.variation() == variation)
                return true;
        return false;
    }

	@Override
	public Array<Character> getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	@Override
	public Array<IVisualHability> getHabilities() {
		Array<IVisualHability> returnHabilities = new Array<IVisualHability>();
		for(int i = 0;i < 3;i++) {
			returnHabilities.add(habilities[i]); 
		}
		return returnHabilities;
	}

    @Override
    public void updateTimeRemaining(float t) {
        timeRemaining += t;
        
        if(timeRemaining <= 0) {
            dead = true;
            setPhantom(true);
            lantern.turnOff();
            SoundManager.playPlayerDying();
            game.gameOver(false);
        }
        else if(timeRemaining > totalTime)
            timeRemaining = totalTime;
    }

}
