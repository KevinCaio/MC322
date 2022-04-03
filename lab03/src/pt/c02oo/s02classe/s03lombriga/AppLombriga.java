package pt.c02oo.s02classe.s03lombriga;

public class AppLombriga {

   public static void main(String[] args) {
      Toolkit tk = Toolkit.start();
      
      String lombrigas[] = tk.recuperaLombrigas();
      
      //for (int l = 0; l < lombrigas.length; l++)
      //   System.out.println(lombrigas[l]);
  
      String representacao;
      
      for(int j=0;j < lombrigas.length;j++) {
    	  
    	  int tamAquario = Integer.parseInt(lombrigas[j][1]) *10 + Integer.parseInt(lombrigas[j][2]); //rever essas posicoes
          int tamLombriga = Integer.parseInt(lombrigas[3]) *10 + Integer.parseInt(lombrigas[4]); //eh uma matriz
          int posCabeca = Integer.parseInt(lombrigas[5])*10 + Integer.parseInt(lombrigas[6]); 
          String movimentos = "";
          
          AquarioLombriga aquario = new AquarioLombriga(tamAquario, tamLombriga, posCabeca);
          
          for(int c=7;c < lombrigas[0].length();c++) {
        	  movimentos += lombrigas[0][c];
          }
          Animacao moves = new Animacao(movimentos);
    	  
    	  tk.gravaPasso("=====");
	      for(int i=0; i < moves.tamanho;i++) {
	    	  representacao  = moves.apresenta(aquario);
	    	  tk.gravaPasso(representacao);
	    	  moves.passo();
	      }
      }
      tk.stop();
      
      //chamar um metodo da animacao que vai pegar o caractere e dps chamar um metodo da aquario pra executar a acao
      //primeiro apresenta a lombriga sem fazer nda(primeira cahamda)
      //executa uma acao e apresenta 
            
      //---------------
      //tk.gravaPasso("=====");
      //tk.gravaPasso("sim");
      //tk.gravaPasso("#O@@@###");
      //tk.gravaPasso("#O@@@@##");
      //tk.gravaPasso("O@@@@###");
      //tk.gravaPasso("@@@@O###");
      //tk.gravaPasso("#@@@@O##");
      
      //tk.stop();
   }

}
