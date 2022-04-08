package pt.c02oo.s03relacionamento.s04restaum;

public class Tabuleiro {
	
	char board[][];
	Peca tabuleiro[][];
	
	public Tabuleiro(char board[][]) {
		this.board = board;
		for(int i = 0;i < 7; i++) {
			for(int j = 0; j < 7;j++) {
				this.tabuleiro[i][j] = new Peca(i, j);
			}
		}
	}
	
	public void movimenta(char[] charCommands) {
		int lineOrigin = charCommands[0] - 48;  //se der problema pd ser aqui
		int columnOrigin = charCommands[1] - 97;
		int lineDestiny = charCommands[3] - 48;
		int columnDestiny = charCommands[4] - 97;
		
		if((lineOrigin == lineDestiny && Math.abs(columnDestiny-columnOrigin) == 2) || 
		columnOrigin == columnDestiny && Math.abs(lineOrigin-lineDestiny) == 2) {
			if(tabuleiro[lineOrigin][columnOrigin].valida && tabuleiro[lineOrigin][columnOrigin].viva 
			&& tabuleiro[lineDestiny][columnDestiny].valida && !tabuleiro[lineDestiny][columnDestiny].viva) {
				if((lineOrigin == lineDestiny && tabuleiro[lineOrigin][(columnDestiny+columnOrigin)/2].viva) ||
				   (columnOrigin == columnDestiny && tabuleiro[(lineOrigin+lineDestiny)/2][columnOrigin].viva)) {
					//movimenta a peca
					//as condicoes de posicoes e movimentacoes validas o tabuleiro pd fazer, ja se vai ter algum apeca nodestino
					//ou algo do tipo ai a peca precisa perguntar.
					
					//
				}
				
			}
		}
		
	}
	public void esvazia(int i, int j) {
		board[i][j] = '-';
	}
	
	public void enche(int i, int j) {
		board[i][j] = 'P';
	}
	
	public char[][] apresenta() {
		return board;
	}
	
	
}
