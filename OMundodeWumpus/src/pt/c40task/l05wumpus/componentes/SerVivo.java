package pt.c40task.l05wumpus.componentes;

public abstract class SerVivo extends Componente {
	private boolean vivo = true;
	
	public SerVivo(int x, int y, char simbolo, int prioridade) {
		super(x, y, simbolo, prioridade);
	}
	
	@Override
	public abstract void interagir(Heroi heroi);
	
	public boolean getVivo() {
		return vivo;
	}
	
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	public void matar() {
		vivo = false;
	}
}
