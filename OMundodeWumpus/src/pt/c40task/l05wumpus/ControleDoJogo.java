package pt.c40task.l05wumpus;
import pt.c40task.l05wumpus.componentes.Heroi;
import pt.c40task.l05wumpus.componentes.Wumpus;

public class ControleDoJogo {
	private int pontuacao;
	private char status;
	private boolean rodando;
	private boolean wumpusMorto;
	private Heroi heroi;
	private Wumpus wumpus;
	private String nomeJogador;
	
	public ControleDoJogo() {
		this.pontuacao = 0;
		this.rodando = true;
		this.wumpusMorto = false;
		this.status = 'P';
	}
	
	public int executa(String command) {
		if(command.length() != 1) // Comando invalido
			return 2;
		return executa(command.charAt(0));
	}
	
	public int executa(char command) { 
		command = Character.toLowerCase(command);
		int saida = 0;
		if(command == 'w' || command == 's' || command == 'a' || command == 'd') { 
			if(heroi.getFlechaArmada()) 
				pontuacao += -100;
			
			if(heroi.mover(command)) 
				pontuacao += -15;
			else 
				saida = 1;
			
			if(!wumpus.getVivo() && !wumpusMorto) {
				pontuacao += 500;
				wumpusMorto = true;
			}
		}
		else if(command == 'k')
			heroi.armarFlecha();
		
		else if(command == 'c') 
			heroi.pegarOuro();
		
		else if(command == 'q') {
			rodando = false; 
		}
		else // Comando invalido
			saida = 2;
		
		if(!heroi.getVivo()){
			pontuacao += -1000;
			rodando = false;
			status = 'L';
		}
		if(heroi.carregandoOuro() && heroi.getX() == 0 && heroi.getY() == 0) {
			pontuacao += 1000;
			status = 'W';
			rodando = false;
		}
		return saida;
	}
	
	public void setNome(String nome) {
		this.nomeJogador = nome;
	}
	
	public boolean getRodando() { 
		return rodando;
	}
	
	public String getNome() {
		return nomeJogador;
	}
	
	public char getStatus() {
		return status;
	}
	
	public int getScore() {
		return pontuacao;
	}
	
	public Heroi getHeroi() {
		return heroi;
	}
	
	public char[][] getCaverna(){ 
		char[][] caverna = heroi.getMapa();
		return caverna;
	}
	
	public void conectaHeroi(Heroi heroi) {
		this.heroi = heroi;
	}
	
	public void conectaWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}
}
