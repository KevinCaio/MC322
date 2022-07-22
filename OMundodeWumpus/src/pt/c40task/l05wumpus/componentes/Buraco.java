package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Buraco extends Componente {
	
	public Buraco(int x, int y) {
		super(x, y, 'B', 3);
	}
	
	public void criaBrisa() {
		int[][] dpos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i<dpos.length; i++) {
			Brisa brisa = new Brisa(getX() + dpos[i][0], getY() +  dpos[i][1]);
			cave.inserirCompInicial(brisa);
		}
	}

	@Override
	public void interagir(Heroi heroi) {
		Impressao.adicionaMensagem("Voce caiu em um buraco.");
		heroi.matar();
	}

}
