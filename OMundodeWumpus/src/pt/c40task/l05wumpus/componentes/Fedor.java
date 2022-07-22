package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Fedor extends Componente {
	
	public Fedor(int x, int y) {
		super(x, y, 'f', 1);
	}

	@Override
	public void interagir(Heroi heroi) {
		Impressao.adicionaMensagem("Voce sente um fedor.");
	}

}
