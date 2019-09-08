package processing.test.android;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.app.Activity; 
import android.content.Context; 
import processing.video.*; 

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





boolean buttonPressed = false;
int x, y;
Capture video;

public void touchStarted(){
  println("it has been touched");
}

public void touchMoved(){
}

public void touchEnded(){
}

public void captureEvent(Capture video){
  
}

public void mousePressed(){
   if(mouseX >= 30 && mouseX <= 150 && mouseY >= 30 && mouseY <= 150){
     buttonPressed = true;
   }
}

public void mouseReleased(){
  buttonPressed = false;
}

public void keyPressed(){
  
}


public void setup(){
  background(255,125,255);
  
  textSize(45);
  text("FinPENN: A Financial Hack!", 480,90);
  
  stroke(0);
  textSize(10);
  
  

  rect(620,290,200,200);
  rect(620,490,200,200);
  
  fill(0);
  text("Physical Receipt",680,400);
  text("Digital Invoice",680,600);
}
  public void settings() {  size(1600,800); }
}
