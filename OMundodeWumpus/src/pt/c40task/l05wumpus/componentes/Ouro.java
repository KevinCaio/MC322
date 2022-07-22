package pt.c40task.l05wumpus.componentes;

import pt.c40task.l05wumpus.Impressao;

public class Ouro extends Componente {
	private Heroi heroiCarregando = null;
	private boolean descoberto = false;

	public Ouro(int x, int y) {
		super(x, y, 'O', 3);
	}

	@Override
	public void interagir(Heroi heroi) {
		if(!descoberto) {
			descoberto = true;
			Impressao.adicionaMensagem("Voce encontrou o Ouro!");
		}
		
		if(!heroi.getPegandoOuro()) return;
		cave.remover(this);
		heroi.setOuro(this);
		heroiCarregando = heroi;
		Impressao.adicionaMensagem("Voce coloca o Ouro na sua mochila.");
	}
	
	// ---------------------- Reescreve os metodos getX e getY para que a
	// ---------------------- posicao do ouro seja igual a posicao do heroi
	// ---------------------- quando o ouro esta sendo carregado
	@Override
	public int getX() {
		if(heroiCarregando == null)
			return super.getX();
		return heroiCarregando.getX();
	}
	
	@Override
	public int getY() {
		if(heroiCarregando == null)
			return super.getY();
		return heroiCarregando.getY();
	}
	
}
