package processing.test.android;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.app.Activity; 
import android.content.Context; 
import processing.video.*; 
import ketai.camera.*; 
import android.os.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class android extends PApplet {

//package aws.example.rekognition.image;








//ResponseHeaderOverrides override = new ResponseHeaderOverrides();override.setContentType( "image/jpeg" );

//GeneratePresignedUrlRequest urlRequest =    new GeneratePresignedUrlRequest( Constants.getPictureBucket(), Constants.PICTURE_NAME );// Added an hour's worth of milliseconds to the current time.urlRequest.setExpiration(    new Date( System.currentTimeMillis() + 3600000 ) );urlRequest.setResponseHeaders( override );

int[] sprinkleColors = new int[70];
float[][] cords = new float[70][2];



public void onSavePhotoEvent(String filename)  
{  
    cam.addToMediaLibrary(filename);  
} 

KetaiCamera cam;
PImage img;

boolean topBox = false;
boolean bottomBox = false;
boolean backBox = false;

String directory;

public void onCameraPreviewEvent()
{
  cam.read();
}

//mousePressed() function will be called when the screen is pressed(doesnt need to be directly called by programmer)
public void mousePressed()
{
  if (cam.isStarted())
  {
    cam.setPhotoSize(1280,840);
    cam.savePhoto();
    cam.stop();
  }
  else
    cam.start();
}
public void keyPressed() {
  if (key == CODED) {
    if (keyCode == MENU) {
      if (cam.isFlashEnabled())
        cam.disableFlash();
      else
        cam.enableFlash();
    }
  }
}

public void defaultPage(){
  background(255,125,255);
  textSize(45);
  text("FinPENN: A Financial Hack!", 290,90);
  text("PennApps XX", 360,150);
  
  stroke(0);
  textSize(30);
  
  rect(320,770,400,400);
  rect(320,1170,400,400);
  
  fill(255,0,0);
  rect(320, 1670, 400, 400);
  
  fill(0);
  text("Physical Receipt",360,890);
  text("Digital Invoice",360,1300);
  text("GO TO DEFAULT SCREEN",360,1770);
  fill(255);
}

public void whichPress(){
  for(int i = 0; i < touches.length; i++){
    if(touches[i].y <= 1170 && (touches[i].x >= 320 && touches[i].x <= 720)){
      topBox = true;
      bottomBox = false;
      backBox = false;
    } else if(touches[i].y > 1170 && touches[i].y < 1600 && (touches[i].x >= 320 && touches[i].x <= 720) ){
      bottomBox = true;
      topBox = false;
      backBox = false;
    } else {
      backBox = true;
      bottomBox = false;
      topBox = false;
    }
  }
}


public void setup(){
  background(255);
  requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", "checkPermission");
  directory = new String(Environment.getExternalStorageDirectory().getAbsolutePath());
 
  //trying to put sprinkles as a UI theme(since 20th birthday of PennApps)
  //color is custom data type which takes three parameters: r, g, and b
  for(int i = 0; i < 30; i++){
    cords[i][0] = random(0,1600);
    cords[i][1] = random(0,770);
    sprinkleColors[i] = color((int)(random(0,255)),(int)(random(0,255)),(int)(random(0,255)));
  }
  for(int i = 0; i < 20; i++){
    cords[10+i][0] = random(0,320);
    cords[10+i][1] = random(0,1600);
    sprinkleColors[10+i] = color((int)random(0,255),(int)(random(0,255)),(int)(random(0,255)));
  }
  for(int i = 0; i < 20; i++){
    cords[15+i][0] = random(720,1600);
    cords[15+i][1] = random(0,1600);
    sprinkleColors[15+i] = color((int)(random(0,255)),(int)(random(0,255)),(int)(random(0,255)) );
  }
  
  textSize(45);
  orientation(PORTRAIT);
  imageMode(CENTER);
  
  cam = new KetaiCamera(this, 1800, 1100, 24);
  cam.setSaveDirectory(directory);
  fill(255,125,255);
  
  fill(0);
  text("PRESS TO TAKE PICTURE",1090,1230);
  fill(255);
  text("FinPENN: A Financial Hack!", 480,90);
}

public void draw(){
  background(255);
  defaultPage();
  whichPress();
  if(topBox){
    
    background(255);
    fill(255);
    text("GO TO DEFAULT SCREEN",360,1770);
    fill(0);
    text("GO TO DEFAULT SCREEN",360,1770);
    fill(255,0,0);
    rect(320, 1670, 400, 400);
  
  fill(0);
    image(cam,width/2,height/2);
    //orientation(LANDSCAPE);
  } else if(bottomBox){
    orientation(PORTRAIT);
  } else if(backBox || (!topBox && !bottomBox) ){
    orientation(PORTRAIT);
    defaultPage();
       for(int i = 0; i < 70; i++){
    fill(sprinkleColors[i]);
    ellipse(cords[i][0], cords[i][1], 10,10);
    //println(cords[i][0] + " " + cords[i][1]);
  }
  }
  
 
  
}

public void checkPermission(boolean wasPermissionGranted){
    if (wasPermissionGranted)
        println("Hooray! I can now write to the local file system!");
    else 
        println("Oh no! I was not granted write permission =(");
}
  public void settings() {  size(1940,2240); }
}
