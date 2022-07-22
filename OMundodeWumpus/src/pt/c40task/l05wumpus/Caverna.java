package pt.c40task.l05wumpus;
import pt.c40task.l05wumpus.componentes.Componente;
import pt.c40task.l05wumpus.componentes.Heroi;

public class Caverna {
	private Sala[][] salas;
	
	public Caverna() {
		Sala[][] salas = new Sala[4][4];
		for(int i=0;i < 4;i++) {
			for(int j=0;j < 4;j++) {
				salas[i][j] = new Sala();
			}
		}
		this.salas = new Sala[4][4];
		this.salas = salas;
	}
	
	public boolean inserirCompInicial(Componente aInserir) { 
		if(aInserir.getX() < 0 || aInserir.getX() > 3 || aInserir.getY() < 0 || aInserir.getY() > 3 ) {
			return false;
		}
		return salas[aInserir.getX()][aInserir.getY()].inserirCompInicial(aInserir);
	}
	
	public void remover(Componente comp) {
		salas[comp.getX()][comp.getY()].remover(comp);
	}
	
	public void mover(Componente comp, int posX, int posY) {
		salas[comp.getX()][comp.getY()].remover(comp);
		salas[posX][posY].inserir(comp);
	}
	
	public void interagir(Heroi heroi) {
		salas[heroi.getX()][heroi.getY()].interagir(heroi);
	}
	
	public char[][] getMapa(){
		char[][] mapa = new char[4][4];
		for(int x=0;x < 4;x++)
			for(int y=0;y < 4;y++)
				mapa[y][x] = salas[x][y].getSimbolo(); 
		return mapa;
	}
}
