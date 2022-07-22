package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Caverna;

public abstract class Componente {
	private int x, y;
	protected Caverna cave;
	private char simbolo;
	private int prioridadeSimbolo;
	
	public abstract void interagir(Heroi heroi);
	
	public Componente(int x, int y, char simbolo, int prioridade) {
		this.x = x;
		this.y = y;
		this.simbolo = simbolo;
		this.prioridadeSimbolo = prioridade;
	}
	
	public boolean insereNaCaverna() {
		return cave.inserirCompInicial(this);
	}
	
	public Caverna getCaverna() {
		return cave;
	}
	
	public void conectaCaverna(Caverna cave) {
		this.cave = cave;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getSimbolo() {
		return simbolo;
	}
	
	public int getPrioridade() {
		return prioridadeSimbolo;
	}
	
	public void mover(int xf, int yf) {
		cave.mover(this, xf, yf);
		x = xf;
		y = yf;
	}
}
