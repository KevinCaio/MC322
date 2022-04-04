package pt.c02oo.s02classe.s03lombriga;

public class AppLombriga {

   public static void main(String[] args) {
      Toolkit tk = Toolkit.start();
      
      String lombrigas[] = tk.recuperaLombrigas();
      
      //for (int l = 0; l < lombrigas.length; l++)
      //   System.out.println(lombrigas[l]);
  
      String representacao;
      
      for(int j=0;j < lombrigas.length;j++) {
    	  
    	  char[] charLombrigas = lombrigas[j].toCharArray();
    	  
    	  int tamAquario = (charLombrigas[1] - '0') *10 + (charLombrigas[2] - '0'); 
          int tamLombriga = (charLombrigas[3]) *10 + (charLombrigas[4] - '0'); 
          int posCabeca = (charLombrigas[5] - '0') *10 + (charLombrigas[6] - '0'); 
          
          String movimentos = "";
          
          AquarioLombriga aquario = new AquarioLombriga(tamAquario, tamLombriga, posCabeca);
          
          for(int c=7;c < lombrigas[j].length();c++) {
        	  movimentos += charLombrigas[c];
          }
          
          Animacao moves = new Animacao(movimentos);
    	  
    	  tk.gravaPasso("=====");
	      for(int i=0; i < moves.tamanho;i++) {
	    	  representacao  = moves.apresenta(aquario);
	    	  tk.gravaPasso(representacao);
	    	  moves.passo(aquario);
	      }
      }
      tk.stop();
            
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
