import com.onlylemi.processing.android.capture.*;
import android.app.Activity;
import android.content.Context;
import processing.video.*;
import ketai.data.*;
import com.onlylemi.processing.android.capture.*;
import ketai.camera.*;



boolean topBox = false;
boolean bottomBox = false;
boolean lastPressedKey = false;
HashMap<String,Double> priceComp = new HashMap<String,Double>();


import ketai.data.*;


public KetaiSQLite db = new KetaiSQLite(this);
PImage img;



void setup() {
  size(1600,1600);
  //img = loadImage("jeff.jpg");
  
  for(char a = '1'; a <= '1000'; a++){
    String res = "TABLE" + a;
    String e = "CREATE TABLE (res) (" + "id INTEGER,"+ "product TEXT, price INTEGER)";
    if(!db.tableExists("TABLE1")){
      db.execute(e);
    }
  }
  
  for(char i = '1'; i < '1000'; i++){
    String res = "TABLE" + i;
    if(!db.execute("INSERT into (res) ('product', 'price') " + " VALUES('whole_foods" + "','" + 96 + )" )) {
      println("FAILURE");
    } else {
      println(db.getRecordCount( ("TABLE" + i) );
    }
              
     /*
  for(char a = '1'; a <= '1000'; a++){
    String res = "TABLE" + a;
    String e = "CREATE TABLE (res) (" + "id INTEGER,"+ "product TEXT, price INTEGER)";
    if(!db.tableExists("TABLE1")){
      db.execute(e);
    }
  }
  */
  
  /*
  for(char i = '1'; i < '1000'; i++){
    String res = "TABLE" + i;
    if(!db.execute("INSERT into (res) ('product', 'price') " + " VALUES('whole_foods" + "','" + 96 + )" )) {
      println("FAILURE");
    } else {
      println(db.getRecordCount( ("TABLE" + i) );
    }
    */
    
 }
 
  

  
 


//will graph expenses based on values stored in SQL database
void graphExpenses(){
  //double total = 0;
}

//will give general statistics regarding the entered data and past stored data
void generalStatistics(){
  
}


void defaultPage(){
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
  fill(255);
}


void mousePressed(){
  if(mouseY <= 490 && (mouseX >= 620 && mouseX <= 820) ){
    println("crunch");
    topBox = true;
    bottomBox = false;
  } else if(mouseY > 490 && (mouseX >= 620 && mouseX <= 820)) {
    println("cronch");
    bottomBox = true;
    topBox = false;
  }
}

void keyPressed(){
  if(keyCode == ENTER){
    topBox = false;
    bottomBox = false;
    defaultPage();
  }
}

void draw(){
  background(0);
  //image(img, 0,0);
  color c = get(100,100);
  fill(c);
  image(img,0,0);
  rect(0,0,600,600);
  
 
  
  if(topBox){
     background(255,125,255);
     textSize(30);
     text("INSERT RESPONSE FOR A PHYSICAL RECEIPT RESPONSE",450,450);
  } else if(bottomBox){
    background(255,125,255);
    textSize(30);
    text("INSERT RESPONSE FOR A DIGITAL INVOICE",450,450);
  } else {
    defaultPage();
  }
  
}


void f(KetaiSQLite db){
  if(db.connect()){
    String q = "INSERT INTO TABLE1(1, 'John')";
    if(!db.query(q)){
       println("a");
    }
  }
}
