package pt.c02oo.s02classe.s03lombriga;

public class AquarioLombriga {
	int tamAquario; int tamLombriga; int posCabeca; int posRabo; int direcao;
	
	//direcao -1 eh cabeca pra esquerda
	
	public AquarioLombriga(int tamAquario, int tamLombriga, int posCabeca) { 
		this.tamAquario = tamAquario;
		this.tamLombriga = tamLombriga;
		this.posCabeca = posCabeca;
		this.posRabo = posCabeca + tamLombriga -1;
		direcao = -1;
	}
	
	public void crescer() {
		if(direcao == -1 && posRabo < tamAquario) {
			tamLombriga += 1;
			posRabo += 1;
		}
		if(direcao == 1 && posRabo > 1) {
			tamLombriga += 1;
			posRabo -= 1;
		}
		
	}
	
	public void mover() {
		if((direcao == -1 && posCabeca == 1) || (direcao == 1 && posCabeca == tamAquario)) {
			virar(); 
		}
		else {
			if(direcao == -1) {
				posCabeca -= 1;
				posRabo -= 1;
			}
			if(direcao == 1) {
				posCabeca += 1;
				posRabo += 1;
			}
		}
	}
	
	public void virar() {
		int aux;
		aux = posCabeca;
		posCabeca = posRabo;
		posRabo = aux;
		direcao = direcao * (-1);
	}
	
	public String apresenta() {
		String representacao = "";
		
		if(direcao == -1) {
			for(int i = 1;i <= tamAquario;i++) {
				if(i == posCabeca) { 
					representacao += "0"; 
				} 
				if(i > posCabeca && i <= posRabo) { 
					representacao += "@"; 
				} 
				if(i < posCabeca || i > posRabo) {
				  representacao += "#"; 
				}
			}
		}
		if(direcao == 1) {
			for(int i = 1;i <= tamAquario;i++) {
				if(i == posCabeca) {
					representacao += "0";
				}
				if(i < posCabeca && i >= posRabo) {
					representacao += "@";
				}
				if(i > posCabeca || i < posRabo) {
					representacao += "#";
				}
			}
		}
		return representacao;
	}
}
