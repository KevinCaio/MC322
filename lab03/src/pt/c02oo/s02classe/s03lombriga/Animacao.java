package pt.c02oo.s02classe.s03lombriga;

public class Animacao {
	/*Eu escolho os atributos
	 * Metodos:
	 *passo
	 * */
	String movimentos;
	int tamanho;
	int atual;
	
	public Animacao(String moves) {
		this.movimentos = moves;
		this.tamanho = moves.length();
		this.atual = 1;
	}
	
	public String apresenta(AquarioLombriga aquario) {
		return aquario.apresenta();
	}
	
	public String passo(String movimentos) {//pega a movimentacao q tem q fazer e chama o aquario pra fazer a movimentacao
		//iterar sobre os elementos da string, manter um controle com o atual e o tamanho
		
		return movimentos;  //mandar o objeto pra ca?
	}
	
}
