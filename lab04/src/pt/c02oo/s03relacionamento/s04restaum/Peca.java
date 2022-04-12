package pt.c02oo.s03relacionamento.s04restaum;

public class Peca {

	
	boolean viva;
	boolean valida;
	//colocar a posicao?
	
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
	
	public void movimenta(int lineDestiny, int columnDestiny, int lineOrigin, int columnOrigin) {
		if(valida && objTabu.podeIr(lineDestiny, columnDestiny, lineOrigin, columnOrigin)) {
			objTabu.meMovimenta(lineDestiny, columnDestiny, lineOrigin, columnOrigin);;
		}	
	}
	
	public boolean taViva() {
		return viva;
	}
	
	public boolean ehValida() {
		return valida;
	}
	
	public void esvazia() {
		viva = false;
	}
	
	public void enche() {
		viva = true;
	}
}
