// This is where the assets for the game are stored

package com.mygame.gdx.drop;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
// add this import and NOT the one in the standard library
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.utils.TimeUtils;



public class Drop extends ApplicationAdapter {
   private Texture fruitImage1;
   private Texture fruitImage2;
   private Texture bombImage;
   private Texture basketImage;
  

 
   private OrthographicCamera camera;
  
   private SpriteBatch batch;
   private Rectangle basket;
  
   //The Array class is a libGDX utility class to be used instead of standard Java 
  // collections like ArrayList. The problem with the latter is that they produce garbage 
 //  in various ways
   private Array<Rectangle> fruits;
  
   // Time is stored in nanoseconds
   private long lastFruitTime;
   
   
   
   
   
   
   @Override
   public void create() {
      // Using the create() we access the and generate the images we need
      fruitImage1 = new Texture(Gdx.files.internal("Apple.png"));
      fruitImage2 = new Texture(Gdx.files.internal("Orange.png"));
      bombImage = new Texture(Gdx.files.internal("Bomb.png"));
     

  


   
      // Using a camera to see the game
      camera = new OrthographicCamera();
      camera.setToOrtho(false, 800, 480);
     
      basketImage = new Texture(Gdx.files.internal("Basket.png"));
      batch = new SpriteBatch();
      
      basket = new Rectangle();
      basket.x = 800 / 2 - 64 / 2;
      basket.y = 20;
      basket.width = 64;
      basket.height = 64;
      
      fruits = new Array<Rectangle>();
      spawnFruit();
    
   }
   
   @Override
   public void render() {
      ScreenUtils.clear(0, 0, 0.2f, 1);
      camera.update();
      batch.setProjectionMatrix(camera.combined);
      batch.begin();
      batch.draw(basketImage, basket.x, basket.y);
      batch.end();
      
      if(Gdx.input.isTouched()) {
          Vector3 touchPos = new Vector3();
          touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
          camera.unproject(touchPos);
          basket.x = touchPos.x - 64 / 2;
          

           }
      if(TimeUtils.nanoTime() - lastFruitTime > 1000000000) spawnFruit();
      
      for (Iterator<Rectangle> iter = fruits.iterator(); iter.hasNext(); ) {
          Rectangle fruit = iter.next();
          fruit.y -= 200 * Gdx.graphics.getDeltaTime();
          if(fruit.y + 64 < 0) iter.remove();   
          if(fruit.overlaps(basket)) {
               iter.remove();}
       }
      
      batch.begin();
      batch.draw(basketImage, basket.x, basket.y);
      for(Rectangle fruit: fruits) {
         batch.draw(fruitImage1, fruit.x, fruit.y);
         batch.draw(fruitImage2, fruit.x, fruit.y);
       //  batch.draw(bombImage, fruit.x, fruit.y);
        
      }
     
      batch.end();      

       }      
   

   
   // Fruit and bomb generation 
   
   private void spawnFruit() {
	  
	   
	   
	   
	   	  Rectangle fruit = new Rectangle();
	      fruit.x = MathUtils.random(0, 800-64);
	      fruit.y = 480;
	      fruit.width = 64;
	      fruit.height = 64;
	      fruits.add(fruit);
	      lastFruitTime = TimeUtils.nanoTime();
	   }
   
   // Makes the objects go away when they need to 
   @Override
   public void dispose() {
      fruitImage1.dispose();
      basketImage.dispose();
      fruitImage2.dispose();
      bombImage.dispose();
      batch.dispose();
   } 
   
   
   
   
   
}

