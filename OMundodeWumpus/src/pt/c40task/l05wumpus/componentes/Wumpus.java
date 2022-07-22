package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Wumpus extends SerVivo {

	public Wumpus(int x, int y) {
		super(x, y, 'W', 3);
	}
	
	public void criaFedor() {
		int[][] dpos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i<dpos.length; i++) {
			Fedor fedor = new Fedor(getX() + dpos[i][0], getY() +  dpos[i][1]);
			cave.inserirCompInicial(fedor);
		}
	}

	@Override
	public void interagir(Heroi heroi) {
		if(heroi.getFlechaArmada()) { // Heroi com flecha armada 
			boolean acertouTiro = Math.random() > 0.5 ? true : false;
			heroi.atirarFlecha();
			if(acertouTiro) {
				this.matar();
				cave.remover(this);
				Impressao.adicionaMensagem("Voce mata o Wumpus com sua flecha!");
				return;
			}
			else 
				Impressao.adicionaMensagem("Voce erra a flechada no Wumpus e ele te mata.");
		}
		else
			Impressao.adicionaMensagem("Voce entra desarmado na sala do Wumpus e ele te mata.");
		
		heroi.matar(); // sem flecha aramada ou perdeu na batalha
	}
}
