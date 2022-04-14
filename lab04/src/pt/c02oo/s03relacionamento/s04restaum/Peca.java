package pt.c02oo.s03relacionamento.s04restaum;

public class Peca {

	private int line;
	private int column;
	private boolean viva;
	private boolean valida;
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
		this.line = i;
		this.column = j;
	}
	
	public void conectaTabu(Tabuleiro objTabu) {
		this.objTabu = objTabu;
	}
	
	public void movimenta(int lineDestiny, int columnDestiny) {
		
		if((line == lineDestiny && Math.abs(columnDestiny-column) == 2) || 
			column == columnDestiny && Math.abs(line-lineDestiny) == 2) {
			if(valida && viva && objTabu.podeIr(lineDestiny, columnDestiny, line, column)) {
				objTabu.meMovimenta(lineDestiny, columnDestiny, line, column);;
			}	
		}
	}
	
	public void trocaPos(int line, int column) {
		this.line = line;
		this.column = column;
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
	
}
