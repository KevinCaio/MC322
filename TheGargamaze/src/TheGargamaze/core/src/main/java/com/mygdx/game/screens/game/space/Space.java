package com.mygdx.game.screens.game.space;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.element.Element;
import com.mygdx.game.elements.player.IPlayerInteraction;
import com.mygdx.game.elements.player.Player;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.screens.game.ILantern;
import com.mygdx.game.screens.game.cell.Cell;

public class Space implements ISpace{
	public static int size;
	
	private Array<ILantern> lanterns = new Array<ILantern>();
	private Cell[][] cells; 
	
	public Space() {
		cells = new Cell[size][size]; 
		for(int x = 0; x < size; x++)
 			for(int y = 0; y < size; y++)
 			   cells[x][y] = new Cell();
	}
	
	public void setAlwaysVisibleCells(boolean[][] matrix) {
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                if(matrix[x][y])
                    cells[x][y].setAlwaysVisible();
	}
	
	public void insert(Element toInsert) {
		cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
	}

    public void remove(Element toRemove) {
        cells[toRemove.getX()][toRemove.getY()].remove(toRemove);
    }
    
	public void insert(Player toInsert) {
        cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
        cells[toInsert.getX()][toInsert.getX()].interact(toInsert);
    }
	
	public void remove(Player toRemove) {
        cells[toRemove.getX()][toRemove.getY()].remove(toRemove);
        cells[toRemove.getX()][toRemove.getY()].deaction(toRemove);
    }
	
	public void addLantern(ILantern lantern) {
	    lanterns.add(lantern);
        updateVisibility();
	}
	
	public void move(Player toMove, int xi, int yi,  int xf, int yf, boolean forced) throws ObstructedCell {
		int obsLevel = cells[xf][yf].obstructionLevel();
	    if(obsLevel == 3 || (!forced && obsLevel == 2))
		    throw new ObstructedCell("This cell is obstructed!");
		
		cells[xi][yi].remove(toMove);
        cells[xi][yi].deaction(toMove);
		cells[xf][yf].insert(toMove);
        cells[xf][yf].interact(toMove);
        updateVisibility();
	}
	
	public Cell getCell(int x, int y) {
        return cells[x][y];
    }
	
	public void updateVisibility() {
	    setDark();
	    for(ILantern lantern : lanterns)
	        lantern.iluminate();
	}
	
	private void setDark() {
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y].setVisibility(false);
	}

    @Override
    public void iluminate(int x, int y, float clarity) {
        // metodo chamado pela lanterna para iluminar uma celula especifica
        if(x < 0 || x >= size || y < 0 || y >= size) return;
        cells[x][y].setVisibility(true, clarity);
    }

	@Override
	public void action(int x, int y, IPlayerInteraction player) {
		cells[x][y].action(player);
	}

	@Override
	public void deaction(int x, int y, IPlayerInteraction player) {
		cells[x][y].deaction(player);
	}

    @Override
    public int obstructionLevel(int x, int y) {
        return cells[x][y].obstructionLevel();
    }
}
