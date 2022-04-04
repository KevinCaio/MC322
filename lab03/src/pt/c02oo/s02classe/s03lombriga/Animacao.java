package pt.c02oo.s02classe.s03lombriga;

public class Animacao {
	/*Eu escolho os atributos
	 * Metodos:
	 *passo
	 * */
	char[] movimentos;
	int tamanho;
	int atual;
	
	public Animacao(String moves) {
		this.movimentos = moves.toCharArray();
		this.tamanho = moves.length();
		this.atual = 0;
	}
	
	public String apresenta(AquarioLombriga aquario) {
		return aquario.apresenta();
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
