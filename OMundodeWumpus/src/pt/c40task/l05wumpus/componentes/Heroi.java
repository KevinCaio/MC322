package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Heroi extends SerVivo {
	private boolean temFlecha = true;
	private boolean flechaArmada = false;
	private boolean pegandoOuro = false;
	private Ouro ouro = null;
	
	public Heroi(int x, int y) {
		super(x, y, 'P', 2);
	}
	
	@Override
	public void interagir(Heroi heroi) {
		// Heroi nao interagi com si propio
	}
	
	// --------------------------------- Movimentacao

	public void mover(int xf, int yf) {
		super.mover(xf, yf);
		cave.interagir(this);
		
		if(flechaArmada) { // Se a flecha ainda estiver armada apos a interacao com os elementos da sala
			atirarFlecha();
			Impressao.adicionaMensagem("Voce atira sua unica flecha no nada.");
		}
	}
	
	public boolean mover(char tecla) {
		int xi = getX(),
		    yi = getY();
		switch(Character.toLowerCase(tecla)) {
		case 'w':
			if(getY() > 0)
				mover(getX(), getY() - 1);
			break;
		case 's':
			if(getY() < 3)
				mover(getX(), getY() + 1);
			break;
		case 'a':
			if(getX() > 0)
				mover(getX() - 1, getY());
			break;
		case 'd':
			if(getX() < 3)
				mover(getX() + 1, getY());
			break;
		}
		return xi != getX() || yi != getY(); // retorna true se a posicao final eh diferente da inicial
	}
	
	//------------------------------ Flecha
	public void armarFlecha() {
		if(temFlecha) {
			flechaArmada = true;
			Impressao.adicionaMensagem("Voce arma sua flecha.");
		} else
			Impressao.adicionaMensagem("Voce nao tem flecha para armar.");
	}
	
	public void atirarFlecha() {
		if(!flechaArmada) return;
		temFlecha = false;
		flechaArmada = false;
	}
	
	public boolean getFlechaArmada() {
		return flechaArmada;
	}
	
	public boolean getTemFlecha() {
		return temFlecha;
	}
	
	// -------------------------------- Ouro
	public void pegarOuro() {
		pegandoOuro = true;
		cave.interagir(this);
		pegandoOuro = false;
	}
	
	public boolean getPegandoOuro() {
		return pegandoOuro;
	}
	
	public Ouro getOuro() {
		return ouro;
	}
	
	public void setOuro(Ouro ouro) {
		this.ouro = ouro;
	}
	
	public boolean carregandoOuro() {
		if(ouro == null)
			return false;
		return true;
	}
	
	// ------------------------- Mapa
	public char[][] getMapa() {
		return cave.getMapa();
	}
}
