//package aws.example.rekognition.image;

import android.app.Activity;
import android.content.Context;
import processing.video.*;
import ketai.camera.*;
import android.os.Environment;

color[] sprinkleColors = new color[20];
float[][] cords = new float[20][2];



void onSavePhotoEvent(String filename)  
{  
    cam.addToMediaLibrary(filename);  
} 

KetaiCamera cam;
PImage img;

boolean topBox = false;
boolean bottomBox = false;
boolean backBox = false;


void onCameraPreviewEvent()
{
  cam.read();
}

void mousePressed()
{
  if (cam.isStarted())
  {
    cam.savePhoto("b.png");
    cam.stop();
  }
  else
    cam.start();
}
void keyPressed() {
  if (key == CODED) {
    if (keyCode == MENU) {
      if (cam.isFlashEnabled())
        cam.disableFlash();
      else
        cam.enableFlash();
    }
  }
}

void defaultPage(){
  background(255,125,255);
  textSize(45);
  text("FinPENN: A Financial Hack!", 290,90);
  text("PennApps XX", 330,150);
  
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

void whichPress(){
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


void setup(){
  requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", "checkPermission");
  String directory = new String(Environment.getExternalStorageDirectory().getAbsolutePath());
 
  for(int i = 0; i < 10; i++){
    cords[i][0] = random(0,1940);
    cords[i][1] = random(0,1170);
    sprinkleColors[i] = color((int)(random(0,255)),(int)(random(0,255)),(int)(random(0,255)));
  }
  for(int i = 0; i < 5; i++){
    cords[10+i][0] = random(0,320);
    cords[10+i][1] = random(0,1240);
    sprinkleColors[10+i] = color((int)random(0,255),(int)(random(0,255)),(int)(random(0,255)));
  }
  for(int i = 0; i < 5; i++){
    cords[15+i][0] = random(1170,1940);
    cords[15+i][1] = random(0,1240);
    sprinkleColors[15+i] = color((int)(random(0,255)),(int)(random(0,255)),(int)(random(0,255)) );
  }
  //File file = new File(context.getFilesDir(), "receipt1.png");
  size(1400,1940);
  textSize(45);
  orientation(PORTRAIT);
  imageMode(CENTER);
  
  cam = new KetaiCamera(this, 800, 800, 24);
  cam.setSaveDirectory(directory);
  cam.setPhotoSize(1280,740);
  fill(255,125,255);
  
  fill(0);
  text("PRESS TO TAKE PICTURE",1090,1230);
  fill(255);
  text("FinPENN: A Financial Hack!", 480,90);
}

void draw(){
  background(255);
  for(int i = 0; i < 20; i++){
    color(sprinkleColors[i]);
    ellipse(cords[i][0], cords[i][1], 20,20);
    //println(cords[i][0] + " " + cords[i][1]);
  }
  
  defaultPage();
  whichPress();
  if(topBox){
    
    background(255);
    fill(255);
    text("GO TO DEFAULT SCREEN",360,1770);
    fill(0);
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
  }
}

void checkPermission(boolean wasPermissionGranted){
    if (wasPermissionGranted)
        println("Hooray! I can now write to the local file system!");
    else 
        println("Oh no! I was not granted write permission =(");
}
