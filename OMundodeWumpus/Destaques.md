# Destaques O Mundo de Wumpus

## Método Interagir
```
public abstract void interagir(Heroi heroi);

public void interagir(Heroi heroi) { //brisa
		Impressao.adicionaMensagem("Voce sente uma brisa.");
	}

public void interagir(Heroi heroi) { //buraco
		Impressao.adicionaMensagem("Voce caiu em um buraco.");
		heroi.matar();
	}
```

## Armazenamento de Componentes na Sala
```
this.componentes = new ArrayList<Componente>();
``` 

## 
Este é o destaque pois com essa implementação, cada sala pode receber diversos objetos componentes diferentes e tratá-los como “iguais”, graças ao polimorfismo. Além disso, o método interagir é um destaque, uma vez que ele torna cada objeto Componente responsável por sua própria ação, seja ela a de avisar algo ao herói, como faz o ouro, as brisas e os fedores quando são encontrados, ou de matar o herói, como faz o buraco e o wumpus durante uma possível batalha. Esse método é chamado para todos os objetos dentro de uma sala sempre que o herói entra nela, e caso necessário pode ser chamado novamente pelo herói para executar uma re-interação, por exemplo, como acontece para a coleta do Ouro. Adotada essa estrutura, a expansão para novos componentes fica simples, dado que herdeiros da classe Componente são adicionados naturalmente nas salas e terão um método de interação com o herói, sendo esse específico de acordo com as ações do Componente.
