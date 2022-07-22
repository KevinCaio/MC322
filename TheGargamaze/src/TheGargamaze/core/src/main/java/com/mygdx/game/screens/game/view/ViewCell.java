package com.mygdx.game.screens.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.screens.game.cell.IVisualCell;
import com.mygdx.game.screens.game.space.Space;

public class ViewCell implements IUpdate {
    public static float size;
    
	private static Texture imgCase; 
	private static Texture imgTars;	
	private static Texture imgCasePhantom; 
	private static Texture imgTarsPhantom; 
    private static Texture imgWall;
    private static Texture imgHardWall;
    private static Texture imgGround;
    private static Texture imgDark;
    private static Texture imgGateClosed; 
    private static Texture imgGateOpen;  
    private static Texture imgGateHardClosed;
    private static Texture imgGateHardOpen; 
    private static Texture imgButtonFree; 
    private static Texture imgButtonPressed;
    private static Texture imgCrystalYellow;
    private static Texture imgCrystalBlue;
    private static Texture imgCrystalRed;
    private static Texture imgCrystalGreen;
    private static Texture imgCrystalViolet;
    private static Texture imgCrystalOrange;
    private static Texture imgBlueBG;
    private static Texture imgRedBG;
    private static Texture imgButtonFreeFrame;
    private static Texture imgButtonPressedFrame;
    private static Texture imgDarkness;
        
    private Array<Texture> textures = new Array<Texture>();

    private float x;
    private float y;
    private float clarity;
    private IVisualCell cell;
    
    public ViewCell(int x, int y) {
        this.x = (x*size) + 160;
        this.y = y*size;
        ViewCell.size = 480f/Space.size;
    }
    
    public void connect(IVisualCell cell) {
        this.cell = cell;
    }
    
    public static void loadImages() {
        String path = "images/elements/";
        imgCase     			  = new Texture(Gdx.files.internal(path + "case.png"));	//check
        imgTars     			  = new Texture(Gdx.files.internal(path + "tars.png"));   //check
        imgCasePhantom			  = new Texture(Gdx.files.internal(path + "casePhantom.png"));   //check
        imgTarsPhantom     		  = new Texture(Gdx.files.internal(path + "tarsPhantom.png"));   //check
        imgWall        			  = new Texture(Gdx.files.internal(path + "wall.png"));     //check
        imgHardWall        		  = new Texture(Gdx.files.internal(path + "hardWall.png"));    //check
        imgGround   			  = new Texture(Gdx.files.internal(path + "ground.png"));   //check
        imgDark	        		  = new Texture(Gdx.files.internal(path + "dark.png"));	//check
        imgGateClosed 		      = new Texture(Gdx.files.internal(path + "gate.png"));	//check
        imgGateOpen 	          = new Texture(Gdx.files.internal(path + "gateOpen.png"));   //check
        imgGateHardClosed 		  = new Texture(Gdx.files.internal(path + "hardGate.png"));	//check
        imgGateHardOpen 	      = new Texture(Gdx.files.internal(path + "hardGateOpen1.png"));   
        imgButtonFree 			  = new Texture(Gdx.files.internal(path + "buttonFree.png")); //check
        imgButtonPressed		  = new Texture(Gdx.files.internal(path + "buttonPressed.png"));  //check
        imgDarkness               = new Texture(Gdx.files.internal(path + "darkness.png"));	//check
        imgBlueBG				  = new Texture(Gdx.files.internal(path + "fundoAzul1.png"));	//check 
        imgRedBG				  = new Texture(Gdx.files.internal(path + "fundoVermelho1.png"));	//check
        imgButtonFreeFrame		  = new Texture(Gdx.files.internal(path + "molduraApagada.png"));	//check
        imgButtonPressedFrame	  = new Texture(Gdx.files.internal(path + "molduraAcesa.png"));	//check

        path = "images/crystals/";
        imgCrystalYellow          = new Texture(Gdx.files.internal(path + "crystalYellow1.png"));
        imgCrystalBlue            = new Texture(Gdx.files.internal(path + "crystalBlue.png"));
        imgCrystalRed             = new Texture(Gdx.files.internal(path + "crystalRed1.png"));
        imgCrystalGreen           = new Texture(Gdx.files.internal(path + "crystalGreen1.png"));
        imgCrystalViolet          = new Texture(Gdx.files.internal(path + "crystalViolet1.png"));
        imgCrystalOrange          = new Texture(Gdx.files.internal(path + "crystalOrange1.png"));
        
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgCasePhantom.dispose();
        imgTarsPhantom.dispose();
        imgWall.dispose();
        imgHardWall.dispose();
        imgGround.dispose();
        imgDark.dispose();
        imgButtonPressed.dispose();
        imgButtonFree.dispose();
        imgGateClosed.dispose();
        imgGateOpen.dispose();
        imgGateHardClosed.dispose();
        imgGateHardOpen.dispose();
        imgCrystalYellow.dispose();
        imgCrystalBlue.dispose();
        imgCrystalRed.dispose();
        imgCrystalGreen.dispose();
        imgCrystalViolet.dispose();
        imgCrystalOrange.dispose();
        imgBlueBG.dispose();
        imgRedBG.dispose();
        imgButtonFreeFrame.dispose();
        imgButtonPressedFrame.dispose();
    }
    
	public void update() {
			   
	   int nbElements = cell.nElements();
	   
	   textures.clear();
	   textures.add(imgGround); 
	   if(!cell.visible()) {  
		   textures.add(imgDark);
	   }
	   else {
		   for(int i = 0;i < nbElements;i++) {
			   if(cell.visual(i).type() == 'B') {
				   if(cell.visual(i).variation() == 'C') { //case
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgBlueBG);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
						   textures.add(imgBlueBG);
			        	   textures.add(imgButtonFree);
			           }
				   }
				   else if(cell.visual(i).variation() == 'T') { //tars
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgRedBG);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
			        	   textures.add(imgRedBG);
			        	   textures.add(imgButtonFree);
			           }
				   }
				   else {
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
			        	   textures.add(imgButtonFree);
			           }
				   }
			   }
			   else if(cell.visual(i).type() == 'G') {
				   if(cell.visual(i).variation() == 'H') { //hard
					   if(cell.visual(i).state() == 'c') textures.add(imgGateHardClosed); 
					   else if(cell.visual(i).state() == 'o') textures.add(imgGateHardOpen);  
				   }
				   else {  //normal
					   if(cell.visual(i).state() == 'c') textures.add(imgGateClosed);  
					   else if(cell.visual(i).state() == 'o') textures.add(imgGateOpen); 
				   }
			   }
			   else if(cell.visual(i).type() == 'W')
			   		if(cell.visual(i).variation() == 'H') textures.add(imgHardWall);
			   		else  textures.add(imgWall);
               else if(cell.visual(i).type() == 'D') textures.add(imgGround);
			   else if(cell.visual(i).type() == 'C') {
				   if(cell.visual(i).variation() == '3')textures.add(imgCrystalBlue);
				   else if(cell.visual(i).variation() == '4')textures.add(imgCrystalRed);
				   else if(cell.visual(i).variation() == '0')textures.add(imgCrystalYellow);
				   else if(cell.visual(i).variation() == '2')textures.add(imgCrystalViolet);
				   else if(cell.visual(i).variation() == '1')textures.add(imgCrystalGreen);
			   }
			   else if(cell.visual(i).type() == 'P') {
				   if(cell.visual(i).variation() == 'C') {
					   if(cell.visual(i).state() == 'P') textures.add(imgCasePhantom);
					   else textures.add(imgCase);
				   }
				   if(cell.visual(i).variation() == 'T') {
					   if(cell.visual(i).state() == 'P') textures.add(imgTarsPhantom);
					   else textures.add(imgTars);
				   }
			   }
			   else if(cell.visual(i).type() == 'Z')
			       textures.clear();
		   }
		   if(nbElements > 0 && cell.visual(0).type() == 'D')
               textures.add(imgDarkness);
		   
		   clarity = cell.clarity();
	   }
	}
	
	public static Texture getDefaultTexture() {
	    return imgDark;
	}
	
	public Array<Texture> getTexture() {
	    return textures;
	}
	
	public float getX() {
        return x;
    }
	
	public float getY() {
        return y;
    }
	
    public float getClarity() {
        return clarity;
    }
}
