package pt.c02oo.s02classe.s03lombriga;

public class Animacao {
	char[] movimentos;
	int tamanho;
	int atual;
	int controleChamadas;
	
	public Animacao(String moves) {
		this.movimentos = moves.toCharArray();
		this.tamanho = moves.length();
		this.atual = 0;
		this.controleChamadas = 0;
	}
	
	public String apresenta(AquarioLombriga aquario) {
		if(controleChamadas == 0) {//primeira chamada
			controleChamadas = 1;
			return aquario.apresenta(0);
		}
		return aquario.apresenta(1);
	}
	
	public void passo(AquarioLombriga aquario) {
		
		if(atual < tamanho) {
			if(movimentos[atual] == 'C') {
				aquario.crescer();
			}
			if(movimentos[atual] == 'M') {
				aquario.mover();
			}
			if(movimentos[atual] == 'V') {
				aquario.virar();
			}
			atual += 1;
		}
	}
	
}
