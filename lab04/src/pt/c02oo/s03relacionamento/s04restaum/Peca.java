package pt.c02oo.s03relacionamento.s04restaum;

public class Peca {

	
	boolean viva;
	boolean valida;
	Tabuleiro objTabu;
	
	public Peca(int i, int j) {
		if((i < 2 && j < 2) || (i > 4 && j < 2) || (i < 2  && j > 4) || (i > 4 && j > 4)) {
			this.valida = false;
			this.viva = false;
		}
		if(i == 3 && j == 3) {
			this.valida = true;
			this.viva = false;
		}
		else {
			this.valida = true;
			this.viva = true;
		}
	}
	
	public void movimenta() {
		
	}
	
	public void esvazia() {
		viva = false;
	}
	
	public void enche() {
		viva = true;
	}
}
