package pt.c40task.l05wumpus;
import java.util.ArrayList;
import pt.c40task.l05wumpus.componentes.Heroi;
import pt.c40task.l05wumpus.componentes.Componente;

public class Sala {
	private ArrayList<Componente> componentes;
	private boolean descoberta;
	
	public Sala() {
		this.componentes = new ArrayList<Componente>();
		this.descoberta = false;
	}
	
	public boolean inserirCompInicial(Componente aInserir) { 
		boolean saida = true;
		for(int i = 0;i < this.componentes.size();i++) { 
			if((aInserir.getSimbolo() == 'O' && componentes.get(i).getSimbolo() == 'W') 
			|| (aInserir.getSimbolo() == 'W' && componentes.get(i).getSimbolo() == 'O')) {
				saida = false;
			}
			else if((aInserir.getSimbolo() == 'O' && componentes.get(i).getSimbolo() == 'B')
				 || (aInserir.getSimbolo() == 'B' && componentes.get(i).getSimbolo() == 'O')) {
				saida = false;
			}
			else if((aInserir.getSimbolo() == 'W' && componentes.get(i).getSimbolo() == 'B')
				 || (aInserir.getSimbolo() == 'B' && componentes.get(i).getSimbolo() == 'W')) {
				saida = false;
			}
			else if((aInserir.getSimbolo() == 'W' && componentes.get(i).getSimbolo() == 'P') 
				 || (aInserir.getSimbolo() == 'P' && componentes.get(i).getSimbolo() == 'W')){
				saida = false;
			}
		}
		if(saida) {
			inserir(aInserir);
		}
		return saida;
	}
	
	public void inserir(Componente aInserir) {
		if(aInserir.getSimbolo() == 'P') // verifica se eh um heroi entrando na sala
			this.descoberta = true;
		this.componentes.add(aInserir);
	}
	
	public void remover(Componente aRemover) {
		for(int i = 0;i < this.componentes.size();i++)
			if(this.componentes.get(i) == aRemover)
				this.componentes.remove(i);
	}
	
	public void interagir(Heroi heroi) {  
		for(int i=0;i < componentes.size();i++)
			componentes.get(i).interagir(heroi);
	}
	
	public boolean getDescoberta() {
		return descoberta;
	}
	
	public char getSimbolo() {
		if(!descoberta) return '-';
		int maior = -1;
		char caractMaior = '#'; // # tem prioridade -1
		
		for(int i=0;i < componentes.size();i++)
			if(componentes.get(i).getPrioridade() > maior) {
				maior = componentes.get(i).getPrioridade();
				caractMaior = componentes.get(i).getSimbolo();
			}
		return caractMaior;
	}
}
