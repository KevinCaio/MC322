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
		columnOrigin == columnDestiny && Math.abs(lineOrigin-lineDestiny) == 2) { //movimento valido no trabuleiro
			
			tabuleiro[lineOrigin][columnOrigin].movimenta(lineDestiny, columnDestiny, lineOrigin, columnOrigin);
		}
		
	}
	public boolean podeIr(int lineDestiny, int columnDestiny, int lineOrigin, int columnOrigin) {
		if(tabuleiro[lineDestiny][columnDestiny].ehValida() && !tabuleiro[lineDestiny][columnDestiny].taViva()
				&& (lineOrigin == lineDestiny && tabuleiro[lineOrigin][(columnDestiny+columnOrigin)/2].taViva()) ||
				   (columnOrigin == columnDestiny && tabuleiro[(lineOrigin+lineDestiny)/2][columnOrigin].taViva())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void meMovimenta(int lineDestiny, int columnDestiny, int lineOrigin, int columnOrigin) {
		tabuleiro[lineOrigin][columnOrigin].esvazia();
		tabuleiro[lineDestiny][columnOrigin].enche();
		if(lineOrigin == lineDestiny){
			tabuleiro[lineOrigin][(columnDestiny+columnOrigin)/2].esvazia();
		}
		if(columnOrigin == columnDestiny) {
			tabuleiro[(lineOrigin+lineDestiny)/2][columnOrigin].esvazia();
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
	
	//mudar varias coisas, a peca n vai conseguir chamar o tabuleiro
	//passar as infos pra peca
	//ela julga se vai ou n.
	
	//ou
	
	//posso colocar os metodos do tabuleiro como private, ai a peca n consegue acesar
}
