package pt.c02oo.s02classe.s03lombriga;

public class AquarioLombriga {
	int tamAquario; int tamLombriga; int posCabeca; int posRabo; int direcao;
	
	//direcao -1 eh cabeca pra esquerda
	
	public AquarioLombriga(int tamAquario, int tamLombriga, int posCabeca) { 
		this.tamAquario = tamAquario;
		this.tamLombriga = tamLombriga;
		this.posCabeca = posCabeca;
		direcao = -1;
	}
	
	public void crescer(AquarioLombriga aquario) { //vai ter retorno?
		if(direcao == -1 && aquario.posRabo < aquario.tamAquario) {
			aquario.tamLombriga += 1;
			aquario.posRabo += 1;
		}
		if(direcao == 1 && aquario.posRabo > 1) {
			aquario.tamLombriga += 1;
			aquario.posRabo -= 1;
		}
		
	}
	
	public void mover(AquarioLombriga aquario) {
		if((aquario.direcao == -1 && aquario.posCabeca == 1) || (aquario.direcao == 1 && aquario.posCabeca == aquario.tamAquario)) {
			aquario.virar(); //parametros?
		}
		else {
			if(aquario.direcao == -1) {
				aquario.posCabeca -= 1;
				aquario.posRabo -= 1;
			}
			if(aquario.direcao == 1) {
				aquario.posCabeca += 1;
				aquario.posRabo += 1;
			}
		}
	}
	
	public void virar(AquarioLombriga aquario) {
		int aux;
		aux = aquario.posCabeca;
		aquario.posCabeca = aquario.posRabo;
		aquario.posRabo = aux;
		aquario.direcao = aquario.direcao * (-1);
	}
	
	public String apresenta(AquarioLombriga aquario) {
		String representacao = "";
		
		if(aquario.direcao == -1) {
			for(int i = 0;i <= aquario.tamAquario;i++) {
				if(i == aquario.posCabeca) {
					representacao += "0";
				}
				if(i > aquario.posCabeca && i <= aquario.posRabo) {
					representacao += "@";
				}
				else {
					representacao += "#";
				}
			}
		}
		if(aquario.direcao == 1) {
			for(int i = 0;i <= aquario.tamAquario;i++) {
				if(i == aquario.posCabeca) {
					representacao += "0";
				}
				if(i < aquario.posCabeca && i >= aquario.posRabo) {
					representacao += "@";
				}
				else {
					representacao += "#";
				}
			}
		}
		return representacao;
	}
}
