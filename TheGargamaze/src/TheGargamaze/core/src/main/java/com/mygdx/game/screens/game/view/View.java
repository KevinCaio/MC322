package com.mygdx.game.screens.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.app.IGameControl;
import com.mygdx.game.elements.blackhole.IVisualBH;
import com.mygdx.game.elements.player.IVisualPlayer;
import com.mygdx.game.habilities.IVisualHability;
import com.mygdx.game.screens.game.space.Space;

public class View implements Screen, IViewSwitchHability, IViewCommand {
    public static int size;

    private ShapeRenderer shapeRenderer;
    private ViewCell[][] cells;
    private OrthographicCamera camera;
    private Viewport viewport;
    protected SpriteBatch batch;
    
    private float thX1, thY1, thX2, thY2, thopacity;
    private Array<Texture>  th1, th2;
    private boolean thAnimating = false;
    
    protected boolean fadingIn = true;
    private float fadeInDuration = 1f;
    protected boolean fadingOut = false;
    private float fadeOutDelay = 2f;
    private float fadeOutDuration = 4f;
    private float fadeOpacity = 1;
    private float fadeTime = 0;
    private Texture imgBlack;
    protected IGameControl game;
    
    private IVisualPlayer Vcase;
    private IVisualPlayer Vtars;
    
    private Texture tarsCommands;
    private Texture caseCommands;
    protected boolean showCommands;
    
    private Array<Texture> crystals    = new Array<Texture>();   
    private Array<Texture> visionHab   = new Array<Texture>();
    private Array<Texture> ghostHab    = new Array<Texture>();
    private Array<Texture> teleportHab = new Array<Texture>();
    
    private Rectangle bhPos;
    private Texture imgBlackHole;

    public View() {
        loadImages();
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();
        ViewCell.loadImages();
        View.size = Space.size;
                
        showCommands = false;
        
        cells = new ViewCell[size][size];
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y] = new ViewCell(x,y);
    }
    
    private void loadImages() {
        String path = "images/screens/";
        imgBlack     = new Texture(Gdx.files.internal(path + "black.png"));
        caseCommands = new Texture(Gdx.files.internal(path + "redCommands.png"));
        tarsCommands = new Texture(Gdx.files.internal(path + "blueCommands.png"));
        
        imgBlackHole = new Texture(Gdx.files.internal("images/elements/blackHole.png"));
        
        path = "images/crystals/";
        crystals.add(new Texture(Gdx.files.internal(path + "crystalYellow1.png")));   
        crystals.add(new Texture(Gdx.files.internal(path + "crystalGreen1.png")));
        crystals.add(new Texture(Gdx.files.internal(path + "crystalViolet1.png")));
        crystals.add(new Texture(Gdx.files.internal(path + "crystalBlue.png")));
        crystals.add(new Texture(Gdx.files.internal(path + "crystalRed1.png")));
        crystals.add(new Texture(Gdx.files.internal(path + "crystalBlue2.png")));
        crystals.add(new Texture(Gdx.files.internal(path + "crystalRed2.png")));

        path = "images/habilities/";
        visionHab.add(new Texture(Gdx.files.internal(path + "flashlightBlock.png"))); //block
        visionHab.add(new Texture(Gdx.files.internal(path + "flashlightNormal.png"))); //normal
        visionHab.add(new Texture(Gdx.files.internal(path + "flashlightCooldown.png"))); //cooldown
        visionHab.add(new Texture(Gdx.files.internal(path + "flashlightRunning.png"))); //running
            
        ghostHab.add(new Texture(Gdx.files.internal(path + "ghostBlock.png"))); //block
        ghostHab.add(new Texture(Gdx.files.internal(path + "ghostNormal.png"))); //normal
        ghostHab.add(new Texture(Gdx.files.internal(path + "ghostCooldown.png"))); //cooldown
        ghostHab.add(new Texture(Gdx.files.internal(path + "ghostRunning.png"))); //running
            
        teleportHab.add(new Texture(Gdx.files.internal(path + "troca2Block.png"))); //block
        teleportHab.add(new Texture(Gdx.files.internal(path + "troca2Normal.png"))); //normal
        teleportHab.add(new Texture(Gdx.files.internal(path + "troca2Cooldown.png"))); //cooldown
        teleportHab.add(new Texture(Gdx.files.internal(path + "troca2Running.png"))); //running 
    }
    
    public void connect(IVisualPlayer Vcase, IVisualPlayer Vtars) {
        this.Vcase = Vcase;
        this.Vtars = Vtars;
    }
    
    public void connect(IGameControl game) {
        this.game = game;
    }
    
    public void createBH(IVisualBH bh) {
        float posX   = 160 + bh.getX()*ViewCell.size;
        float posY   =       bh.getY()*ViewCell.size;
        float lenght = bh.getSize()*ViewCell.size;
        bhPos = new Rectangle(posX, posY, lenght, lenght);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); //cor do fundo
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        
        drawBlackHole();
        drawMap();
        drawStatus(Vtars, 0);
        drawStatus(Vcase, 160+480);
        drawTeleportHiders();
        drawCrystals();
        drawHabilities();
        
        if(showCommands) drawCommands();
        
        if(fadingIn || fadingOut) {
            drawFade();
            updateFade(delta);
        }
    
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.
    }

    private void drawMap() {
        batch.begin();
        
        for(int x = 0;x < size;x++) {
            for(int y = 0;y < size;y++) {
                batch.setColor(1f,1f,1f,1f);
                ViewCell aux = cells[x][y];
                
         	   	for(Texture texture: aux.getTexture()) {
                    batch.draw(texture, aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
         	   	}
         	   	
         	   	// Adjust Clarity
                batch.setColor(1f,1f,1f, 1 - aux.getClarity());
                batch.draw(ViewCell.getDefaultTexture(), aux.getX(), aux.getY(),
                                                        ViewCell.size, ViewCell.size);
            }
        }
        
        batch.setColor(1f,1f,1f,1f);
        batch.end();
    }
    
    private void drawBlackHole() {
        if(bhPos == null) return;
        batch.begin();
        batch.setColor(1f,1f,1f,1f);
        batch.draw(imgBlackHole, bhPos.x, bhPos.y, bhPos.width, bhPos.height);
        batch.end();
    }
    
    private void drawStatus(IVisualPlayer player, int xRef) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(xRef+18, 360, 160-36, 24);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(xRef+20, 360+2, 160-40, 20);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(xRef+22, 360+4, player.timeRemaining()*(160-44), 16);
        shapeRenderer.end();
    }
    
    public void showCommands(boolean show) {
    	showCommands = show;
    }
    
	private void drawCommands() {
		float width = viewport.getWorldWidth();
		float height = viewport.getWorldHeight();

		batch.begin();
		
		batch.draw(caseCommands, 0, height*1/10 , (367/3)+22, (471/3)+22);
        batch.draw(tarsCommands, (width*5/6)-12, height*1/10, (367/3)+22, (471/3)+22);
		
	    batch.end();
	}
	
	private void drawCrystals() {
		float width = viewport.getWorldWidth();
		float rFirstCrystal = (width*5/6)-9;
		float size = 3/2*(15.48f) + 8;
		boolean bigCrystalCase = false;
		boolean bigCrystalTars = false;

		float posY = 360-size-10;
		float posYBig = 360-3*size-13;
		
		Array<Character> caseInventory = Vcase.getInventory();
		Array<Character> tarsInventory = Vtars.getInventory();
		
		batch.begin();
		
		int i = 0;
 	   	for(Character character: caseInventory) {
 	   		batch.draw(crystals.get(Character.getNumericValue(character)), rFirstCrystal+i*size, posY, size, size); 
 	   		i++;
 	   		if(character == '3') bigCrystalCase = true;
 	   	}
 	   	
 	   	int j = 0;
	   	for(Character character: tarsInventory) {
 	   		batch.draw(crystals.get(Character.getNumericValue(character)), 18+j*size, posY, size, size);
	   		j++;
	   		if(character == '4') bigCrystalTars = true;
	   	}
	   	if(bigCrystalCase) batch.draw(crystals.get(3), rFirstCrystal+size+12, posYBig, size*2, size*2); 
	   	else batch.draw(crystals.get(5), rFirstCrystal+size+12, posYBig, size*2, size*2);     //ghost
	   	
	   	if(bigCrystalTars) batch.draw(crystals.get(4), 18+size+12, posYBig, size*2, size*2);
	   	else batch.draw(crystals.get(6), 18+size+12, posYBig, size*2, size*2);               //ghost
	   	
	   	batch.end();
	}
	
	private void drawHabilities() {
		float width = viewport.getWorldWidth();
		
		float rFirstCrystal = (width*5/6) + 12;
		
		float size = 3/2*(15.48f)+8; 
		
		Array<IVisualHability> caseHabilities = Vcase.getHabilities();
		Array<IVisualHability> tarsHabilities = Vtars.getHabilities();

		float posY = 360-5*size; 
		
		batch.begin();	
		
	   	int k = 0;
	   	for(IVisualHability hability: caseHabilities) {
	   		Array<Texture> auxCase = null;
	   		if(hability.type() == 'V') auxCase = visionHab;
	   		else if(hability.type() == 'T') auxCase = teleportHab;
	   		else if(hability.type() == 'P') auxCase = ghostHab;

	   		if(!hability.unlocked()) batch.draw(auxCase.get(0), rFirstCrystal+k*size, posY, size, size); //block
	   		else {
	   			if(hability.isRunning()) batch.draw(auxCase.get(3), rFirstCrystal+k*size, posY, size, size); //rodando
	   			else if(hability.onCooldown()) batch.draw(auxCase.get(2), rFirstCrystal+k*size, posY, size, size);//cooldown
	   			else batch.draw(auxCase.get(1), rFirstCrystal+k*size, posY, size, size); //normal
	   		}
	   		k++;
	   	}
		
	   	int l = 0;
		for(IVisualHability hability: tarsHabilities) {
			Array<Texture> auxTars = null;
			
	   		if(hability.type() == 'V') auxTars = visionHab;
	   		else if(hability.type() == 'T') auxTars = teleportHab;
	   		else if(hability.type() == 'P') auxTars = ghostHab;    

	   		if(!hability.unlocked())  batch.draw(auxTars.get(0), 40+l*size, posY, size, size); //bloqueada
	   		else {
	   			if(hability.isRunning())  batch.draw(auxTars.get(3), 40+l*size, posY, size, size); //rodando
	   			else if(hability.onCooldown()) batch.draw(auxTars.get(2), 40+l*size, posY, size, size);//cooldown
	   			else batch.draw(auxTars.get(1), 40+l*size, posY, size, size); //normal
	   		}
	   		l++;
	   	}		
		batch.end();
	}

    public void dispose() {
    	ViewCell.dispose();
    	imgBlack.dispose();
    	imgBlackHole.dispose();
    	batch.dispose();
    	
    	caseCommands.dispose();
    	tarsCommands.dispose();
    	for(Texture texture: crystals) {
    		texture.dispose();
    	}
    	for(Texture texture: visionHab) {
    		texture.dispose();
    	}
    	for(Texture texture: ghostHab) {
    		texture.dispose();
    	}
    	for(Texture texture: teleportHab) {
    		texture.dispose();
    	}
    }
    
    public ViewCell getCell(int x, int y) {
        return cells[x][y];
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void drawTeleportHiders() {
        if(!thAnimating) return;
        
        batch.begin();
        batch.setColor(1f,1f,1f,thopacity);
        
     	for(Texture texture: th1)
 	   		batch.draw(texture, thX1, thY1, ViewCell.size, ViewCell.size);
 	   		
     	for(Texture texture: th2)
 	   		batch.draw(texture, thX2, thY2, ViewCell.size, ViewCell.size);

        batch.setColor(1f,1f,1f,1f);
        batch.end();
    }

    @Override
    public void startAnimation(int x1, int y1, int x2, int y2) {
        thAnimating = true;
        
        thX1 = x1*ViewCell.size + 160;
        thY1 = y1*ViewCell.size;
        thX2 = x2*ViewCell.size + 160;
        thY2 = y2*ViewCell.size;
        
        th1 = cells[x1][y1].getTexture();
        th2 = cells[x2][y2].getTexture();
        
        thopacity = 0;
    }

    @Override
    public void setOpacity(float opacity) {
        thopacity = opacity;
    }

    @Override
    public void stopAnimation() {
        thAnimating = false;
    }

    @Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }
    
    public void fadeOut() {
        fadingOut = true;
    }
    
    private void drawFade() {
        batch.begin();
        batch.setColor(1f,1f,1f,fadeOpacity);
        batch.draw(imgBlack, 0, 0, 800, 480);
        batch.end();
    }

    private void updateFade(float delta) {
        fadeTime += delta;
        
        if(fadingOut) {
            if(fadeTime>fadeOutDelay)
                fadeOpacity = (fadeTime-fadeOutDelay)/(fadeOutDuration-fadeOutDelay);
                
            if(fadeTime>fadeOutDuration)
                game.gameOverContinue();
        }
        else if(fadingIn) {
            fadeOpacity = 1 - fadeTime/fadeInDuration;
                
            if(fadeTime>fadeInDuration) {
                fadingIn = false;
                fadeTime = 0;
            }
        }
    }
    
    public void unproject(Vector3 touchPos) {
        viewport.unproject(touchPos);
    }
}