package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Brisa extends Componente {

	public Brisa(int x, int y) {
		super(x, y, 'b', 0);
	}

	@Override
	public void interagir(Heroi heroi) {
		Impressao.adicionaMensagem("Voce sente uma brisa.");
	}

}
