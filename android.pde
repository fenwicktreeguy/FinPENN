//package aws.example.rekognition.image;

import android.app.Activity;
import android.content.Context;
import processing.video.*;
import ketai.camera.*;


void onSavePhotoEvent(String filename)  
{  
    cam.savePhoto(filename);
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
    onSavePhotoEvent("receipt1.png");
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
  text("PennApps XX", 310,120);
  
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
  for(int i = 0; i < 10; i++){
    fill(color(random(0,255),random(0,255),random(0,255)));
    ellipse((float)(random(0,1940)), (float)(random(0,1170)), 10,10);
  }
  for(int i = 0; i < 5; i++){
    fill(color(random(0,255),random(0,255),random(0,255)));
    ellipse((float)(random(0,320)), (float)(random(0,1240)),10,10);
  }
  for(int i = 0; i < 5; i++){
    fill(color(random(0,255),random(0,255),random(0,255)));
    ellipse((float)(random(1170,1940)), (float)(random(0,1240)),10,10);
  }
  //File file = new File(context.getFilesDir(), "receipt1.png");
  size(1400,2040);
  textSize(45);
  orientation(PORTRAIT);
  imageMode(CENTER);
  cam = new KetaiCamera(this, 800, 800, 24);
  fill(255,125,255);
  
  fill(0);
  text("PRESS TO TAKE PICTURE",1090,1230);
  fill(255);
  text("FinPENN: A Financial Hack!", 480,90);
}

void draw(){
  background(255);
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
