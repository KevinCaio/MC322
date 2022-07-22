package pt.c40task.l05wumpus;

import java.util.ArrayList;

import pt.c40task.l05wumpus.componentes.Heroi;

public class Impressao {
	public static ArrayList<String> mensagens = new ArrayList<String>();
	
	public static void estado(char[][] caverna, String player, int score) {
		System.out.println(' ');
		for(int y=0;y < 4;y++) 
			for(int x=0;x < 4;x++) 
				System.out.print("" + caverna[y][x] + (x != 3 ? ' ' : '\n'));

		player(player);
		score(score);
	}
	
	public static void mensagem(String mensagem) {
		System.out.println(mensagem);
	}
	
	public static void player(String player) {
		System.out.println("Player: " + player);
	}
	
	public static void score(int score) {
		System.out.println("Score: " + score);
	}
	
	public static void pegarNome() {
		System.out.println("Digite o seu nick: ");
	}
	
	public static void erro(int erro) {
		if(erro == 1)
			Impressao.mensagem("Movimento Invalido!");
		else if(erro == 2)
			Impressao.mensagem("Comando Invalido!");
	}
	
	public static void adicionaMensagem(String msg) {
		mensagens.add(msg);
	}
	
	public static void imprimeMensagens() {
		for(int i = 0; i<mensagens.size(); i++)
			Impressao.mensagem(mensagens.get(i));
		mensagens.clear();
	}

	public static void inventario(Heroi heroi) {
		String inv = "Inventario: ";
		int n = 0;
		
		if(heroi.getTemFlecha()) {
			inv += "Flecha";
			if(heroi.getFlechaArmada())
				inv += " (Armada)";
			n++;
		}
		
		if(heroi.carregandoOuro()) {
			if(n==1) 
				inv += ", ";
			inv += "Ouro";
			n++;
		}
		
		if(n==0) inv += "Vazio";
		inv += ".";
		
		Impressao.mensagem(inv);
	}

	public static void fimDeJogo(char status) {
		if(status == 'W')
			//Impressao.mensagem("Voce Ganhou!! :D");
			Impressao.mensagem("Voce Ganhou =D !!!");
		else if(status == 'L')
			//Impressao.mensagem("Voce Perdeu... D:");
			Impressao.mensagem("Voce Perdeu =( ...");
		else
			Impressao.mensagem("Volte Sempre!");
	}
}
