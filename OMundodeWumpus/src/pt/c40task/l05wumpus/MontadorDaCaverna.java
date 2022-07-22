package pt.c40task.l05wumpus;
import pt.c40task.l05wumpus.componentes.Heroi;
import pt.c40task.l05wumpus.componentes.Wumpus;
import pt.c40task.l05wumpus.componentes.Buraco;
import pt.c40task.l05wumpus.componentes.Ouro;

public class MontadorDaCaverna {
	private String cave[][];
	private Heroi heroi;
	private Wumpus wumpus;
	private Caverna caverna;
	
	public MontadorDaCaverna(String cave[][]) { 
		this.cave = cave;
	}
	
	public boolean montar() {
		boolean saida = true;
		if(conferirArquivo()) {
			criarCaverna();		
			
			for(int i=0;i < cave.length && saida;i++) {
				int posX = Integer.parseInt(cave[i][1]) -1;  
				int posY = Integer.parseInt(cave[i][0]) -1;
				
				switch(cave[i][2]) {
					case "P":
						this.heroi = new Heroi(posX, posY); 
						this.heroi.conectaCaverna(caverna);
						saida = heroi.insereNaCaverna();
						break;
					case "O":
						Ouro ouro = new Ouro(posX, posY);  
						ouro.conectaCaverna(caverna);
						saida = ouro.insereNaCaverna();
						break;
					case "B":
						Buraco buraco = new Buraco(posX, posY);
						buraco.conectaCaverna(caverna);
						buraco.criaBrisa();
						saida = buraco.insereNaCaverna();
						break;
					case "W":
						this.wumpus = new Wumpus(posX, posY);
						this.wumpus.conectaCaverna(caverna);
						this.wumpus.criaFedor();
						saida = wumpus.insereNaCaverna();
						break;
					default:
						break;
				}
			}
		}
		else {
			saida =  false;
		}
		return saida;
	}
	
	public boolean conferirArquivo() {
		int qtdBuracos = 0;
		int qtdWumpus = 0;
		int qtdOuro = 0;
		int qtdHeroi = 0;
		int posX;
		int posY;
		boolean posicoesOk = true;
		
		for(int i=0;i < cave.length && posicoesOk;i++) {
			posX = Integer.parseInt(cave[i][1]);  
			posY = Integer.parseInt(cave[i][0]);
			posicoesOk = conferirPos(posX, posY);
			
			switch(cave[i][2]) {
				case "P":
					if(posX != 1 || posY != 1) {
						posicoesOk = false;
					}
					qtdHeroi += 1;
					break;
				case "O":
					qtdOuro += 1;
					break;
				case "B":
					qtdBuracos += 1;
					break;
				case "W":
					qtdWumpus += 1;
					break;
				default:
					break;
			}
		}
		if(qtdBuracos < 2 || qtdBuracos > 3 || qtdWumpus != 1 || qtdHeroi != 1 || qtdOuro != 1 || !posicoesOk) {
			return false;
		}
		return true;
	}
	
	public boolean conferirPos(int posX, int posY) {
		if(posX < 1 || posX > 4 || posY < 1 || posY > 4) {
			return false;
		}
		return true;
	}
	
	public void criarCaverna() {
		this.caverna = new Caverna();
	}
	
	public Heroi getHeroi() {
		return heroi;
	}
	
	public Wumpus getWumpus() {
		return wumpus;
	}
}
