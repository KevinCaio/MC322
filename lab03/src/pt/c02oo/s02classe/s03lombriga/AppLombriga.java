package pt.c02oo.s02classe.s03lombriga;

public class AppLombriga {

   public static void main(String[] args) {
      Toolkit tk = Toolkit.start();
      
      String lombrigas[] = tk.recuperaLombrigas();
      
      String representacao;
      
      for(int j=0;j < lombrigas.length;j++) {
    	  
    	  char[] charLombrigas = lombrigas[j].toCharArray();
    	  
    	  int tamAquario = (charLombrigas[0] - '0') *10 + (charLombrigas[1] - '0'); 
          int tamLombriga = (charLombrigas[2] - '0') *10 + (charLombrigas[3] - '0'); 
          int posCabeca = (charLombrigas[4] - '0') *10 + (charLombrigas[5] - '0'); 
          
          String movimentos = "";
          
          AquarioLombriga aquario = new AquarioLombriga(tamAquario, tamLombriga, posCabeca);
          
          for(int c=6;c < lombrigas[j].length();c++) {
        	  movimentos += charLombrigas[c];
          }
          
          Animacao moves = new Animacao(movimentos);
    	  
    	  tk.gravaPasso("=====");
    	  representacao  = moves.apresenta(aquario);
    	  tk.gravaPasso(representacao);
    	  
	      for(int i=0; i < moves.tamanho;i++) {
	    	  moves.passo(aquario);
	    	  representacao  = moves.apresenta(aquario);
	    	  tk.gravaPasso(representacao);
	      }
      }
      tk.stop();
         
      
      //rever o teste 2, o q fazer qndo ela for crescer e estiver na borda??
      //minha ideia: andar 1 e crescer. So ver se o prof n previu esse caso ja
   }

}
